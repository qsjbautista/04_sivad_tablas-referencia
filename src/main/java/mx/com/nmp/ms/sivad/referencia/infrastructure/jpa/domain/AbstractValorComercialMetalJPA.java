/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;

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
    @Column(name = "calidad", nullable = false)
    protected String calidad;

    /**
     * Precio en pesos del metal.
     */
    @Column(name = "precio", nullable = false)
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

}
