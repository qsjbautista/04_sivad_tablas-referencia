/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando la consulta de los listados sea por una fecha de vigencia posterior a
 * la fecha actual.
 *
 * @author ngonzalez
 */
public class FechaVigenciaFuturaException extends RuntimeException {

    private static final long serialVersionUID = 827992683123784193L;

    private Class<?> entidad;



    // METODOS

    /**
     * Constructor.
     *
     * @param msg Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public FechaVigenciaFuturaException(String msg, Class<?> entidad) {
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
