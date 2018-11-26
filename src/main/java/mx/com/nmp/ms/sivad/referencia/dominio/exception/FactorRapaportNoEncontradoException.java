/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando no existe un factor de rapaport
 *
 * @author ecancino
 */
public class FactorRapaportNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 3700379427227693535L;

    /**
     * Variable que contiene la clase del factor de rapaport que genero la excepción.
     */
    private final Class<?> factorRapaport;

    /**
     * Constructor.
     *
     * @param factorRapaport Clase del factor de rapaport que genero la excepción
     * @param mensaje Mensaje que describe la excepción.
     */
    public FactorRapaportNoEncontradoException(Class<?> factorRapaport, String mensaje) {
        super(mensaje);
        this.factorRapaport = factorRapaport;
    }

    /**
     * Método que regresa la clase del factor de rapaport que genero la excepción.
     *
     * @return Clase del factor de rapaport que género la excepción.
     */
    public Class<?> getFactorRapaport() {
        return factorRapaport;
    }
}
