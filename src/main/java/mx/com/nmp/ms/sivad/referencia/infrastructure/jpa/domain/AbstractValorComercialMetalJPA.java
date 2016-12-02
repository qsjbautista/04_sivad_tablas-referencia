/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Clase abstracta que factoriza los atributos comunes de los valores comerciales de Metal.
 *
 * @author ngonzalez
 */
@MappedSuperclass
public abstract class AbstractValorComercialMetalJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    /**
     * El tipo del metal (Ejemplo: Plata).
     */
    @Column(name = "metal", nullable = false)
    protected String metal;

    /**
     * Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     */
    @Column(name = "calidad", nullable = true)
    protected String calidad;

    /**
     * Precio en pesos del metal.
     */
    @Column(name = "precio", precision = 10, scale = 3, nullable = false)
    protected BigDecimal precio;



    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractValorComercialMetalJPA)) return false;

        AbstractValorComercialMetalJPA that = (AbstractValorComercialMetalJPA) o;

        if (calidad != null ? !calidad.equals(that.calidad) : that.calidad != null) return false;
        if (!id.equals(that.id)) return false;
        if (!metal.equals(that.metal)) return false;
        if (!precio.equals(that.precio)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, metal, calidad, precio);
    }

}
