/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase abstracta que factoriza los atributos comunes de los factores alhaja.
 *
 * @author mmarquez
 */
@MappedSuperclass
public abstract class AbstractFactorAlhajaJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "metal", nullable = false)
    private String metal;

    @Column(name = "calidad", nullable = true)
    private String calidad;

    @Column(name = "rango", nullable = false)
    private String rango;

    @Column(name = "factor", nullable = false)
    private BigDecimal factor;

    @Column(name = "factor_comercial", nullable = false)
    private BigDecimal factorComercial;

    @Column(name = "limite_inferior", nullable = false)
    private BigDecimal limiteInferior;

    @Column(name = "limite_superior", nullable = false)
    private BigDecimal limiteSuperior;

    @Column(name = "ultima_actualizacion", nullable = false)
    protected Date ultimaActualizacion;

    @ManyToOne
    @JoinColumn(name = "listado")
    private ListadoFactorAlhajaJPA listado;

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

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
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

    public ListadoFactorAlhajaJPA getListado() {
        return listado;
    }

    public void setListado(ListadoFactorAlhajaJPA listado) {
        this.listado = listado;
    }
}
