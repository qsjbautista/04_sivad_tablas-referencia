/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Clase abstracta que factoriza los atributos comunes de los valores comerciales de Diamante.
 *
 * @author ngonzalez, ecancino
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
    @Column(name = "tamanio_inferior", precision = 6, scale = 2, nullable = false)
    protected BigDecimal tamanioInferior;

    /**
     * Tamaño superior en quilates que abarca el valor comercial.
     */
    @Column(name = "tamanio_superior", precision = 6, scale = 2, nullable = false)
    protected BigDecimal tamanioSuperior;

    /**
     * Precio en dólares del diamante.
     */
    @Column(name = "precio", precision = 10, scale = 4, nullable = false)
    protected BigDecimal precio;

    /**
     * Tipo de cambio
     */
    @Column(name = "tipo_cambio", precision = 12, scale = 4, nullable = false)
    private BigDecimal tipoCambio;

    /**
     * Precio en pesos con factor rapaport
     */
    @Column(name = "montovbd", precision = 10, scale = 4, nullable = false)
    protected BigDecimal montoVbd;

    /**
     * Precio con el porcentaje aplicado de castigo por rango de pesos
     */
    @Column(name = "montofcastigoxrango", precision = 10, scale = 4, nullable = false)
    protected BigDecimal montofCastigoxRango;



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

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public BigDecimal getMontoVbd() {
        return montoVbd;
    }

    public void setMontoVbd(BigDecimal montoVbd) {
        this.montoVbd = montoVbd;
    }

    public BigDecimal getMontofCastigoxRango() {
        return montofCastigoxRango;
    }

    public void setMontofCastigoxRango(BigDecimal montofCastigoxRango) {
        this.montofCastigoxRango = montofCastigoxRango;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractValorComercialDiamanteJPA)) return false;

        AbstractValorComercialDiamanteJPA that = (AbstractValorComercialDiamanteJPA) o;

        if (!claridad.equals(that.claridad)) return false;
        if (!color.equals(that.color)) return false;
        if (!corte.equals(that.corte)) return false;
        if (!id.equals(that.id)) return false;
        if (!precio.equals(that.precio)) return false;
        if (!tamanioInferior.equals(that.tamanioInferior)) return false;
        if (!tamanioSuperior.equals(that.tamanioSuperior)) return false;
        if (!tipoCambio.equals(that.tipoCambio)) return false;
        if (!montoVbd.equals(that.montoVbd)) return false;
        if (!montofCastigoxRango.equals(that.montofCastigoxRango)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, corte, color, claridad, tamanioInferior, tamanioSuperior, precio,
            tipoCambio, montoVbd, montofCastigoxRango);
    }

}
