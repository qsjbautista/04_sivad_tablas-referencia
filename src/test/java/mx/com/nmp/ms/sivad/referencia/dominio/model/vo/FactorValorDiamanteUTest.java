package mx.com.nmp.ms.sivad.referencia.dominio.model.vo;

import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.infrastructure.factory.FactorValorDiamanteFactoryImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Pruebas de unidad para el Value Object {@link FactorValorDiamante}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public class FactorValorDiamanteUTest {
    /**
     * Referencia a la clase que será probada.
     */
    private FactorValorDiamanteFactory fabrica;

    /**
     * Constructor.
     */
    public FactorValorDiamanteUTest() {
        super();

        fabrica = new FactorValorDiamanteFactoryImpl();
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithParametrosNulosTest() {
        fabrica.crearCon(null, null, null);
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithMinimoNuloTest() {
        fabrica.crearCon(null, BigDecimal.ONE, BigDecimal.TEN);
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithMedioNuloTest() {
        fabrica.crearCon(BigDecimal.ONE, null, BigDecimal.TEN);
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithMaximoNuloTest() {
        fabrica.crearCon(BigDecimal.ONE, BigDecimal.TEN, null);
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithParametrosNegativosTest() {
        fabrica.crearCon(BigDecimal.valueOf(-1), BigDecimal.ZERO, BigDecimal.valueOf(-2));
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithMinimoNegativoTest() {
        fabrica.crearCon(BigDecimal.valueOf(-1), BigDecimal.ONE, BigDecimal.TEN);
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithMedioNegativoTest() {
        fabrica.crearCon(BigDecimal.ONE, BigDecimal.valueOf(-1), BigDecimal.TEN);
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithMaximoNegativoTest() {
        fabrica.crearCon(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.valueOf(-1));
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamanteFactory#crearCon(BigDecimal, BigDecimal, BigDecimal)
     */
    @Test
    public void crearWithTest() {
        FactorValorDiamante test = fabrica.crearCon(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN);

        assertNotNull(test);
        assertNotNull(test.getMinimo());
        assertNotNull(test.getMedio());
        assertNotNull(test.getMaximo());
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamante#equals(Object)
     */
    @Test
    public void crearWithEqualsTest(){
        FactorValorDiamante test = fabrica.crearCon(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN);
        FactorValorDiamante test2 = fabrica.crearCon(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN);

        assertEquals(test, test);
        assertFalse(test.equals(null));
        assertFalse(test.equals(new Object()));
        assertEquals(test, test2);
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamante#hashCode()
     */
    @Test
    public void crearWithHashCodeTest(){
        FactorValorDiamante test = fabrica.crearCon(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN);

        assertNotNull(test.hashCode());
    }

    /**
     * (non-Javadoc)
     * @see FactorValorDiamante#toString()
     */
    @Test
    public void crearWithToStringTest(){
        FactorValorDiamante test = fabrica.crearCon(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN);

        assertTrue(test.toString().contains(BigDecimal.TEN.toString()));
    }
}
