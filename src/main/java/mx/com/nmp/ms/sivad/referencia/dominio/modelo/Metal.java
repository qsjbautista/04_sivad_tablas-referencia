/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;

/**
 * Entidad que representa un metal (diferente de oro).
 *
 * @author ngonzalez
 */
public class Metal {

    /**
     * Identificador del registro.
     */
    private Long id;

    /**
     * El tipo del metal (Ejemplo: Plata).
     */
    private String metal;

    /**
     * Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     */
    private String calidad;

    /**
     * Precio en pesos del metal.
     */
    private BigDecimal precio;



    // METODOS

    /**
     * Constructor.
     *
     * @param metal El tipo del metal.
     * @param calidad Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     */
    Metal(String metal, String calidad) {
        this.metal = metal;
        this.calidad = calidad;
    }

    /**
     * Constructor.
     *
     * @param metal El tipo del metal.
     * @param calidad Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     * @param precio Precio en pesos del metal por gramo.
     */
    Metal(String metal, String calidad, BigDecimal precio) {
        this.metal = metal;
        this.calidad = calidad;
        this.precio = precio;
    }

    /**
     * Permite obtener el valor por gramo del metal correspondiente a sus características.
     *
     * @return El precio por gramo correspondiente al metal y calidad solicitado.
     */
    public BigDecimal obtenerValorGramo() {
        return precio;
    }



    // GETTERS

    public Long getId() {
        return id;
    }

    public String getMetal() {
        return metal;
    }

    public String getCalidad() {
        return calidad;
    }

}
