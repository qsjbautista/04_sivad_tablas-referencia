/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.conector.referencia.factory;

import mx.com.nmp.ms.sivad.cambiario.ws.consulta.datatypes.ValorPorUnidadRequest;
import mx.com.nmp.ms.sivad.cambiario.ws.consulta.datatypes.ObjectFactory;
import org.springframework.stereotype.Component;

/**
 * Se encarga de crear una referencia hacia el Servicio Web Consulta Tipo de Cambio
 *
 * @author ecancino
 */
@Component
public class ReferenciaConsultaTipoCambioFactory {

    /**
     * Referencia al creador de objetos.
     * @see ObjectFactory
     */
    private ObjectFactory objectFactory;

    /**
     * Constructor.
     */
    public ReferenciaConsultaTipoCambioFactory() {
        super();
        objectFactory = new ObjectFactory();
    }

    /**
     * Crea una instancia de {@link ValorPorUnidadRequest}
     *
     * @param base Objeto que contiene los criterios de consulta.
     * @param destino Objeto que contiene los criterios de consulta.
     *
     * @return La instancia de {@link ValorPorUnidadRequest}
     */
    public ValorPorUnidadRequest crearValorPorUnidadRequest(final String base, final String destino){
        ValorPorUnidadRequest valorPorUnidad = objectFactory.createValorPorUnidadRequest();

        valorPorUnidad.setBase(base);
        valorPorUnidad.setDestino(destino);

        return valorPorUnidad;
    }

}
