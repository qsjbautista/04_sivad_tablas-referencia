package mx.com.nmp.ms.sivad.referencia.conector;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ConvertirRequest;
import mx.com.nmp.ms.sivad.cambiario.ws.convertidor.datatypes.ConvertirResponse;
import mx.com.nmp.ms.sivad.referencia.conector.referencia.ReferenciaConvertidorConector;
import mx.com.nmp.ms.sivad.referencia.conector.referencia.factory.ReferenciaConvertidorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Interface que convierte las divisas  para las fachadas hacia el Micro Servicio de Tipo Cambiario.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~roramirez">Roberto Omar Ramirez Torres</a>
 */
@Component
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ConvertidorProxy implements Convertidor{
    private Logger LOGGER = LoggerFactory.getLogger(ConvertidorProxy.class);

    /**
     * Referencia al conector hacia el Servicio Web Convertidor
     */
    @Inject
    ReferenciaConvertidorConector referenciaConvertidorConector;

    /**
     * Referencia a la fabrica de Tipos de Datos del Servicio Web convertidor
     */
    @Inject
    private ReferenciaConvertidorFactory referenciaConvertidorFactory;

    /**
     * Permite obtener el valor por divisa de acuerdo al tipo de cambio que se requiere
     *
     * @param base Divisa Base
     * @param destino Divisa Destino a convertir
     * @param monto Monto total a convertir
     *
     * @return BigDecimal Objeto que contiene la informción de la respuesta.
     */
    @Override
    public BigDecimal convertir(@NotNull String base, @NotNull String destino, @NotNull BigDecimal monto) {
        LOGGER.info(">> convertir(base {}, destino{}, monto{})", base, destino, monto);

        ConvertirRequest convertirRequest = referenciaConvertidorFactory.crearConvertirRequest(base, destino, monto);
        ConvertirResponse convertirResponse = referenciaConvertidorConector.getWsReferenciaTipoCambio()
            .convertir(convertirRequest);

        return convertirResponse.getConversion();
    }

}
