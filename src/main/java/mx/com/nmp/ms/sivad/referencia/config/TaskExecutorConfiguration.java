package mx.com.nmp.ms.sivad.referencia.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${thread.pool.size.core}")
    private Integer corePoolSize;

    @Value("${thread.pool.size.max}")
    private Integer maxPoolSize;

    @Value("${thread.pool.size.queue}")
    private Integer queueCapacity;

    /**
     * Instancia de {@link Executor} para ser utilizada con batch
     */
    @Bean(name = "taskExecutor")
    public Executor getExecutor() {
        log.info("Creando Task Executor...");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("sivad-tr-exec-");
        return executor;
    }
}
