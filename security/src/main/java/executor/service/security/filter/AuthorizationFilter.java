package executor.service.security.filter;

import executor.service.security.authorization.TokenBasedAuthorization;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Component
@Order(2)
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final TokenBasedAuthorization tokenBasedAuthorization;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION);
        if (header != null && header.startsWith(AuthorizationType.BEARER.getPrefix()))
            tokenBasedAuthorization.authorizeIfTokenValid(header.substring(AuthorizationType.BEARER.getPrefix().length()));
        filterChain.doFilter(request, response);
    }
}
