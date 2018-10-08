/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando no existe un castigo por rango de peso
 *
 * @author ecancino
 */
public class CastigoRangoPesoNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 3700378447217792545L;

    /**
     * Variable que contiene la clase del castigo por rango de peso que genero la excepción.
     */
    private final Class<?> castigoRangoPeso;

    /**
     * Constructor.
     *
     * @param castigoRangoPeso Clase del castigo por rango de peso que genero la excepción
     * @param mensaje Mensaje que describe la excepción.
     */
    public CastigoRangoPesoNoEncontradoException(Class<?> castigoRangoPeso, String mensaje) {
        super(mensaje);
        this.castigoRangoPeso = castigoRangoPeso;
    }

    /**
     * Método que regresa la clase del castigo por rango de peso que genero la excepción.
     *
     * @return Clase del castigo por rango de peso que género la excepción.
     */
    public Class<?> getCastigoRangoPeso() {
        return castigoRangoPeso;
    }
}
