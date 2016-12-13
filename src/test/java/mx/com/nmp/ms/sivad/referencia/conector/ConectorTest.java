package mx.com.nmp.ms.sivad.referencia.conector;

import mx.com.nmp.ms.sivad.cambiario.api.ws.ConvertidorTipoCambioService;
import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ConvertirResponse;
import mx.com.nmp.ms.sivad.referencia.conector.referencia.ReferenciaConvertidorConector;

import java.math.BigDecimal;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ConectorTest {

    Convertidor convertidor;

    ReferenciaConvertidorConector referenciaConvertidorConector;

    ConvertidorTipoCambioService convertidorTipoCambioService;

    // FIXME la prueba no debe ser desde Convertidor, podría ser desde ReferenciaConvertidorConector
    public void convertirTest() {
        ConvertirResponse response = new ConvertirResponse();
        response.setConversion(BigDecimal.valueOf(18l));

        //given(this.convertidorTipoCambioService.convertir(any(ConvertirRequest.class))).willReturn(response);
        // FIXME se debería probar que ReferenciaConvertidorConector funciona bien con un WS mock como el anterior
        //given(this.referenciaConvertidorConector.getWsReferenciaTipoCambio()).willReturn(convertidorTipoCambioService);

        //BigDecimal divisaConvertida = convertidor.convertir("MXN", "USD", new BigDecimal(18));
        //assertEquals(BigDecimal.valueOf(18), divisaConvertida);
    }

}
