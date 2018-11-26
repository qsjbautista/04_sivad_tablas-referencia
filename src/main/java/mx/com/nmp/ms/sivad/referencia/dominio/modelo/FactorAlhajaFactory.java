/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

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
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     * @return La entidad creada.
     */
    public static FactorAlhaja crear(String metal, String calidad, String rango) {
        return new FactorAlhaja(metal, calidad, rango);
    }

    /**
     * Permite crear una entidad de tipo FactorAlhaja con base en los argumentos recibidos.
     *
     * @param metal Metal de la alhaja.
     * @param calidad Calidad del metal de la alhaja.
     * @param rango Rango de la alhaja.
     * @param factor Factor de la alhaja.
     * @param desplazamiento Factor comercial.
     * @param limiteInferior Limite inferior.
     * @param limiteSuperior Limite superior.
     * @param ultimaActualizacion fecha de la ultima actualizacion.
     * @return La entidad creada.
     */
    public static FactorAlhaja crear(String metal, String calidad, String rango, BigDecimal factor, BigDecimal desplazamiento_limiteInferior,
                                      BigDecimal desplazamiento_limiteSuperior,Integer desplazamiento_incremento,BigDecimal limiteInferior, 
                                      BigDecimal limiteSuperior,Integer incremento, DateTime ultimaActualizacion) {
        return new FactorAlhaja(metal, calidad, rango, factor, desplazamiento_limiteInferior,desplazamiento_limiteSuperior,
            desplazamiento_incremento, limiteInferior, limiteSuperior, incremento, ultimaActualizacion);
    }

}
