/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.conector.ConsultaTipoCambio;
import mx.com.nmp.ms.sivad.referencia.conector.Convertidor;
import mx.com.nmp.ms.sivad.referencia.conector.TipoCambioEnum;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.*;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.CalculosPrecioDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.PrecioCorteDetalleBatch;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.PrecioCorteDetalle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.when;

/**
 * Clase de prueba para el repositorio CalculosPrecioDiamanteRepository.
 *
 * @author ecancino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class CalculosPrecioDiamanteRepositoryIntTest {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculosPrecioDiamanteRepositoryIntTest.class);

    private static final String CORTE = "Oval";
    private static final String COLOR = "D";
    private static final String CLARIDAD = "VS1";
    private static final BigDecimal QUILATES_DESDE = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_HASTA = new BigDecimal(0.94D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO = new BigDecimal(65.70D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_EXISTE_PESOS = new BigDecimal(1460.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TIPO_CAMBIO = new BigDecimal(19.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal MONTOVBD = new BigDecimal(146000.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal MONTOFCASTIGOXRANGO = new BigDecimal(14600.00D).setScale(4, BigDecimal.ROUND_HALF_UP);

    /**
     * Referencia al repositorio de CalculosPrecioDiamanteRepository
     */
    @Inject
    private CalculosPrecioDiamanteRepository calculosPrecioDiamanteRepository;

    /**
     * Referencia al conector con microservicio de tipo cambiario.
     */
    @Mock
    private Convertidor convertidor;

    /**
     * Referencia al microservicio de consulta de tipo cambiario
     */
    @Mock
    private ConsultaTipoCambio consultaTipoCambio;



    // METODOS

    /**
     * Constructor.
     */
    public CalculosPrecioDiamanteRepositoryIntTest() {
        super();
    }

    /**
     * Configuración inicial.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(calculosPrecioDiamanteRepository, "convertidor", convertidor);
        ReflectionTestUtils.setField(calculosPrecioDiamanteRepository, "consultaTipoCambio", consultaTipoCambio);
    }

    /**
     * Utilizado para calcular las nuevas columnas del diamante
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void calcularColumnasTest01() {
        when(consultaTipoCambio.valorPorUnidad(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo())))
            .thenReturn(TIPO_CAMBIO);

        when(convertidor.convertir(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo()),
            any(BigDecimal.class))).thenReturn(PRECIO_EXISTE_PESOS);

        PrecioCorteDetalle precioCorteDetalle = new PrecioCorteDetalle();
        precioCorteDetalle.setClaridad(CLARIDAD);
        precioCorteDetalle.setColor(COLOR);
        precioCorteDetalle.setPrecio(PRECIO);
        precioCorteDetalle.setTamanioInferior(QUILATES_DESDE);
        precioCorteDetalle.setTamanioSuperior(QUILATES_HASTA);
        PrecioCorteDetalleBatch precioCorteDetalleBatch = new PrecioCorteDetalleBatch(CORTE, precioCorteDetalle);
        Diamante result = calculosPrecioDiamanteRepository.calcularColumnas(precioCorteDetalleBatch);

        assertNotNull(result);
        assertEquals(TIPO_CAMBIO, result.getTipoCambio());
        assertEquals(MONTOVBD, result.getMontoVbd().setScale(4));
        assertEquals(MONTOFCASTIGOXRANGO, result.getMontofCastigoxRango().setScale(4));
    }

    /**
     * Utilizado para calcular las nuevas columnas del diamante.
     * No existe un tipo de cambio
     */
    @Test(expected = TipoCambioNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void calcularColumnasTest02() {
        when(consultaTipoCambio.valorPorUnidad(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo())))
            .thenReturn(null);

        PrecioCorteDetalle precioCorteDetalle = new PrecioCorteDetalle();
        precioCorteDetalle.setClaridad(CLARIDAD);
        precioCorteDetalle.setColor(COLOR);
        precioCorteDetalle.setPrecio(PRECIO);
        precioCorteDetalle.setTamanioInferior(QUILATES_DESDE);
        precioCorteDetalle.setTamanioSuperior(QUILATES_HASTA);
        PrecioCorteDetalleBatch precioCorteDetalleBatch = new PrecioCorteDetalleBatch(CORTE, precioCorteDetalle);
        calculosPrecioDiamanteRepository.calcularColumnas(precioCorteDetalleBatch);
    }

    /**
     * Utilizado para calcular las nuevas columnas del diamante.
     * No existe un factor de rapaport
     */
    @Test(expected = FactorRapaportNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante_02-h2.sql")
    public void calcularColumnasTest03() {
        when(consultaTipoCambio.valorPorUnidad(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo())))
            .thenReturn(TIPO_CAMBIO);

        when(convertidor.convertir(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo()),
            any(BigDecimal.class))).thenReturn(PRECIO_EXISTE_PESOS);

        PrecioCorteDetalle precioCorteDetalle = new PrecioCorteDetalle();
        precioCorteDetalle.setClaridad(CLARIDAD);
        precioCorteDetalle.setColor(COLOR);
        precioCorteDetalle.setPrecio(PRECIO);
        precioCorteDetalle.setTamanioInferior(QUILATES_DESDE);
        precioCorteDetalle.setTamanioSuperior(QUILATES_HASTA);
        PrecioCorteDetalleBatch precioCorteDetalleBatch = new PrecioCorteDetalleBatch(CORTE, precioCorteDetalle);
        calculosPrecioDiamanteRepository.calcularColumnas(precioCorteDetalleBatch);
    }

    /**
     * Utilizado para calcular las nuevas columnas del diamante.
     * No existe un castigo de rango por peso
     */
    @Test(expected = CastigoRangoPesoNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante_03-h2.sql")
    public void calcularColumnasTest04() {
        when(consultaTipoCambio.valorPorUnidad(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo())))
            .thenReturn(TIPO_CAMBIO);

        when(convertidor.convertir(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo()),
            any(BigDecimal.class))).thenReturn(PRECIO_EXISTE_PESOS);

        PrecioCorteDetalle precioCorteDetalle = new PrecioCorteDetalle();
        precioCorteDetalle.setClaridad(CLARIDAD);
        precioCorteDetalle.setColor(COLOR);
        precioCorteDetalle.setPrecio(PRECIO);
        precioCorteDetalle.setTamanioInferior(QUILATES_DESDE);
        precioCorteDetalle.setTamanioSuperior(QUILATES_HASTA);
        PrecioCorteDetalleBatch precioCorteDetalleBatch = new PrecioCorteDetalleBatch(CORTE, precioCorteDetalle);
        calculosPrecioDiamanteRepository.calcularColumnas(precioCorteDetalleBatch);
    }

}
