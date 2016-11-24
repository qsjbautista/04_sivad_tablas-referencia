/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private static final String COLOR_ORO_EXISTE = "Amarillo";
    private static final String COLOR_ORO_NO_EXISTE = "Verde";
    private static final String FECHA_VIGENCIA = "2016-11-23";
    private static final String FORMATO_FECHA = "yyyy-MM-dd";

    private static final BigDecimal PRECIO_ORO_EXISTE = new BigDecimal(150.25);

    /**
     * Referencia al repositorio de ValorComercialOroRepository.
     */
    @Inject
    private ValorComercialOroRepository valorComercialOroRepository;



    // METODOS

    /**
     * Utilizado para consultar un valor por gramo de oro, cuyo color y calidad no existen.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    public void obtenerValorGramoOroTest01() {
        Oro oro = OroFactory.create(COLOR_ORO_NO_EXISTE, CALIDAD_ORO_NO_EXISTE);
        valorComercialOroRepository.consultarOroVigente(oro);
    }

    /**
     * Utilizado para consultar un valor por gramo de oro, cuyo color no existe.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    public void obtenerValorGramoOroTest02() {
        Oro oro = OroFactory.create(COLOR_ORO_NO_EXISTE, CALIDAD_ORO_EXISTE);
        valorComercialOroRepository.consultarOroVigente(oro);
    }

    /**
     * Utilizado para consultar un valor por gramo de oro, cuya calidad no existe.
     */
    @Test(expected = ValorGramoNoEncontradoException.class)
    @Transactional
    public void obtenerValorGramoOroTest03() {
        Oro oro = OroFactory.create(COLOR_ORO_EXISTE, CALIDAD_ORO_NO_EXISTE);
        valorComercialOroRepository.consultarOroVigente(oro);
    }

    /**
     * Utilizado para consultar un valor por gramo de oro, cuyo color y calidad existen.
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-valor-comercial-oro-h2.sql")
    public void obtenerValorGramoOroTest04() {
        Oro oro = OroFactory.create(COLOR_ORO_EXISTE, CALIDAD_ORO_EXISTE);
        Oro result = valorComercialOroRepository.consultarOroVigente(oro);

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
    @Sql("/bd/test-data-valor-comercial-oro-h2.sql")
    public void consultarListadoVigenteTest02() {
        ListadoValorComercialOro result = valorComercialOroRepository.consultarListadoVigente();

        assertNotNull(result);
        assertNotNull(result.getUltimaActualizacion());
        assertNotNull(result.getValoresComerciales());
        assertFalse(result.getValoresComerciales().isEmpty());
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

//    /**
//     * Utilizado para consultar los listados de valores comerciales del oro de la fecha de vigencia indicada
//     * (fecha de vigencia anterior a fecha actual y con datos).
//     */
//    @Test
//    @Transactional
//    @Sql("/bd/test-data-valor-comercial-oro-h2.sql")
//    public void consultarListadoPorFechaVigenciaTest03() {
//        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
//        Calendar calendar = Calendar.getInstance();
//
//        try {
//            calendar.setTime(sdf.parse(FECHA_VIGENCIA));
//        } catch (ParseException e) {
//            LOGGER.error("Ocurrio una excepcion inesperada al realizar la operacion. {}", e.getMessage());
//            fail();
//        }
//
//        LocalDate fechaVigencia = LocalDate.fromDateFields(calendar.getTime());
//        List<ListadoValorComercialOro> result =
//            valorComercialOroRepository.consultarListadoPorFechaVigencia(fechaVigencia);
//
//        assertNotNull(result);
//        assertTrue(result.size() > 1);
//    }

}
