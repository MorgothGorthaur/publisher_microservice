package executor.service.publisher.controller.proxy;

import executor.service.publisher.controller.SourceController;
import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.processing.proxy.ProxyProcessingService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Component
@RequestMapping("/publisher/proxy")
public class ProxySourceController implements SourceController<ProxyConfigHolder> {

    private final ProxyProcessingService service;

    public ProxySourceController(ProxyProcessingService service) {
        this.service = service;
    }

    @Override
    public void add(ProxyConfigHolder proxy) {
        service.add(proxy);
    }

    @Override
    public void addAll(List<ProxyConfigHolder> proxies) {
        service.addAll(proxies);
    }

    @Override
    public List<ProxyConfigHolder> removeByCount(Integer size) {
        return service.removeByCount(size);
    }

    @Override
    public Optional<ProxyConfigHolder> poll() {
        return service.poll();
    }

    @Override
    public List<ProxyConfigHolder> removeAll() {
        return service.removeAll();
    }
}