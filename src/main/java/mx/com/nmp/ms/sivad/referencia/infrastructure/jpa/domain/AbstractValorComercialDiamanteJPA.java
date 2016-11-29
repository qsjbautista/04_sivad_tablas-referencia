/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Clase abstracta que factoriza los atributos comunes de los valores comerciales de Diamante.
 *
 * @author ngonzalez
 */
@MappedSuperclass
public abstract class AbstractValorComercialDiamanteJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    /**
     * El tipo de corte del diamante con base en el catálogo de cortes.
     */
    @Column(name = "corte", nullable = false)
    protected String corte;

    /**
     * El tipo de color del diamante con base en la clasificación GIA.
     */
    @Column(name = "color", nullable = false)
    protected String color;

    /**
     * El tipo de claridad del diamante con base en la clasificación GIA.
     */
    @Column(name = "claridad", nullable = false)
    protected String claridad;

    /**
     * Tamaño inferior en quilates que abarca el valor comercial.
     */
    @Column(name = "tamanio_inferior", nullable = false)
    protected BigDecimal tamanioInferior;

    /**
     * Tamaño superior en quilates que abarca el valor comercial.
     */
    @Column(name = "tamanio_superior", nullable = false)
    protected BigDecimal tamanioSuperior;

    /**
     * Precio en dólares del diamante.
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

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClaridad() {
        return claridad;
    }

    public void setClaridad(String claridad) {
        this.claridad = claridad;
    }

    public BigDecimal getTamanioInferior() {
        return tamanioInferior;
    }

    public void setTamanioInferior(BigDecimal tamanioInferior) {
        this.tamanioInferior = tamanioInferior;
    }

    public BigDecimal getTamanioSuperior() {
        return tamanioSuperior;
    }

    public void setTamanioSuperior(BigDecimal tamanioSuperior) {
        this.tamanioSuperior = tamanioSuperior;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}
