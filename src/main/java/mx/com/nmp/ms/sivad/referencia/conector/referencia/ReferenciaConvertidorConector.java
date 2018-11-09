package mx.com.nmp.ms.sivad.referencia.conector.referencia;


import mx.com.nmp.ms.sivad.cambiario.api.ws.ConvertidorTipoCambioEndpointService;
import mx.com.nmp.ms.sivad.cambiario.api.ws.ConvertidorTipoCambioService;
import mx.com.nmp.ms.sivad.referencia.security.WSSecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Se encarga de crear una referencia hacia el Servicio Web Convertidor
 *
 * @author <a href="https://wiki.quarksoft.net/display/~roramirez">Roberto Omar Ramirez Torres</a>
 */
@Component
public class ReferenciaConvertidorConector {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaConvertidorConector.class);

    /**
     * Contienen la URL donde se encuentra publicado el Servicio Web Referencia de Alhajas.
     */
    @Value("${tipocambio.convertidor.wsdlLocation}")
    private String wsdlLocation;

    /**
     * Header name
     */
    @Value("${tipocambio.header.api.name}")
    private String apiName;

    /**
     * Token value
     */
    @Value("${tipocambio.header.api.key}")
    private String apiKey;

    /**
     * Referencia hacia el Servicio Web
     */
    private ConvertidorTipoCambioService wsReferenciaTipoCambio;

    /**
     * Regresa la referencia hacia el Servicio Web Referencia de Alhajas.
     *
     * @return Referencia hacia el Servicio Web
     */
    public ConvertidorTipoCambioService getWsReferenciaTipoCambio() {
        if (ObjectUtils.isEmpty(wsReferenciaTipoCambio)) {
            crearReferenciaConvertidorTipoCambio();
        }

        LOGGER.info("Recuperando referencia al WS.");
        return wsReferenciaTipoCambio;
    }

    /**
     * Crea una referencia hacia el Servicio Web
     */
    private void crearReferenciaConvertidorTipoCambio() {
        ConvertidorTipoCambioEndpointService tipoCambio;

        URL url = getURL();

        if (ObjectUtils.isEmpty(url)) {
            LOGGER.info("Creando referencia al WS con valores por defecto");
            tipoCambio = new ConvertidorTipoCambioEndpointService();
        } else {
            LOGGER.info("Creando referencia al WS con URL {}", url);
            tipoCambio = new ConvertidorTipoCambioEndpointService(url);
        }

        wsReferenciaTipoCambio = tipoCambio.getConvertidorTipoCambioEndpointPort();
        
        WSSecurityUtils.addHttpAPIKeyHeader(wsReferenciaTipoCambio, apiName, apiKey, "http://nmp.com.mx/ms/sivad/cambiario/ws/convertidor/");

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
