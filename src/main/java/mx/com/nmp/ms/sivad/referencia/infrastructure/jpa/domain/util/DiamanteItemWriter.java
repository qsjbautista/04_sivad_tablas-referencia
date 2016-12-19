package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;

/**
* Created by Usuario on 15/12/2016.
*/
public class DiamanteItemWriter implements ItemWriter<Diamante> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiamanteItemWriter.class);

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    @Inject
    private ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    @Override
    public void write(List<? extends Diamante> list) throws Exception {
        //LOGGER.info(">> write({})", list);

        ListadoValorComercialDiamante listadoValorComercialDiamante = ListadoValorComercialDiamanteFactory.create(LocalDate.now(),
            new HashSet(list));
        valorComercialDiamanteRepository.actualizarListadoSinHist(listadoValorComercialDiamante);
        LOGGER.debug("--------------------------------------Guardando en Base de Datos-----------------");
    }
}
