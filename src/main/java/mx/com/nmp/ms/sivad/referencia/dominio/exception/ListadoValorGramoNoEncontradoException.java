/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando el listado de valor gramo oro o el listado de valor gramo metal no existan.
 *
 * @author ngonzalez
 */
public class ListadoValorGramoNoEncontradoException extends AbstractException {



    // METODOS

    /**
     * Constructor.
     *
     * @param msg Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public ListadoValorGramoNoEncontradoException(String msg, Class<?> entidad) {
        super(msg, entidad);
    }

}
