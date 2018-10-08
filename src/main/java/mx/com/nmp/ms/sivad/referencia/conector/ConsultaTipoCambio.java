/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.conector;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;

import java.math.BigDecimal;

/**
 * Referencia al servicio web de Consulta del Tipo de Cambio
 *
 * @author ecancino
 */
public interface ConsultaTipoCambio {

    /**
     * Permite obtener el valor del tipo cambiario vigente de una divisa con respecto
     * a la moneda base.
     *
     * @param base Divisa Base
     * @param destino Divisa Destino
     *
     * @return BigDecimal Objeto que contiene la informcion de la respuesta.
     */
    BigDecimal valorPorUnidad(@NotNull final String base, @NotNull final String destino);

}
