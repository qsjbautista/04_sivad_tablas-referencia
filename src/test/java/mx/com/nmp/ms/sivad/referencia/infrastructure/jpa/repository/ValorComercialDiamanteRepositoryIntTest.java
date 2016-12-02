/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorComercialSinElementosException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.DiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialDiamanteJPA;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Clase de prueba para el repositorio ValorComercialDiamanteRepository.
 *
 * @author ngonzalez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ValorComercialDiamanteRepositoryIntTest {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialDiamanteRepositoryIntTest.class);

    private static final String CORTE_EXISTE = "Oval";
    private static final String CORTE_NO_EXISTE = "Rombo";
    private static final String CORTE_NUEVO = "Esmeralda";
    private static final String COLOR_EXISTE = "D";
    private static final String COLOR_NO_EXISTE = "Z";
    private static final String COLOR_NUEVO = "K";
    private static final String CLARIDAD_EXISTE = "VS1";
    private static final String CLARIDAD_NO_EXISTE = "Z1";
    private static final String CLARIDAD_NUEVO_1 = "SI1";
    private static final String CLARIDAD_NUEVO_2 = "SI2";
    private static final String CLARIDAD_NUEVO_3 = "SI3";
    private static final String FECHA_VIGENCIA = "2016-11-21";
    private static final String FECHA_VIGENCIA_VIGENTES = "2016-11-23";
    private static final String FORMATO_FECHA = "yyyy-MM-dd";

    private static final BigDecimal QUILATES_CT_EXISTE = new BigDecimal(0.92D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_LIMITE_INFERIOR_EXISTE = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_LIMITE_SUPERIOR_EXISTE = new BigDecimal(0.99D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_NO_EXISTE = new BigDecimal(100.00D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_LIMITE_INFERIOR_NO_EXISTE = new BigDecimal(0.89D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_LIMITE_SUPERIOR_NO_EXISTE = new BigDecimal(1.00D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal QUILATES_CT_NUEVO = new BigDecimal(1.35D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_EXISTE = new BigDecimal(73.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_1 = new BigDecimal(40.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_2 = new BigDecimal(36.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_3 = new BigDecimal(30.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_INFERIOR_EXISTE = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_INFERIOR_NUEVO = new BigDecimal(1.00D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_SUPERIOR_EXISTE = new BigDecimal(0.99D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal TAMANIO_SUPERIOR_NUEVO = new BigDecimal(1.49D).setScale(2, BigDecimal.ROUND_HALF_UP);

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



    // METODOS

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - NO EXISTE
     * COLOR - NO EXISTE
     * CLARIDAD - NO EXISTE
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest01() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_NO_EXISTE, COLOR_NO_EXISTE,
            CLARIDAD_NO_EXISTE, QUILATES_CT_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - NO EXISTE
     * COLOR - NO EXISTE
     * CLARIDAD - NO EXISTE
     * QUILATES CT - SI EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest02() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_NO_EXISTE, COLOR_NO_EXISTE,
            CLARIDAD_NO_EXISTE, QUILATES_CT_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - NO EXISTE
     * COLOR - NO EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest03() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_NO_EXISTE, COLOR_NO_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - NO EXISTE
     * COLOR - NO EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - SI EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest04() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_NO_EXISTE, COLOR_NO_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - NO EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - NO EXISTE
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest05() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_NO_EXISTE, COLOR_EXISTE,
            CLARIDAD_NO_EXISTE, QUILATES_CT_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - NO EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - NO EXISTE
     * QUILATES CT - SI EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest06() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_NO_EXISTE, COLOR_EXISTE,
            CLARIDAD_NO_EXISTE, QUILATES_CT_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - NO EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest07() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_NO_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - NO EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - SI EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest08() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_NO_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - NO EXISTE
     * CLARIDAD - NO EXISTE
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest09() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_NO_EXISTE,
            CLARIDAD_NO_EXISTE, QUILATES_CT_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - NO EXISTE
     * CLARIDAD - NO EXISTE
     * QUILATES CT - SI EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest10() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_NO_EXISTE,
            CLARIDAD_NO_EXISTE, QUILATES_CT_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - NO EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest11() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_NO_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - NO EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - SI EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest12() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_NO_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - NO EXISTE
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest13() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_NO_EXISTE, QUILATES_CT_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - NO EXISTE
     * QUILATES CT - SI EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest14() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_NO_EXISTE, QUILATES_CT_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - NO EXISTE
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest15() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - SI EXISTE (se encuentra dentro del rango)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest16() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_EXISTE);
        Diamante result = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);

        assertNotNull(result);
        assertEquals(CORTE_EXISTE, result.getCorte());
        assertEquals(COLOR_EXISTE, result.getColor());
        assertEquals(CLARIDAD_EXISTE, result.getClaridad());
        assertEquals(TAMANIO_INFERIOR_EXISTE, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_EXISTE, result.getTamanioSuperior());
        assertEquals(PRECIO_EXISTE, result.getPrecio());
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - SI EXISTE (se encuentra en el límite inferior del rango)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest17() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_LIMITE_INFERIOR_EXISTE);
        Diamante result = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);

        assertNotNull(result);
        assertEquals(CORTE_EXISTE, result.getCorte());
        assertEquals(COLOR_EXISTE, result.getColor());
        assertEquals(CLARIDAD_EXISTE, result.getClaridad());
        assertEquals(TAMANIO_INFERIOR_EXISTE, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_EXISTE, result.getTamanioSuperior());
        assertEquals(PRECIO_EXISTE, result.getPrecio());
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - SI EXISTE (se encuentra en el límite superior del rango)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest18() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_LIMITE_SUPERIOR_EXISTE);
        Diamante result = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);

        assertNotNull(result);
        assertEquals(CORTE_EXISTE, result.getCorte());
        assertEquals(COLOR_EXISTE, result.getColor());
        assertEquals(CLARIDAD_EXISTE, result.getClaridad());
        assertEquals(TAMANIO_INFERIOR_EXISTE, result.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_EXISTE, result.getTamanioSuperior());
        assertEquals(PRECIO_EXISTE, result.getPrecio());
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - NO EXISTE (se encuentra por debajo del límite inferior del rango)
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest19() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_LIMITE_INFERIOR_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - NO EXISTE (se encuentra por encima del límite superior del rango)
     */
    @Test(expected = ValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialDiamanteTest20() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_LIMITE_SUPERIOR_NO_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el valor comercial de un diamante cuyas características son:
     * CORTE - SI EXISTE
     * COLOR - SI EXISTE
     * CLARIDAD - SI EXISTE
     * QUILATES CT - SI EXISTE (se encuentra dentro del rango)
     *
     * Existe más de un registro en la BD con las mismas características.
     */
    @Test(expected = IncorrectResultSizeDataAccessException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante_02-h2.sql")
    public void obtenerValorComercialDiamanteTest21() {
        DiamanteVO diamanteVO = new DiamanteVO(CORTE_EXISTE, COLOR_EXISTE,
            CLARIDAD_EXISTE, QUILATES_CT_EXISTE);
        valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
    }

    /**
     * Utilizado para consultar el listado vigente de valores comerciales del diamante (sin datos).
     */
    @Test(expected = ListadoValorComercialNoEncontradoException.class)
    @Transactional
    public void consultarListadoVigenteTest01() {
        valorComercialDiamanteRepository.consultarListadoVigente();
    }

    /**
     * Utilizado para consultar el listado vigente de valores comerciales del diamante (con datos).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void consultarListadoVigenteTest02() {
        ListadoValorComercialDiamante result = valorComercialDiamanteRepository.consultarListadoVigente();

        assertNotNull(result);
        assertNotNull(result.getFechaCarga());
        assertNotNull(result.getFechaListado());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 10);

        // TODO - TMP
        LOGGER.info("| {} | {} | {} | {} | {} | {} |", "CORTE", "COLOR", "CLARIDAD",
            "TAMANIO INFERIOR", "TAMANIO SUPERIOR", "PRECIO");
        for (Diamante d : result.getValoresComerciales()){

            LOGGER.info("| {} | {} | {} | {} | {} | {} |", d.getCorte(), d.getColor(), d.getClaridad(),
                d.getTamanioInferior(), d.getTamanioSuperior(), d.getPrecio());
        }
    }

    /**
     * Utilizado para consultar el listado vigente de valores comerciales del diamante (con datos).
     *
     * Existe más de un listado vigente en la BD.
     */
    @Test(expected = IncorrectResultSizeDataAccessException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante_02-h2.sql")
    public void consultarListadoVigenteTest03() {
        valorComercialDiamanteRepository.consultarListadoVigente();
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del diamante de la fecha de vigencia indicada
     * (fecha de vigencia posterior a fecha actual).
     */
    @Test(expected = FechaVigenciaFuturaException.class)
    @Transactional
    public void consultarListadoPorFechaVigenciaTest01() {
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DATE, 1);
        LocalDate fechaFutura = LocalDate.fromDateFields(calendar.getTime());

        valorComercialDiamanteRepository.consultarListadoPorFechaVigencia(fechaFutura);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del diamante de la fecha de vigencia indicada
     * (fecha de vigencia anterior a fecha actual y sin datos).
     */
    @Test(expected = ListadoValorComercialNoEncontradoException.class)
    @Transactional
    public void consultarListadoPorFechaVigenciaTest02() {
        LocalDate fecha = new LocalDate();
        valorComercialDiamanteRepository.consultarListadoPorFechaVigencia(fecha);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del diamante de la fecha de vigencia indicada
     * (fecha de vigencia anterior a fecha actual y con datos - coincidencia históricos).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void consultarListadoPorFechaVigenciaTest03() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(sdf.parse(FECHA_VIGENCIA));
        } catch (ParseException e) {
            LOGGER.error("Ocurrio una excepcion inesperada al realizar la operacion. {}", e.getMessage());
            fail();
        }

        LocalDate fechaVigencia = LocalDate.fromDateFields(calendar.getTime());
        Set<ListadoValorComercialDiamante> result =
            valorComercialDiamanteRepository.consultarListadoPorFechaVigencia(fechaVigencia);

        assertNotNull(result);
        assertTrue(result.size() == 2);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del diamante de la fecha de vigencia indicada
     * (fecha de vigencia anterior a fecha actual y con datos - coincidencia vigentes).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void consultarListadoPorFechaVigenciaTest04() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(sdf.parse(FECHA_VIGENCIA_VIGENTES));
        } catch (ParseException e) {
            LOGGER.error("Ocurrio una excepcion inesperada al realizar la operacion. {}", e.getMessage());
            fail();
        }

        LocalDate fechaVigencia = LocalDate.fromDateFields(calendar.getTime());
        Set<ListadoValorComercialDiamante> result =
            valorComercialDiamanteRepository.consultarListadoPorFechaVigencia(fechaVigencia);

        assertNotNull(result);
        assertTrue(result.size() == 1);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del diamante (con un listado vacío).
     */
    @Test(expected = ListadoValorComercialSinElementosException.class)
    @Transactional
    public void actualizarListadoTest01() {
        Set<Diamante> valoresComerciales = new HashSet<>();
        ListadoValorComercialDiamante listado = ListadoValorComercialDiamanteFactory.create(LocalDate.now(), valoresComerciales);
        valorComercialDiamanteRepository.actualizarListado(listado);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del diamante (sin datos iniciales).
     */
    @Test
    @Transactional
    public void actualizarListadoTest02() {
        List<ListadoValorComercialDiamanteJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() == 0);

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() == 0);

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
        valorComercialDiamanteRepository.actualizarListado(listado);

        ListadoValorComercialDiamante resultListadoVigente = valorComercialDiamanteRepository.consultarListadoVigente();

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getFechaCarga());
        assertNotNull(resultListadoVigente.getFechaListado());
        assertNotNull(resultListadoVigente.getValoresComerciales());
        assertFalse(resultListadoVigente.getValoresComerciales().isEmpty());
        assertTrue(resultListadoVigente.getValoresComerciales().size() == 3);

        DiamanteVO diamanteVigenteVO = new DiamanteVO(CORTE_NUEVO, COLOR_NUEVO, CLARIDAD_NUEVO_3, QUILATES_CT_NUEVO);
        Diamante resultDiamanteVigente = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVigenteVO);

        assertNotNull(resultDiamanteVigente);
        assertEquals(CORTE_NUEVO, resultDiamanteVigente.getCorte());
        assertEquals(COLOR_NUEVO, resultDiamanteVigente.getColor());
        assertEquals(CLARIDAD_NUEVO_3, resultDiamanteVigente.getClaridad());
        assertEquals(TAMANIO_INFERIOR_NUEVO, resultDiamanteVigente.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_NUEVO, resultDiamanteVigente.getTamanioSuperior());
        assertEquals(PRECIO_NUEVO_3, resultDiamanteVigente.getPrecio());

        List<ListadoValorComercialDiamanteJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() == 0);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del diamante (con datos iniciales).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void actualizarListadoTest03() {
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
        valorComercialDiamanteRepository.actualizarListado(listado);

        ListadoValorComercialDiamante resultListadoVigente = valorComercialDiamanteRepository.consultarListadoVigente();

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getFechaCarga());
        assertNotNull(resultListadoVigente.getFechaListado());
        assertNotNull(resultListadoVigente.getValoresComerciales());
        assertFalse(resultListadoVigente.getValoresComerciales().isEmpty());
        assertTrue(resultListadoVigente.getValoresComerciales().size() == 3);

        DiamanteVO diamanteVigenteVO = new DiamanteVO(CORTE_NUEVO, COLOR_NUEVO, CLARIDAD_NUEVO_3, QUILATES_CT_NUEVO);
        Diamante resultDiamanteVigente = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVigenteVO);

        assertNotNull(resultDiamanteVigente);
        assertEquals(CORTE_NUEVO, resultDiamanteVigente.getCorte());
        assertEquals(COLOR_NUEVO, resultDiamanteVigente.getColor());
        assertEquals(CLARIDAD_NUEVO_3, resultDiamanteVigente.getClaridad());
        assertEquals(TAMANIO_INFERIOR_NUEVO, resultDiamanteVigente.getTamanioInferior());
        assertEquals(TAMANIO_SUPERIOR_NUEVO, resultDiamanteVigente.getTamanioSuperior());
        assertEquals(PRECIO_NUEVO_3, resultDiamanteVigente.getPrecio());

        List<ListadoValorComercialDiamanteJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() > 0);
        assertEquals(tamanioHistListadoInicial + 1, histListadoInicialVuelta.size());
    }

    /**
     * Utilizado para restaurar el listado anterior de valores comerciales del diamante (sin datos iniciales).
     */
    @Test(expected = ListadoValorComercialNoEncontradoException.class)
    @Transactional
    public void restaurarListadoAnteriorTest01() {
        valorComercialDiamanteRepository.restaurarListadoAnterior();
    }

    /**
     * Utilizado para restaurar el listado anterior de valores comerciales del diamante (con datos iniciales,
     * pero solamente con información de históricos).
     */
    @Test(expected = ListadoValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante_03-h2.sql")
    public void restaurarListadoAnteriorTest02() {
        valorComercialDiamanteRepository.restaurarListadoAnterior();
    }

    /**
     * Utilizado para restaurar el listado anterior de valores comerciales del diamante (con datos iniciales).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void restaurarListadoAnteriorTest03() {
        List<ListadoValorComercialDiamanteJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() > 0);

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() > 0);
        int tamanioHistListadoInicial = histListadoInicialIda.size();

        ListadoValorComercialDiamante result = valorComercialDiamanteRepository.restaurarListadoAnterior();

        assertNotNull(result);
        assertNotNull(result.getFechaCarga());
        assertNotNull(result.getFechaListado());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 4);

        List<ListadoValorComercialDiamanteJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() > 0);
        assertEquals(tamanioHistListadoInicial + 1, histListadoInicialVuelta.size());
    }

    /**
     * Utilizado para restaurar el listado de valores comerciales del diamante que corresponda a la fecha de
     * vigencia indicada (fecha de vigencia posterior a fecha actual).
     */
    @Test(expected = FechaVigenciaFuturaException.class)
    @Transactional
    public void restaurarListadoPorFechaVigenciaTest01() {
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DATE, 1);
        LocalDate fechaFutura = LocalDate.fromDateFields(calendar.getTime());

        valorComercialDiamanteRepository.restaurarListadoPorFechaVigencia(fechaFutura);
    }

    /**
     * Utilizado para restaurar el listado de valores comerciales del diamante que corresponda a la fecha de
     * vigencia indicada (fecha de vigencia anterior a fecha actual y sin datos iniciales).
     */
    @Test(expected = ListadoValorComercialNoEncontradoException.class)
    @Transactional
    public void restaurarListadoPorFechaVigenciaTest02() {
        LocalDate fecha = new LocalDate();
        valorComercialDiamanteRepository.restaurarListadoPorFechaVigencia(fecha);
    }

    /**
     * Utilizado para restaurar el listado de valores comerciales del diamante que corresponda a la fecha de
     * vigencia indicada (fecha de vigencia anterior a fecha actual y con datos iniciales, pero solamente con
     * información de históricos).
     */
    @Test(expected = ListadoValorComercialNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante_03-h2.sql")
    public void restaurarListadoPorFechaVigenciaTest03() {
        LocalDate fecha = new LocalDate();
        valorComercialDiamanteRepository.restaurarListadoPorFechaVigencia(fecha);
    }

    /**
     * Utilizado para restaurar el listado de valores comerciales del diamante que corresponda a la fecha de
     * vigencia indicada (fecha de vigencia anterior a fecha actual y con datos iniciales).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void restaurarListadoPorFechaVigenciaTest04() {
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
        ListadoValorComercialDiamante result =
            valorComercialDiamanteRepository.restaurarListadoPorFechaVigencia(fechaVigencia);

        assertNotNull(result);
        assertNotNull(result.getFechaCarga());
        assertNotNull(result.getFechaListado());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 4);

        List<ListadoValorComercialDiamanteJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialDiamanteJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() > 0);
        assertEquals(tamanioHistListadoInicial + 1, histListadoInicialVuelta.size());
    }

}
