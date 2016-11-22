package mx.com.nmp.ms.sivad.referencia.infrastructure.factory;

import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorNumero;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;

import static mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ConstructorUtil.getConstructor;
import static mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ConstructorUtil.getInstancia;

/**
 * Fabrica para crear Value Object tipo {@link FactorValorDiamanteFactory}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
@Component
public class FactorValorDiamanteFactoryImpl implements FactorValorDiamanteFactory {
    /**
     * Referencias al constructor del Value Object.
     */
    private final Constructor<FactorValorDiamante> constructor;

    /**
     * Constructor.
     */
    public FactorValorDiamanteFactoryImpl() {
        super();

        constructor = getConstructor(FactorValorDiamante.class, FactorValorDiamante.Builder.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FactorValorDiamante createWith(final BigDecimal minimo, final BigDecimal medio, final BigDecimal maximo) {
        final FactorValorDiamante.Builder builder = getBuilder(minimo, medio, maximo);

        return createFrom(builder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FactorValorDiamante createFrom(FactorValorDiamante.Builder builder) {
        validarBuilder(builder);

        return getInstancia(constructor, builder);
    }

    /**
     * Crea un objeto constructor a partir del valor de los argumentos.
     *
     * @param minimo Factor valor Mínimo.
     * @param medio Factor valor Medio.
     * @param maximo Factor valor Máximo.
     *
     * @return Objeto constructor creado.
     */
    private static FactorValorDiamante.Builder getBuilder(final BigDecimal minimo, final BigDecimal medio,
            final BigDecimal maximo) {
        return new FactorValorDiamante.Builder(){

            @Override
            public BigDecimal getMinimo() {
                return minimo;
            }

            @Override
            public BigDecimal getMedio() {
                return medio;
            }

            @Override
            public BigDecimal getMaximo() {
                return maximo;
            }
        };
    }

    /**
     * Valida que los datos con los que va a ser creado el Value Object sean correctos.
     *
     * @param builder Objeto constructor del Value Object.
     */
    private static void validarBuilder(final FactorValorDiamante.Builder builder) {
        ValidadorNumero.validar(builder.getMinimo());
        ValidadorNumero.validar(builder.getMedio());
        ValidadorNumero.validar(builder.getMaximo());
    }
}
