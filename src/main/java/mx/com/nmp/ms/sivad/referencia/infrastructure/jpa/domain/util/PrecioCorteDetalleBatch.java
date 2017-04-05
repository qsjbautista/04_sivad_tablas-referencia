package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.PrecioCorteDetalle;
import org.springframework.beans.BeanUtils;

/**
 * Utilizado para el procesamiento de registros de precio
 *
 * @author osanchez
 */
public class PrecioCorteDetalleBatch extends PrecioCorteDetalle {
    private String corte;

    public PrecioCorteDetalleBatch(String corte, PrecioCorteDetalle pcd) {
        this.corte = corte;
        BeanUtils.copyProperties(pcd, this);
    }

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }
}
