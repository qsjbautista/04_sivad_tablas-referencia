package mx.com.nmp.ms.sivad.cambiario.api.ws;

import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ConvertirRequest;
import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ConvertirResponse;
import java.math.BigDecimal;

public class ConvertidorTipoCambioEndpoint implements ConvertidorTipoCambioService {

    @Override
    public ConvertirResponse convertir(ConvertirRequest parameters) {

        ConvertirResponse response = new ConvertirResponse();
        response.setConversion(BigDecimal.valueOf(18));

        return response;
    }

}
