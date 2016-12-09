package mx.com.nmp.ms.sivad.referencia.conector;

import mx.com.nmp.ms.sivad.cambiario.api.ws.ConvertidorTipoCambioEndpoint;
import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class, properties = "server.port=10344",
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConectorTest {
    private static Endpoint ep;

    @Inject
    Convertidor convertidor;

    @Inject
    private SpringBus bus;

    @PostConstruct
    public void setUp() {
        if (ObjectUtils.isEmpty(ep)) {
            ep = new EndpointImpl(bus, new ConvertidorTipoCambioEndpoint());
            ((EndpointImpl) ep).setPublishedEndpointUrl("http://localhost:10344/soap-api/ConvertidorTipoCambioService?wsdl");
            ep.publish("/ConvertidorTipoCambioService");
        }
    }


    @Test
    public void convertirTest(){
      BigDecimal divisaConvertida = convertidor.convertir("MXN", "USD", new BigDecimal(18));
        assertEquals(BigDecimal.valueOf(18), divisaConvertida);
    }

}
