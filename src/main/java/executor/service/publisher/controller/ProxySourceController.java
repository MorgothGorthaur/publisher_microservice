package executor.service.publisher.controller;

import executor.service.publisher.model.ProxyConfigHolderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publisher")
public interface ProxySourceController {
    @PostMapping("/proxy")
    ResponseEntity<ProxyConfigHolderDto> add(@RequestBody ProxyConfigHolderDto proxyConfigHolderDto);

    @PostMapping("/proxies")
    ResponseEntity<List<ProxyConfigHolderDto>> addAll(@RequestBody List<ProxyConfigHolderDto> proxyConfigHolderDtos);

    @DeleteMapping("/proxy")
    ResponseEntity<Optional<ProxyConfigHolderDto>> poll();

    @DeleteMapping("/proxies")
    ResponseEntity<List<ProxyConfigHolderDto>> removeAll();
}
