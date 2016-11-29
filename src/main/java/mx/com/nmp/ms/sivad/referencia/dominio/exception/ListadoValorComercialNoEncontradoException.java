/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando el listado de valor comercial del diamante no exista.
 *
 * @author ngonzalez
 */
public class ListadoValorComercialNoEncontradoException extends AbstractException {



    // METODOS

    /**
     * Constructor.
     *
     * @param msg Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public ListadoValorComercialNoEncontradoException(String msg, Class<?> entidad) {
        super(msg, entidad);
    }

}
