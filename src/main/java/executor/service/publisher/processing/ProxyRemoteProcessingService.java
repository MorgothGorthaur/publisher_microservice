package executor.service.publisher.processing;

import executor.service.publisher.exception.validator.UnknownProxyTypeException;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.queue.QueueHandler;
import executor.service.publisher.source.SourceService;
import executor.service.publisher.validation.ProxyValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
public class ProxyRemoteProcessingService<T> implements RemoteProcessingService<ProxySourceDto> {
    private final Map<String,ProxyValidator> validators;
    private final ProxySourceDto defaultSource;
    private final QueueHandler<ProxyConfigHolderDto> queueHandler;

    private final Map<String, SourceService<ProxyConfigHolderDto>> sourceServices;

    public ProxyRemoteProcessingService(List<ProxyValidator> validators, ProxySourceDto defaultSource,
                                        QueueHandler<ProxyConfigHolderDto> queueHandler, List<SourceService<ProxyConfigHolderDto>> services) {
        this.validators = new ConcurrentHashMap<>(validators.stream().collect(Collectors.toMap(ProxyValidator::getType, Function.identity())));
        this.defaultSource = defaultSource;
        this.queueHandler = queueHandler;
        sourceServices = new ConcurrentHashMap<>(services.stream().collect(Collectors.toMap(SourceService::getType, Function.identity())));
    }

    @Override
    public void loadFromDefaultRemoteSource() {
        loadFromCustomRemoteSource(defaultSource);
    }

    @Override
    public void loadFromCustomRemoteSource(ProxySourceDto dto) {
        ProxyValidator validator = Optional.ofNullable(validators.get(dto.getProxyType()))
                .orElseThrow(() -> new UnknownProxyTypeException(dto.getProxyType()));
        List<ProxyConfigHolderDto> dtoList = Optional.ofNullable(sourceServices.get(dto.getProxySourceType()))
                .orElseThrow(RuntimeException::new).loadData(dto);
        Consumer<ProxyConfigHolderDto> asyncValidate = v -> CompletableFuture.runAsync(() -> {
            if (validator.isValid(v)) queueHandler.add(v);
        });
        CompletableFuture.runAsync(() -> dtoList.forEach(asyncValidate));
    }
}