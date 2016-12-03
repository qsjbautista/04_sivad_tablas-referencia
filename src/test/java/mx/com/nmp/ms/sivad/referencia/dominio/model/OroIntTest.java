/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.model;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.OroVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.HistListadoValorComercialOroJPARepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.ListadoValorComercialOroJPARepository;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Clase de prueba utilizada para validar el comportamiento de la entidad de dominio Oro.
 *
 * @author ngonzalez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class OroIntTest {

    private static final String COLOR = "Amarillo";
    private static final String COLOR_NUEVO = "Morado";
    private static final Integer CALIDAD = 14;
    private static final Integer CALIDAD_NUEVA_1 = 11;
    private static final Integer CALIDAD_NUEVA_2 = 13;
    private static final Integer CALIDAD_NUEVA_3 = 15;
    private static final BigDecimal PRECIO = new BigDecimal(150.250).setScale(3, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_1 = new BigDecimal(50.250).setScale(3, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_2 = new BigDecimal(60.250).setScale(3, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_3 = new BigDecimal(70.250).setScale(3, BigDecimal.ROUND_HALF_UP);

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
     * Utilizado para crear una entidad Oro a través del factory con las siguientes características:
     *
     * COLOR - NULO
     * CALIDAD - NULO
     * PRECIO - NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearOroTest01() {
        OroFactory.create(null, null, null);
    }

    /**
     * Utilizado para crear una entidad Oro a través del factory con las siguientes características:
     *
     * COLOR - NULO
     * CALIDAD - NO NULO
     * PRECIO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearOroTest02() {
        OroFactory.create(null, CALIDAD, PRECIO);
    }

    /**
     * Utilizado para crear una entidad Oro a través del factory con las siguientes características:
     *
     * COLOR - NO NULO
     * CALIDAD - NULO
     * PRECIO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearOroTest03() {
        OroFactory.create(COLOR, null, PRECIO);
    }

    /**
     * Utilizado para crear una entidad Oro a través del factory con las siguientes características:
     *
     * COLOR - NO NULO
     * CALIDAD - NO NULO
     * PRECIO - NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearOroTest04() {
        OroFactory.create(COLOR, CALIDAD, null);
    }

    /**
     * Utilizado para crear una entidad Oro a través del factory con las siguientes características:
     *
     * COLOR - NO NULO
     * CALIDAD - NO NULO
     * PRECIO - NO NULO
     */
    @Test
    public void crearOroTest05() {
        Oro result = OroFactory.create(COLOR, CALIDAD, PRECIO);

        assertNotNull(result);
        assertEquals(COLOR, result.getColor());
        assertEquals(CALIDAD, result.getCalidad());
        assertNotNull(result.obtenerValorGramo());
        assertEquals(PRECIO, result.obtenerValorGramo());
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialOroTest01() {
        ListadoValorComercialOroFactory.create(null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialOroTest02() {
        Set<Oro> valoresComerciales = new HashSet<>();
        ListadoValorComercialOroFactory.create(valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test
    public void crearListadoValorComercialOroTest03() {
        Oro oro = OroFactory.create(COLOR, CALIDAD, PRECIO);

        Set<Oro> valoresComerciales = new HashSet<>();
        valoresComerciales.add(oro);
        ListadoValorComercialOro result = ListadoValorComercialOroFactory.create(valoresComerciales);

        assertNotNull(result);
        assertNotNull(result.getValoresComerciales());
        assertEquals(1, result.getValoresComerciales().size());
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NULA
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialOroTest04() {
        ListadoValorComercialOroFactory.create(null, null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NULA
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialOroTest05() {
        Set<Oro> valoresComerciales = new HashSet<>();
        ListadoValorComercialOroFactory.create(null, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialOroTest06() {
        ListadoValorComercialOroFactory.create(DateTime.now(), null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NO NULA
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialOroTest07() {
        Set<Oro> valoresComerciales = new HashSet<>();
        ListadoValorComercialOroFactory.create(DateTime.now(), valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA FUTURA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialOroTest08() {
        Oro oro = OroFactory.create(COLOR, CALIDAD, PRECIO);

        DateTime fechaFutura = DateTime.now();
        fechaFutura = fechaFutura.plusDays(1);

        Set<Oro> valoresComerciales = new HashSet<>();
        valoresComerciales.add(oro);
        ListadoValorComercialOroFactory.create(fechaFutura, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialOro a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test
    public void crearListadoValorComercialOroTest09() {
        Oro oro = OroFactory.create(COLOR, CALIDAD, PRECIO);

        DateTime ultimaActualizacion = DateTime.now();

        Set<Oro> valoresComerciales = new HashSet<>();
        valoresComerciales.add(oro);
        ListadoValorComercialOro result = ListadoValorComercialOroFactory.create(ultimaActualizacion, valoresComerciales);

        assertNotNull(result);
        assertEquals(ultimaActualizacion, result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertEquals(1, result.getValoresComerciales().size());
    }

    /**
     * Utilizado para consultar el valor comercial por gramo de oro.
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_oro-h2.sql")
    public void obtenerValorGramoOroTest01() {
        OroVO oroVO = new OroVO(COLOR, CALIDAD);
        Oro result = valorComercialOroRepository.consultarOroVigente(oroVO);

        assertNotNull(result);
        assertEquals(COLOR, result.getColor());
        assertEquals(CALIDAD, result.getCalidad());
        assertNotNull(result.obtenerValorGramo());
        assertEquals(PRECIO, result.obtenerValorGramo());
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del oro (sin datos iniciales).
     */
    @Test
    @Transactional
    public void actualizarListadoTest01() {
        List<ListadoValorComercialOroJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() == 0);

        List<HistListadoValorComercialOroJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() == 0);

        Set<Oro> valoresComerciales = new HashSet<>();
        Oro oro1 = OroFactory.create(COLOR, CALIDAD, PRECIO);
        valoresComerciales.add(oro1);

        ListadoValorComercialOro listado = ListadoValorComercialOroFactory.create(valoresComerciales);
        listado.actualizar();

        ListadoValorComercialOro result = valorComercialOroRepository.consultarListadoVigente();

        assertNotNull(result);
        assertNotNull(result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 1);

        OroVO oroVigenteVO = new OroVO(COLOR, CALIDAD);
        Oro resultOroVigente = valorComercialOroRepository.consultarOroVigente(oroVigenteVO);

        assertNotNull(resultOroVigente);
        assertEquals(COLOR, resultOroVigente.getColor());
        assertEquals(CALIDAD, resultOroVigente.getCalidad());
        assertNotNull(resultOroVigente.obtenerValorGramo());
        assertEquals(PRECIO, resultOroVigente.obtenerValorGramo());

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
    public void actualizarListadoTest02() {
        List<ListadoValorComercialOroJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() > 0);

        List<HistListadoValorComercialOroJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() > 0);
        int tamanioHistListadoInicial = histListadoInicialIda.size();

        Set<Oro> valoresComerciales = new HashSet<>();
        Oro oro1 = OroFactory.create(COLOR_NUEVO, CALIDAD_NUEVA_1, PRECIO_NUEVO_1);
        Oro oro2 = OroFactory.create(COLOR_NUEVO, CALIDAD_NUEVA_2, PRECIO_NUEVO_2);
        Oro oro3 = OroFactory.create(COLOR_NUEVO, CALIDAD_NUEVA_3, PRECIO_NUEVO_3);
        valoresComerciales.add(oro1);
        valoresComerciales.add(oro2);
        valoresComerciales.add(oro3);

        ListadoValorComercialOro listado = ListadoValorComercialOroFactory.create(valoresComerciales);
        listado.actualizar();

        ListadoValorComercialOro result = valorComercialOroRepository.consultarListadoVigente();

        assertNotNull(result);
        assertNotNull(result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 3);

        OroVO oroVigenteVO = new OroVO(COLOR_NUEVO, CALIDAD_NUEVA_3);
        Oro resultOroVigente = valorComercialOroRepository.consultarOroVigente(oroVigenteVO);

        assertNotNull(resultOroVigente);
        assertEquals(COLOR_NUEVO, resultOroVigente.getColor());
        assertEquals(CALIDAD_NUEVA_3, resultOroVigente.getCalidad());
        assertNotNull(resultOroVigente.obtenerValorGramo());
        assertEquals(PRECIO_NUEVO_3, resultOroVigente.obtenerValorGramo());

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
