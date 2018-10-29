/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Entidad que representa el factor de la alhaja.
 *
 * @author mmarquez
 */
public class FactorAlhaja {

    /**
     * Metal de la alhaja.
     */
    private String metal;

    /**
     * Calidad del metal de la alhaja.
     */
    private String calidad;

    /**
     * Rango de la alhaja.
     */
    private String rango;

    /**
     * Factor de la alhaja
     */
    private BigDecimal factor;

    /**
     * Desplazamiento Comercial limite inferior
     */
    private BigDecimal desplazamiento_limite_inferior;
    /**
     * Desplazamiento Comercial limite superior
     */
    private BigDecimal desplazamiento_limite_superior;
    /**
     * Desplazamiento Comercial incremento
     */
    private Integer desplazamiento_incremento;

    /**
     * Incremento Limite inferior
     */
    private BigDecimal limiteInferior;

    /**
     * Incremento Limite superior
     */
    private BigDecimal limiteSuperior;
    /**
     * Pasos de la propiedad Incremento 
     */
    private Integer incremento;

    /**
     * Fecha de la ultima actualizacion
     */
    protected DateTime ultimaActualizacion;

    /**
     * Constructor.
     *
     * @param calidad Calidad del metal de la alhaja.
     * @param factor Factor de la alhaja.
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     * @param desplazamiento_limite_inferior factor de desplazamiento limite inferior
     * @param desplazamiento_limite_superior factor de desplazamiento limite superior
     * @param desplazamiento_incremento incremento de factor de incremento
     * @param limiteInferior Incremento Limite inferior.
     * @param limiteSuperior Incremento Limite superior.
     * @param incremento Incremento del factor de Incremento
     * @param ultimaActualizacion fecha de la ultima actualizacion.
     */
    public FactorAlhaja(String metal, String calidad, String rango, BigDecimal factor, BigDecimal desplazamiento_limite_inferior,
                        BigDecimal desplazamiento_limite_superior,Integer desplazamiento_incremento,BigDecimal limiteInferior, 
                        BigDecimal limiteSuperior,Integer incremento, DateTime ultimaActualizacion) {
        this.calidad = calidad;
        this.factor = factor;
        this.metal = metal;
        this.rango = rango;
        this.desplazamiento_limite_inferior = desplazamiento_limite_inferior;
        this.desplazamiento_limite_superior = desplazamiento_limite_superior;
        this.desplazamiento_incremento = desplazamiento_incremento;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.incremento = incremento;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    /**
     * Constructor.
     *
     * @param metal Metal de la alhaja.
     * @param calidad Calidad del metal de la alhaja.
     * @param rango Rango de la alhaja.
     */
    public FactorAlhaja(String metal, String calidad, String rango) {
        this.calidad = calidad;
        this.metal = metal;
        this.rango = rango;
    }

    public String getMetal() {
        return metal;
    }

    public String getCalidad() {
        return calidad;
    }

    public String getRango() {
        return rango;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public BigDecimal getLimiteSuperior() {
        return limiteSuperior;
    }

    public DateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    
    public BigDecimal getDesplazamiento_limite_inferior() {
        return desplazamiento_limite_inferior;
    }

    public BigDecimal getDesplazamiento_limite_superior() {
        return desplazamiento_limite_superior;
    }

    public Integer getDesplazamiento_incremento() {
        return desplazamiento_incremento;
    }

    public Integer getIncremento() {
        return incremento;
    }
}
