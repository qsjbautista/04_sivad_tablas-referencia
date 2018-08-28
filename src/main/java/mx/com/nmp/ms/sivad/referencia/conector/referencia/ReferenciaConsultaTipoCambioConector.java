/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.conector.referencia;

import mx.com.nmp.ms.sivad.cambiario.api.ws.ConsultaTipoCambioEndpointService;
import mx.com.nmp.ms.sivad.cambiario.api.ws.ConsultaTipoCambioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Se encarga de crear una referencia hacia el Servicio Web Consulta Tipo de Cambio
 *
 * @author ecancino
 */
@Component
public class ReferenciaConsultaTipoCambioConector {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaConsultaTipoCambioConector.class);

    /**
     * Contienen la URL donde se encuentra publicado el Servicio Web Referencia de Alhajas.
     */
    @Value("${tipocambio.consulta.wsdlLocation}")
    private String wsdlLocation;

    /**
     * Referencia hacia el Servicio Web
     */
    private ConsultaTipoCambioService wsConsultaTipoCambio;

    /**
     * Regresa la referencia hacia el Servicio Web Referencia de Alhajas.
     *
     * @return Referencia hacia el Servicio Web
     */
    public ConsultaTipoCambioService getWsReferenciaConsultaTipoCambio() {
        if (ObjectUtils.isEmpty(wsConsultaTipoCambio)) {
            crearReferenciaConsultaTipoCambio();
        }

        LOGGER.info("Recuperando referencia al WS.");
        return wsConsultaTipoCambio;
    }

    /**
     * Crea una referencia hacia el Servicio Web
     */
    private void crearReferenciaConsultaTipoCambio() {
        ConsultaTipoCambioEndpointService tipoCambio;

        URL url = getURL();

        if (ObjectUtils.isEmpty(url)) {
            LOGGER.info("Creando referencia al WS con valores por defecto");
            tipoCambio = new ConsultaTipoCambioEndpointService();
        } else {
            LOGGER.info("Creando referencia al WS con URL {}", url);
            tipoCambio = new ConsultaTipoCambioEndpointService(url);
        }

        wsConsultaTipoCambio = tipoCambio.getConsultaTipoCambioEndpointPort();
    }

    /**
     * Recupera la URL de las propiedades de entorno.
     *
     * @return URL.
     */
    private URL getURL() {
        URL url = null;

        if (!ObjectUtils.isEmpty(wsdlLocation)) {
            try {
                LOGGER.info("Creando URL con {}", wsdlLocation);
                url = new URL(wsdlLocation);
            } catch (MalformedURLException e) {
                LOGGER.warn("La URL no es accesible. {}", wsdlLocation, e);
            }
        }
        return url;
    }

}
