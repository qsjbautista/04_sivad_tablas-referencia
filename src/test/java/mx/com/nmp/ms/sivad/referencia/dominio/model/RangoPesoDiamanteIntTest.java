/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.model;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.RangoPesoDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.RangoPesoDiamanteFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Clase de prueba utilizada para validar el comportamiento de la entidad de dominio RangoPesoDiamante.
 *
 * @author ecancino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class RangoPesoDiamanteIntTest {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RangoPesoDiamanteIntTest.class);

    private static final BigDecimal TAMANIO_INFERIOR = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_SUPERIOR = new BigDecimal(0.99D).setScale(2, BigDecimal.ROUND_HALF_UP);



    // METODOS

    /**
     * Constructor.
     */
    public RangoPesoDiamanteIntTest() {
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
     * Utilizado para crear una entidad RangoPesoDiamante a través del factory con las siguientes características:
     *
     * TAMANIO INFERIOR - NULO
     * TAMANIO SUPERIOR - NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearRangoPesoDiamanteTest01() {
        RangoPesoDiamanteFactory.create(null, null);
    }

    /**
     * Utilizado para crear una entidad RangoPesoDiamante a través del factory con las siguientes características:
     *
     * TAMANIO INFERIOR - NULO
     * TAMANIO SUPERIOR - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearRangoPesoDiamanteTest02() {
        RangoPesoDiamanteFactory.create(null, TAMANIO_SUPERIOR);
    }

    /**
     * Utilizado para crear una entidad RangoPesoDiamante a través del factory con las siguientes características:
     *
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearRangoPesoDiamanteTest03() {
        RangoPesoDiamanteFactory.create(TAMANIO_INFERIOR, null);
    }

    /**
     * Utilizado para crear una entidad RangoPesoDiamante a través del factory con las siguientes características:
     *
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     */
    @Test
    public void crearRangoPesoDiamanteTest04() {
        RangoPesoDiamante result = RangoPesoDiamanteFactory.create(TAMANIO_INFERIOR, TAMANIO_SUPERIOR);

        assertNotNull(result);
        assertEquals(TAMANIO_INFERIOR, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR, result.getTamanioSuperior());
    }
}
