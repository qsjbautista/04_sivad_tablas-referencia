package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public ItemProcessor<PrecioCorteDetalleBatch, Diamante> valorComercialDiamanteProcessor() {
        return new ValorComercialDiamanteProcessor();
    }

    @Bean
    public ItemWriter<Diamante> diamanteItemWriter() {
        return new DiamanteItemWriter();
    }

}
