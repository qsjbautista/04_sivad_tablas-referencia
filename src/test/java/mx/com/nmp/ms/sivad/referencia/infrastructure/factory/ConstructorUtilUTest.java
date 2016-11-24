package mx.com.nmp.ms.sivad.referencia.infrastructure.factory;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorNumero;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Pruebas de unidad para {@link ConstructorUtil}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public class ConstructorUtilUTest {

    /**
     * Constructor.
     */
    public ConstructorUtilUTest() {
        super();
    }

    /**
     * (non-Javadoc)
     * @see ConstructorUtil#getConstructor(Class, Class...)
     */
    @Test
    public void getConstructorNoExisteTest() {
        Constructor<FactorValorDiamante> constructor = ConstructorUtil.getConstructor(FactorValorDiamante.class);

        assertNull(constructor);
    }

    /**
     * (non-Javadoc)
     * @see ConstructorUtil#getConstructor(Class, Class...)
     */
    @Test
    public void getConstructorTest() {
        Constructor<FactorValorDiamante> constructor = ConstructorUtil.getConstructor(FactorValorDiamante.class,
            FactorValorDiamante.Builder.class);

        assertNotNull(constructor);
    }

    /**
     * (non-Javadoc)
     * @see ConstructorUtil#getConstructor(Class, Class...)
     * @see ConstructorUtil#getInstancia(Constructor, Object...)
     */
    @Test
    public void getInstanciaErrorTest() {
        Constructor<FactorValorDiamante> constructor = ConstructorUtil.getConstructor(FactorValorDiamante.class,
            FactorValorDiamante.Builder.class);

        FactorValorDiamante instancia = ConstructorUtil.getInstancia(constructor);

        assertNull(instancia);
    }

    /**
     * (non-Javadoc)
     * @see ConstructorUtil#getConstructor(Class, Class...)
     * @see ConstructorUtil#getInstancia(Constructor, Object...)
     */
    @Test
    public void getInstanciaTest() {
        Constructor<FactorValorDiamante> constructor = ConstructorUtil.getConstructor(FactorValorDiamante.class,
            FactorValorDiamante.Builder.class);

        FactorValorDiamante instancia = ConstructorUtil.getInstancia(constructor, new FactorValorDiamante.Builder() {

            @Override
            public BigDecimal getMinimo() {
                return BigDecimal.ONE;
            }

            @Override
            public BigDecimal getMedio() {
                return BigDecimal.ONE;
            }

            @Override
            public BigDecimal getMaximo() {
                return BigDecimal.ONE;
            }
        });

        assertNotNull(instancia);
    }

    /**
     * (non-Javadoc)
     * @see ConstructorUtil#getConstructor(Class, Class...)
     * @see ConstructorUtil#getInstancia(Constructor, Object...)
     */
    @Test
    public void getConstructorPropioTest() {
        Constructor<ConstructorUtil> constructor = ConstructorUtil.getConstructor(ConstructorUtil.class);
        ConstructorUtil instancia = ConstructorUtil.getInstancia(constructor);

        assertNotNull(constructor);
        assertNotNull(instancia);
    }

    /**
     * (non-Javadoc)
     * @see ConstructorUtil#getConstructor(Class, Class...)
     * @see ConstructorUtil#getInstancia(Constructor, Object...)
     */
    @Test
    public void getConstructorValidadorNumeroTest() {
        Constructor<ValidadorNumero> constructor = ConstructorUtil.getConstructor(ValidadorNumero.class);
        ValidadorNumero instancia = ConstructorUtil.getInstancia(constructor);

        assertNotNull(constructor);
        assertNotNull(instancia);
    }
}
