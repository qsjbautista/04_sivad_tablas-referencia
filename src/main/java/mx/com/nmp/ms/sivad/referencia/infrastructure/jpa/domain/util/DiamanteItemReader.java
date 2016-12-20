package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.PrecioCorte;

import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.PrecioCorteDetalle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Usuario on 15/12/2016.
 */
public class DiamanteItemReader implements ItemReader<Map<String, PrecioCorteDetalle>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiamanteItemReader.class);

    private List<PrecioCorte> listaPrecioCorte;

    private int nextPrecioIndex;
    private int nextPrecioDetalleIndex;

    public DiamanteItemReader(List<PrecioCorte> listaPrecioCorte) {
        LOGGER.info(">> DiamanteItemReader.size({})", listaPrecioCorte.size());
        this.listaPrecioCorte = listaPrecioCorte;
    }

    @Override
    public Map<String, PrecioCorteDetalle> read() throws Exception {
        LOGGER.info(">> read()");

        Map<String, PrecioCorteDetalle> mapListaPrecioCorteDetalle = null;


        if (nextPrecioIndex < listaPrecioCorte.size()) {
            mapListaPrecioCorteDetalle = new HashMap<>();
            PrecioCorte precioCorte = listaPrecioCorte.get(nextPrecioIndex);
            if (nextPrecioDetalleIndex < precioCorte.getPrecioCorte().size()) {
                mapListaPrecioCorteDetalle.put(precioCorte.getCorte(), precioCorte.getPrecioCorte().get(nextPrecioDetalleIndex));
                nextPrecioDetalleIndex++;
            } else {
                nextPrecioDetalleIndex = 0;
                nextPrecioIndex++;
            }
            LOGGER.info("Codigo de salida: {}", precioCorte.getCorte());
        }

        LOGGER.debug("<< read(): {}", mapListaPrecioCorteDetalle);
        return mapListaPrecioCorteDetalle;
    }
}
