package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class ValorComercialDiamanteProcessor
    implements ItemProcessor<PrecioCorteDetalleBatch, Diamante> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialDiamanteProcessor.class);

    private final String _COLOR = "Color";
    private final String _CLARITY = "Clarity";
    private final String _SHAPE = "Shape";


    @Override
    public Diamante process(final PrecioCorteDetalleBatch precioCorteDetalle) throws Exception {
        Diamante diamante = null;
        try {

            if (precioCorteDetalle != null) {
                String corte = precioCorteDetalle.getCorte();
                diamante = DiamanteFactory.create(validaString(corte, _SHAPE),
                    validaString(precioCorteDetalle.getColor(), _COLOR),
                    validaString(precioCorteDetalle.getClaridad(), _CLARITY),
                    precioCorteDetalle.getTamanioInferior(),
                    precioCorteDetalle.getTamanioSuperior(),
                    precioCorteDetalle.getPrecio());
            }

        } catch (Exception e) {
            String mensaje = e.getMessage() + " "
                + "Registro: {" + precioCorteDetalle.getColor() + ", " +
                precioCorteDetalle.getClaridad() + ", " +
                precioCorteDetalle.getTamanioInferior() + ", " +
                precioCorteDetalle.getTamanioSuperior() + ", " +
                precioCorteDetalle.getPrecio() + "}";
            LOGGER.info(WebServiceExceptionCodes.NMPR004.getMessageException() + "." + mensaje);
            throw new IllegalArgumentException(mensaje);
        }
        LOGGER.debug("<< process(): {}", diamante);
        return diamante;
    }

    /**
     * Se encarga de validar que la informacion de los campos sean correctos y tengan la longitud adecuada.
     *
     * @param cadena Contiene la informacion del campo a validar.
     * @param field  Contiene el nombre del campo que se va a validar
     * @return Regresa la cadena que fue validada.
     */
    private String validaString(String cadena, String field) {
        int tamanio;
        if (!cadena.isEmpty()) {
            if (field.equalsIgnoreCase(_COLOR)) {
                tamanio = 1;
            } else if (field.equalsIgnoreCase(_CLARITY)) {
                tamanio = 4;
            } else {
                tamanio = 60;
            }
            if (cadena.length() > tamanio) {
                throw new IllegalArgumentException("El campo=" +
                    field + " contiene un tamanio superior al permitido.");
            }
        } else {
            throw new IllegalArgumentException("El campo=" +
                field + " no contiene un valor.");
        }
        return cadena;
    }

}

