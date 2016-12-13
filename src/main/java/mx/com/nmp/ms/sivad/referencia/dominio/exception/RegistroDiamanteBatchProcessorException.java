/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando un registro del archivo rapaport es incorrecto.
 *
 * @author mmarquez
 */
public class RegistroDiamanteBatchProcessorException extends AbstractException {



    // METODOS

    /**
     * Constructor.
     *
     * @param msg Mensaje informativo.
     * @param entidad Entidad objetivo.
     */
    public RegistroDiamanteBatchProcessorException(String msg, Class<?> entidad) {
        super(msg, entidad);
    }

}
