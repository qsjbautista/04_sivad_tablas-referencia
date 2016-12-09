package mx.com.nmp.ms.sivad.referencia.conector.referencia.factory;

import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ConvertirRequest;
import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ObjectFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Se encarga de crear una referencia hacia el Servicio Web Convertidor
 *
 * @author <a href="https://wiki.quarksoft.net/display/~roramirez">Roberto Omar Ramirez Torres</a>
 */
@Component
public class ReferenciaConvertidorFactory {
    /**
     * Referencia al creador de objetos.
     * @see ObjectFactory
     */
    private ObjectFactory objectFactory;

    /**
     * Constructor.
     */
    public ReferenciaConvertidorFactory() {
        super();
        objectFactory = new ObjectFactory();
    }

    /**
     * Crea una instancia de {@link ConvertirRequest}
     *
     * @param base Objeto que contiene los criterios de consulta.
     * @param destino Objeto que contiene los criterios de consulta.
     * @param monto Objeto que contiene los criterios de consulta.
     *
     * @return La instancia de {@link ConvertirRequest}
     */
    public ConvertirRequest crearConvertirRequest(final String base, final String destino, final BigDecimal monto   ){
        ConvertirRequest convertir = objectFactory.createConvertirRequest();

        convertir.setBase(base);
        convertir.setDestino(destino);
        convertir.setMonto(monto);

        return convertir;
    }

}
