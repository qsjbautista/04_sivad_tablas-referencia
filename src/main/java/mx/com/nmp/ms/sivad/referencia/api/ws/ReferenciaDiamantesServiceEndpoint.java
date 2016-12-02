package mx.com.nmp.ms.sivad.referencia.api.ws;

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

        if (parameters != null) {
            DiamanteVO diamanteVO = new DiamanteVO(parameters.getCorte(), parameters.getColor(), parameters.getClaridad(), parameters.getQuilatesCt());

            try {
                Diamante diamante = valorComercialDiamanteRepository.obtenerValorComercial(diamanteVO);
                ValorComercial valorComercial = new ValorComercial();

                valorComercial.setValorMaximo(diamante.getTamanioSuperior());
                valorComercial.setValorMedio(diamante.getPrecio());
                valorComercial.setValorMinimo(diamante.getTamanioInferior());

                response.setValorComercial(valorComercial);
            } catch (ValorComercialNoEncontradoException e) {
                LOGGER.info(e.getMessage());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon("NPM-TR-010", e.getMessage());
            }
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

        if (parameters != null) {
            CertificadoVO certificadoVO = new CertificadoVO(parameters.getCertificadoDiamante());

            try {
                Certificado factor = modificadorCertificadoRepository.consultarModificadorCertificadoVigente(certificadoVO);
                response.setFactor(factor.getFactor());
            } catch (CertificadoNoEncontradoException e) {
                LOGGER.info(e.getMessage());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon("NPM-TR-009", e.getMessage());
            }
        }

        LOGGER.info("<< {}", response.getFactor());

        return response;
    }
}
