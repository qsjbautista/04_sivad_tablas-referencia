/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.conector;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.cambiario.ws.consulta.datatypes.ValorPorUnidadRequest;
import mx.com.nmp.ms.sivad.cambiario.ws.consulta.datatypes.ValorPorUnidadResponse;
import mx.com.nmp.ms.sivad.referencia.conector.referencia.ReferenciaConsultaTipoCambioConector;
import mx.com.nmp.ms.sivad.referencia.conector.referencia.factory.ReferenciaConsultaTipoCambioFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Referencia al servicio web de Consulta del Tipo de Cambio
 *
 * @author ecancino
 */
@Component
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ConsultaTipoCambioProxy implements ConsultaTipoCambio {
    private Logger LOGGER = LoggerFactory.getLogger(ConsultaTipoCambioProxy.class);

    /**
     * Referencia al conector hacia el Servicio Web Consulta Tipo de Cambio
     */
    @Inject
    private ReferenciaConsultaTipoCambioConector referenciaConsultaTipoCambioConector;

    /**
     * Referencia a la fabrica de Tipos de Datos del Servicio Web Consulta Tipo de Cambio
     */
    @Inject
    private ReferenciaConsultaTipoCambioFactory referenciaConsultaTipoCambioFactory;


    // METODOS

    /**
     * {@inheritDoc}
     */
    @Cacheable("ConsultaTipoCambioProxy.valorPorUnidad")
    @Override
    public BigDecimal valorPorUnidad(@NotNull String base, @NotNull String destino) {
        LOGGER.info(">> valorPorUnidad(base {}, destino{})", base, destino);

        ValorPorUnidadRequest consultaTipoCambioRequest = referenciaConsultaTipoCambioFactory.crearValorPorUnidadRequest(base, destino);
        ValorPorUnidadResponse consultaTipoCambioResponse = referenciaConsultaTipoCambioConector.getWsReferenciaConsultaTipoCambio()
            .valorPorUnidad(consultaTipoCambioRequest);

        return consultaTipoCambioResponse.getValorPorUnidad();
    }

}
