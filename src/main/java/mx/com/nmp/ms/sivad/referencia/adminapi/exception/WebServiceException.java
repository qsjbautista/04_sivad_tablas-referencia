package mx.com.nmp.ms.sivad.referencia.adminapi.exception;

/**
 * Excepción que será lanzada cuando ocurra algún error en la obtención de información de tablas de referencia.
 *
 * @author jbautista
 */
public class WebServiceException extends RuntimeException {


    /**
     * Variable que contiene el código de error de la excepción.
     */
    private final String codigoError;

    /**
     * Constructor.
     *
     * @param codigoError Código de error de la excepción.
     * @param mensaje Mensaje que descibe el error.
     */
    public WebServiceException(String codigoError, String mensaje) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    /**
     * Constructor.
     *
     * @param codigoError Código de error de la excepción.
     * @param mensaje Mensaje que describe el error.
     * @param causa Excepción que origino el error.
     */
    public WebServiceException(String codigoError, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
    }

    /**
     * Método que regresa el código de error de la excepción.
     *
     * @return Código de error de la excepción.
     */
    public String getCodigoError() {
        return codigoError;
    }

}
