/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Clase abstracta que factoriza los atributos comunes de los valores comerciales de Oro.
 *
 * @author ngonzalez
 */
@MappedSuperclass
public abstract class AbstractValorComercialOroJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    /**
     * El color del oro (amarillo o blanco).
     */
    @Column(name = "color", nullable = false)
    protected String color;

    /**
     * El valor aplicable a la calidad del oro (kilataje), el cual se requiere para obtener el precio por gramo.
     */
    @Column(name = "kilataje", nullable = false)
    protected Integer calidad;

    /**
     * Precio en pesos del oro.
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCalidad() {
        return calidad;
    }

    public void setCalidad(Integer calidad) {
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
        if (!(o instanceof AbstractValorComercialOroJPA)) return false;

        AbstractValorComercialOroJPA that = (AbstractValorComercialOroJPA) o;

        if (!calidad.equals(that.calidad)) return false;
        if (!color.equals(that.color)) return false;
        if (!id.equals(that.id)) return false;
        if (!precio.equals(that.precio)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, calidad, precio);
    }

}
