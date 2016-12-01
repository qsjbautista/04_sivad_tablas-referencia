package mx.com.nmp.ms.sivad.referencia.adminapi.exception;

import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * Excepción que será lanzada cuando ocurra algún error en la obtención de información de tablas de referencia.
 *
 * @author jbautista
 */
public class WebServiceException extends SOAPFaultException {
    private static final long serialVersionUID = -3148483076881584839L;

    /**
     * Constructor.
     *
     * @param falla <code>SOAPFault</code> representando la falla.
     */
    public WebServiceException(SOAPFault falla) {
        super(falla);
    }
}
