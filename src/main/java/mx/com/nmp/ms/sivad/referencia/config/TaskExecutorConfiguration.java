package mx.com.nmp.ms.sivad.referencia.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Configuración de beans relacionados con ejecución asíncrona/múltiples threads
 *
 * @author osanchez
 */
@Configuration
public class TaskExecutorConfiguration {
    private final Logger log = LoggerFactory.getLogger(TaskExecutorConfiguration.class);

    /**
     * Instancia de {@link Executor} para ser utilizada con batch
     */
    @Bean(name = "taskExecutor")
    public Executor getExecutor() {
        log.info("Creando Task Executor...");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix("sivad-tr-exec-");
        return executor;
    }
}
