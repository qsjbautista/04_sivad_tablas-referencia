package mx.com.nmp.ms.sivad.referencia.dominio.model;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoModificadorCertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoRangoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.*;
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
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Pruebas de integración para la entidad {@link ModificadorValorDiamante}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class FactorAlhajasITest {
    /**
     * Referencia a la clase que será probada.
     */
    @Inject
    private ListadoRangoFactory fabrica;

    private FactorAlhaja factorAlhaja;

    private Set<FactorAlhaja> listaFactoresAlhaja;

    /**
     * Constructor.
     */
    public FactorAlhajasITest() {
        super();
    }

    /**
     * Se ejecuta antes de cada método marcado con {@code @Test}
     *
     * @throws Exception Si ocurre algún error.
     */
    @Before
    public void setUp() throws Exception {
        factorAlhaja = FactorAlhajaFactory.crear("metal", "calidad", "rango", new BigDecimal(10), new BigDecimal(30),
            new BigDecimal(05),5,new BigDecimal(05), new BigDecimal(50),5, DateTime.now());
        listaFactoresAlhaja = new HashSet<FactorAlhaja>();
        listaFactoresAlhaja.add(factorAlhaja);

    }

    /**
     * (non-Javadoc)
     * @see ListadoRangoFactory#crearPersistible(DateTime, LocalDate, Set<FactorAlhaja>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteParametrosNulosTest() {
        fabrica.crearPersistible(null, null, null);
    }

    /**
     * (non-Javadoc)
     * @see ListadoRangoFactory#crearPersistible(DateTime, LocalDate, Set<FactorAlhaja>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteFechaCargaNulaTest() {
        fabrica.crearPersistible(null, LocalDate.now(), listaFactoresAlhaja);
    }

    /**
     * (non-Javadoc)
     * @see ListadoRangoFactory#crearPersistible(DateTime, LocalDate, Set<FactorAlhaja>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteFechaListadoNulaTest() {
        fabrica.crearPersistible(DateTime.now(), null, listaFactoresAlhaja);
    }

    /**
     * (non-Javadoc)
     * @see ListadoRangoFactory#crearPersistible(DateTime, LocalDate, Set<FactorAlhaja>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteVONuloTest() {
        fabrica.crearPersistible(DateTime.now(), LocalDate.now(), null);
    }

    /**
     * (non-Javadoc)
     * @see ListadoRangoFactory#crear(Set<FactorAlhaja>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void creaVONuloTest() {
        fabrica.crear(null);
    }

    /**
     * (non-Javadoc)
     * @see ListadoRangoFactory#crear(Set<FactorAlhaja>)
     */
    @Test
    public void crearTest(){
        ListadoRango test = fabrica.crear(listaFactoresAlhaja);

        assertNotNull(test);
        assertNotNull(test.getFactorAlhajas());
        assertEquals(test.getFactorAlhajas().size(), 1);
    }

    /**
     * (non-Javadoc)
     * @see ListadoRangoFactory#crearPersistible(DateTime, LocalDate, Set<FactorAlhaja>)
     */
    @Test
    public void crearWithPersistenteTest(){
        ListadoRango test = fabrica.crearPersistible(DateTime.now(), LocalDate.now(), listaFactoresAlhaja);

        assertNotNull(test);
        assertNotNull(test.getFactorAlhajas());
        assertEquals(test.getFactorAlhajas().size(), 1);
        assertNotNull(test.getUltimaActualizacion());
        assertNotNull(test.getFechaListado());
    }

    /**
     * (non-Javadoc)
     * @see ListadoRangoFactory#crearPersistible(DateTime, LocalDate, Set<FactorAlhaja>)
     * @see ListadoRango#actualizar()
     */
    @Test
    @Transactional
    public void actualizarTest(){
        ListadoRango listadoRango = fabrica.crearPersistible(DateTime.now(), LocalDate.now(), listaFactoresAlhaja);
        ListadoRango test = listadoRango.actualizar();

        assertNotNull(test);
        assertNotNull(test.getFactorAlhajas());
        assertNotNull(test.getUltimaActualizacion());
        assertNotNull(test.getFechaListado());
    }
}
