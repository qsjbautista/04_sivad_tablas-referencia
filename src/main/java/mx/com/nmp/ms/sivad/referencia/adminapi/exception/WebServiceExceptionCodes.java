package mx.com.nmp.ms.sivad.referencia.adminapi.exception;

/**
 * Enumeración de los codigos de excepciones que seran manejadas en servicios.
 *
 * @author jbautista
 */
public enum WebServiceExceptionCodes {
    NMPR007("NMP-TR-007","No existe el rango solicitado"),
    NMPR008("NMP-TR-008", "No existe un valor gramo para las características de oro solicitadas"),
    NMPR009("NMP-TR-009","No existe el certificado solicitado"),
    NMPR010("NMP-TR-010","No existe un valor comercial para las características de diamante solicitadas"),
    NMPR011("NMP-TR-011", "No existe el metal solicitado");

    private String codeException;
    private String messageException;

    /**
     * Constructor
     *
     * @param codeException codigo de excepcion
     */
    WebServiceExceptionCodes(String codeException, String messageException) {
        this.codeException = codeException;
        this.messageException = messageException;
    }

    /**
     * Regresa el identificador de la excpeción.
     *
     * @return identificador de excepcion.
     */
    public String getCodeException() {
        return codeException;
    }

    /**
     * Regresa el mensaje de la excepción.
     *
     * @return mensaje de excepcion.
     */
    public String getMessageException() { return messageException; }
}
