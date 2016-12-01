/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoValorComercialDiamante.
 *
 * @author ngonzalez
 */
public final class ListadoValorComercialDiamanteFactory {

    /**
     * Permite crear una entidad de tipo ListadoValorComercialDiamante con base en los argumentos recibidos.
     *
     * @param fechaListado La fecha de origen de la información.
     * @param valoresComerciales Lista de valores comerciales de diamantes.
     * @return La entidad creada.
     */
    public static ListadoValorComercialDiamante create(LocalDate fechaListado, Set<Diamante> valoresComerciales) {
        return new ListadoValorComercialDiamante(fechaListado, valoresComerciales);
    }

    /**
     * Permite crear una entidad de tipo ListadoValorComercialDiamante con base en los argumentos recibidos.
     *
     * @param fechaCarga Fecha en que se realiza la última actualización (fecha de vigencia).
     * @param fechaListado La fecha de origen de la información.
     * @param valoresComerciales Lista de valores comerciales de diamantes.
     * @return
     */
    public static ListadoValorComercialDiamante create(DateTime fechaCarga, LocalDate fechaListado, Set<Diamante> valoresComerciales) {
        return new ListadoValorComercialDiamante(fechaCarga, fechaListado, valoresComerciales);
    }

}
