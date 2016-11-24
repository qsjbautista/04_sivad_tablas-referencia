/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.util.Date;
import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoRango.
 *
 * @author mmarquez
 */
public class ListadoRangoFactory {

    /**
     * Permite crear una entidad de tipo ListadoRango con base en los argumentos recibidos.
     *
     * @param factorAlhajas Lista de los factores alhaja.
     * @return La entidad creada.
     */
    public static ListadoRango create(Set<FactorAlhaja> factorAlhajas) {
        return new ListadoRango(factorAlhajas);
    }

    /**
     * Permite crear una entidad de tipo ListadoRango con base en los argumentos recibidos.
     *
     * @param id Identificador del registro.
     * @param fechaCarga Fecha en que se realiza la carga.
     * @param fechaListado Fecha en del listado.
     * @param factoresAlhajas Lista de factores alhaja.
     * @return La entidad creada.
     */
    public static ListadoRango create(Long id, Date fechaCarga, Date fechaListado, Set<FactorAlhaja> factoresAlhajas) {
        return new ListadoRango(id, fechaCarga, fechaListado, factoresAlhajas);
    }

}
