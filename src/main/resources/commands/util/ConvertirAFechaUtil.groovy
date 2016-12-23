/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands.util

import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorFecha
import org.joda.time.LocalDate

/**
 * Convierte una fecha contenida en una cadena a {@link LocalDate}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
final class ConvertirAFechaUtil {
    /**
     * Convierte una fecha contenida en una cadena a {@link LocalDate}
     *
     * @param valor Cadena que contiene la fecha.
     *
     * @return {@link LocalDate}
     *
     * @throws IllegalArgumentException Si el formato no es valido.<
     */
    static LocalDate convertirAFecha(String valor) {
        LocalDate fecha

        try {
            fecha = LocalDate.parse(valor)
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            throw new IllegalArgumentException(
                "El formato de la fecha [$valor] no es valido.\n${e.getLocalizedMessage()}")
        }

        ValidadorFecha.validarFechaFutura(fecha, "La fecha de vigencia solicitada no puede ser una fecha futura.")
    }
}
