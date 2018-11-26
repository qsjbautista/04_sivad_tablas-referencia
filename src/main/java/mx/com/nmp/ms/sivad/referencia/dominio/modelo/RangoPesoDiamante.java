/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;

/**
 * Entidad que representa el peso de un diamante.
 *
 * @author ecancino
 */
public class RangoPesoDiamante {

    /**
     * Tamaño inferior en quilates que abarca el valor comercial.
     */
    private BigDecimal tamanioInferior;

    /**
     * Tamaño superior en quilates que abarca el valor comercial.
     */
    private BigDecimal tamanioSuperior;



    // METODOS

    /**
     * Constructor.
     *
     * @param tamanioInferior Tamanio inferior en quilates que abarca el valor comercial.
     * @param tamanioSuperior Tamanio superior en quilates que abarca el valor comercial.
     */
    RangoPesoDiamante(BigDecimal tamanioInferior, BigDecimal tamanioSuperior) {
        this.tamanioInferior = tamanioInferior;
        this.tamanioSuperior = tamanioSuperior;
    }



    // GETTERS

    public BigDecimal getTamanioInferior() {
        return tamanioInferior;
    }

    public BigDecimal getTamanioSuperior() {
        return tamanioSuperior;
    }

    @Override
    public String toString() {
        return "RangoPesoDiamante{" +
            "tamanioInferior=" + tamanioInferior +
            ", tamanioSuperior=" + tamanioSuperior + +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangoPesoDiamante)) return false;

        RangoPesoDiamante diamante = (RangoPesoDiamante) o;

        if (!tamanioInferior.equals(diamante.tamanioInferior)) return false;
        if (!tamanioSuperior.equals(diamante.tamanioSuperior)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tamanioInferior.hashCode();
        result = 31 * result + tamanioSuperior.hashCode();
        return result;
    }

}
