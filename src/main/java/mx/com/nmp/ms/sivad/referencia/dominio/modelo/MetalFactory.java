/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;

/**
 * Fábrica que se encarga de crear objetos de tipo Metal.
 *
 * @author ngonzalez
 */
public final class MetalFactory {

    /**
     * Permite crear una entidad de tipo Metal con base en los argumentos recibidos.
     *
     * @param metal El tipo del metal.
     * @param calidad Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     * @return La entidad creada.
     */
    public static Metal create(String metal, String calidad) {
        return new Metal(metal, calidad);
    }

    /**
     * Permite crear una entidad de tipo Metal con base en los argumentos recibidos.
     *
     * @param metal El tipo del metal.
     * @param calidad Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     * @param precio Precio en pesos del metal por gramo.
     * @return La entidad creada.
     */
    public static Metal create(String metal, String calidad, BigDecimal precio) {
        return new Metal(metal, calidad, precio);
    }

    /**
     * Permite crear una entidad de tipo Metal con base en los argumentos recibidos.
     *
     * @param id Identificador del registro.
     * @param metal El tipo del metal.
     * @param calidad Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     * @param precio Precio en pesos del metal por gramo.
     * @return La entidad creada.
     */
    public static Metal create(Long id, String metal, String calidad, BigDecimal precio) {
        return new Metal(id, metal, calidad, precio);
    }

}
