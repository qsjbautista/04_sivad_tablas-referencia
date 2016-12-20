/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
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
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Realiza el procesamiento de los valores diamante recibidos por el WS.
 *
 * @author mmarquez
 */
@Component
public class ValorComercialDiamanteBatchProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialDiamanteBatchProcessor.class);

    private final String _COLOR = "Color";
    private final String _CLARITY = "Clarity";
    private final String _SHAPE = "Shape";


    /**
     * Metodo encargado de validar y transformar la informacion para que se pueda ser guardada por la entidad ListadoValorComercialDiamante.
     *
     * @param parameters Contiene la informacion de los valores comerciales de los diamantes.
     */
    public void procesa(ActualizarListaValorComercialRequest parameters) {
        LOGGER.info(">> procesa({})", parameters);
        int i = 1;
        int j = 1;
        if (parameters.getListado() != null) {
            try {
                LocalDate fechaListado = new LocalDate(parameters.getListado().getFecha().toGregorianCalendar().getTime());
                Set<Diamante> diamantes = new HashSet<>();
                if (parameters.getListado().getPreciosCorte() != null || !parameters.getListado().getPreciosCorte().isEmpty()) {
                    for (PrecioCorte precioCorte : parameters.getListado().getPreciosCorte()) {
                        LOGGER.debug("precioCorte: {}", precioCorte.getCorte());
                        if (precioCorte != null) {
                            for (PrecioCorteDetalle precioCorteDetalle : precioCorte.getPrecioCorte()) {
                                if (precioCorteDetalle != null) {
                                    try {
                                        Diamante diamante = DiamanteFactory.create(validaString(precioCorte.getCorte(), _SHAPE),
                                            validaString(precioCorteDetalle.getColor(), _COLOR),
                                            validaString(precioCorteDetalle.getClaridad(), _CLARITY),
                                            precioCorteDetalle.getTamanioInferior(),
                                            precioCorteDetalle.getTamanioSuperior(),
                                            precioCorteDetalle.getPrecio());
                                        if (diamantes.contains(diamante)) {
                                            final String mensaje = "El Corte:" + precioCorte.getCorte() +
                                                ". El elemento " + j + " ya existe en el listado.";
                                            LOGGER.info("<< " + mensaje);
                                            throw new RegistroDiamanteBatchProcessorException(mensaje, Diamante.class);
                                        }
                                        diamantes.add(diamante);
                                    } catch (IllegalArgumentException e) {
                                        final String mensaje = "El Corte:" + precioCorte.getCorte() +
                                            ". En el elemento " + j + " contiene elementos no validos.";
                                        LOGGER.info("<< {}", mensaje, e);
                                        throw new RegistroDiamanteBatchProcessorException(mensaje,
                                            Diamante.class);
                                    }

                                } else {
                                    final String mensaje = "El Corte:" + precioCorte.getCorte() +
                                        ". En el elemento " + j + " no contiene datos.";
                                    LOGGER.info("<< " + mensaje);
                                    throw new RegistroDiamanteBatchProcessorException(mensaje, Diamante.class);
                                }
                                j++;
                            }
                        } else {
                            final String mensaje = "El elemento " + i + " no contiene datos.";
                            LOGGER.info("<< " + mensaje);
                            throw new RegistroDiamanteBatchProcessorException(mensaje, Diamante.class);
                        }
                        i++;
                    }
                } else {
                    final String mensaje = "La Lista de Precios esta vacia o es nula.";
                    LOGGER.info("<< {}", mensaje);
                    throw new RegistroDiamanteBatchProcessorException(mensaje, Diamante.class);
                }
                ListadoValorComercialDiamante listadoValorComercialDiamante = ListadoValorComercialDiamanteFactory.create(fechaListado,
                    diamantes);
                listadoValorComercialDiamante.actualizar();
            } catch (Exception e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR004.getMessageException() + "." + e.getMessage());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getMessageException(), e.getMessage());
            }
        }
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
                    field + " contiene un tamaño superior al permitido.");
            }
        } else {
            throw new IllegalArgumentException("El campo=" +
                field + " no contiene un valor.");
        }
        return cadena;
    }

}
