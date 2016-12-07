package mx.com.nmp.ms.sivad.referencia.api.ws;


import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactorAlhajaNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhaja;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorAlhajaVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.MetalVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.OroVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;
import mx.com.nmp.ms.sivad.referencia.ws.alhajas.ReferenciaAlhajaService;
import mx.com.nmp.ms.sivad.referencia.ws.alhajas.datatypes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

/**
 * Clase que contiene los metodos para recuperar información necesaria de diamantes.
 *
 * @author jbautista
 */
public class ReferenciaAlhajaServiceEndpoint implements ReferenciaAlhajaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaAlhajaServiceEndpoint.class);

    @Inject
    ValorComercialOroRepository valorComercialOroRepository;

    @Inject
    ValorComercialMetalRepository valorComercialMetalRepository;

    @Inject
    ModificadorRangoRepository modificadorRangoRepository;

    /**
     * Obtiene el valor por gramo correspondiente a las caracteristicas del metal de una alhaja.
     *
     * @param parameters color y calidad
     * @return returns mx.com.nmp.ms.ws.referencia.datatypes.GetOroResponse
     */
    @Override
    public ObtenerValorGramoOroResponse obtenerValorGramoOro(ObtenerValorGramoOroRequest parameters) {
        LOGGER.info(">> obtenerValorGramoOro({})", parameters);

        ObtenerValorGramoOroResponse response = new ObtenerValorGramoOroResponse();

        if (parameters != null) {
            try {
                OroVO oroVO = new OroVO(parameters.getColor(), parameters.getCalidad());
                Oro oroResult = valorComercialOroRepository.consultarOroVigente(oroVO);
                response.setPrecioPorGramo(oroResult.obtenerValorGramo());
            } catch (ValorGramoNoEncontradoException e) {
                LOGGER.info(e.getMessage());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon("NMP-TR-008", e.getMessage());
            }
        }

        LOGGER.info("<< {}", response.getPrecioPorGramo());

        return response;
    }

    /**
     * Permite obtener el valor por gramo de metales distintos a oro.
     *
     * @param parameters Metal y Calidad
     * @return returns mx.com.nmp.ms.ws.referencia.datatypes.ObtenerValorGramoMetalResponse
     */
    @Override
    public ObtenerValorGramoMetalResponse obtenerValorGramoMetal(ObtenerValorGramoMetalRequest parameters) {
        LOGGER.info(">> obtenerValorGramoMetal({})", parameters);

        ObtenerValorGramoMetalResponse response = new ObtenerValorGramoMetalResponse();

        if (parameters != null) {
            try {
                MetalVO metalVO = new MetalVO(parameters.getMetal(), parameters.getCalidad());
                Metal metal = valorComercialMetalRepository.consultarMetalVigente(metalVO);
                response.setPrecioPorGramo(metal.obtenerValorGramo());
            } catch (ValorGramoNoEncontradoException e) {
                LOGGER.info(e.getMessage());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon("NMP-TR-011", e.getMessage());
            }
        }

        LOGGER.info("<< {}", response.getPrecioPorGramo());
        return response;
    }

    /**
     * Obtiene el limite maximo correspondiente al modificador por desplazamiento comercial de la alhaja.
     *
     * @param parameters Metal, Calidad y Rango.
     * @return returns mx.com.nmp.ms.ws.referencia.datatypes.ObtenerDesplazamientoResponse
     */
    @Override
    public ObtenerDesplazamientoResponse obtenerDesplazamiento(ObtenerDesplazamientoRequest parameters) {
        LOGGER.info(">> obtenerDesplazamiento({})", parameters);

        ObtenerDesplazamientoResponse response = new ObtenerDesplazamientoResponse();

        if (parameters != null) {
            try {
                FactorAlhajaVO factorAlhajaVO = new FactorAlhajaVO(parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                FactorAlhaja factorAlhaja = modificadorRangoRepository.obtenerFactor(factorAlhajaVO);
                response.setDesplazamiento(factorAlhaja.getFactor());
            } catch (FactorAlhajaNoEncontradoException e) {
                LOGGER.info("No existe el rango solicitado");
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon("NPM-TR-007", "No existe el rango solicitado");
            }
        }

        LOGGER.info("<< {}", response.getDesplazamiento());

        return response;
    }

    /**
     * Obtiene el factor de modificación aplicable a la relación metal-calida-rango de una alhaja.
     *
     * @param parameters Metal, Calidad y Rango
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.datatypes.ObtenerFactorResponse
     */
    @Override
    public ObtenerFactorResponse obtenerFactor(ObtenerFactorRequest parameters) {
        LOGGER.info(">> obtenerFactor({})", parameters);

        ObtenerFactorResponse response = new ObtenerFactorResponse();

        if (parameters != null) {
            try {
                FactorAlhajaVO factorAlhajaVO = new FactorAlhajaVO(parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                FactorAlhaja factorAlhaja = modificadorRangoRepository.obtenerFactor(factorAlhajaVO);
                response.setFactor(factorAlhaja.getFactor());
            } catch (FactorAlhajaNoEncontradoException e) {
                LOGGER.info("No existe el rango solicitado");
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon("NPM-TR-007", "No existe el rango solicitado");
            }
        }

        LOGGER.info("<< {}", response.getFactor());

        return response;
    }

    /**
     * Obtiene el rango de incremento asociado a la relación metal-calidad-rango.
     *
     * @param parameters Metal, Calidad y Rango
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.datatypes.ObtenerLimitesIncrementoResponse
     */
    @Override
    public ObtenerLimitesIncrementoResponse obtenerLimitesIncremento(ObtenerLimitesIncrementoRequest parameters) {
        LOGGER.info(">> obtenerLimitesIncremento({})", parameters);

        ObtenerLimitesIncrementoResponse response = new ObtenerLimitesIncrementoResponse();

        if (parameters != null) {
            try {
                FactorAlhajaVO factorAlhajaVO = new FactorAlhajaVO(parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                FactorAlhaja factorAlhaja = modificadorRangoRepository.obtenerFactor(factorAlhajaVO);

                RangoLimite rangoLimite = new RangoLimite();
                rangoLimite.setLimiteInferior(factorAlhaja.getLimiteInferior());
                rangoLimite.setLimiteSuperior(factorAlhaja.getLimiteSuperior());

                response.setLimitesIncremento(rangoLimite);

            } catch (FactorAlhajaNoEncontradoException e) {
                LOGGER.info("No existe el rango solicitado");
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon("NPM-TR-007", "No existe el rango solicitado");
            }
        }

        LOGGER.info("<< {}", response.getLimitesIncremento());

        return response;
    }
}
