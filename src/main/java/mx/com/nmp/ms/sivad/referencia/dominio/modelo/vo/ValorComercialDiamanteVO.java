/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo;

import java.math.BigDecimal;

/**
 * Value Object con la información del valor comercial del diamante.
 *
 * @author ngonzalez
 */
public class ValorComercialDiamanteVO {

    /**
     * Valor comercial mínimo.
     */
    private BigDecimal valorMinimo;

    /**
     * Valor comercial medio.
     */
    private BigDecimal valorMedio;

    /**
     * Valor comercial máximo.
     */
    private BigDecimal valorMaximo;



    // METODOS

    /**
     * Constructor.
     *
     * @param valorMinimo Valor comercial mínimo.
     * @param valorMedio Valor comercial medio.
     * @param valorMaximo Valor comercial máximo.
     */
    public ValorComercialDiamanteVO(BigDecimal valorMinimo, BigDecimal valorMedio, BigDecimal valorMaximo) {
        this.valorMinimo = valorMinimo;
        this.valorMedio = valorMedio;
        this.valorMaximo = valorMaximo;
    }



    // GETTERS

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public BigDecimal getValorMedio() {
        return valorMedio;
    }

    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }

}
