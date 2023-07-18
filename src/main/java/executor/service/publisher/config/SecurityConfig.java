package executor.service.publisher.config;

import executor.service.publisher.config.filter.AuthorizationFilter;
import executor.service.publisher.config.filter.ExceptionHandlingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final AuthorizationFilter authorizationFilter;
    private final ExceptionHandlingFilter exceptionHandlingFilter;
    @Value("${token.authorization.key}")
    private String key;

    public SecurityConfig(AuthorizationFilter authorizationFilter, ExceptionHandlingFilter exceptionHandlingFilter) {
        this.authorizationFilter = authorizationFilter;
        this.exceptionHandlingFilter = exceptionHandlingFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(authorizationFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlingFilter, AuthorizationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        UserDetails user = User.builder()
                .username(key)
                .password("")
                .roles()
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
