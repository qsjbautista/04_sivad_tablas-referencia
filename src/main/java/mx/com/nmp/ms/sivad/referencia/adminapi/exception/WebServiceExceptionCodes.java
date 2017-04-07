package mx.com.nmp.ms.sivad.referencia.adminapi.exception;

/**
 * Enumeración de los codigos de excepciones que seran manejadas en servicios.
 *
 * @author jbautista
 */
public enum WebServiceExceptionCodes {
    NMPR004("NMP-TR-004", "Error en datos de entrada."),
    NMPR007("NMP-TR-007", "No existe el rango solicitado."),
    NMPR008("NMP-TR-008", "No existe un valor gramo para las características de oro solicitadas."),
    NMPR009("NMP-TR-009", "No existe el certificado solicitado."),
    // Se modifica por petición del cliente, es mensaje será lanzado cuando
    // no exista un valor comercial para un diamante (no necesariamente por falta del quilataje).
    NMPR010("NMP-TR-010",
        "El quilataje seleccionado no corresponde a ese tipo de corte, " +
            "favor de verificar la información ingresada de acuerdo a la política de Valuar Partida sección Diamantes"),
    NMPR011("NMP-TR-011", "No existe un valor gramo para las características de metal solicitadas.");

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
