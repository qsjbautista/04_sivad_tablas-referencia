package mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo;

import java.math.BigDecimal;

/**
 * Value Object con la información del Factor Diamante.
 *
 * Created @jbautista
 */
public class FactorAlhajaVO {

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
     * Constructor.
     *
     * @param metal Metal de la alhaja.
     * @param calidad Calidad del metal de la alhaja.
     * @param rango Rango de la alhaja.
     */
    public FactorAlhajaVO(String metal, String calidad, String rango) {
        this.calidad = calidad;
        this.metal = metal;
        this.rango = rango;
    }

    /**
     * Constructor.
     *
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     */
    public FactorAlhajaVO(String metal, String rango) {
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

}
