package mx.com.nmp.ms.sivad.referencia.dominio.model;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;

/**
 * Pruebas de integración para la entidad {@link ModificadorValorDiamante}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ModificadorValorDiamanteITest {
    /**
     * Referencia a la clase que será probada.
     */
    @Inject
    private ModificadorValorDiamanteFactory fabrica;

    @Inject
    private FactorValorDiamanteFactory fabricaVO;

    private FactorValorDiamante vo;

    /**
     * Constructor.
     */
    public ModificadorValorDiamanteITest() {
        super();
    }

    /**
     * Se ejecuta antes de cada método marcado con {@code @Test}
     *
     * @throws Exception Si ocurre algún error.
     */
    @Before
    public void setUp() throws Exception {
        vo = fabricaVO.crearCon(BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0.30));
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearPersistibleCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteParametrosNulosTest() {
        fabrica.crearPersistibleCon(null, null, null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearPersistibleCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteFechaCargaNulaTest() {
        fabrica.crearPersistibleCon(null, LocalDate.now(), vo);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearPersistibleCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteFechaListadoNulaTest() {
        fabrica.crearPersistibleCon(DateTime.now(), null, vo);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearPersistibleCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteVONuloTest() {
        fabrica.crearPersistibleCon(DateTime.now(), LocalDate.now(), null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteFactory#crearPersistibleCon(DateTime, LocalDate, FactorValorDiamante)
     */
    @Test
    public void crearWithPersistenteTest(){
        ModificadorValorDiamante test = fabrica.crearPersistibleCon(DateTime.now(), LocalDate.now(), vo);

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
     * @see ModificadorValorDiamanteFactory#crearPersistibleCon(DateTime, LocalDate, FactorValorDiamante)
     * @see ModificadorValorDiamante#actualizar()
     */
    @Test
    @Transactional
    public void actualizarTest(){
        ModificadorValorDiamante modificador = fabrica.crearPersistibleCon(DateTime.now(), LocalDate.now(), vo);
        ModificadorValorDiamante test = modificador.actualizar();

        assertNotNull(test);
        assertNotNull(test.getFactor());
        assertNotNull(test.getFactor().getMinimo());
        assertNotNull(test.getFactor().getMedio());
        assertNotNull(test.getFactor().getMaximo());
        assertNotNull(test.getFechaCarga());
        assertNotNull(test.getFechaListado());
    }
}
