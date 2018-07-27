package mx.com.nmp.ms.sivad.referencia.api.ws;

import com.codahale.metrics.annotation.Timed;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CertificadoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.RangoPesoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.RangoPesoDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.CertificadoVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.DiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.RangoPesoDiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.ValorComercialDiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.RangoPesoDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.ReferenciaDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Clase que contiene los metodos que son invocados para obtener la información comercial de diamantes.
 *
 * @author osanchez, ecancino
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ReferenciaDiamantesServiceEndpoint implements ReferenciaDiamanteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaDiamantesServiceEndpoint.class);

    @Inject
    ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    @Inject
    ModificadorCertificadoRepository modificadorCertificadoRepository;

    @Inject
    RangoPesoDiamanteRepository rangoPesoDiamanteRepository;


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
            LOGGER.info(">> obtenerValorComercial({},{},{},{},{},{})", new Object[]{
                parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt(),
                parameters.getQuilatesDesde(), parameters.getQuilatesHasta()
            });
        }

        ObtenerValorComercialResponse response = new ObtenerValorComercialResponse();

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getCorte()) && !ObjectUtils.isEmpty(parameters.getColor()) &&
            !ObjectUtils.isEmpty(parameters.getClaridad()) && !ObjectUtils.isEmpty(parameters.getQuilatesCt()) && !ObjectUtils.isEmpty(parameters.getQuilatesDesde())
            && !ObjectUtils.isEmpty(parameters.getQuilatesHasta())) {

            DiamanteVO diamanteVO = new DiamanteVO(parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt(),
                parameters.getQuilatesDesde(), parameters.getQuilatesHasta());

            try {
                Diamante diamante = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
                ValorComercialDiamanteVO valorComercialDiamanteVO = diamante.obtenerValorComercial();
                ValorComercial valorComercial = new ValorComercial();

                valorComercial.setValorMaximo(valorComercialDiamanteVO.getValorMaximo());
                valorComercial.setValorMedio(valorComercialDiamanteVO.getValorMedio());
                valorComercial.setValorMinimo(valorComercialDiamanteVO.getValorMinimo());

                response.setValorComercial(valorComercial);
            } catch (ValorComercialNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR010.getMessageException() + " para las entradas: corte: ({}), color: ({}), claridad: ({}), Quilates: ({}), RangoPeso: ({},{})",
                    parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt(), parameters.getQuilatesDesde(), parameters.getQuilatesHasta());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR010.getCodeException(), WebServiceExceptionCodes.NMPR010.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), corte: ({}), color: ({}), claridad: ({}), Quilates: ({}), RangoPeso: ({},{})",
                parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt(), parameters.getQuilatesDesde(), parameters.getQuilatesHasta());
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

    /**
     * Servicio que permite calcular el rango de peso y un valor aproximado de peso para el total de diamantes
     * dado un valor en quilates.
     *
     * @param parameters EL objeto que contiene las caracteristicas del diamante: quilataje y cantidad de diamantes.
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ObtenerRangoPesoResponse
     *  El peso aproximado y el rango de peso en el que se encuentran los diamantes.
     */
    public ObtenerRangoPesoResponse obtenerRangoPeso(ObtenerRangoPesoRequest parameters) {
        if (LOGGER.isInfoEnabled() && parameters != null) {
            LOGGER.info(">> obtenerRangoPeso({},{})", parameters.getQuilataje(), parameters.getCantidad());
        }

        ObtenerRangoPesoResponse response = new ObtenerRangoPesoResponse();

        //SE VALIDA QUE LOS PARAMETROS NO TENGAN VALORES NULOS O VACIOS
        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getQuilataje()) && !ObjectUtils.isEmpty(parameters.getCantidad())) {

            RangoPesoDiamanteVO rangoPesoDiamanteVO = new RangoPesoDiamanteVO(parameters.getQuilataje());

            try {
                //SE OBTIENE EL RANGO DE PESO AL QUE PERTENECE EN BASE AL PESO EN QUILATES RECIBIDO.
                RangoPesoDiamante rangoPesoDiamante = rangoPesoDiamanteRepository.obtenerRangoPeso(rangoPesoDiamanteVO);

                response.setQuilatesDesde(rangoPesoDiamante.getTamanioInferior());
                response.setQuilatesHasta(rangoPesoDiamante.getTamanioSuperior());

                //SE MULTIPLICAN LOS QUILATES INGRESADOS POR EL CAMPO DIAMANTES IGUALES PARA OBTENER EL PESO APROXIMADO
                BigDecimal pesoAproximado = parameters.getQuilataje().multiply(new BigDecimal(parameters.getCantidad()));
                response.setPesoAproximado(pesoAproximado);

            } catch (RangoPesoNoEncontradoException e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR007.getMessageException() + " para las entradas: quilates: ({})", parameters.getQuilataje());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR007.getCodeException(), WebServiceExceptionCodes.NMPR007.getMessageException());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), quilataje: ({}), cantidad de diamantes: ({})", parameters,  parameters.getQuilataje(), parameters.getCantidad());
            throwWebServiceException();
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("<< {}/{}/{}", response.getQuilatesDesde(), response.getQuilatesHasta(), response.getPesoAproximado());
        }

        return response;
    }

    private static void throwWebServiceException() {
        throw WebServiceExceptionFactory
            .crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getCodeException(),
                WebServiceExceptionCodes.NMPR004.getMessageException());
    }
}
