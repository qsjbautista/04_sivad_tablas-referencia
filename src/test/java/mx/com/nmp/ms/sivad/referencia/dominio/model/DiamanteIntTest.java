/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.model;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.conector.Convertidor;
import mx.com.nmp.ms.sivad.referencia.conector.TipoCambioEnum;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoCorteNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.DiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.ValorComercialDiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.CastigoCorteDiamanteJPARepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.HistListadoValorComercialDiamanteJPARepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.ListadoValorComercialDiamanteJPARepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.when;

/**
 * Clase de prueba utilizada para validar el comportamiento de la entidad de dominio Diamante.
 *
 * @author ngonzalez, ecancino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class DiamanteIntTest {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DiamanteIntTest.class);

    private static final String CLARIDAD = "VS1";
    private static final String CLARIDAD_NUEVO_1 = "SI1";
    private static final String CLARIDAD_NUEVO_2 = "SI2";
    private static final String CLARIDAD_NUEVO_3 = "SI3";
    private static final String COLOR = "D";
    private static final String COLOR_NUEVO = "K";
    private static final String CORTE = "Oval";
    private static final String SUBCORTE = "Acojinado";
    private static final String CORTE_NUEVO = "Esmeralda";
    private static final String FECHA_VIGENCIA = "2016-11-21";
    private static final String FORMATO_FECHA = "yyyy-MM-dd";
    private static final BigDecimal PRECIO = new BigDecimal(73.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_PESOS = new BigDecimal(1460.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_1 = new BigDecimal(40.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_2 = new BigDecimal(36.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_3 = new BigDecimal(30.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_3_PESOS = new BigDecimal(600.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT = new BigDecimal(0.92D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_NUEVO = new BigDecimal(1.35D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_INFERIOR = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_INFERIOR_NUEVO = new BigDecimal(1.00D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_SUPERIOR = new BigDecimal(0.99D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_SUPERIOR_NUEVO = new BigDecimal(1.49D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TIPO_CAMBIO = new BigDecimal(19.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal MONTOVBD = new BigDecimal(73.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal MONTOFCASTIGOXRANGO = new BigDecimal(73.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_DESDE = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_HASTA = new BigDecimal(0.94D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_PESOS_CASTIGO = new BigDecimal(65.70D).setScale(4, BigDecimal.ROUND_HALF_UP);

    private static final BigDecimal FACTOR_VALOR_DIAMANTE_MINIMO = new BigDecimal(0.30D).setScale(3, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal FACTOR_VALOR_DIAMANTE_MEDIO = new BigDecimal(0.40D).setScale(3, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal FACTOR_VALOR_DIAMANTE_MAXIMO = new BigDecimal(0.50D).setScale(3, BigDecimal.ROUND_HALF_UP);


    private static final BigDecimal VALOR_COMERCIAL_MINIMO_PESOS =
        new BigDecimal(438.0000D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal VALOR_COMERCIAL_MEDIO_PESOS =
        new BigDecimal(584.0000D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal VALOR_COMERCIAL_MAXIMO_PESOS =
        new BigDecimal(730.0000D).setScale(4, BigDecimal.ROUND_HALF_UP);

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    @Inject
    private ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    /**
     * Referencia al repositorio de ValorComercialDiamanteJPARepository.
     */
    @Inject
    private ListadoValorComercialDiamanteJPARepository jpaRepository;

    /**
     * Referencia al repositorio de HistListadoValorComercialDiamanteJPARepository.
     */
    @Inject
    private HistListadoValorComercialDiamanteJPARepository histJPARepository;

	/**
     * Referencia al conector con microservicio de tipo cambiario.
     */
    //@Mock
    //private Convertidor convertidor;


    // METODOS

    /**
     * Constructor.
     */
    public DiamanteIntTest() {
        super();
    }

    /**
     * Configuración inicial.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //ReflectionTestUtils.setField(valorComercialDiamanteRepository, "convertidor", convertidor);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NULO
     * COLOR - NULO
     * CLARIDAD - NULA
     * TAMANIO INFERIOR - NULO
     * TAMANIO SUPERIOR - NULO
     * PRECIO - NULO
     * TIPO CAMBIO - NULO
     * MONTOVBD - NULO
     * MONTOFCASTIGOXRANGO - NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest01() {
        DiamanteFactory.create(null, null, null, null, null, null,
            null, null, null);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NULO
     * COLOR - NO NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NO NULO
     * TIPO CAMBIO - NO NULO
     * MONTOVBD - NO NULO
     * MONTOFCASTIGOXRANGO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest02() {
        DiamanteFactory.create(null, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO, TIPO_CAMBIO,
            MONTOVBD, MONTOFCASTIGOXRANGO);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NO NULO
     * TIPO CAMBIO - NO NULO
     * MONTOVBD - NO NULO
     * MONTOFCASTIGOXRANGO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest03() {
        DiamanteFactory.create(CORTE, null, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO, TIPO_CAMBIO,
            MONTOVBD, MONTOFCASTIGOXRANGO);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NO NULO
     * CLARIDAD - NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NO NULO
     * TIPO CAMBIO - NO NULO
     * MONTOVBD - NO NULO
     * MONTOFCASTIGOXRANGO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest04() {
        DiamanteFactory.create(CORTE, COLOR, null, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO, TIPO_CAMBIO,
            MONTOVBD, MONTOFCASTIGOXRANGO);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NO NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NO NULO
     * TIPO CAMBIO - NO NULO
     * MONTOVBD - NO NULO
     * MONTOFCASTIGOXRANGO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest05() {
        DiamanteFactory.create(CORTE, COLOR, CLARIDAD, null, TAMANIO_SUPERIOR, PRECIO, TIPO_CAMBIO,
            MONTOVBD, MONTOFCASTIGOXRANGO);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NO NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NULO
     * PRECIO - NO NULO
     * TIPO CAMBIO - NO NULO
     * MONTOVBD - NO NULO
     * MONTOFCASTIGOXRANGO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest06() {
        DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, null, PRECIO, TIPO_CAMBIO,
            MONTOVBD, MONTOFCASTIGOXRANGO);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NO NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NULO
     * TIPO CAMBIO - NO NULO
     * MONTOVBD - NO NULO
     * MONTOFCASTIGOXRANGO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest07() {
        DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, null, TIPO_CAMBIO,
            MONTOVBD, MONTOFCASTIGOXRANGO);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NO NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NO NULO
     */
    @Test
    public void crearDiamanteTest08() {
        Diamante result = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        assertNotNull(result);
        assertEquals(CORTE, result.getCorte());
        assertEquals(COLOR, result.getColor());
        assertEquals(CLARIDAD, result.getClaridad());
        assertEquals(TAMANIO_INFERIOR, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR, result.getTamanioSuperior());
        assertEquals(PRECIO, result.getPrecio());
        assertEquals(TIPO_CAMBIO, result.getTipoCambio());
        assertEquals(MONTOVBD, result.getMontoVbd());
        assertEquals(MONTOFCASTIGOXRANGO, result.getMontofCastigoxRango());
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NO NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NO NULO
     * TIPO CAMBIO - NULO
     * MONTOVBD - NO NULO
     * MONTOFCASTIGOXRANGO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest09() {
        DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO, null,
            MONTOVBD, MONTOFCASTIGOXRANGO);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NO NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NO NULO
     * TIPO CAMBIO - NO NULO
     * MONTOVBD - NULO
     * MONTOFCASTIGOXRANGO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest10() {
        DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO, TIPO_CAMBIO,
            null, MONTOFCASTIGOXRANGO);
    }

    /**
     * Utilizado para crear una entidad Diamante a través del factory con las siguientes características:
     *
     * CORTE - NO NULO
     * COLOR - NO NULO
     * CLARIDAD - NO NULA
     * TAMANIO INFERIOR - NO NULO
     * TAMANIO SUPERIOR - NO NULO
     * PRECIO - NO NULO
     * TIPO CAMBIO - NO NULO
     * MONTOVBD - NO NULO
     * MONTOFCASTIGOXRANGO - NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearDiamanteTest11() {
        DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO, TIPO_CAMBIO,
            MONTOVBD, null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA LISTADO - FECHA NULA
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest01() {
        ListadoValorComercialDiamanteFactory.create(null, null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA LISTADO - FECHA NULA
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest02() {
        Set<Diamante> valoresComerciales = new HashSet<>();
        ListadoValorComercialDiamanteFactory.create(null, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA LISTADO - FECHA NULA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest03() {
        Diamante diamante = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        Set<Diamante> valoresComerciales = new HashSet<>();
        valoresComerciales.add(diamante);
        ListadoValorComercialDiamanteFactory.create(null, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA LISTADO - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest04() {
        ListadoValorComercialDiamanteFactory.create(LocalDate.now(), null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA LISTADO - FECHA NO NULA
     * VALORES COMERCIALES - LISTA VACIA
     */
    //@Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest05() {
        Set<Diamante> valoresComerciales = new HashSet<>();
        ListadoValorComercialDiamanteFactory.create(LocalDate.now(), valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA LISTADO - FECHA FUTURA
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest06() {
        Diamante diamante = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        LocalDate fechaFutura = LocalDate.now();
        fechaFutura = fechaFutura.plusDays(1);

        Set<Diamante> valoresComerciales = new HashSet<>();
        valoresComerciales.add(diamante);
        ListadoValorComercialDiamanteFactory.create(fechaFutura, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA LISTADO - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test
    public void crearListadoValorComercialDiamanteTest07() {
        Diamante diamante = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        LocalDate fechaListado = LocalDate.now();

        Set<Diamante> valoresComerciales = new HashSet<>();
        valoresComerciales.add(diamante);
        ListadoValorComercialDiamante result =
            ListadoValorComercialDiamanteFactory.create(fechaListado, valoresComerciales);

        assertNotNull(result);
        assertEquals(fechaListado, result.getFechaListado());
        assertNotNull(result.getValoresComerciales());
        assertEquals(1, result.getValoresComerciales().size());
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA CARGA - FECHA NULA
     * FECHA LISTADO - FECHA NULA
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest08() {
        ListadoValorComercialDiamanteFactory.create(null, null, null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA CARGA - FECHA NULA
     * FECHA LISTADO - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest09() {
        Diamante diamante = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        Set<Diamante> valoresComerciales = new HashSet<>();
        valoresComerciales.add(diamante);
        ListadoValorComercialDiamanteFactory.create(null, LocalDate.now(), valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA CARGA - FECHA NO NULA
     * FECHA LISTADO - FECHA NULA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest10() {
        Diamante diamante = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        Set<Diamante> valoresComerciales = new HashSet<>();
        valoresComerciales.add(diamante);
        ListadoValorComercialDiamanteFactory.create(DateTime.now(), null, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA CARGA - FECHA NO NULA
     * FECHA LISTADO - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest11() {
        ListadoValorComercialDiamanteFactory.create(DateTime.now(), LocalDate.now(), null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA CARGA - FECHA NO NULA
     * FECHA LISTADO - FECHA NO NULA
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest12() {
        Set<Diamante> valoresComerciales = new HashSet<>();
        ListadoValorComercialDiamanteFactory.create(DateTime.now(), LocalDate.now(), valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA CARGA - FECHA FUTURA
     * FECHA LISTADO - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest13() {
        Diamante diamante = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        DateTime fechaFutura = DateTime.now();
        fechaFutura = fechaFutura.plusDays(1);

        Set<Diamante> valoresComerciales = new HashSet<>();
        valoresComerciales.add(diamante);
        ListadoValorComercialDiamanteFactory.create(fechaFutura, LocalDate.now(), valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA CARGA - FECHA NO NULA
     * FECHA LISTADO - FECHA FUTURA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialDiamanteTest14() {
        Diamante diamante = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        LocalDate fechaFutura = LocalDate.now();
        fechaFutura = fechaFutura.plusDays(1);

        Set<Diamante> valoresComerciales = new HashSet<>();
        valoresComerciales.add(diamante);
        ListadoValorComercialDiamanteFactory.create(DateTime.now(), fechaFutura, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialDiamante a través del factory con las
     * siguientes características:
     *
     * FECHA CARGA - FECHA NO NULA
     * FECHA LISTADO - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test
    public void crearListadoValorComercialDiamanteTest15() {
        Diamante diamante = DiamanteFactory.create(CORTE, COLOR, CLARIDAD, TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO,
            TIPO_CAMBIO, MONTOVBD, MONTOFCASTIGOXRANGO);

        DateTime fechaCarga = DateTime.now();
        LocalDate fechaListado = LocalDate.now();

        Set<Diamante> valoresComerciales = new HashSet<>();
        valoresComerciales.add(diamante);
        ListadoValorComercialDiamante result =
            ListadoValorComercialDiamanteFactory.create(fechaCarga, fechaListado, valoresComerciales);

        assertNotNull(result);
        assertEquals(fechaCarga, result.getFechaCarga());
        assertEquals(fechaListado, result.getFechaListado());
        assertNotNull(result.getValoresComerciales());
        assertEquals(1, result.getValoresComerciales().size());
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante encontrando porcentaje de castigo
     * por tipo de corte.
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest01() {
        /*when(convertidor.convertir(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo()),
            any(BigDecimal.class))).thenReturn(PRECIO_PESOS);*/

        DiamanteVO diamanteVO = new DiamanteVO(CORTE, SUBCORTE, COLOR, CLARIDAD, QUILATES_CT, QUILATES_DESDE, QUILATES_HASTA);
        Diamante result = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);

        assertNotNull(result);
        assertEquals(CORTE, result.getCorte());
        assertEquals(COLOR, result.getColor());
        assertEquals(CLARIDAD, result.getClaridad());
        assertEquals(TAMANIO_INFERIOR, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR, result.getTamanioSuperior());
        assertEquals(PRECIO_PESOS_CASTIGO, result.getPrecio().setScale(4));

        ValorComercialDiamanteVO valorComercialDiamanteVO = result.obtenerValorComercial();
        assertNotNull(valorComercialDiamanteVO);
        assertNotNull(valorComercialDiamanteVO.getValorMinimo());
        assertNotNull(valorComercialDiamanteVO.getValorMedio());
        assertNotNull(valorComercialDiamanteVO.getValorMaximo());

        BigDecimal precioMinimo = PRECIO_PESOS_CASTIGO.multiply(FACTOR_VALOR_DIAMANTE_MINIMO).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal precioMedio = PRECIO_PESOS_CASTIGO.multiply(FACTOR_VALOR_DIAMANTE_MEDIO).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal precioMaximo = PRECIO_PESOS_CASTIGO.multiply(FACTOR_VALOR_DIAMANTE_MAXIMO).setScale(4, BigDecimal.ROUND_HALF_UP);

        assertEquals(precioMinimo, valorComercialDiamanteVO.getValorMinimo());
        assertEquals(precioMedio, valorComercialDiamanteVO.getValorMedio());
        assertEquals(precioMaximo, valorComercialDiamanteVO.getValorMaximo());
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante sin encontrar
     * porcentaje de castigo por tipo de corte.
     */
    @Test(expected = CastigoCorteNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante_04-h2.sql")
    public void obtenerValorComercialDiamanteTest02() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE, SUBCORTE, COLOR, CLARIDAD, QUILATES_CT, QUILATES_DESDE, QUILATES_HASTA);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del diamante (sin datos iniciales).
     */
    //@Test
    //@Transactional
/*    public void actualizarListadoTest01() {
        List<ListadoValorComercialDiamanteJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() == 0);

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() == 0);

        Set<Diamante> valoresComerciales = new HashSet<>();
        Diamante diamante1 = DiamanteFactory.create(CORTE, COLOR, CLARIDAD,
            TAMANIO_INFERIOR, TAMANIO_SUPERIOR, PRECIO);
        valoresComerciales.add(diamante1);

        ListadoValorComercialDiamante listado =
            ListadoValorComercialDiamanteFactory.create(LocalDate.now(), valoresComerciales);
        listado.actualizar();

        ListadoValorComercialDiamante resultListadoVigente = valorComercialDiamanteRepository.consultarListadoVigente();

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getFechaCarga());
        assertNotNull(resultListadoVigente.getFechaListado());
        assertNotNull(resultListadoVigente.getValoresComerciales());
        assertFalse(resultListadoVigente.getValoresComerciales().isEmpty());
        assertTrue(resultListadoVigente.getValoresComerciales().size() == 1);

        when(convertidor.convertir(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo()),
            any(BigDecimal.class))).thenReturn(PRECIO_PESOS);

        DiamanteVO diamanteVigenteVO = new DiamanteVO(CORTE, COLOR, CLARIDAD, QUILATES_CT);
        Diamante resultDiamanteVigente = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVigenteVO);

        assertNotNull(resultDiamanteVigente);
        assertEquals(CORTE, resultDiamanteVigente.getCorte());
        assertEquals(COLOR, resultDiamanteVigente.getColor());
        assertEquals(CLARIDAD, resultDiamanteVigente.getClaridad());
        assertEquals(TAMANIO_INFERIOR, resultDiamanteVigente.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR, resultDiamanteVigente.getTamanioSuperior());
        assertEquals(PRECIO_PESOS, resultDiamanteVigente.getPrecio());

        List<ListadoValorComercialDiamanteJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() == 0);
    }*/

    /**
     * Utilizado para actualizar el listado de valores comerciales del diamante (con datos iniciales).
     */
    //@Test
    //@Transactional
    //@Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
/*    public void actualizarListadoTest02() {
        List<ListadoValorComercialDiamanteJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() > 0);

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() > 0);
        int tamanioHistListadoInicial = histListadoInicialIda.size();

        Set<Diamante> valoresComerciales = new HashSet<>();
        Diamante diamante1 = DiamanteFactory.create(CORTE_NUEVO, COLOR_NUEVO, CLARIDAD_NUEVO_1,
            TAMANIO_INFERIOR_NUEVO, TAMANIO_SUPERIOR_NUEVO, PRECIO_NUEVO_1);
        Diamante diamante2 = DiamanteFactory.create(CORTE_NUEVO, COLOR_NUEVO, CLARIDAD_NUEVO_2,
            TAMANIO_INFERIOR_NUEVO, TAMANIO_SUPERIOR_NUEVO, PRECIO_NUEVO_2);
        Diamante diamante3 = DiamanteFactory.create(CORTE_NUEVO, COLOR_NUEVO, CLARIDAD_NUEVO_3,
            TAMANIO_INFERIOR_NUEVO, TAMANIO_SUPERIOR_NUEVO, PRECIO_NUEVO_3);
        valoresComerciales.add(diamante1);
        valoresComerciales.add(diamante2);
        valoresComerciales.add(diamante3);

        ListadoValorComercialDiamante listado = ListadoValorComercialDiamanteFactory.create(LocalDate.now(), valoresComerciales);
        listado.actualizar();

        ListadoValorComercialDiamante resultListadoVigente = valorComercialDiamanteRepository.consultarListadoVigente();

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getFechaCarga());
        assertNotNull(resultListadoVigente.getFechaListado());
        assertNotNull(resultListadoVigente.getValoresComerciales());
        assertFalse(resultListadoVigente.getValoresComerciales().isEmpty());

        when(convertidor.convertir(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo()),
            any(BigDecimal.class))).thenReturn(PRECIO_NUEVO_3_PESOS);

        DiamanteVO diamanteVigenteVO = new DiamanteVO(CORTE_NUEVO, COLOR_NUEVO, CLARIDAD_NUEVO_3, QUILATES_CT_NUEVO);
        Diamante resultDiamanteVigente = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVigenteVO);

        assertNotNull(resultDiamanteVigente);
        assertEquals(CORTE_NUEVO, resultDiamanteVigente.getCorte());
        assertEquals(COLOR_NUEVO, resultDiamanteVigente.getColor());
        assertEquals(CLARIDAD_NUEVO_3, resultDiamanteVigente.getClaridad());
        assertEquals(TAMANIO_INFERIOR_NUEVO, resultDiamanteVigente.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_NUEVO, resultDiamanteVigente.getTamanioSuperior());
        assertEquals(PRECIO_NUEVO_3_PESOS, resultDiamanteVigente.getPrecio());

        List<ListadoValorComercialDiamanteJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() > 0);
        assertEquals(tamanioHistListadoInicial + 1, histListadoInicialVuelta.size());
    }*/

    /**
     * Utilizado para restaurar el listado anterior de valores comerciales del diamante (con datos iniciales).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void restaurarListadoAnteriorTest01() {
        List<ListadoValorComercialDiamanteJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() > 0);

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() > 0);
        int tamanioHistListadoInicial = histListadoInicialIda.size();

        ListadoValorComercialDiamante listadoVigente = valorComercialDiamanteRepository.consultarListadoVigente();
        /*ListadoValorComercialDiamante result = listadoVigente.restaurarAnterior();

        assertNotNull(result);
        assertNotNull(result.getFechaCarga());
        assertNotNull(result.getFechaListado());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 4);*/

        List<ListadoValorComercialDiamanteJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() > 0);
        assertEquals(tamanioHistListadoInicial, histListadoInicialVuelta.size());
    }

    /**
     * Utilizado para restaurar el listado de valores comerciales del diamante que corresponda a la fecha de
     * vigencia indicada (fecha de vigencia anterior a fecha actual y con datos iniciales).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void restaurarListadoPorFechaVigenciaTest01() {
        List<ListadoValorComercialDiamanteJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() > 0);

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() > 0);
        int tamanioHistListadoInicial = histListadoInicialIda.size();

        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(sdf.parse(FECHA_VIGENCIA));
        } catch (ParseException e) {
            LOGGER.error("Ocurrio una excepcion inesperada al realizar la operacion. {}", e.getMessage());
            fail();
        }

        LocalDate fechaVigencia = LocalDate.fromDateFields(calendar.getTime());
        ListadoValorComercialDiamante listadoVigente = valorComercialDiamanteRepository.consultarListadoVigente();
        /*ListadoValorComercialDiamante result = listadoVigente.restaurarPorFecha(fechaVigencia);

        assertNotNull(result);
        assertNotNull(result.getFechaCarga());
        assertNotNull(result.getFechaListado());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 4);*/

        List<ListadoValorComercialDiamanteJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() > 0);
        assertEquals(tamanioHistListadoInicial, histListadoInicialVuelta.size());
    }

}
