/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

/**
 * Enum que define los tipos de metal.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public enum TipoMetalEnum {

    AU("Oro"),

    AG("Plata");

    /**
     * Nombre del metal.
     */
    private String nombre;



    // METODOS

    /**
     * Constructor.
     *
     * @param nombre El nombre del metal.
     */
    TipoMetalEnum(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el valor de {@code nombre}
     *
     * @return {@code nombre}
     */
    public String getNombre() {
        return nombre;
    }
}
