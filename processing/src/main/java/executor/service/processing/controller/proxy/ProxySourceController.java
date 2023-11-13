package executor.service.processing.controller.proxy;


import executor.service.processing.controller.SourceController;
import executor.service.model.ProxyConfigHolder;
import executor.service.processing.service.proxy.ProxyProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Component
@RequestMapping("/publisher/proxy")
@RequiredArgsConstructor
public class ProxySourceController implements SourceController<ProxyConfigHolder> {

    private final ProxyProcessingService service;
    @Override
    public void add(ProxyConfigHolder proxy) {
        service.add(proxy);
    }

    @Override
    public void addAll(List<ProxyConfigHolder> proxies) {
        service.addAll(proxies);
    }
}