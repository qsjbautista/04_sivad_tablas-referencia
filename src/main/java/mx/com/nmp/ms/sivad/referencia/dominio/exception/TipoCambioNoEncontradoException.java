/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando no existe un tipo de cambio
 *
 * @author ecancino
 */
public class TipoCambioNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 3700379327226693545L;

    /**
     * Variable que contiene la clase del tipo de cambio que genero la excepción.
     */
    private final Class<?> tipoCambio;

    /**
     * Constructor.
     *
     * @param tipoCambio Clase del tipo de cambio que genero la excepción
     * @param mensaje Mensaje que describe la excepción.
     */
    public TipoCambioNoEncontradoException(Class<?> tipoCambio, String mensaje) {
        super(mensaje);
        this.tipoCambio = tipoCambio;
    }

    /**
     * Método que regresa la clase del tipo de cambio que genero la excepción.
     *
     * @return Clase del tipo de cambio que género la excepción.
     */
    public Class<?> getTipoCambio() {
        return tipoCambio;
    }
}
