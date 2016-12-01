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
     * Factor de la alhaja
     */
    private BigDecimal desplazamiento;

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
    protected DateTime ultimaActualizacion;

    /**
     * Constructor.
     *
     * @param calidad Calidad del metal de la alhaja.
     * @param factor Factor de la alhaja.
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     * @param desplazamiento Factor de desplazamiento.
     * @param limiteInferior Limite inferior.
     * @param limiteSuperior Limite superior.
     * @param ultimaActualizacion fecha de la ultima actualizacion.
     */
    public FactorAlhaja(String metal, String calidad, String rango, BigDecimal factor, BigDecimal desplazamiento,
                        BigDecimal limiteInferior, BigDecimal limiteSuperior, DateTime ultimaActualizacion) {
        this.calidad = calidad;
        this.factor = factor;
        this.metal = metal;
        this.rango = rango;
        this.desplazamiento = desplazamiento;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
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

    public BigDecimal getDesplazamiento() {
        return desplazamiento;
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
}
