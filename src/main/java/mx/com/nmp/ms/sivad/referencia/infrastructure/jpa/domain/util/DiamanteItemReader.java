package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import java.util.Queue;

/**
 * Created by Usuario on 15/12/2016.
 */
public class DiamanteItemReader implements ItemReader<PrecioCorteDetalleBatch> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiamanteItemReader.class);

    private Queue<PrecioCorteDetalleBatch> listaPrecioCorte;

    public DiamanteItemReader(Queue<PrecioCorteDetalleBatch> listaPrecioCorte) {
        LOGGER.info(">> DiamanteItemReader.size({})", listaPrecioCorte.size());
        this.listaPrecioCorte = listaPrecioCorte;
    }

    @Override
    public PrecioCorteDetalleBatch read() throws Exception {
        LOGGER.info(">> read()");

        PrecioCorteDetalleBatch precioCorte = null;
        if (!listaPrecioCorte.isEmpty()) {
            precioCorte = listaPrecioCorte.remove();
        }

        LOGGER.debug("<< read(): {}", precioCorte);
        return precioCorte;
    }
}
