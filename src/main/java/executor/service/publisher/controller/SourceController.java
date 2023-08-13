package executor.service.publisher.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public interface SourceController<T> {
    @PostMapping
    void add(@RequestBody T dto);

    @PostMapping( "/all")
    void addAll(@RequestBody List<T> dtoList);

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/count/{size}")
    List<T> removeByCount(@PathVariable Integer size);

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping
    Optional<T> poll();

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping( "/all")
    List<T> removeAll();
}