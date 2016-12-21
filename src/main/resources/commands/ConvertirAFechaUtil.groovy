package commands

import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorFecha
import org.joda.time.LocalDate

/**
 *
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
final class ConvertirAFechaUtil {
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
