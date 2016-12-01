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
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetalFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.MetalFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.MetalVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialMetalJPA;
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
 * Clase de prueba para el repositorio ValorComercialMetalRepository.
 *
 * @author ngonzalez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ValorComercialMetalRepositoryIntTest {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialMetalRepositoryIntTest.class);

    private static final String CALIDAD_METAL_EXISTE = "0.725";
    private static final String CALIDAD_METAL_NO_EXISTE = "0.125";
    private static final String CALIDAD_METAL_NUEVO_1 = "0.225";
    private static final String CALIDAD_METAL_NUEVO_2 = "0.325";
    private static final String CALIDAD_METAL_NUEVO_3 = "0.425";
    private static final String TIPO_METAL_EXISTE = "Plata";
    private static final String TIPO_METAL_NO_EXISTE = "Cobre";
    private static final String TIPO_METAL_NUEVO = "Acero";
    private static final String FECHA_VIGENCIA = "2016-11-21";
    private static final String FORMATO_FECHA = "yyyy-MM-dd";

    private static final BigDecimal PRECIO_METAL_EXISTE = new BigDecimal(150.25);
    private static final BigDecimal PRECIO_METAL_NUEVO_1 = new BigDecimal(50.25);
    private static final BigDecimal PRECIO_METAL_NUEVO_2 = new BigDecimal(60.25);
    private static final BigDecimal PRECIO_METAL_NUEVO_3 = new BigDecimal(70.25);

    /**
     * Referencia al repositorio de ValorComercialMetalRepository.
     */
    @Inject
    private ValorComercialMetalRepository valorComercialMetalRepository;

    /**
     * Referencia al repositorio de ValorComercialMetalJPARepository.
     */
    @Inject
    private ListadoValorComercialMetalJPARepository jpaRepository;

    /**
     * Referencia al repositorio de HistListadoValorComercialMetalJPARepository.
     */
    @Inject
    private HistListadoValorComercialMetalJPARepository histJPARepository;



    // METODOS

    /**
     * Utilizado para consultar un valor por gramo de metal, cuyo tipo de metal y calidad no existen.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
    public void obtenerValorGramoMetalTest01() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_NO_EXISTE, CALIDAD_METAL_NO_EXISTE);
        valorComercialMetalRepository.consultarMetalVigente(metalVO);
    }

    /**
     * Utilizado para consultar un valor por gramo de metal, cuyo tipo de metal no existe.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
    public void obtenerValorGramoMetalTest02() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_NO_EXISTE, CALIDAD_METAL_EXISTE);
        valorComercialMetalRepository.consultarMetalVigente(metalVO);
    }

    /**
     * Utilizado para consultar un valor por gramo de metal, cuya calidad no existe.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
    public void obtenerValorGramoMetalTest03() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_EXISTE, CALIDAD_METAL_NO_EXISTE);
        valorComercialMetalRepository.consultarMetalVigente(metalVO);
    }

    /**
     * Utilizado para consultar un valor por gramo de metal, cuyo tipo de metal y calidad existen.
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
    public void obtenerValorGramoMetalTest04() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_EXISTE, CALIDAD_METAL_EXISTE);
        Metal result = valorComercialMetalRepository.consultarMetalVigente(metalVO);

        assertNotNull(result);
        assertEquals(TIPO_METAL_EXISTE, result.getMetal());
        assertEquals(CALIDAD_METAL_EXISTE, result.getCalidad());
        assertNotNull(result.obtenerValorGramo());
        assertEquals(PRECIO_METAL_EXISTE, result.obtenerValorGramo());
    }

    /**
     * Utilizado para consultar el listado de valores comerciales del metal vigente (sin datos).
     */
    @Test(expected = ListadoValorGramoNoEncontradoException.class)
    @Transactional
    public void consultarListadoVigenteTest01() {
        valorComercialMetalRepository.consultarListadoVigente();
    }

    /**
     * Utilizado para consultar el listado de valores comerciales del metal vigente (con datos).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
    public void consultarListadoVigenteTest02() {
        ListadoValorComercialMetal result = valorComercialMetalRepository.consultarListadoVigente();

        assertNotNull(result);
        assertNotNull(result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 4);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del metal de la fecha de vigencia indicada
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

        valorComercialMetalRepository.consultarListadoPorFechaVigencia(fechaFutura);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del metal de la fecha de vigencia indicada
     * (fecha de vigencia anterior a fecha actual y sin datos).
     */
    @Test(expected = ListadoValorGramoNoEncontradoException.class)
    @Transactional
    public void consultarListadoPorFechaVigenciaTest02() {
        LocalDate fecha = new LocalDate();
        valorComercialMetalRepository.consultarListadoPorFechaVigencia(fecha);
    }

    /**
     * Utilizado para consultar los listados de valores comerciales del metal de la fecha de vigencia indicada
     * (fecha de vigencia anterior a fecha actual y con datos).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
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
        Set<ListadoValorComercialMetal> result =
            valorComercialMetalRepository.consultarListadoPorFechaVigencia(fechaVigencia);

        assertNotNull(result);
        assertTrue(result.size() == 2);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del metal (con un listado vacío).
     */
    @Test(expected = ListadoValorGramoSinElementosException.class)
    @Transactional
    public void actualizarListadoTest01() {
        Set<Metal> valoresComerciales = new HashSet<>();
        ListadoValorComercialMetal listado = ListadoValorComercialMetalFactory.create(valoresComerciales);
        valorComercialMetalRepository.actualizarListado(listado);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del metal (sin datos iniciales).
     */
    @Test
    @Transactional
    public void actualizarListadoTest02() {
        List<ListadoValorComercialMetalJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() == 0);

        List<HistListadoValorComercialMetalJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() == 0);

        Set<Metal> valoresComerciales = new HashSet<>();
        Metal metal1 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_METAL_NUEVO_1, PRECIO_METAL_NUEVO_1);
        Metal metal2 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_METAL_NUEVO_2, PRECIO_METAL_NUEVO_2);
        Metal metal3 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_METAL_NUEVO_3, PRECIO_METAL_NUEVO_3);
        valoresComerciales.add(metal1);
        valoresComerciales.add(metal2);
        valoresComerciales.add(metal3);

        ListadoValorComercialMetal listado = ListadoValorComercialMetalFactory.create(valoresComerciales);
        valorComercialMetalRepository.actualizarListado(listado);

        ListadoValorComercialMetal resultListadoVigente = valorComercialMetalRepository.consultarListadoVigente();

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getUltimaActualizacion());
        assertNotNull(resultListadoVigente.getValoresComerciales());
        assertFalse(resultListadoVigente.getValoresComerciales().isEmpty());
        assertTrue(resultListadoVigente.getValoresComerciales().size() == 3);

        MetalVO metalVigenteVO = new MetalVO(TIPO_METAL_NUEVO, CALIDAD_METAL_NUEVO_3);
        Metal resultMetalVigente = valorComercialMetalRepository.consultarMetalVigente(metalVigenteVO);

        assertNotNull(resultMetalVigente);
        assertEquals(TIPO_METAL_NUEVO, resultMetalVigente.getMetal());
        assertEquals(CALIDAD_METAL_NUEVO_3, resultMetalVigente.getCalidad());
        assertNotNull(resultMetalVigente.obtenerValorGramo());
        assertEquals(PRECIO_METAL_NUEVO_3, resultMetalVigente.obtenerValorGramo());

        List<ListadoValorComercialMetalJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialMetalJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() == 0);
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del metal (con datos iniciales).
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
    public void actualizarListadoTest03() {
        List<ListadoValorComercialMetalJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() > 0);

        List<HistListadoValorComercialMetalJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() > 0);
        int tamanioHistListadoInicial = histListadoInicialIda.size();

        Set<Metal> valoresComerciales = new HashSet<>();
        Metal metal1 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_METAL_NUEVO_1, PRECIO_METAL_NUEVO_1);
        Metal metal2 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_METAL_NUEVO_2, PRECIO_METAL_NUEVO_2);
        Metal metal3 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_METAL_NUEVO_3, PRECIO_METAL_NUEVO_3);
        valoresComerciales.add(metal1);
        valoresComerciales.add(metal2);
        valoresComerciales.add(metal3);

        ListadoValorComercialMetal listado = ListadoValorComercialMetalFactory.create(valoresComerciales);
        valorComercialMetalRepository.actualizarListado(listado);

        ListadoValorComercialMetal resultListadoVigente = valorComercialMetalRepository.consultarListadoVigente();

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getUltimaActualizacion());
        assertNotNull(resultListadoVigente.getValoresComerciales());
        assertFalse(resultListadoVigente.getValoresComerciales().isEmpty());
        assertTrue(resultListadoVigente.getValoresComerciales().size() == 3);

        MetalVO metalVigenteVO = new MetalVO(TIPO_METAL_NUEVO, CALIDAD_METAL_NUEVO_3);
        Metal resultMetalVigente = valorComercialMetalRepository.consultarMetalVigente(metalVigenteVO);

        assertNotNull(resultMetalVigente);
        assertEquals(TIPO_METAL_NUEVO, resultMetalVigente.getMetal());
        assertEquals(CALIDAD_METAL_NUEVO_3, resultMetalVigente.getCalidad());
        assertNotNull(resultMetalVigente.obtenerValorGramo());
        assertEquals(PRECIO_METAL_NUEVO_3, resultMetalVigente.obtenerValorGramo());

        List<ListadoValorComercialMetalJPA> listadoInicialVuelta = jpaRepository.findAll();
        assertNotNull(listadoInicialVuelta);
        assertTrue(listadoInicialVuelta.size() > 0);
        assertEquals(1, listadoInicialVuelta.size());

        List<HistListadoValorComercialMetalJPA> histListadoInicialVuelta = histJPARepository.findAll();
        assertNotNull(histListadoInicialVuelta);
        assertTrue(histListadoInicialVuelta.size() > 0);
        assertEquals(tamanioHistListadoInicial + 1, histListadoInicialVuelta.size());
    }

}
