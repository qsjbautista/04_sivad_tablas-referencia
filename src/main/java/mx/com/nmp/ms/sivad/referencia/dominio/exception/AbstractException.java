/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Clase abstracta que factoriza los atributos comunes de las excepciones de dominio.
 *
 * @author ngonzalez
 */
public abstract class AbstractException extends RuntimeException {

    private static final long serialVersionUID = 827992683168782193L;

    /**
     * La entidad por la cual se desencadena la excepción.
     */
    protected Class<?> entidad;



    // METODOS

    /**
     * Constructor.
     *
     * @param msg Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public AbstractException(String msg, Class<?> entidad) {
        super(msg);
        this.entidad = entidad;
    }



    // GETTERS

    /**
     * Obtiene el valor de entidad.
     *
     * @return Valor de entidad.
     */
    public Class<?> getEntidad() {
        return entidad;
    }

}
