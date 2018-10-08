/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando no existe un factor de depreciacion
 *
 * @author ecancino
 */
public class FactorDepreciacionNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 3700379427227693535L;

    /**
     * Variable que contiene la clase del factor de depreciacion que genero la excepción.
     */
    private final Class<?> factorDepreciacion;

    /**
     * Constructor.
     *
     * @param factorDepreciacion Clase del factor de depreciacion que genero la excepción
     * @param mensaje Mensaje que describe la excepción.
     */
    public FactorDepreciacionNoEncontradoException(Class<?> factorDepreciacion, String mensaje) {
        super(mensaje);
        this.factorDepreciacion = factorDepreciacion;
    }

    /**
     * Método que regresa la clase del factor de depreciacion que genero la excepción.
     *
     * @return Clase del factor de depreciacion que género la excepción.
     */
    public Class<?> getFactorDepreciacion() {
        return factorDepreciacion;
    }
}
