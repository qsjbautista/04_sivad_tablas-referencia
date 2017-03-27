/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.dominio.validador;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.TipoMetalEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Clase de utilería para validar, si para un metal es requerida la calidad.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public final class ValidadorCalidadMetal {
    /**
     * Contiene los metales para los cuales es requerida la calidad.
     */
    private static final List<String> listaMetalesCalidad;

    static {
        listaMetalesCalidad = Arrays.asList(TipoMetalEnum.AU.name(), TipoMetalEnum.AG.name());
    }

    /**
     * Constructor, privado ya que no debe ser instanciada.
     */
    private ValidadorCalidadMetal() {
        super();
    }

    public static boolean validar(String metal, String calidad) {
        return !listaMetalesCalidad.contains(metal) || StringUtils.isNotBlank(calidad);
    }

    public static boolean validarMetalRequiereCalidad(String metal) {
        return listaMetalesCalidad.contains(metal);
    }
}
