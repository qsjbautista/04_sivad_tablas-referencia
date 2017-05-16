package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import java.util.*;

/**
 *
 */
public class DiamanteItemWriter implements ItemWriter<Diamante> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiamanteItemWriter.class);

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    private final ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    public DiamanteItemWriter(ValorComercialDiamanteRepository valorComercialDiamanteRepository) {
        super();

        this.valorComercialDiamanteRepository = valorComercialDiamanteRepository;
    }

    @Override
    public void write(List<? extends Diamante> list) throws Exception {
        if (list == null) {
            return;
        }

        LOGGER.info(">> write({} registros)", list.size());

        valorComercialDiamanteRepository.procesarLista(list);
    }
}
