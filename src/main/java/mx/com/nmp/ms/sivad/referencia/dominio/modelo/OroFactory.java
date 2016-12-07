/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorNumero;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Fábrica que se encarga de crear objetos de tipo Oro.
 *
 * @author ngonzalez
 */
public final class OroFactory {

    private static final String CALIDAD_NULO = "La calidad no debe ser nula.";
    private static final String COLOR_NULO = "El color no debe ser nulo.";
    private static final String PRECIO_NULO = "El precio no debe ser nulo.";



    // METODOS

    /**
     * Permite crear una entidad de tipo Oro con base en los argumentos recibidos.
     *
     * @param color El color del oro.
     * @param calidad La calidad del oro.
     * @param precio Precio en pesos del oro por gramo.
     * @return La entidad creada.
     */
    public static Oro create(String color, String calidad, BigDecimal precio) {
        Assert.notNull(color, COLOR_NULO);
        Assert.notNull(calidad, CALIDAD_NULO);
        Assert.notNull(precio, PRECIO_NULO);

        ValidadorNumero.validarPositivo(precio);
        return new Oro(color, calidad, precio);
    }

}
