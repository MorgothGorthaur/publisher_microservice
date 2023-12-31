package executor.service.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@PropertySource("classpath:security.properties")
public class SecurityConfig {
    @Value("${jwt.secret.key}")
    private String secretKey;
}
