package mx.com.nmp.ms.sivad.referencia.conector;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;

import java.math.BigDecimal;

/**
 * Interface que permite realizar la conversion entre divisas con base en el tipo de cambio vigente.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~roramirez">Roberto Omar Ramirez Torres</a>
 */
public interface Convertidor {

    /**
     * Permite obtener el valor por divisa de acuerdo al tipo de cambio que se requiere
     *
     * @param base Divisa Base
     * @param destino Divisa Destino a convertir
     * @param monto Monto total a convertir
     *
     * @return BigDecimal Objeto que contiene la informción de la respuesta.
     */
    BigDecimal convertir(@NotNull final String base, @NotNull final String destino, @NotNull final BigDecimal monto);

}
