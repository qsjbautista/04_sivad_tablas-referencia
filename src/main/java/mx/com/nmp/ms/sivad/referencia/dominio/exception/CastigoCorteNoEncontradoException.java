/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando no existe un castigo por corte
 *
 * @author ecancino
 */
public class CastigoCorteNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 3700379437227793545L;

    /**
     * Variable que contiene la clase del castigo por corte que genero la excepción.
     */
    private final Class<?> castigoCorte;

    /**
     * Constructor.
     *
     * @param castigoCorte Clase del castigo por corte que genero la excepción
     * @param mensaje Mensaje que describe la excepción.
     */
    public CastigoCorteNoEncontradoException(Class<?> castigoCorte, String mensaje) {
        super(mensaje);
        this.castigoCorte = castigoCorte;
    }

    /**
     * Método que regresa la clase del castigo por corte que genero la excepción.
     *
     * @return Clase del castigo por corte que género la excepción.
     */
    public Class<?> getCastigoCorte() {
        return castigoCorte;
    }
}
