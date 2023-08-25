package executor.service.publisher.validation;

import executor.service.publisher.model.ProxyConfigHolder;
import executor.service.publisher.model.ProxyCredentials;
import executor.service.publisher.model.ProxyNetworkConfig;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HttpProxyValidatorTest {

    private ProxyValidator validator;

    @BeforeEach
    void setUp() {
        OkHttpClient client = new OkHttpClient();
        validator = new HttpProxyValidator(client);
    }

    @Test
    void testIsValid_shouldReturnTrue() {
        ProxyConfigHolder dto = new ProxyConfigHolder(new ProxyNetworkConfig("20.210.113.32", 8080), new ProxyCredentials());
        boolean valid = validator.isValid(dto);
        assertThat(valid).isTrue();
    }

    @Test
    void testIsValid_shouldReturnFalse() {
        ProxyConfigHolder dto = new ProxyConfigHolder(new ProxyNetworkConfig("20.201.114.32", 3030), new ProxyCredentials());
        boolean valid = validator.isValid(dto);
        assertThat(valid).isFalse();
    }

}