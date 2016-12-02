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

    public String getMetal() {
        return metal;
    }

    public String getCalidad() {
        return calidad;
    }

    @Override
    public String toString() {
        return "Metal{" +
            "metal='" + metal + '\'' +
            ", calidad='" + calidad + '\'' +
            ", precio=" + precio +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Metal)) return false;

        Metal metal1 = (Metal) o;

        if (calidad != null ? !calidad.equals(metal1.calidad) : metal1.calidad != null) return false;
        if (!metal.equals(metal1.metal)) return false;
        if (!precio.equals(metal1.precio)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = metal.hashCode();
        result = 31 * result + (calidad != null ? calidad.hashCode() : 0);
        result = 31 * result + precio.hashCode();
        return result;
    }

}
