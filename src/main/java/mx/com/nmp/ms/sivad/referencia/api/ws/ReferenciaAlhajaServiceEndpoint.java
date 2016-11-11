package mx.com.nmp.ms.sivad.referencia.api.ws;


import mx.com.nmp.ms.sivad.referencia.ws.alhajas.ReferenciaAlhajaService;
import mx.com.nmp.ms.sivad.referencia.ws.alhajas.datatypes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class ReferenciaAlhajaServiceEndpoint implements ReferenciaAlhajaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaAlhajaServiceEndpoint.class);

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.ws.referencia.datatypes.GetOroResponse
     */
    @Override
    public ObtenerValorGramoOroResponse obtenerValorGramoOro(ObtenerValorGramoOroRequest parameters) {
        LOGGER.info(">> obtenerValorGramoOro({})", parameters);

        if (parameters != null) {
            LOGGER.info("'{}','{}'", parameters.getColor(), parameters.getCalidad());
        }

        ObtenerValorGramoOroResponse response = new ObtenerValorGramoOroResponse();
        response.setPrecioPorGramo(new BigDecimal(10.5));
        LOGGER.info("<< {}", response.getPrecioPorGramo());
        return response;
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.ws.referencia.datatypes.ObtenerValorGramoMetalResponse
     */
    @Override
    public ObtenerValorGramoMetalResponse obtenerValorGramoMetal(ObtenerValorGramoMetalRequest parameters) {
        LOGGER.info(">> obtenerValorGramoMetal({})", parameters);

        ObtenerValorGramoMetalResponse response = new ObtenerValorGramoMetalResponse();
        response.setPrecioPorGramo(BigDecimal.TEN);

        return response;
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.ws.referencia.datatypes.ObtenerDesplazamientoResponse
     */
    @Override
    public ObtenerDesplazamientoResponse obtenerDesplazamiento(ObtenerDesplazamientoRequest parameters) {
        LOGGER.info(">> obtenerDesplazamiento({})", parameters);

        ObtenerDesplazamientoResponse response = new ObtenerDesplazamientoResponse();
        response.setDesplazamiento(BigDecimal.ONE);

        return response;
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.datatypes.ObtenerFactorResponse
     */
    @Override
    public ObtenerFactorResponse obtenerFactor(ObtenerFactorRequest parameters) {
        LOGGER.info(">> obtenerDesplazamiento({})", parameters);

        ObtenerFactorResponse response = new ObtenerFactorResponse();
        response.setFactor(BigDecimal.TEN);

        return response;
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.datatypes.ObtenerLimitesIncrementoResponse
     */
    @Override
    public ObtenerLimitesIncrementoResponse obtenerLimitesIncremento(ObtenerLimitesIncrementoRequest parameters) {
        LOGGER.info(">> obtenerDesplazamiento({})", parameters);

        ObtenerLimitesIncrementoResponse response = new ObtenerLimitesIncrementoResponse();
        RangoLimite rangoLimite = new RangoLimite();
        rangoLimite.setLimiteInferior(BigDecimal.ONE);
        rangoLimite.setLimiteSuperior(BigDecimal.TEN);
        response.setLimitesIncremento(rangoLimite);

        return response;
    }
}
