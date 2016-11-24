package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Pruebas de integración para el repositorio {@link ModificadorValorDiamanteRepository}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ModificadorValorDiamanteRepositoryITest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModificadorValorDiamanteRepositoryITest.class);

    /**
     * Referencia a la clase que será probada
     */
    @Inject
    private ModificadorValorDiamanteRepository test;

    @Inject
    private ModificadorValorDiamanteFactory fabrica;

    @Inject
    private FactorValorDiamanteFactory fabricaVO;

    /**
     * Constructor.
     */
    public ModificadorValorDiamanteRepositoryITest() {
        super();
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#actualizar(ModificadorValorDiamante)
     */
    @Test(expected = IllegalArgumentException.class)
    public void actualizarEntidadNulaTest() {
        test.actualizar(null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar(DateTime)
     */
    @Test(expected = IllegalArgumentException.class)
    public void consultarPorFechaNulaTest() {
        test.consultar(null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar()
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    public void consultarVigentesNoDatosTest() {
        test.consultar();
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar()
     */
    @Test
    public void consultarVigentesNoDatosCatchTest() {
        try {
            test.consultar();
        } catch (ValorComercialNoEncontradoException e) {
            assertEquals(e.getValorComercial().getClass(), FactorValorDiamante.class.getClass());
        }
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar(DateTime)
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    public void consultarPorFechaNoDatosTest() {
        test.consultar(DateTime.now());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar()
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-cfg_diamante_factor-h2.sql")
    public void consultarTest() {
        ModificadorValorDiamante entidad = test.consultar();

        assertNotNull(entidad);
        assertNotNull(entidad.getFactor());

        LOGGER.info(entidad.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar(DateTime)
     */
    @Transactional
    @Sql("/bd/test-data-cfg_diamante_factor-h2.sql")
    @Test(expected = ValorComercialNoEncontradoException.class)
    public void consultarPorFechaNoExisteTest() {
        test.consultar(DateTime.now());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar(DateTime)
     */
    @Transactional
    @Sql("/bd/test-data-cfg_diamante_factor-h2.sql")
    @Test(expected = FechaVigenciaFuturaException.class)
    public void consultarPorFechaFuturaTest() {
        test.consultar(DateTime.now().plusDays(1));
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar(DateTime)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-cfg_diamante_factor-h2.sql")
    public void consultarPorFechaFuturaCatchTest() {
        try {
            test.consultar(DateTime.now().plusDays(1));
        } catch (FechaVigenciaFuturaException e) {
            assertEquals(e.getEntidad().getClass(), ModificadorValorDiamante.class.getClass());
        }
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar(DateTime)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-cfg_diamante_factor-h2.sql")
    public void consultarPorFechaNoExisteCatchTest() {
        try {
            test.consultar(DateTime.now());
        } catch (ValorComercialNoEncontradoException e) {
            assertEquals(e.getValorComercial().getClass(), FactorValorDiamante.class.getClass());
        }
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar(DateTime)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-cfg_diamante_factor-h2.sql")
    public void consultarPorFechaR2Test() {
        List<ModificadorValorDiamante> entidades = test.consultar(DateTime.parse("2016-11-24T01"));

        assertNotNull(entidades);
        assertEquals(entidades.size(), 2);

        LOGGER.info(entidades.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#consultar(DateTime)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-cfg_diamante_factor-h2.sql")
    public void consultarPorFechaR1Test() {
        List<ModificadorValorDiamante> entidades = test.consultar(DateTime.parse("2016-11-24T01:53"));

        assertNotNull(entidades);
        assertEquals(entidades.size(), 1);

        LOGGER.info(entidades.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorValorDiamanteRepository#actualizar(ModificadorValorDiamante)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-cfg_diamante_factor-h2.sql")
    public void actualizarTest() {
        FactorValorDiamante vo = fabricaVO
            .crearCon(BigDecimal.valueOf(0.40), BigDecimal.valueOf(0.50), BigDecimal.valueOf(0.60));
        ModificadorValorDiamante modificador = fabrica.crearCon(DateTime.now(), LocalDate.now(), vo);

        ModificadorValorDiamante result = test.actualizar(modificador);

        assertNotNull(result);
        assertNotNull(result.getFactor());

        LOGGER.info(result.toString());
    }
}
