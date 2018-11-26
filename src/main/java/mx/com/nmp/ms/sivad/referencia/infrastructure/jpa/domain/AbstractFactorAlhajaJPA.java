/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(name = "factor", precision = 8, scale = 2, nullable = false)
    private BigDecimal factor;
    
    @Column(name = "desplazamiento_limite_inferior", precision = 8, scale = 2, nullable = false)
    private BigDecimal desplazamiento_limiteInferior;
    
    @Column(name = "desplazamiento_limite_superior", precision = 8, scale = 2, nullable = false)
    private BigDecimal desplazamiento_limiteSuperior;
    
    @Column(name = "desplazamiento_incremento", nullable = false)
    private Integer desplazamiento_incremento;

    @Column(name = "limite_inferior", precision = 8, scale = 2, nullable = false)
    private BigDecimal limiteInferior;

    @Column(name = "limite_superior", precision = 8, scale = 2, nullable = false)
    private BigDecimal limiteSuperior;
    
    @Column(name = "incremento", nullable = false)
    private Integer incremento;

    @Column(name = "ultima_actualizacion", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime ultimaActualizacion;

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

    public DateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(DateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public BigDecimal getDesplazamiento_limiteInferior() {
        return desplazamiento_limiteInferior;
    }

    public void setDesplazamiento_limiteInferior(BigDecimal desplazamiento_limiteInferior) {
        this.desplazamiento_limiteInferior = desplazamiento_limiteInferior;
    }

    public BigDecimal getDesplazamiento_limiteSuperior() {
        return desplazamiento_limiteSuperior;
    }

    public void setDesplazamiento_limiteSuperior(BigDecimal desplazamiento_limiteSuperior) {
        this.desplazamiento_limiteSuperior = desplazamiento_limiteSuperior;
    }

    public Integer getDesplazamiento_incremento() {
        return desplazamiento_incremento;
    }

    
    public void setDesplazamiento_incremento(Integer desplazamiento_incremento) {
        this.desplazamiento_incremento = desplazamiento_incremento;
    }

    
    public Integer getIncremento() {
        return incremento;
    }

    public void setIncremento(Integer incremento) {
        this.incremento = incremento;
    }

}
