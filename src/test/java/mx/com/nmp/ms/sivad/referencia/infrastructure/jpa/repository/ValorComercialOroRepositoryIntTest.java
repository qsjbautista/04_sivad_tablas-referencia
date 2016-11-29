/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoSinElementosException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.OroVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialOroJPA;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Clase de prueba para el repositorio ValorComercialOroRepository.
 *
 * @author ngonzalez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ValorComercialOroRepositoryIntTest {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialOroRepositoryIntTest.class);

    private static final Integer CALIDAD_ORO_EXISTE = 14;
    private static final Integer CALIDAD_ORO_NO_EXISTE = 2;
    private static final Integer CALIDAD_ORO_NUEVO_1 = 11;
    private static final Integer CALIDAD_ORO_NUEVO_2 = 13;
    private static final Integer CALIDAD_ORO_NUEVO_3 = 15;
    private static final String COLOR_ORO_EXISTE = "Amarillo";
    private static final String COLOR_ORO_NO_EXISTE = "Verde";
    private static final String COLOR_ORO_NUEVO = "Morado";
    private static final String FECHA_VIGENCIA = "2016-11-21";
    private static final String FORMATO_FECHA = "yyyy-MM-dd";

    private static final BigDecimal PRECIO_ORO_EXISTE = new BigDecimal(150.25);
    private static final BigDecimal PRECIO_ORO_NUEVO_1 = new BigDecimal(50.25);
    private static final BigDecimal PRECIO_ORO_NUEVO_2 = new BigDecimal(60.25);
    private static final BigDecimal PRECIO_ORO_NUEVO_3 = new BigDecimal(70.25);

    /**
     * Referencia al repositorio de ValorComercialOroRepository.
     */
    @Inject
    private ValorComercialOroRepository valorComercialOroRepository;

    /**
     * Referencia al repositorio de ValorComercialOroJPARepository.
     */
    @Inject
    private ListadoValorComercialOroJPARepository jpaRepository;

    /**
     * Referencia al repositorio de HistListadoValorComercialOroJPARepository.
     */
    @Inject
    private HistListadoValorComercialOroJPARepository histJPARepository;



    // METODOS

    /**
     * Utilizado para consultar un valor por gramo de oro, cuyo color y calidad no existen.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    public void obtenerValorGramoOroTest01() {
        OroVO oroVO = new OroVO(COLOR_ORO_NO_EXISTE, CALIDAD_ORO_NO_EXISTE);
        valorComercialOroRepository.consultarOroVigente(oroVO);
    }

    /**
     * Utilizado para consultar un valor por gramo de oro, cuyo color no existe.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    public void obtenerValorGramoOroTest02() {
        OroVO oroVO = new OroVO(COLOR_ORO_NO_EXISTE, CALIDAD_ORO_EXISTE);
        valorComercialOroRepository.consultarOroVigente(oroVO);
    }

    /**
     * Utilizado para consultar un valor por gramo de oro, cuya calidad no existe.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    public void obtenerValorGramoOroTest03() {
        OroVO oroVO = new OroVO(COLOR_ORO_EXISTE, CALIDAD_ORO_NO_EXISTE);
        valorComercialOroRepository.consultarOroVigente(oroVO);
    }

    /**
     * Utilizado para consultar un valor por gramo de oro, cuyo color y calidad existen.
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_oro-h2.sql")
    public void obtenerValorGramoOroTest04() {
        OroVO oroVO = new OroVO(COLOR_ORO_EXISTE, CALIDAD_ORO_EXISTE);
        Oro result = valorComercialOroRepository.consultarOroVigente(oroVO);

        assertNotNull(result);
        assertEquals(COLOR_ORO_EXISTE, result.getColor());
        assertEquals(CALIDAD_ORO_EXISTE, result.getCalidad());
        assertNotNull(result.obtenerValorGramo());
        assertEquals(PRECIO_ORO_EXISTE, result.obtenerValorGramo());
    }

    /**
     * Utilizado para consultar el listado de valores comerciales del oro vigente (sin datos).
     */
    @Test(expected = ListadoValorGramoNoEncontradoException.class)
    @Transactional
    public void consultarListadoVigenteTest01() {
        valorComercialOroRepository.consultarListadoVigente();
    }

    /**
     * Utilizado para consultar el listado de valores comerciales del oro vigente (con datos).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_oro-h2.sql")
    public void consultarListadoVigenteTest02() {
        ListadoValorComercialOro result = valorComercialOroRepository.consultarListadoVigente();

        assertNotNull(result);
        assertNotNull(result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 4);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del oro de la fecha de vigencia indicada
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

        valorComercialOroRepository.consultarListadoPorFechaVigencia(fechaFutura);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del oro de la fecha de vigencia indicada
     * (fecha de vigencia anterior a fecha actual y sin datos).
     */
    @Test(expected = ListadoValorGramoNoEncontradoException.class)
    @Transactional
    public void consultarListadoPorFechaVigenciaTest02() {
        LocalDate fecha = new LocalDate();
        valorComercialOroRepository.consultarListadoPorFechaVigencia(fecha);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del oro de la fecha de vigencia indicada
     * (fecha de vigencia anterior a fecha actual y con datos).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_oro-h2.sql")
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
        Set<ListadoValorComercialOro> result =
            valorComercialOroRepository.consultarListadoPorFechaVigencia(fechaVigencia);

        assertNotNull(result);
        assertTrue(result.size() == 2);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del oro (con un listado vacío).
     */
    @Test(expected = ListadoValorGramoSinElementosException.class)
    @Transactional
    public void actualizarListadoTest01() {
        Set<Oro> valoresComerciales = new HashSet<>();
        ListadoValorComercialOro listado = ListadoValorComercialOroFactory.create(valoresComerciales);
        valorComercialOroRepository.actualizarListado(listado);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del oro (sin datos iniciales).
     */
    @Test
    @Transactional
    public void actualizarListadoTest02() {
        List<ListadoValorComercialOroJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() == 0);

        List<HistListadoValorComercialOroJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() == 0);

        Set<Oro> valoresComerciales = new HashSet<>();
        Oro oro1 = OroFactory.create(COLOR_ORO_NUEVO, CALIDAD_ORO_NUEVO_1, PRECIO_ORO_NUEVO_1);
        Oro oro2 = OroFactory.create(COLOR_ORO_NUEVO, CALIDAD_ORO_NUEVO_2, PRECIO_ORO_NUEVO_2);
        Oro oro3 = OroFactory.create(COLOR_ORO_NUEVO, CALIDAD_ORO_NUEVO_3, PRECIO_ORO_NUEVO_3);
        valoresComerciales.add(oro1);
        valoresComerciales.add(oro2);
        valoresComerciales.add(oro3);

        ListadoValorComercialOro listado = ListadoValorComercialOroFactory.create(valoresComerciales);
        valorComercialOroRepository.actualizarListado(listado);

        ListadoValorComercialOro resultListadoVigente = valorComercialOroRepository.consultarListadoVigente();

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getUltimaActualizacion());
        assertNotNull(resultListadoVigente.getValoresComerciales());
        assertFalse(resultListadoVigente.getValoresComerciales().isEmpty());
        assertTrue(resultListadoVigente.getValoresComerciales().size() == 3);

        OroVO oroVigenteVO = new OroVO(COLOR_ORO_NUEVO, CALIDAD_ORO_NUEVO_3);
        Oro resultOroVigente = valorComercialOroRepository.consultarOroVigente(oroVigenteVO);

        assertNotNull(resultOroVigente);
        assertEquals(COLOR_ORO_NUEVO, resultOroVigente.getColor());
        assertEquals(CALIDAD_ORO_NUEVO_3, resultOroVigente.getCalidad());
        assertNotNull(resultOroVigente.obtenerValorGramo());
        assertEquals(PRECIO_ORO_NUEVO_3, resultOroVigente.obtenerValorGramo());

        List<ListadoValorComercialOroJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialOroJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() == 0);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del oro (con datos iniciales).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_oro-h2.sql")
    public void actualizarListadoTest03() {
        List<ListadoValorComercialOroJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() > 0);

        List<HistListadoValorComercialOroJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() > 0);
        int tamanioHistListadoInicial = histListadoInicialIda.size();

        Set<Oro> valoresComerciales = new HashSet<>();
        Oro oro1 = OroFactory.create(COLOR_ORO_NUEVO, CALIDAD_ORO_NUEVO_1, PRECIO_ORO_NUEVO_1);
        Oro oro2 = OroFactory.create(COLOR_ORO_NUEVO, CALIDAD_ORO_NUEVO_2, PRECIO_ORO_NUEVO_2);
        Oro oro3 = OroFactory.create(COLOR_ORO_NUEVO, CALIDAD_ORO_NUEVO_3, PRECIO_ORO_NUEVO_3);
        valoresComerciales.add(oro1);
        valoresComerciales.add(oro2);
        valoresComerciales.add(oro3);

        ListadoValorComercialOro listado = ListadoValorComercialOroFactory.create(valoresComerciales);
        valorComercialOroRepository.actualizarListado(listado);

        ListadoValorComercialOro resultListadoVigente = valorComercialOroRepository.consultarListadoVigente();

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getUltimaActualizacion());
        assertNotNull(resultListadoVigente.getValoresComerciales());
        assertFalse(resultListadoVigente.getValoresComerciales().isEmpty());
        assertTrue(resultListadoVigente.getValoresComerciales().size() == 3);

        OroVO oroVigenteVO = new OroVO(COLOR_ORO_NUEVO, CALIDAD_ORO_NUEVO_3);
        Oro resultOroVigente = valorComercialOroRepository.consultarOroVigente(oroVigenteVO);

        assertNotNull(resultOroVigente);
        assertEquals(COLOR_ORO_NUEVO, resultOroVigente.getColor());
        assertEquals(CALIDAD_ORO_NUEVO_3, resultOroVigente.getCalidad());
        assertNotNull(resultOroVigente.obtenerValorGramo());
        assertEquals(PRECIO_ORO_NUEVO_3, resultOroVigente.obtenerValorGramo());

        List<ListadoValorComercialOroJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialOroJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() > 0);
        assertEquals(tamanioHistListadoInicial + 1, histListadoInicialVuelta.size());
    }

}
