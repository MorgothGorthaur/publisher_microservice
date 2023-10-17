package executor.service.logger.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class LoggerConfiguration {
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("QUEUE_LOGGER");
    }
}
