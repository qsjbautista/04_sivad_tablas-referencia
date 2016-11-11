package mx.com.nmp.ms.sivad.referencia.api.ws;

import mx.com.nmp.ms.sivad.referencia.ws.diamantes.ReferenciaDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author osanchez
 */
public class ReferenciaDiamantesServiceEndpoint implements ReferenciaDiamanteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaDiamantesServiceEndpoint.class);

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerValorComercialResponse
     */
    @Override
    public ObtenerValorComercialResponse obtenerValorComercial(ObtenerValorComercialRequest parameters) {
        LOGGER.info(">> obtenerValorComercial({})", parameters);

        ObtenerValorComercialResponse response = new ObtenerValorComercialResponse();
        ValorComercial valorComercial = new ValorComercial();
        valorComercial.setValorMinimo(BigDecimal.ZERO);
        valorComercial.setValorMedio(BigDecimal.ONE);
        valorComercial.setValorMaximo(BigDecimal.TEN);
        response.setValorComercial(valorComercial);

        return response;
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerModificadorResponse
     */
    @Override
    public ObtenerModificadorResponse obtenerModificador(ObtenerModificadorRequest parameters) {
        LOGGER.info(">> obtenerModificador({})", parameters);

        ObtenerModificadorResponse response = new ObtenerModificadorResponse();
        response.setFactor(BigDecimal.TEN);

        return response;
    }
}
