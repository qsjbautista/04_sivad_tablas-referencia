package mx.com.nmp.ms.sivad.referencia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuración de base de datos
 *
 * @author osanchez
 */
@Configuration
@EnableJpaRepositories("mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository")
public class DatabaseConfiguration {
}
