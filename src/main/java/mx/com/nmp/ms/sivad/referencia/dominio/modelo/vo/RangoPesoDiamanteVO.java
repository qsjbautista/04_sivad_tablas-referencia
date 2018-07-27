/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo;

import java.math.BigDecimal;

/**
 * Value Object con la información del peso del diamante.
 *
 * @author ecancino
 */
public class RangoPesoDiamanteVO {

    /**
     * El valor en quilates del diamante.
     */
    private BigDecimal quilates;



    // METODOS

    /**
     * Constructor.
     *
     * @param quilates El valor en quilates del diamante.
     */
    public RangoPesoDiamanteVO(BigDecimal quilates) {
        this.quilates = quilates;
    }



    // GETTERS

    public BigDecimal getQuilates() {
        return quilates;
    }

    @Override
    public String toString() {
        return "RangoPesoDiamanteVO{" +
            "quilates=" + quilates +
            '}';
    }

}
