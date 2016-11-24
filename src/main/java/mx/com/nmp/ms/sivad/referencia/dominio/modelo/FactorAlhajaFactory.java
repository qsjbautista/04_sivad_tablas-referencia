/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Fábrica que se encarga de crear objetos de FactorAlhaja.
 *
 * @author mmarquez
 */
public class FactorAlhajaFactory {

    /**
     * Permite crear una entidad de tipo FactorAlhaja con base en los argumentos recibidos.
     *
     * @param calidad Calidad del metal de la alhaja.
     * @param factor Factor de la alhaja.
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     * @return La entidad creada.
     */
    public static FactorAlhaja create(String calidad, BigDecimal factor, String metal, String rango) {
        return new FactorAlhaja(calidad, factor, metal, rango);
    }

    /**
     * Permite crear una entidad de tipo FactorAlhaja con base en los argumentos recibidos.
     *
     * @param id Identificador del factor alhaja.
     * @param calidad Calidad del metal de la alhaja.
     * @param factor Factor de la alhaja.
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     * @param factorComercial Factor comercial.
     * @param limiteInferior Limite inferior.
     * @param limiteSuperior Limite superior.
     * @param ultimaActualizacion fecha de la ultima actualizacion.
     * @return La entidad creada.
     */
    public static FactorAlhaja create(Long id, String calidad, BigDecimal factor, String metal, String rango, BigDecimal factorComercial,
                                      BigDecimal limiteInferior, BigDecimal limiteSuperior, Date ultimaActualizacion) {
        return new FactorAlhaja(id, calidad, factor, metal, rango, factorComercial, limiteInferior, limiteSuperior, ultimaActualizacion);
    }

}
