package mx.com.nmp.ms.sivad.referencia.api.ws;

import com.codahale.metrics.annotation.Timed;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CertificadoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.CertificadoVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.DiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.ValorComercialDiamanteVO;
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
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ReferenciaDiamantesServiceEndpoint implements ReferenciaDiamanteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaDiamantesServiceEndpoint.class);

    @Inject
    ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    @Inject
    ModificadorCertificadoRepository modificadorCertificadoRepository;


    /**
     * Obtiene el valor comercial de diamante en base a sus caracteristcas.
     *
     * @param parameters Parametros
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerValorComercialResponse
     */
    @Timed
    @Override
    public ObtenerValorComercialResponse obtenerValorComercial(ObtenerValorComercialRequest parameters) {
        if (LOGGER.isInfoEnabled() && parameters != null) {
            LOGGER.info(">> obtenerValorComercial({},{},{},{})", new Object[]{
                parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt()
            });
        }

        ObtenerValorComercialResponse response = new ObtenerValorComercialResponse();

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getCorte()) && !ObjectUtils.isEmpty(parameters.getColor()) &&
            !ObjectUtils.isEmpty(parameters.getClaridad()) && !ObjectUtils.isEmpty(parameters.getQuilatesCt())) {

            DiamanteVO diamanteVO = new DiamanteVO(parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt());

            try {
                Diamante diamante = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
                ValorComercialDiamanteVO valorComercialDiamanteVO = diamante.obtenerValorComercial();
                ValorComercial valorComercial = new ValorComercial();

                valorComercial.setValorMaximo(valorComercialDiamanteVO.getValorMaximo());
                valorComercial.setValorMedio(valorComercialDiamanteVO.getValorMedio());
                valorComercial.setValorMinimo(valorComercialDiamanteVO.getValorMinimo());

                response.setValorComercial(valorComercial);
            } catch (ValorComercialNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR010.getMessageException() + " para las entradas: corte: ({}), color: ({}), claridad: ({}), Quilates: ({})", parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR010.getCodeException(), WebServiceExceptionCodes.NMPR010.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), corte: ({}), color: ({}), claridad: ({}), Quilates: ({})", parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt());
            throwWebServiceException();
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("<< {}/{}/{}", new Object[]{
                response.getValorComercial().getValorMinimo(),
                response.getValorComercial().getValorMedio(),
                response.getValorComercial().getValorMaximo()});
        }

        return response;
    }

    /**
     * Obtiene el porcentaje de modificación de valor correspondiente a un certificado.
     *
     * @param parameters Parametros
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
            throwWebServiceException();
        }

        LOGGER.info("<< {}", response.getFactor());

        return response;
    }

    private static void throwWebServiceException() {
        throw WebServiceExceptionFactory
            .crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getCodeException(),
                WebServiceExceptionCodes.NMPR004.getMessageException());
    }
}
