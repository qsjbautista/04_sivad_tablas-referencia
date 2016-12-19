package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.PrecioCorteDetalle;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public ItemProcessor<Map<String, PrecioCorteDetalle>, Diamante> valorComercialDiamanteProcessor() {
        return new ValorComercialDiamanteProcessor();
    }

    @Bean
    public ItemWriter<Diamante> diamanteItemWriter() {
        return new DiamanteItemWriter();
    }

}
