/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad que representa el factor de la alhaja.
 *
 * @author mmarquez
 */
public class FactorAlhaja {

    /**
     * Identificador del factor de la alhaja.
     */
    private Long id;

    /**
     * Calidad del metal de la alhaja.
     */
    private String calidad;

    /**
     * Factor de la alhaja
     */
    private BigDecimal factor;

    /**
     * Metal de la alhaja.
     */
    private String metal;

    /**
     * Rango de la alhaja.
     */
    private String rango;

    /**
     * Factor de la alhaja
     */
    private BigDecimal factorComercial;

    /**
     * Limite inferior
     */
    private BigDecimal limiteInferior;

    /**
     * Limite superior
     */
    private BigDecimal limiteSuperior;

    /**
     * Fecha de la ultima actualizacion
     */
    protected Date ultimaActualizacion;

    /**
     * Constructor.
     *
     * @param id Identificador del factor Alhaja.
     * @param calidad Calidad del metal de la alhaja.
     * @param factor Factor de la alhaja.
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     * @param factorComercial Factor comercial.
     * @param limiteInferior Limite inferior.
     * @param limiteSuperior Limite superior.
     * @param ultimaActualizacion fecha de la ultima actualizacion.
     */
    public FactorAlhaja(Long id, String calidad, BigDecimal factor, String metal, String rango, BigDecimal factorComercial,
                        BigDecimal limiteInferior, BigDecimal limiteSuperior, Date ultimaActualizacion) {
        this.id = id;
        this.calidad = calidad;
        this.factor = factor;
        this.metal = metal;
        this.rango = rango;
        this.factorComercial = factorComercial;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    /**
     * Constructor.
     *
     * @param calidad Calidad del metal de la alhaja.
     * @param factor Factor de la alhaja.
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     */
    public FactorAlhaja(String calidad, BigDecimal factor, String metal, String rango) {
        this.calidad = calidad;
        this.factor = factor;
        this.metal = metal;
        this.rango = rango;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public BigDecimal getFactorComercial() {
        return factorComercial;
    }

    public void setFactorComercial(BigDecimal factorComercial) {
        this.factorComercial = factorComercial;
    }

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(BigDecimal limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public BigDecimal getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(BigDecimal limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
}
