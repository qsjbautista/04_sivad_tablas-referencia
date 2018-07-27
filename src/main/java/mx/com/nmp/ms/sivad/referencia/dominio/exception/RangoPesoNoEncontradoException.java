/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando no existe un rango de peso para el diamante.
 *
 * @author ecancino
 */
public class RangoPesoNoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 3700389427127594525L;

    /**
     * Variable que contiene la clase del rango de peso que genero la excepción.
     */
    private final Class<?> rangoPeso;

    /**
     * Constructor.
     *
     * @param rangoPeso Clase del rango de peso que genero la excepción
     * @param mensaje Mensaje que describe la excepción.
     */
    public RangoPesoNoEncontradoException(Class<?> rangoPeso, String mensaje) {
        super(mensaje);
        this.rangoPeso = rangoPeso;
    }

    /**
     * Método que regresa la clase del rango de peso que genero la excepción.
     *
     * @return Clase del rango de peso que género la excepción.
     */
    public Class<?> getRangoPeso() {
        return rangoPeso;
    }
}
