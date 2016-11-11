package mx.com.nmp.ms.sivad.referencia.adminapi.ws;

import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.ReferenciaListasDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.ActualizarListaFactorRequest;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.ActualizarListaValorComercialRequest;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author osanchez
 */
public class ReferenciaListasDiamantesServiceEndpoint implements ReferenciaListasDiamanteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaListasDiamantesServiceEndpoint.class);

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    public Void actualizarListaValorComercial(ActualizarListaValorComercialRequest parameters) {
        LOGGER.info(">> actualizarListaValorComercial({})", parameters);
        return new Void();
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    public Void actualizarListaFactor(ActualizarListaFactorRequest parameters) {
        LOGGER.info(">> actualizarListaFactor({})", parameters);
        return new Void();
    }
}
