/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.RangoPesoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.RangoPesoDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.RangoPesoDiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.RangoPesoDiamanteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Clase de prueba para el repositorio RangoPesoDiamanteRepository.
 *
 * @author ecancino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class RangoPesoDiamanteRepositoryIntTest {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RangoPesoDiamanteRepositoryIntTest.class);

    private static final BigDecimal QUILATES_CT_EXISTE = new BigDecimal(0.92D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_LIMITE_INFERIOR_EXISTE = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_LIMITE_SUPERIOR_EXISTE = new BigDecimal(0.96D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_NO_EXISTE = new BigDecimal(100.00D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_LIMITE_INFERIOR_NO_EXISTE = new BigDecimal(0.89D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_LIMITE_SUPERIOR_NO_EXISTE = new BigDecimal(1.00D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_INFERIOR_EXISTE = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_SUPERIOR_EXISTE = new BigDecimal(0.96D).setScale(2, BigDecimal.ROUND_HALF_UP);

    /**
     * Referencia al repositorio de rangoPesoDiamanteRepository.
     */
    @Inject
    private RangoPesoDiamanteRepository rangoPesoDiamanteRepository;



    // METODOS

    /**
     * Constructor.
     */
    public RangoPesoDiamanteRepositoryIntTest() {
        super();
    }

    /**
     * Configuración inicial.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Utilizado para consultar el rango de peso de un diamante cuyas características son:
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = RangoPesoNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerRangoPesoDiamanteTest01() {
        RangoPesoDiamanteVO rangoPesoDiamanteVO = new RangoPesoDiamanteVO(QUILATES_CT_NO_EXISTE);
        rangoPesoDiamanteRepository.obtenerRangoPeso(rangoPesoDiamanteVO);
    }

    /**
     * Utilizado para consultar el rango de peso de un diamante cuyas características son:
     * QUILATES CT - SI EXISTE
     */
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerRangoPesoDiamanteTest02() {
        RangoPesoDiamanteVO rangoPesoDiamanteVO = new RangoPesoDiamanteVO(QUILATES_CT_EXISTE);
        rangoPesoDiamanteRepository.obtenerRangoPeso(rangoPesoDiamanteVO);
    }

    /**
     * Utilizado para consultar el rango de peso de un diamante cuyas características son:
     * QUILATES CT - SI EXISTE (se encuentra dentro del rango)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerRangoPesoDiamanteTest03() {
        RangoPesoDiamanteVO rangoPesoDiamanteVO = new RangoPesoDiamanteVO(QUILATES_CT_EXISTE);
        RangoPesoDiamante result = rangoPesoDiamanteRepository.obtenerRangoPeso(rangoPesoDiamanteVO);

        assertNotNull(result);
        assertEquals(TAMANIO_INFERIOR_EXISTE, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_EXISTE, result.getTamanioSuperior());
    }

    /**
     * Utilizado para consultar el rango de peso de un diamante cuyas características son:
     * QUILATES CT - SI EXISTE (se encuentra en el límite inferior del rango)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerRangoPesoDiamanteTest04() {
        RangoPesoDiamanteVO rangoPesoDiamanteVO = new RangoPesoDiamanteVO(QUILATES_CT_LIMITE_INFERIOR_EXISTE);
        RangoPesoDiamante result = rangoPesoDiamanteRepository.obtenerRangoPeso(rangoPesoDiamanteVO);

        assertNotNull(result);
        assertEquals(TAMANIO_INFERIOR_EXISTE, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_EXISTE, result.getTamanioSuperior());
    }

    /**
     * Utilizado para consultar el rango de peso de un diamante cuyas características son:
     * QUILATES CT - SI EXISTE (se encuentra en el límite superior del rango)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerRangoPesoDiamanteTest05() {
        RangoPesoDiamanteVO rangoPesoDiamanteVO = new RangoPesoDiamanteVO(QUILATES_CT_LIMITE_SUPERIOR_EXISTE);
        RangoPesoDiamante result = rangoPesoDiamanteRepository.obtenerRangoPeso(rangoPesoDiamanteVO);

        assertNotNull(result);
        assertEquals(TAMANIO_INFERIOR_EXISTE, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_EXISTE, result.getTamanioSuperior());
    }

    /**
     * Utilizado para consultar el rango de peso de un diamante cuyas características son:
     * QUILATES CT - NO EXISTE (se encuentra por debajo del límite inferior del rango)
     */
    @Test(expected = RangoPesoNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerRangoPesoDiamanteTest06() {
        RangoPesoDiamanteVO rangoPesoDiamanteVO = new RangoPesoDiamanteVO(QUILATES_CT_LIMITE_INFERIOR_NO_EXISTE);
        rangoPesoDiamanteRepository.obtenerRangoPeso(rangoPesoDiamanteVO);
    }

    /**
     * Utilizado para consultar el rango de peso de un diamante cuyas características son:
     * QUILATES CT - NO EXISTE (se encuentra por encima del límite superior del rango)
     */
    @Test(expected = RangoPesoNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerRangoPesoDiamanteTest07() {
        RangoPesoDiamanteVO rangoPesoDiamanteVO = new RangoPesoDiamanteVO(QUILATES_CT_LIMITE_SUPERIOR_NO_EXISTE);
        rangoPesoDiamanteRepository.obtenerRangoPeso(rangoPesoDiamanteVO);
    }
}
