package executor.service.publisher.controller;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProxySourceControllerTest {

    @Mock
    private QueueHandler<ProxyConfigHolderDto> mockProxyHandler;

    @InjectMocks
    private ProxySourceControllerImpl proxySourceController;

    @Test
    public void testAddProxyConfigHolderDto() {
        ProxyConfigHolderDto proxyConfigHolderDto = new ProxyConfigHolderDto();
        ResponseEntity<?> responseEntity = proxySourceController.add(proxyConfigHolderDto);

        verify(mockProxyHandler, times(1)).add(proxyConfigHolderDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testAddAllProxyConfigHolderDtos() {
        List<ProxyConfigHolderDto> proxyConfigHolderDtos = Collections.singletonList(new ProxyConfigHolderDto());
        ResponseEntity<?> responseEntity = proxySourceController.addAll(proxyConfigHolderDtos);

        verify(mockProxyHandler, times(1)).addAll(proxyConfigHolderDtos);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPoll() {
        ProxyConfigHolderDto expectedProxy = new ProxyConfigHolderDto();
        when(mockProxyHandler.poll()).thenReturn(Optional.of(expectedProxy));
        ResponseEntity<ProxyConfigHolderDto> responseEntity = proxySourceController.poll();

        verify(mockProxyHandler, times(1)).poll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ProxyConfigHolderDto responseProxy = responseEntity.getBody();
        assertNotNull(responseProxy, "Response body should not be null");
        assertEquals(expectedProxy, responseProxy);
    }

    @Test
    public void testRemoveAll() {
        List<ProxyConfigHolderDto> removedProxies = Collections.singletonList(new ProxyConfigHolderDto());
        when(mockProxyHandler.removeAll()).thenReturn(removedProxies);

        ResponseEntity<?> responseEntity = proxySourceController.removeAll();
        verify(mockProxyHandler, times(1)).removeAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(removedProxies, responseEntity.getBody());
    }
}
