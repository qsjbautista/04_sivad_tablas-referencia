package mx.com.nmp.ms.sivad.referencia.dominio.factory;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;

import java.math.BigDecimal;

/**
 * Interface que define el contrato para la fabricación de Value Object tipo {@link FactorValorDiamanteFactory}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public interface FactorValorDiamanteFactory {
    /**
     * Crea un Value Object a partir del valor de los argumentos.
     *
     * @param minimo Factor valor Mínimo.
     * @param medio Factor valor Medio.
     * @param maximo Factor valor Máximo.
     *
     * @return El Value Object creado.
     */
    FactorValorDiamante createWith(BigDecimal minimo, BigDecimal medio, BigDecimal maximo);

    /**
     * Crea una Value Object a partir de un objeto constructor.
     *
     * @param builder Referencia al objeto constructor.
     *
     * @return El Value Object creado.
     */
    FactorValorDiamante createFrom(FactorValorDiamante.Builder builder);
}
