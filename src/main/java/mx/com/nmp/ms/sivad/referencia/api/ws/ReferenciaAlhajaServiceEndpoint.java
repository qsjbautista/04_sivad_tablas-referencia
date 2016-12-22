package mx.com.nmp.ms.sivad.referencia.api.ws;

import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

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

    @Autowired
    private Environment env;

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

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getCalidad()) && !ObjectUtils.isEmpty(parameters.getColor())) {
            try {
                OroVO oroVO = new OroVO(parameters.getColor(), parameters.getCalidad());
                Oro oroResult = valorComercialOroRepository.consultarOroVigente(oroVO);
                response.setPrecioPorGramo(oroResult.obtenerValorGramo());
            } catch (ValorGramoNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR008.getMessageException() + " Para las entradas: calidad({}), color: ({})", parameters.getCalidad(), parameters.getColor());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR008.getCodeException(), WebServiceExceptionCodes.NMPR008.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), calidad: ({}), color: ({}) ", parameters, parameters.getCalidad(), parameters.getColor());
        }

        LOGGER.info("<< {}", response.getPrecioPorGramo());

        return response;
    }

    /**
     * Permite simo el valor por gramo de metales distintos a oro.
     *
     * @param parameters Metal y Calidad
     * @return returns mx.com.nmp.ms.ws.referencia.datatypes.ObtenerValorGramoMetalResponse
     */
    @Override
    public ObtenerValorGramoMetalResponse obtenerValorGramoMetal(ObtenerValorGramoMetalRequest parameters) {
        LOGGER.info(">> obtenerValorGramoMetal({})", parameters);

        ObtenerValorGramoMetalResponse response = new ObtenerValorGramoMetalResponse();

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getMetal()) && !ObjectUtils.isEmpty(parameters.getCalidad())) {
            try {
                MetalVO metalVO = new MetalVO(parameters.getMetal(), parameters.getCalidad());
                Metal metal = valorComercialMetalRepository.consultarMetalVigente(metalVO);
                response.setPrecioPorGramo(metal.obtenerValorGramo());
            } catch (ValorGramoNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR011.getMessageException() + " Para las entradas: metal({}), calidad: ({})", parameters.getMetal(), parameters.getCalidad());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR011.getCodeException(), WebServiceExceptionCodes.NMPR011.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), metal: ({}), calidad: ({}) ", parameters, parameters.getMetal(), parameters.getCalidad());
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

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getMetal()) && !ObjectUtils.isEmpty(parameters.getCalidad()) && !ObjectUtils.isEmpty(parameters.getRango())) {
            try {
                FactorAlhajaVO factorAlhajaVO = new FactorAlhajaVO(parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                FactorAlhaja factorAlhaja = modificadorRangoRepository.obtenerFactor(factorAlhajaVO);
                response.setDesplazamiento(factorAlhaja.getFactor());
            } catch (FactorAlhajaNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR007.getMessageException() + " para las entradas ({}), ({}), ({})", parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR007.getCodeException(), WebServiceExceptionCodes.NMPR007.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), metal: ({}), calidad: ({}), rango({}) ", parameters, parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
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

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getMetal()) && !ObjectUtils.isEmpty(parameters.getCalidad()) && !ObjectUtils.isEmpty(parameters.getRango())) {
            try {
                FactorAlhajaVO factorAlhajaVO = new FactorAlhajaVO(parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                FactorAlhaja factorAlhaja = modificadorRangoRepository.obtenerFactor(factorAlhajaVO);
                response.setFactor(factorAlhaja.getFactor());
            } catch (FactorAlhajaNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR007.getMessageException() + " para las entradas ({}), ({}), ({})", parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR007.getCodeException(), WebServiceExceptionCodes.NMPR007.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), metal: ({}), calidad: ({}), rango({}) ", parameters, parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
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

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getMetal()) && !ObjectUtils.isEmpty(parameters.getCalidad()) && !ObjectUtils.isEmpty(parameters.getRango())) {
            try {
                FactorAlhajaVO factorAlhajaVO = new FactorAlhajaVO(parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                FactorAlhaja factorAlhaja = modificadorRangoRepository.obtenerFactor(factorAlhajaVO);

                RangoLimite rangoLimite = new RangoLimite();
                rangoLimite.setLimiteInferior(factorAlhaja.getLimiteInferior());
                rangoLimite.setLimiteSuperior(factorAlhaja.getLimiteSuperior());

                response.setLimitesIncremento(rangoLimite);

            } catch (FactorAlhajaNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR007.getMessageException() + " para las entradas ({}), ({}), ({})", parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR007.getCodeException(), WebServiceExceptionCodes.NMPR007.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), metal: ({}), calidad: ({}), rango({}) ", parameters, parameters.getMetal(), parameters.getCalidad(), parameters.getRango());
        }

        LOGGER.info("<< {}", response.getLimitesIncremento());

        return response;
    }
}
