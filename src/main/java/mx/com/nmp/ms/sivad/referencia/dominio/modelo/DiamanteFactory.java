/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;

/**
 * Fábrica que se encarga de crear objetos de tipo Diamante.
 *
 * @author ngonzalez
 */
public final class DiamanteFactory {

    /**
     * Permite crear una entidad de tipo Diamante con base en los argumentos recibidos.
     *
     * @param corte El tipo de corte del diamante.
     * @param color El tipo de color del diamante.
     * @param claridad El tipo de claridad del diamante.
     * @param tamanioInferior Tamaño inferior en quilates que abarca el valor comercial.
     * @param tamanioSuperior Tamaño superior en quilates que abarca el valor comercial.
     * @param precio Precio en dólares del diamante.
     * @return La entidad creada.
     */
    public static Diamante create(String corte, String color, String claridad, BigDecimal tamanioInferior,
                                  BigDecimal tamanioSuperior, BigDecimal precio) {
        return new Diamante(corte, color, claridad, tamanioInferior, tamanioSuperior, precio);
    }

}
