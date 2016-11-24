/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Value Object que contiene los factores aplicables sobre el valor del Diamante (factor valor Mínimo,
 * factor valor Medio, factor valor Máximo).
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public class FactorValorDiamante {
    /**
     * Factor valor Mínimo.
     */
    private BigDecimal minimo;

    /**
     * Factor valor Medio.
     */
    private BigDecimal medio;

    /**
     * Factor valor Máximo.
     */
    private BigDecimal maximo;

    /**
     * Interface que define el contrato para crear Value Objects {@link FactorValorDiamante}
     */
    public interface Builder {
        /**
         * Recupera el factor valor Mínimo.
         *
         * @return Factor valor Mínimo.
         */
        BigDecimal getMinimo();

        /**
         * Recupera el factor valor Medio.
         *
         * @return Factor valor Medio.
         */
        BigDecimal getMedio();

        /**
         * Recupera el factor valor Máximo.
         *
         * @return Factor valor Máximo.
         */
        BigDecimal getMaximo();
    }

    /**
     * Constructor. Privado para que solo la fabrica tenga acceso a crear objetos.
     *
     * @param builder Referencia al objeto que contiene los datos necesarios para construir la entidad.
     */
    private FactorValorDiamante(Builder builder) {
        super();

        minimo = builder.getMinimo();
        medio = builder.getMedio();
        maximo = builder.getMaximo();
    }

    /**
     * Regresa el factor valor Mínimo
     *
     * @return Valor Mínimo
     */
    public BigDecimal getMinimo() {
        return minimo;
    }

    /**
     * Regresa el factor valor Medio
     *
     * @return Valor Medio
     */
    public BigDecimal getMedio() {
        return medio;
    }

    /**
     * Regresa el factor valor Máximo
     *
     * @return Valor Máximo
     */
    public BigDecimal getMaximo() {
        return maximo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FactorValorDiamante that = (FactorValorDiamante) o;

        return Objects.equals(minimo, that.minimo) &&
            Objects.equals(medio, that.medio) &&
            Objects.equals(maximo, that.maximo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(minimo, medio, maximo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("{minimo=%s, medio=%s, maximo=%s}", minimo, medio, maximo);
    }
}
