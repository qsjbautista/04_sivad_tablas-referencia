/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando se intente actualizar el listado con una lista de valores vacía.
 *
 * @author ngonzalez
 */
public class ListadoValorGramoSinElementosException extends RuntimeException {

    private static final long serialVersionUID = 827992683199784193L;

    private Class<?> entidad;



    // METODOS

    /**
     * Constructor.
     *
     * @param msg Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public ListadoValorGramoSinElementosException(String msg, Class<?> entidad) {
        super(msg);
        this.entidad = entidad;
    }

    /**
     * Obtiene el valor de entidad.
     *
     * @return Valor de entidad.
     */
    public Class<?> getEntidad() {
        return entidad;
    }

}
