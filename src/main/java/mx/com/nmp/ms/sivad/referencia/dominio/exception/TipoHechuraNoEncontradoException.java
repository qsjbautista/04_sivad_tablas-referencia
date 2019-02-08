/**
 * Proyecto:        NMP - Sistema de Operacion Prendaria Emergente
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que sera lanzada cuando no existe un tipo de hechura, segun el metal, calidad y subramo correspondientes.
 *
 * @author ecancino
 */
public class TipoHechuraNoEncontradoException extends AbstractException {

    /**
     * Constructor.
     *
     * @param msg     Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public TipoHechuraNoEncontradoException(String msg, Class<?> entidad) {
        super(msg, entidad);
    }
}
