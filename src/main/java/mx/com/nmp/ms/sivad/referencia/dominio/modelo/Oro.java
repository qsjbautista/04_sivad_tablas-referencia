/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;

/**
 * Entidad que representa el metal de tipo oro.
 *
 * @author ngonzalez
 */
public class Oro {

    /**
     * Identificador del registro.
     */
    private Long id;

    /**
     * El color del oro (amarillo o blanco).
     */
    private String color;

    /**
     * El valor aplicable a la calidad del oro (kilataje), el cual se requiere para obtener el precio por gramo.
     */
    private Integer calidad;

    /**
     * Precio en pesos del oro.
     */
    private BigDecimal precio;



    // METODOS

    /**
     * Constructor.
     *
     * @param color El color del oro.
     * @param calidad La calidad del oro.
     */
    Oro(String color, Integer calidad) {
        this.color = color;
        this.calidad = calidad;
    }

    /**
     * Constructor.
     *
     * @param id Identificador del registro.
     * @param color El color del oro.
     * @param calidad La calidad del oro.
     * @param precio Precio en pesos del oro por gramo.
     */
    Oro(Long id, String color, Integer calidad, BigDecimal precio) {
        this.id = id;
        this.color = color;
        this.calidad = calidad;
        this.precio = precio;
    }

    /**
     * Permite obtener el valor por gramo de oro correspondiente a las características del metal.
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

    public String getColor() {
        return color;
    }

    public Integer getCalidad() {
        return calidad;
    }

}
