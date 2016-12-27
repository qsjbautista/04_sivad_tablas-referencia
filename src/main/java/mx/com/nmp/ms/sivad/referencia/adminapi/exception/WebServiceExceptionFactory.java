package mx.com.nmp.ms.sivad.referencia.adminapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;

/**
 * Fabrica para crear excepciones tipo {@link WebServiceException}
 */
public final class WebServiceExceptionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceExceptionFactory.class);

    /**
     * Constructor. Privado para que no se puedan crear objetos.
     */
    private WebServiceExceptionFactory() {
        super();
    }

    /**
     * Crea excepción tipo {@link WebServiceException}
     *
     * @param codigoError Texto empleado para indicar el tipo de error.
     * @param mensaje Texto empleado para explicar el tipo de error.
     *
     * @return Excepción creada.
     */
    public static WebServiceException crearWebServiceExceptionCon(String codigoError, String mensaje) {
        return new WebServiceException(getFalla(codigoError, mensaje));
    }

    /**
     * Crea excepción tipo {@link WebServiceException}
     *
     * @param codigoError Texto empleado para indicar el tipo de error.
     * @param mensaje Texto empleado para explicar el tipo de error.
     * @param actor Texto empleado para indicar quien causo el error.
     *
     * @return Excepción creada.
     */
    public static WebServiceException crearWebServiceExceptionCon(String codigoError, String mensaje, Throwable actor) {
        return new WebServiceException(getFalla(codigoError, mensaje, actor.getMessage()));
    }

    /**
     * Crea excepción tipo {@link WebServiceException}
     *
     * @param codigoError Texto empleado para indicar el tipo de error.
     * @param mensaje Texto empleado para explicar el tipo de error.
     * @param actor Texto empleado para indicar quien causo el error.
     *
     * @return Excepción creada.
     */
    public static WebServiceException crearWebServiceExceptionCon(String codigoError, String mensaje, String actor) {
        return new WebServiceException(getFalla(codigoError, mensaje, actor));
    }

    /**
     *
     * @param codigoError Texto empleado para indicar el tipo de error.
     * @param mensaje Texto empleado para explicar el tipo de error.
     *
     * @return <code>SOAPFault</code> representando la falla.
     */
    private static SOAPFault getFalla(String codigoError, String mensaje) {
        QName faultCode = new QName("", codigoError);
        SOAPFault falla = null;

        try {
            SOAPFactory factory = SOAPFactory.newInstance();
            falla = factory.createFault(mensaje, faultCode);
        } catch (SOAPException e) {
            LOGGER.warn(e.getMessage(), e);
        }

        return falla;
    }

    /**
     *
     * @param codigoError Texto empleado para indicar el tipo de error.
     * @param mensaje Texto empleado para explicar el tipo de error.
     * @param actor Texto empleado para indicar quien causo el error.
     *
     * @return <code>SOAPFault</code> representando la falla.
     */
    private static SOAPFault getFalla(String codigoError, String mensaje, String actor) {
        SOAPFault falla = getFalla(codigoError, mensaje);

        try {
            falla.setFaultActor(actor);
        } catch (SOAPException e) {
            LOGGER.warn(e.getMessage(), e);
        }

        return falla;
    }
}
