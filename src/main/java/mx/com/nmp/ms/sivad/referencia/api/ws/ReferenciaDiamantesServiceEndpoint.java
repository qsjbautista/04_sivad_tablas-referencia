package mx.com.nmp.ms.sivad.referencia.api.ws;

import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CertificadoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.CertificadoVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.DiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.ReferenciaDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;

/**
 * Clase que contiene los metodos que son invocados para obtener la información comercial de diamantes.
 *
 * @author osanchez
 */
public class ReferenciaDiamantesServiceEndpoint implements ReferenciaDiamanteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaDiamantesServiceEndpoint.class);

    @Inject
    ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    @Inject
    ModificadorCertificadoRepository modificadorCertificadoRepository;


    /**
     * Obtiene el valor comercial de diamante en base a sus caracteristcas.
     *
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerValorComercialResponse
     */
    @Override
    public ObtenerValorComercialResponse obtenerValorComercial(ObtenerValorComercialRequest parameters) {
        LOGGER.info(">> obtenerValorComercial({})", parameters);

        ObtenerValorComercialResponse response = new ObtenerValorComercialResponse();

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getCorte()) && !ObjectUtils.isEmpty(parameters.getColor()) && !ObjectUtils.isEmpty(parameters.getClaridad()) && !ObjectUtils.isEmpty(parameters.getQuilatesCt())) {
            DiamanteVO diamanteVO = new DiamanteVO(parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt());

            try {
                Diamante diamante = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
                ValorComercial valorComercial = new ValorComercial();

                valorComercial.setValorMaximo(diamante.getTamanioSuperior());
                valorComercial.setValorMedio(diamante.getPrecio());
                valorComercial.setValorMinimo(diamante.getTamanioInferior());

                response.setValorComercial(valorComercial);
            } catch (ValorComercialNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR010.getMessageException() + " para las entradas: corte: ({}), color: ({}), claridad: ({}), Quilates: ({})", parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR010.getCodeException(), WebServiceExceptionCodes.NMPR010.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), corte: ({}), color: ({}), claridad: ({}), Quilates: ({})", parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt());
        }

        LOGGER.info("<< {}", response.getValorComercial());

        return response;
    }

    /**
     * Obtiene el porcentaje de modificación de valor correspondiente a un certificado.
     *
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerModificadorResponse
     */
    @Override
    public ObtenerModificadorResponse obtenerModificador(ObtenerModificadorRequest parameters) {
        LOGGER.info(">> obtenerModificador({})", parameters);

        ObtenerModificadorResponse response = new ObtenerModificadorResponse();

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getCertificadoDiamante())) {
            CertificadoVO certificadoVO = new CertificadoVO(parameters.getCertificadoDiamante());

            try {
                Certificado factor = modificadorCertificadoRepository.consultarModificadorCertificadoVigente(certificadoVO);
                response.setFactor(factor.getFactor());
            } catch (CertificadoNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR009.getMessageException() + " para las entradas: certificado ({})", parameters.getCertificadoDiamante());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR009.getCodeException(), WebServiceExceptionCodes.NMPR009.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), certificado: ({}) ", parameters, parameters.getCertificadoDiamante());
        }

        LOGGER.info("<< {}", response.getFactor());

        return response;
    }
}
