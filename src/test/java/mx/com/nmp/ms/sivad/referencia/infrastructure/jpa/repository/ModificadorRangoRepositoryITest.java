package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CertificadoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactorAlhajaNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoModificadorCertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoRangoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.*;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorAlhajaVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoModificadorCertificadoJPA;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Pruebas de integración para el repositorio {@link ModificadorValorDiamanteRepository}
 *
 * @author mmarquez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ModificadorRangoRepositoryITest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModificadorRangoRepositoryITest.class);

    /**
     * Referencia a la clase que será probada
     */
    @Inject
    private ModificadorRangoRepository test;

    @Inject
    private ListadoRangoFactory fabricaListado;

    private FactorAlhaja factorAlhaja;

    private Set<FactorAlhaja> listaFactorAlhaja;

    /**
     * Constructor.
     */
    public ModificadorRangoRepositoryITest() {
        super();
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#actualizarListado(ListadoRango)
     */
    @Test(expected = IllegalArgumentException.class)
    public void actualizarListadoNulaTest() {
        test.actualizarListado(null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoPorFechaCarga(LocalDate)
     */
    @Test(expected = IllegalArgumentException.class)
    public void consultarListadoPorFechaCargaNulaTest() {
        test.consultarListadoPorFechaCarga(null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoVigente()
     */
    @Test(expected = FactorAlhajaNoEncontradoException.class)
    public void consultarVigentesNoDatosTest() {
        test.consultarListadoVigente();
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoVigente()
     */
    @Test
    public void consultarVigentesNoDatosCatchTest() {
        try {
            test.consultarListadoVigente();
        } catch (FactorAlhajaNoEncontradoException e) {
            assertEquals(e.getClass().getClass(), FactorAlhajaNoEncontradoException.class.getClass());
        }
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoPorFechaCarga(LocalDate)
     */
    @Test(expected = FactorAlhajaNoEncontradoException.class)
    public void consultarListadoPorFechaCargaNoDatosTest() {
        test.consultarListadoPorFechaCarga(LocalDate.now());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#obtenerFactor(FactorAlhajaVO)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void obtenerFactor3ParamTest() {
        FactorAlhajaVO factorAlhaja= new FactorAlhajaVO("met1", "cal1", "ran1");
        FactorAlhaja entidad = test.obtenerFactor(factorAlhaja);

        assertNotNull(entidad);
        assertNotNull(entidad.getFactor());
        assertNotNull(entidad.getDesplazamiento_limite_inferior());
        assertNotNull(entidad.getDesplazamiento_limite_superior());
        assertNotNull(entidad.getDesplazamiento_incremento());
        assertNotNull(entidad.getLimiteInferior());
        assertNotNull(entidad.getLimiteSuperior());
        assertNotNull(entidad.getIncremento());
        assertNotNull(entidad.getUltimaActualizacion());

            LOGGER.info(entidad.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#obtenerFactor(FactorAlhajaVO)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void obtenerFactor2ParamTest() {
        FactorAlhajaVO factorAlhaja= new FactorAlhajaVO("met1", "ran1");
        FactorAlhaja entidad = test.obtenerFactor(factorAlhaja);

        assertNotNull(entidad);
        assertNotNull(entidad.getFactor());
        assertNotNull(entidad.getDesplazamiento_limite_inferior());
        assertNotNull(entidad.getDesplazamiento_limite_superior());
        assertNotNull(entidad.getDesplazamiento_incremento());
        assertNotNull(entidad.getLimiteInferior());
        assertNotNull(entidad.getLimiteSuperior());
        assertNotNull(entidad.getIncremento());
        assertNotNull(entidad.getUltimaActualizacion());

        LOGGER.info(entidad.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoVigente()
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void consultarListadoVigenteTest() {
        ListadoRango entidad = test.consultarListadoVigente();

        assertNotNull(entidad);
        assertEquals(entidad.getFactorAlhajas().size(),4);

        LOGGER.info(entidad.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoPorFechaCarga(LocalDate)
     */
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    @Test(expected = FactorAlhajaNoEncontradoException.class)
    public void consultarPorFechaNoExisteTest() {
        test.consultarListadoPorFechaCarga(LocalDate.now());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoPorFechaCarga(LocalDate)
     */
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    @Test(expected = FactorAlhajaNoEncontradoException.class)
    public void consultarPorFechaFuturaTest() {
        test.consultarListadoPorFechaCarga(LocalDate.now().plusDays(1));
    }


    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoPorFechaCarga(LocalDate)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void consultarPorFechaNoExisteCatchTest() {
        try {
            test.consultarListadoPorFechaCarga(LocalDate.now());
        } catch (FactorAlhajaNoEncontradoException e) {
            assertEquals(e.getEntidad().getClass(), FactorAlhajaNoEncontradoException.class.getClass());
        }
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoPorFechaCarga(LocalDate)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void consultarPorFechaR2Test() {
        Set<ListadoRango> entidades = test.consultarListadoPorFechaCarga(LocalDate.parse("2016-11-27"));

        assertNotNull(entidades);
        assertEquals(entidades.size(), 1);

        LOGGER.info(entidades.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#consultarListadoPorFechaCarga(LocalDate)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void consultarPorFechaR1Test() {
        Set<ListadoRango> entidades = test.consultarListadoPorFechaCarga(LocalDate.parse("2016-11-26"));

        assertNotNull(entidades);
        assertEquals(entidades.size(), 4);

        LOGGER.info(entidades.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorRangoRepository#actualizarListado(ListadoRango)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void actualizarListadoTest() {
        ListadoRango listadoInicialIda = test.consultarListadoVigente();
        assertNotNull(listadoInicialIda);
        assertEquals(listadoInicialIda.getFactorAlhajas().size(), 4);

        Set<ListadoRango> histListadoInicialIda = test.consultarListadoPorFechaCarga(LocalDate.parse("2016-11-26"));
        assertNotNull(histListadoInicialIda);
        assertEquals(histListadoInicialIda.size(), 4);

        Set<FactorAlhaja> factoresAlhaja = new HashSet<>();
        FactorAlhaja factor1 = FactorAlhajaFactory.crear("met1", "cal1", "ran1", new BigDecimal(10), new BigDecimal(10),
            new BigDecimal(10),5,new BigDecimal(10), new BigDecimal(20),5, DateTime.now());
        FactorAlhaja factor2 = FactorAlhajaFactory.crear("met2", "cal2", "ran2", new BigDecimal(10), new BigDecimal(10),
            new BigDecimal(10),5,new BigDecimal(10), new BigDecimal(20),5, DateTime.now());
        FactorAlhaja factor3 = FactorAlhajaFactory.crear("met3", "cal3", "ran3", new BigDecimal(10), new BigDecimal(10),
            new BigDecimal(10),5,new BigDecimal(10), new BigDecimal(20),5, DateTime.now());
        factoresAlhaja.add(factor1);
        factoresAlhaja.add(factor2);
        factoresAlhaja.add(factor3);

        ListadoRango listado = fabricaListado.crear(DateTime.parse("2016-11-28"), LocalDate.now().plusDays(-1),factoresAlhaja);
        listado = test.actualizarListado(listado);
        assertEquals(listado.getFactorAlhajas().size(), 3);
        ListadoRango resultListadoVigente = test.consultarListadoVigente();
        assertEquals(resultListadoVigente.getFactorAlhajas().size(), 3);
        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getUltimaActualizacion());
        assertNotNull(resultListadoVigente.getFactorAlhajas());
        assertFalse(resultListadoVigente.getFactorAlhajas().isEmpty());
        assertTrue(resultListadoVigente.getFactorAlhajas().size() == 3);

        histListadoInicialIda = test.consultarListadoPorFechaCarga(LocalDate.parse("2016-11-27"));

        assertNotNull(histListadoInicialIda);
        assertNotNull(histListadoInicialIda.isEmpty());
        assertEquals(histListadoInicialIda.size(),1);

        FactorAlhaja factor4 = FactorAlhajaFactory.crear("met4", "cal4", "ran4", new BigDecimal(10), new BigDecimal(10),
            new BigDecimal(10),5,new BigDecimal(10), new BigDecimal(20),5, DateTime.now());

        factoresAlhaja.clear();
        factoresAlhaja.add(factor4);
        listado = fabricaListado.crear(DateTime.parse("2016-11-29T01"), LocalDate.now(),factoresAlhaja);
        listado = test.actualizarListado(listado);
        assertEquals(listado.getFactorAlhajas().size(),1);
        resultListadoVigente = test.consultarListadoVigente();
        assertEquals(resultListadoVigente.getFactorAlhajas().size(),1);

        histListadoInicialIda = test.consultarListadoPorFechaCarga(LocalDate.parse("2016-11-27"));

        assertNotNull(histListadoInicialIda);
        assertNotNull(histListadoInicialIda.isEmpty());
        assertEquals(histListadoInicialIda.size(),1);

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getUltimaActualizacion());
        assertNotNull(resultListadoVigente.getFactorAlhajas());
        assertFalse(resultListadoVigente.getFactorAlhajas().isEmpty());
        assertTrue(resultListadoVigente.getFactorAlhajas().size() == 1);

        listado = test.consultarListadoVigente();

        assertNotNull(listado);
        assertNotNull(listado.getFactorAlhajas());
        assertEquals(listado.getFactorAlhajas().size(),1);
    }
}
