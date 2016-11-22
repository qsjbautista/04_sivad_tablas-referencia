/*
 *
 * Microservicios - Tipo cambiario
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.dominio.validador;

import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Clase de utilería para validar numeros.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public final class ValidadorNumero {

    /**
     * Constructor, privado ya que no debe ser instanciada.
     */
    private ValidadorNumero() {
        super();
    }

    /**
     * Método que valida si {@code valor} es mayor que cero.
     *
     * @param valor Valor numérico a validar.
     *
     * @return {@code valor} si es mayor que cero.
     *
     * @throws IllegalArgumentException Cuando {@code valor} es menor o igual que cero.
     */
    public static BigDecimal validar(BigDecimal valor) {
        Assert.notNull(valor, "El valor no debe ser nulo");

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El valor debe ser mayor que 0.");
        }

        return valor;
    }
}
