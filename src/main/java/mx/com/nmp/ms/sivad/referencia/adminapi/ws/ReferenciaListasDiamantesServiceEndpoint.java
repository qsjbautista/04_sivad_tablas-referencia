package mx.com.nmp.ms.sivad.referencia.adminapi.ws;

import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.*;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.ValorComercialDiamanteBatchProcessor;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.ReferenciaListasDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.*;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;

/**
 * @author osanchez
 */
public class ReferenciaListasDiamantesServiceEndpoint implements ReferenciaListasDiamanteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaListasDiamantesServiceEndpoint.class);
    /**
     * Referencia al repositorio de ModificadorValorDiamanteFactory.
     */
    @Inject
    ModificadorValorDiamanteFactory modificadorValorDiamanteFactory;

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    @Inject
    private ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    /**
     * Referencia a la fabrica de Value Object
     */
    @Inject
    FactorValorDiamanteFactory factorValorDiamanteFactory;

    /**
     * Referencia a la fabrica de Value Object
     */
    @Inject
    ValorComercialDiamanteBatchProcessor valorComercialDiamanteBatchProcessor;

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    public Void actualizarListaValorComercial(ActualizarListaValorComercialRequest parameters) {
        LOGGER.info(">> actualizarListaValorComercial({})", parameters);
        valorComercialDiamanteBatchProcessor.procesa(parameters);
        return new Void();
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    public Void actualizarListaFactor(ActualizarListaFactorRequest parameters) {
        LOGGER.info(">> actualizarListaFactor({})", parameters);

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getFactorDiamante())
            && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMinimo()) && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMedio())
            && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMaximo())) {
            try {
                FactorValorDiamante vo = factorValorDiamanteFactory
                    .crearCon(parameters.getFactorDiamante().getFactorMinimo(), parameters.getFactorDiamante().getFactorMedio(),
                        parameters.getFactorDiamante().getFactorMaximo());
                ModificadorValorDiamante modificadorValorDiamante = modificadorValorDiamanteFactory.crearPersistibleCon(DateTime.now(), LocalDate.now(), vo);
                modificadorValorDiamante.actualizar();
            } catch (Exception e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR004.getMessageException() + "."+ e.getMessage());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getMessageException(), e.getMessage());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), minimo: ({}), medio: ({}), maximo: ({}) ", parameters,
                parameters.getFactorDiamante().getFactorMinimo(), parameters.getFactorDiamante().getFactorMedio() , parameters.getFactorDiamante().getFactorMaximo());
        }
        return new Void();
    }
}
