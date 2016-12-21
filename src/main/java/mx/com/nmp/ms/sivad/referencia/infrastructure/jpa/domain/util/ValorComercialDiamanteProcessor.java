package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.RegistroDiamanteBatchProcessorException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.ActualizarListaValorComercialRequest;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.PrecioCorte;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.PrecioCorteDetalle;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValorComercialDiamanteProcessor
    implements ItemProcessor<Map<String, PrecioCorteDetalle>, Diamante>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialDiamanteProcessor.class);

    private final String _COLOR = "Color";
    private final String _CLARITY = "Clarity";
    private final String _SHAPE = "Shape";


    @Override
    public Diamante process(final Map<String, PrecioCorteDetalle> precioCortes) throws Exception {
        Diamante diamante = null;
        PrecioCorteDetalle precioCorteDetalle = null;
        try {
            for (Map.Entry<String, PrecioCorteDetalle> corteDiamante : precioCortes.entrySet()) {
                String corte = corteDiamante.getKey();
                precioCorteDetalle = corteDiamante.getValue();
                if (precioCorteDetalle != null) {
                        diamante = DiamanteFactory.create(validaString(corte, _SHAPE),
                            validaString(precioCorteDetalle.getColor(), _COLOR),
                            validaString(precioCorteDetalle.getClaridad(), _CLARITY),
                            precioCorteDetalle.getTamanioInferior(),
                            precioCorteDetalle.getTamanioSuperior(),
                            precioCorteDetalle.getPrecio());
                }
            }
        } catch (Exception e) {
            String mensaje ="<< " + e.getMessage() + " "
                + "Registor: {" + precioCorteDetalle.getColor()+", " +
                    precioCorteDetalle.getClaridad() +", " +
                    precioCorteDetalle.getTamanioInferior() +", " +
                    precioCorteDetalle.getTamanioSuperior() +", " +
                    precioCorteDetalle.getPrecio()+"}";
            LOGGER.info(WebServiceExceptionCodes.NMPR004.getMessageException() + "." +mensaje);
            throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getMessageException(), mensaje);
        }
        LOGGER.debug("<< process(): {}", diamante);
        return diamante;
    }

    /**
     * Se encarga de validar que la informacion de los campos sean correctos y tengan la longitud adecuada.
     *
     * @param cadena Contiene la informacion del campo a validar.
     * @param field Contiene el nombre del campo que se va a validar
     *
     * @return Regresa la cadena que fue validada.
     */
    private String validaString(String cadena, String field ) {
        int tamanio;
        if(!cadena.isEmpty()) {
            if(field.equalsIgnoreCase(_COLOR)) {
                tamanio = 1;
            } else if ( field.equalsIgnoreCase(_CLARITY)){
                tamanio = 4;
            } else {
                tamanio = 60;
            }
            if(cadena.length() > tamanio) {
                throw new IllegalArgumentException("El campo=" +
                    field + " contiene un tamanio superior al permitido.");
            }
        } else{
            throw new IllegalArgumentException("El campo=" +
                field + " no contiene un valor.");
        }
        return cadena;
    }
 
}

