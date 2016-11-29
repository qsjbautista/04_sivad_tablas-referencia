/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo;

import java.math.BigDecimal;

/**
 * Value Object con la información del diamante.
 *
 * @author ngonzalez
 */
public class DiamanteVO {

    /**
     * El tipo de corte del diamante con base en el catálogo de cortes.
     */
    private String corte;

    /**
     * El tipo de color del diamante con base en la clasificación GIA.
     */
    private String color;

    /**
     * El tipo de claridad del diamante con base en la clasificación GIA.
     */
    private String claridad;

    /**
     * El valor en quilates del diamante.
     */
    private BigDecimal quilatesCt;



    // METODOS

    /**
     * Constructor.
     *
     * @param corte El tipo de corte del diamante.
     * @param color El tipo de color del diamante.
     * @param claridad El tipo de claridad del diamante.
     * @param quilatesCt El valor en quilates del diamante.
     */
    public DiamanteVO(String corte, String color, String claridad, BigDecimal quilatesCt) {
        this.corte = corte;
        this.color = color;
        this.claridad = claridad;
        this.quilatesCt = quilatesCt;
    }



    // GETTERS

    public String getCorte() {
        return corte;
    }

    public String getColor() {
        return color;
    }

    public String getClaridad() {
        return claridad;
    }

    public BigDecimal getQuilatesCt() {
        return quilatesCt;
    }

}
