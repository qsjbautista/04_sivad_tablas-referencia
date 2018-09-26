package mx.com.nmp.ms.sivad.referencia.adminapi.api.ws;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.conector.Convertidor;
import mx.com.nmp.ms.sivad.referencia.conector.TipoCambioEnum;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.ReferenciaDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.*;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.when;

/**
 * Clase de prueba para Tabla Diamantes.
 *
 * @author jbautista, ecancino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ReferenciaDiamanteServiceEndpointITest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaDiamanteServiceEndpointITest.class);

    private static final BigDecimal PRECIO_PESOS = new BigDecimal(2700.00D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal VALOR_COMERCIAL_MINIMO_PESOS = new BigDecimal(36.4500D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal VALOR_COMERCIAL_MEDIO_PESOS = new BigDecimal(48.6000D).setScale(4, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal VALOR_COMERCIAL_MAXIMO_PESOS = new BigDecimal(60.7500D).setScale(4, BigDecimal.ROUND_HALF_UP);

    private static final BigDecimal RANGO_PESO_INFERIOR = new BigDecimal(0.90D).setScale(2, BigDecimal.ROUND_HALF_UP);
    private static final BigDecimal RANGO_PESO_SUPERIOR = new BigDecimal(0.96D).setScale(2, BigDecimal.ROUND_HALF_UP);

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    @Inject
    private ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    /**
     * Referencia al conector con microservicio de tipo cambiario.
     */
    //@Mock
    //private Convertidor convertidor;

	/**
     * Referencia al endpoint del servicio de diamantes.
     */
    @Inject
    ReferenciaDiamanteService referenciaDiamanteServiceEndPoint;



    // METODOS

    /**
     * Constructor.
     */
    public ReferenciaDiamanteServiceEndpointITest() {
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

    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerValorComercialTest() {
        LOGGER.info(">> obtenerValorComercialTest");

        /*when(convertidor.convertir(matches(TipoCambioEnum.USD.getTipo()), matches(TipoCambioEnum.MXN.getTipo()),
            any(BigDecimal.class))).thenReturn(PRECIO_PESOS);*/

        ObtenerValorComercialRequest parameters = new ObtenerValorComercialRequest();
        parameters.setCorte("Oval");
        parameters.setSubcorte("Acojinado");
        parameters.setColor("D");
        parameters.setClaridad("IF");
        parameters.setQuilatesCt(new BigDecimal(0.95).setScale(2, BigDecimal.ROUND_HALF_UP));
        parameters.setQuilatesDesde(new BigDecimal(0.90).setScale(2, BigDecimal.ROUND_HALF_UP));
        parameters.setQuilatesHasta(new BigDecimal(0.96).setScale(2, BigDecimal.ROUND_HALF_UP));
        ObtenerValorComercialResponse response = referenciaDiamanteServiceEndPoint.obtenerValorComercial(parameters);

        assertEquals(VALOR_COMERCIAL_MINIMO_PESOS, response.getValorComercial().getValorMinimo());
        assertEquals(VALOR_COMERCIAL_MEDIO_PESOS, response.getValorComercial().getValorMedio());
        assertEquals(VALOR_COMERCIAL_MAXIMO_PESOS, response.getValorComercial().getValorMaximo());
    }

    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    public void obtenerModificadorTest() {
        LOGGER.info(">> obtenerModificadorTest");

        ObtenerModificadorRequest parameters = new ObtenerModificadorRequest();
        parameters.setCertificadoDiamante("Certificado 1");
        ObtenerModificadorResponse response = referenciaDiamanteServiceEndPoint.obtenerModificador(parameters);
        assertEquals(response.getFactor(), new BigDecimal(5.00).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    @Transactional
    @Sql("/bd/test-data-valor_comercial_diamante-h2.sql")
    public void obtenerRangoPesoTest() {
        LOGGER.info(">> obtenerModificadorTest");

        ObtenerRangoPesoRequest parameters = new ObtenerRangoPesoRequest();
        parameters.setCantidad(2);
        parameters.setQuilataje(new BigDecimal(0.95).setScale(2, BigDecimal.ROUND_HALF_UP));
        ObtenerRangoPesoResponse response = referenciaDiamanteServiceEndPoint.obtenerRangoPeso(parameters);
        assertEquals(response.getQuilatesDesde(), RANGO_PESO_INFERIOR);
        assertEquals(response.getQuilatesHasta(), RANGO_PESO_SUPERIOR);
        assertEquals(response.getPesoAproximado(), (BigDecimal.valueOf(parameters.getCantidad()).multiply(parameters.getQuilataje())).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

}
