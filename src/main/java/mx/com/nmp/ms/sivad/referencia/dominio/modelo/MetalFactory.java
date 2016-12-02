/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorNumero;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Fábrica que se encarga de crear objetos de tipo Metal.
 *
 * @author ngonzalez
 */
public final class MetalFactory {

    private static final String METAL_NULO = "El metal no debe ser nulo.";
    private static final String PRECIO_NULO = "El precio no debe ser nulo.";



    // METODOS

    /**
     * Permite crear una entidad de tipo Metal con base en los argumentos recibidos.
     *
     * @param metal El tipo del metal.
     * @param calidad Valor aplicable a la calidad del metal (Ejemplo: 0.925).
     * @param precio Precio en pesos del metal por gramo.
     * @return La entidad creada.
     */
    public static Metal create(String metal, String calidad, BigDecimal precio) {
        Assert.notNull(metal, METAL_NULO);
        Assert.notNull(precio, PRECIO_NULO);

        ValidadorNumero.validarPositivo(precio);
        return new Metal(metal, calidad, precio);
    }

}
