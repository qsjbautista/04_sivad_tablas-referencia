/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.model;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetalFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.MetalFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.MetalVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.HistListadoValorComercialMetalJPARepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.ListadoValorComercialMetalJPARepository;
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
 * Clase de prueba utilizada para validar el comportamiento de la entidad de dominio Metal.
 *
 * @author ngonzalez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class MetalIntTest {

    private static final String CALIDAD = "0.725";
    private static final String CALIDAD_NUEVA_1 = "0.225";
    private static final String CALIDAD_NUEVA_2 = "0.325";
    private static final String CALIDAD_NUEVA_3 = "0.425";
    private static final String TIPO_METAL = "Plata";
    private static final String TIPO_METAL_NUEVO = "Acero";
    private static final BigDecimal PRECIO = new BigDecimal(150.250).setScale(3, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_1 = new BigDecimal(50.250).setScale(3, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_2 = new BigDecimal(60.250).setScale(3, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal PRECIO_NUEVO_3 = new BigDecimal(70.250).setScale(3, BigDecimal.ROUND_HALF_UP);

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
     * Utilizado para crear una entidad Metal a través del factory con las siguientes características:
     *
     * METAL - NULO
     * CALIDAD - NULO
     * PRECIO - NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearMetalTest01() {
        MetalFactory.create(null, null, null);
    }

    /**
     * Utilizado para crear una entidad Metal a través del factory con las siguientes características:
     *
     * METAL - NULO
     * CALIDAD - NO NULO
     * PRECIO - NO NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearMetalTest02() {
        MetalFactory.create(null, CALIDAD, PRECIO);
    }

    /**
     * Utilizado para crear una entidad Metal a través del factory con las siguientes características:
     *
     * METAL - NO NULO
     * CALIDAD - NULO
     * PRECIO - NO NULO
     */
    @Test
    public void crearMetalTest03() {
        Metal result = MetalFactory.create(TIPO_METAL, null, PRECIO);

        assertNotNull(result);
        assertEquals(TIPO_METAL, result.getMetal());
        assertEquals(null, result.getCalidad());
        assertNotNull(result.obtenerValorGramo());
        assertEquals(PRECIO, result.obtenerValorGramo());
    }

    /**
     * Utilizado para crear una entidad Metal a través del factory con las siguientes características:
     *
     * METAL - NO NULO
     * CALIDAD - NO NULO
     * PRECIO - NULO
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearMetalTest04() {
        MetalFactory.create(TIPO_METAL, CALIDAD, null);
    }

    /**
     * Utilizado para crear una entidad Metal a través del factory con las siguientes características:
     *
     * METAL - NO NULO
     * CALIDAD - NO NULO
     * PRECIO - NO NULO
     */
    @Test
    public void crearMetalTest05() {
        Metal result = MetalFactory.create(TIPO_METAL, CALIDAD, PRECIO);

        assertNotNull(result);
        assertEquals(TIPO_METAL, result.getMetal());
        assertEquals(CALIDAD, result.getCalidad());
        assertNotNull(result.obtenerValorGramo());
        assertEquals(PRECIO, result.obtenerValorGramo());
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialMetalTest01() {
        ListadoValorComercialMetalFactory.create(null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialMetalTest02() {
        Set<Metal> valoresComerciales = new HashSet<>();
        ListadoValorComercialMetalFactory.create(valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test
    public void crearListadoValorComercialMetalTest03() {
        Metal metal = MetalFactory.create(TIPO_METAL, CALIDAD, PRECIO);

        Set<Metal> valoresComerciales = new HashSet<>();
        valoresComerciales.add(metal);
        ListadoValorComercialMetal result = ListadoValorComercialMetalFactory.create(valoresComerciales);

        assertNotNull(result);
        assertNotNull(result.getValoresComerciales());
        assertEquals(1, result.getValoresComerciales().size());
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NULA
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialMetalTest04() {
        ListadoValorComercialMetalFactory.create(null, null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NULA
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialMetalTest05() {
        Set<Metal> valoresComerciales = new HashSet<>();
        ListadoValorComercialMetalFactory.create(null, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NULA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialMetalTest06() {
        ListadoValorComercialMetalFactory.create(DateTime.now(), null);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NO NULA
     * VALORES COMERCIALES - LISTA VACIA
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialMetalTest07() {
        Set<Metal> valoresComerciales = new HashSet<>();
        ListadoValorComercialMetalFactory.create(DateTime.now(), valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA FUTURA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearListadoValorComercialMetalTest08() {
        Metal metal = MetalFactory.create(TIPO_METAL, CALIDAD, PRECIO);

        DateTime fechaFutura = DateTime.now();
        fechaFutura = fechaFutura.plusDays(1);

        Set<Metal> valoresComerciales = new HashSet<>();
        valoresComerciales.add(metal);
        ListadoValorComercialMetalFactory.create(fechaFutura, valoresComerciales);
    }

    /**
     * Utilizado para crear una entidad ListadoValorComercialMetal a través del factory con las
     * siguientes características:
     *
     * ULTIMA ACTUALIZACION - FECHA NO NULA
     * VALORES COMERCIALES - LISTA NO NULA Y CON ELEMENTOS
     */
    @Test
    public void crearListadoValorComercialMetalTest09() {
        Metal metal = MetalFactory.create(TIPO_METAL, CALIDAD, PRECIO);

        DateTime ultimaActualizacion = DateTime.now();

        Set<Metal> valoresComerciales = new HashSet<>();
        valoresComerciales.add(metal);
        ListadoValorComercialMetal result = ListadoValorComercialMetalFactory.create(ultimaActualizacion, valoresComerciales);

        assertNotNull(result);
        assertEquals(ultimaActualizacion, result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertEquals(1, result.getValoresComerciales().size());
    }

    /**
     * Utilizado para consultar el valor comercial por gramo de metal.
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
    public void obtenerValorGramoMetalTest01() {
        MetalVO metalVO = new MetalVO(TIPO_METAL, CALIDAD);
        Metal result = valorComercialMetalRepository.consultarMetalVigente(metalVO);

        assertNotNull(result);
        assertEquals(TIPO_METAL, result.getMetal());
        assertEquals(CALIDAD, result.getCalidad());
        assertNotNull(result.obtenerValorGramo());
        assertEquals(PRECIO, result.obtenerValorGramo());
    }

    /**
     * Utilizado para actualizar el listado de valores comerciales del metal (sin datos iniciales).
     */
    @Test
    @Transactional
    public void actualizarListadoTest01() {
        List<ListadoValorComercialMetalJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() == 0);

        List<HistListadoValorComercialMetalJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() == 0);

        Set<Metal> valoresComerciales = new HashSet<>();
        Metal metal1 = MetalFactory.create(TIPO_METAL, CALIDAD, PRECIO);
        valoresComerciales.add(metal1);

        ListadoValorComercialMetal listado = ListadoValorComercialMetalFactory.create(valoresComerciales);
        listado.actualizar();

        ListadoValorComercialMetal result = valorComercialMetalRepository.consultarListadoVigente();

        assertNotNull(result);
        assertNotNull(result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 1);

        MetalVO metalVigenteVO = new MetalVO(TIPO_METAL, CALIDAD);
        Metal resultMetalVigente = valorComercialMetalRepository.consultarMetalVigente(metalVigenteVO);

        assertNotNull(resultMetalVigente);
        assertEquals(TIPO_METAL, resultMetalVigente.getMetal());
        assertEquals(CALIDAD, resultMetalVigente.getCalidad());
        assertNotNull(resultMetalVigente.obtenerValorGramo());
        assertEquals(PRECIO, resultMetalVigente.obtenerValorGramo());

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
    public void actualizarListadoTest02() {
        List<ListadoValorComercialMetalJPA> listadoInicialIda = jpaRepository.findAll();
        assertNotNull(listadoInicialIda);
        assertTrue(listadoInicialIda.size() > 0);

        List<HistListadoValorComercialMetalJPA> histListadoInicialIda = histJPARepository.findAll();
        assertNotNull(histListadoInicialIda);
        assertTrue(histListadoInicialIda.size() > 0);
        int tamanioHistListadoInicial = histListadoInicialIda.size();

        Set<Metal> valoresComerciales = new HashSet<>();
        Metal metal1 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_NUEVA_1, PRECIO_NUEVO_1);
        Metal metal2 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_NUEVA_2, PRECIO_NUEVO_2);
        Metal metal3 = MetalFactory.create(TIPO_METAL_NUEVO, CALIDAD_NUEVA_3, PRECIO_NUEVO_3);
        valoresComerciales.add(metal1);
        valoresComerciales.add(metal2);
        valoresComerciales.add(metal3);

        ListadoValorComercialMetal listado = ListadoValorComercialMetalFactory.create(valoresComerciales);
        listado.actualizar();

        ListadoValorComercialMetal result = valorComercialMetalRepository.consultarListadoVigente();

        assertNotNull(result);
        assertNotNull(result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
        assertTrue(result.getValoresComerciales().size() == 3);

        MetalVO metalVigenteVO = new MetalVO(TIPO_METAL_NUEVO, CALIDAD_NUEVA_3);
        Metal resultMetalVigente = valorComercialMetalRepository.consultarMetalVigente(metalVigenteVO);

        assertNotNull(resultMetalVigente);
        assertEquals(TIPO_METAL_NUEVO, resultMetalVigente.getMetal());
        assertEquals(CALIDAD_NUEVA_3, resultMetalVigente.getCalidad());
        assertNotNull(resultMetalVigente.obtenerValorGramo());
        assertEquals(PRECIO_NUEVO_3, resultMetalVigente.obtenerValorGramo());

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
