/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.util.Date;
import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoValorComercialOro.
 *
 * @author ngonzalez
 */
public final class ListadoValorComercialOroFactory {

    /**
     * Permite crear una entidad de tipo ListadoValorComercialOro con base en los argumentos recibidos.
     *
     * @param valoresComerciales Lista de valores comerciales del oro.
     * @return La entidad creada.
     */
    public static ListadoValorComercialOro create(Set<Oro> valoresComerciales) {
        return new ListadoValorComercialOro(valoresComerciales);
    }

    /**
     * Permite crear una entidad de tipo ListadoValorComercialOro con base en los argumentos recibidos.
     *
     * @param ultimaActualizacion Fecha en que se realiza la última actualización.
     * @param valoresComerciales Lista de valores comerciales del oro.
     * @return La entidad creada.
     */
    public static ListadoValorComercialOro create(Date ultimaActualizacion, Set<Oro> valoresComerciales) {
        return new ListadoValorComercialOro(ultimaActualizacion, valoresComerciales);
    }

}
