/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo;

/**
 * Value Object con la información del metal (diferente de oro).
 *
 * @author ngonzalez
 */
public class MetalVO {

    /**
     * El tipo del metal (Ejemplo: Plata).
     */
    private String metal;

    /**
     * Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     */
    private String calidad;



    // METODOS

    /**
     * Constructor.
     *
     * @param metal El tipo del metal.
     * @param calidad Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     */
    public MetalVO(String metal, String calidad) {
        this.metal = metal;
        this.calidad = calidad;
    }



    // GETTERS

    public String getMetal() {
        return metal;
    }

    public String getCalidad() {
        return calidad;
    }

}
