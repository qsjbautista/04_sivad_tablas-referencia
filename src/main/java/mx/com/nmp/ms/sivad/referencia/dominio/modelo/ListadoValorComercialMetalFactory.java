/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.util.Date;
import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoValorComercialMetal.
 *
 * @author ngonzalez
 */
public class ListadoValorComercialMetalFactory {

    /**
     * Permite crear una entidad de tipo ListadoValorComercialMetal con base en los argumentos recibidos.
     *
     * @param valoresComerciales Lista de valores comerciales de metales.
     * @return La entidad creada.
     */
    public static ListadoValorComercialMetal create(Set<Metal> valoresComerciales) {
        return new ListadoValorComercialMetal(valoresComerciales);
    }

    /**
     * Permite crear una entidad de tipo ListadoValorComercialMetal con base en los argumentos recibidos.
     *
     * @param ultimaActualizacion Fecha en que se realiza la última actualización.
     * @param valoresComerciales Lista de valores comerciales de metales.
     * @return La entidad creada.
     */
    public static ListadoValorComercialMetal create(Date ultimaActualizacion, Set<Metal> valoresComerciales) {
        return new ListadoValorComercialMetal(ultimaActualizacion, valoresComerciales);
    }

}
