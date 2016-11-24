/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * Excepción que será lanzada cuando no existe un valor comercial.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public class ValorComercialNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 3700389437127693545L;

    /**
     * Variable que contiene la clase del valor comercial que genero la excepción.
     */
    private final Class<?> valorComercial;

    /**
     * Constructor.
     *
     * @param valorComercial Clase del valor comercial que genero la excepción
     * @param mensaje Mensaje que describe la excepción.
     */
    public ValorComercialNoEncontradoException(Class<?> valorComercial, String mensaje) {
        super(mensaje);
        this.valorComercial = valorComercial;
    }

    /**
     * Método que regresa la clase del valor comercial que genero la excepción.
     *
     * @return Clase del valor comercial que género la excepción.
     */
    public Class<?> getValorComercial() {
        return valorComercial;
    }
}
