/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando se intente actualizar el listado de valores comerciales de diamantes con
 * una lista de valores vacía.
 *
 * @author ngonzalez
 */
public class ListadoValorComercialSinElementosException extends AbstractException {



    // METODOS

    /**
     * Constructor.
     *
     * @param msg Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public ListadoValorComercialSinElementosException(String msg, Class<?> entidad) {
        super(msg, entidad);
    }

}
