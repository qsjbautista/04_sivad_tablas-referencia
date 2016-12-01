/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo;

/**
 * Value Object con la información del oro.
 *
 * @author ngonzalez
 */
public class OroVO {

    /**
     * El color del oro (amarillo o blanco).
     */
    private String color;

    /**
     * El valor aplicable a la calidad del oro (kilataje), el cual se requiere para obtener el precio por gramo.
     */
    private Integer calidad;



    // METODOS

    /**
     * Constructor.
     *
     * @param color El color del oro.
     * @param calidad La calidad del oro.
     */
    public OroVO(String color, Integer calidad) {
        this.color = color;
        this.calidad = calidad;
    }



    // GETTERS

    public String getColor() {
        return color;
    }

    public Integer getCalidad() {
        return calidad;
    }

}