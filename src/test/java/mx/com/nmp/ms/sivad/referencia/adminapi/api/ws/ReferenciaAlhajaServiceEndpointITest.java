package mx.com.nmp.ms.sivad.referencia.adminapi.api.ws;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceException;
import mx.com.nmp.ms.sivad.referencia.ws.alhajas.ReferenciaAlhajaService;
import mx.com.nmp.ms.sivad.referencia.ws.alhajas.datatypes.*;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Clase de prueba
 * @author jbautista
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ReferenciaAlhajaServiceEndpointITest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaAlhajaServiceEndpointITest.class);

    @Inject
    ReferenciaAlhajaService referenciaAlhajaServiceEndPoint;

    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_oro-h2.sql")
    public void obtenerValorGramoOroTest(){
        ObtenerValorGramoOroRequest parameters = new ObtenerValorGramoOroRequest();
        parameters.setCalidad("10");
        parameters.setColor("Amarillo");
        ObtenerValorGramoOroResponse response =referenciaAlhajaServiceEndPoint.obtenerValorGramoOro(parameters);
        assertEquals(response.getPrecioPorGramo(), new BigDecimal(100.250).setScale(3, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_metal-h2.sql")
    public void obtenerValorGramMetalTest(){
        ObtenerValorGramoMetalRequest parameters = new ObtenerValorGramoMetalRequest();
        parameters.setMetal("Plata");
        parameters.setCalidad("0.925");
        ObtenerValorGramoMetalResponse response =referenciaAlhajaServiceEndPoint.obtenerValorGramoMetal(parameters);
        assertEquals(response.getPrecioPorGramo(), new BigDecimal(100.250).setScale(3, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void obtenerDesplazamientoTest(){
        ObtenerDesplazamientoRequest parameters = new ObtenerDesplazamientoRequest();
        parameters.setMetal("met1");
        parameters.setCalidad("cal1");
        parameters.setRango("ran1");
        ObtenerDesplazamientoResponse response =referenciaAlhajaServiceEndPoint.obtenerDesplazamiento(parameters);
        assertEquals(response.getLimitesIncremento().getLimiteInferior(), new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP));
        assertEquals(response.getLimitesIncremento().getLimiteSuperior(), new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP));
//        assertEquals(response.getDesplazamiento(), new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void obtenerFactorTest(){
        ObtenerFactorRequest parameters = new ObtenerFactorRequest();
        parameters.setMetal("met1");
        parameters.setCalidad("cal1");
        parameters.setRango("ran1");
        ObtenerFactorResponse response =referenciaAlhajaServiceEndPoint.obtenerFactor(parameters);
        assertEquals(response.getFactor(), new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_rango-h2.sql")
    public void obtenerLimitesIncrementoTest(){
        ObtenerLimitesIncrementoRequest parameters = new ObtenerLimitesIncrementoRequest();
        parameters.setMetal("met1");
        parameters.setCalidad("cal1");
        parameters.setRango("ran1");
        ObtenerLimitesIncrementoResponse response =referenciaAlhajaServiceEndPoint.obtenerLimitesIncremento(parameters);

        assertEquals(response.getLimitesIncremento().getLimiteInferior(), new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP));
        assertEquals(response.getLimitesIncremento().getLimiteSuperior(), new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * Sin todos los parametros requeridos
     */
    @Test(expected = WebServiceException.class)
    public void obtenerTiposHechuraTest1(){
        ObtenerTiposHechuraRequest parameters = new ObtenerTiposHechuraRequest();
        referenciaAlhajaServiceEndPoint.obtenerTiposHechura(parameters);
    }

    /**
     * Con todos los parametros requeridos, sin calidad
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest2(){
        ObtenerTiposHechuraRequest parameters = new ObtenerTiposHechuraRequest();
        parameters.setMetal("PD");
        parameters.setSubramo("AL");
        ObtenerTiposHechuraResponse response = referenciaAlhajaServiceEndPoint.obtenerTiposHechura(parameters);
        assertNotNull(response);
        assertEquals(1, response.getTiposHechura().size());
    }

    /**
     * Con todos los parametros requeridos, y tambien la calidad
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest3(){
        ObtenerTiposHechuraRequest parameters = new ObtenerTiposHechuraRequest();
        parameters.setMetal("AU");
        parameters.setSubramo("AL");
        parameters.setCalidad("8_Q");
        ObtenerTiposHechuraResponse response = referenciaAlhajaServiceEndPoint.obtenerTiposHechura(parameters);
        assertNotNull(response);
        assertEquals(4, response.getTiposHechura().size());
    }

    /**
     * Sin registros de tipos de hechura
     */
    @Test(expected = WebServiceException.class)
    public void obtenerTiposHechuraTest4(){
        ObtenerTiposHechuraRequest parameters = new ObtenerTiposHechuraRequest();
        parameters.setMetal("Plata");
        parameters.setCalidad("0.925");
        referenciaAlhajaServiceEndPoint.obtenerTiposHechura(parameters);
    }
}
