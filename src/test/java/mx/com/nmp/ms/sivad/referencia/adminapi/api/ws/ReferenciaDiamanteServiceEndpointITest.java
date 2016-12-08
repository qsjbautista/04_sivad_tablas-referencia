package mx.com.nmp.ms.sivad.referencia.adminapi.api.ws;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.ReferenciaDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerModificadorRequest;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerModificadorResponse;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerValorComercialRequest;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerValorComercialResponse;
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

/**
 * Clase de prueba para Tabla Diamantes.
 *
 * @author jbautista
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ReferenciaDiamanteServiceEndpointITest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaDiamanteServiceEndpointITest.class);

    @Inject
    ReferenciaDiamanteService referenciaDiamanteServiceEndPoint;

    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialTest() {
        ObtenerValorComercialRequest parameters = new ObtenerValorComercialRequest();
        parameters.setCorte("Oval");
        parameters.setColor("D");
        parameters.setClaridad("IF");
        parameters.setQuilatesCt(new BigDecimal(0.95).setScale(2, BigDecimal.ROUND_HALF_UP));
        ObtenerValorComercialResponse response = referenciaDiamanteServiceEndPoint.obtenerValorComercial(parameters);

        assertEquals(response.getValorComercial().getValorMinimo(), new BigDecimal(0.90).setScale(2, BigDecimal.ROUND_HALF_UP));
        assertEquals(response.getValorComercial().getValorMedio(), new BigDecimal(135.0000).setScale(4, BigDecimal.ROUND_HALF_UP));
        assertEquals(response.getValorComercial().getValorMaximo(), new BigDecimal(0.99).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    public void obtenerModificadorTest() {
        ObtenerModificadorRequest parameters = new ObtenerModificadorRequest();
        parameters.setCertificadoDiamante("Certificado 1");
        ObtenerModificadorResponse response = referenciaDiamanteServiceEndPoint.obtenerModificador(parameters);
        assertEquals(response.getFactor(), new BigDecimal(5.00).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
