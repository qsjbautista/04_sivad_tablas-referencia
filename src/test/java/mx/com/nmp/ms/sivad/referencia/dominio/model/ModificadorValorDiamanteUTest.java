package mx.com.nmp.ms.sivad.referencia.dominio.model;

import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.infrastructure.factory.FactorValorDiamanteFactoryImpl;
import mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ModificadorValorDiamanteFactoryImpl;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Pruebas de unidad para la entidad {@link ModificadorValorDiamante}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public class ModificadorValorDiamanteUTest {

    /**
     * Referencia a la clase que será probada.
     */
    private ModificadorValorDiamanteFactory fabrica;

    private FactorValorDiamante vo;

    /**
     * Constructor.
     */
    public ModificadorValorDiamanteUTest() {
        super();

        fabrica = new ModificadorValorDiamanteFactoryImpl();

        FactorValorDiamanteFactory factoryVO = new FactorValorDiamanteFactoryImpl();
        vo = factoryVO.crearCon(BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.30));
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithParametrosNulosTest() {
        fabrica.crearCon(null, null, null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithFechaCargaNulaTest() {
        fabrica.crearCon(null, LocalDate.now(), vo);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithFechaListadoNulaTest() {
        fabrica.crearCon(DateTime.now(), null, vo);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithVONuloTest() {
        fabrica.crearCon(DateTime.now(), LocalDate.now(), null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test
    public void crearWithNoPersistenteTest(){
        ModificadorValorDiamante test = fabrica.crearCon(DateTime.now(), LocalDate.now(), vo);

        assertNotNull(test);
        assertNotNull(test.getFactor());
        assertNotNull(test.getFactor().getMinimo());
        assertNotNull(test.getFactor().getMedio());
        assertNotNull(test.getFactor().getMaximo());
        assertNotNull(test.getFechaCarga());
        assertNotNull(test.getFechaListado());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = NullPointerException.class)
    public void crearWithNoPersistenteErrorTest(){
        ModificadorValorDiamante test = fabrica.crearCon(DateTime.now(), LocalDate.now(), vo);

        test.actualizar();
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamante#equals(Object)
     */
    @Test
    public void crearWithEqualsTest(){
        ModificadorValorDiamante test = fabrica.crearCon(DateTime.now(), LocalDate.now(), vo);
        ModificadorValorDiamante test2 = fabrica.crearCon(DateTime.now(), LocalDate.now(), vo);

        assertEquals(test, test);
        assertFalse(test.equals(null));
        assertFalse(test.equals(new Object()));
        assertEquals(test, test2);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamante#hashCode()
     */
    @Test
    public void crearWithHashCodeTest(){
        ModificadorValorDiamante test = fabrica.crearCon(DateTime.now(), LocalDate.now(), vo);

        assertNotNull(test.hashCode());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamante#toString()
     */
    @Test
    public void crearWithToStringTest(){
        ModificadorValorDiamante test = fabrica.crearCon(DateTime.now(), LocalDate.now(), vo);

        assertTrue(test.toString().contains(test.getClass().getSimpleName()));
    }
}
