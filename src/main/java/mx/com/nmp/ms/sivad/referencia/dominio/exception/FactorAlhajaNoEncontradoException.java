/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando no existen resultados en la consulta.
 *
 * @author mmarquez
 */
public class FactorAlhajaNoEncontradoException extends RuntimeException {


    private static final long serialVersionUID = -6218590165456978131L;
    private Class<?> entidad;


    // METODOS

    /**
     * Constructor.
     *
     * @param msg Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public FactorAlhajaNoEncontradoException(String msg, Class<?> entidad) {
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
