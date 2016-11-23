/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;

/**
 * Fábrica que se encarga de crear objetos de tipo Oro.
 *
 * @author ngonzalez
 */
public class OroFactory {

    /**
     * Permite crear una entidad de tipo Oro con base en los argumentos recibidos.
     *
     * @param color El color del oro.
     * @param calidad La calidad del oro.
     * @return La entidad creada.
     */
    public static Oro create(String color, Integer calidad) {
        return new Oro(color, calidad);
    }

    /**
     * Permite crear una entidad de tipo Oro con base en los argumentos recibidos.
     *
     * @param color El color del oro.
     * @param calidad La calidad del oro.
     * @param precio Precio en pesos del oro por gramo.
     * @return La entidad creada.
     */
    public static Oro create(String color, Integer calidad, BigDecimal precio) {
        return new Oro(color, calidad, precio);
    }

    /**
     * Permite crear una entidad de tipo Oro con base en los argumentos recibidos.
     *
     * @param id Identificador del registro.
     * @param color El color del oro.
     * @param calidad La calidad del oro.
     * @param precio Precio en pesos del oro por gramo.
     * @return La entidad creada.
     */
    public static Oro create(Long id, String color, Integer calidad, BigDecimal precio) {
        return new Oro(id, color, calidad, precio);
    }

}
