package mx.com.nmp.ms.sivad.referencia.conector;

import mx.com.nmp.ms.sivad.cambiario.api.ws.ConvertidorTipoCambioService;
import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ConvertirRequest;
import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ConvertirResponse;
import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.conector.referencia.ReferenciaConvertidorConector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ConectorTest {

    @Inject
    Convertidor convertidor;

    @MockBean
    ReferenciaConvertidorConector referenciaConvertidorConector;

    @MockBean
    ConvertidorTipoCambioService convertidorTipoCambioService;

    @Test
    public void convertirTest() {
        ConvertirResponse response = new ConvertirResponse();
        response.setConversion(BigDecimal.valueOf(18l));

        given(this.convertidorTipoCambioService.convertir(any(ConvertirRequest.class))).willReturn(response);
        // FIXME se debería probar que ReferenciaConvertidorConector funciona bien con un WS mock como el anterior
        given(this.referenciaConvertidorConector.getWsReferenciaTipoCambio()).willReturn(convertidorTipoCambioService);

        BigDecimal divisaConvertida = convertidor.convertir("MXN", "USD", new BigDecimal(18));
        assertEquals(BigDecimal.valueOf(18), divisaConvertida);
    }

}
