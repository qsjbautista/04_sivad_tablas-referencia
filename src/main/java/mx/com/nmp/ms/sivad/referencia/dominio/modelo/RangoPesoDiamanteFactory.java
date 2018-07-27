/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorNumero;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * Fábrica que se encarga de crear objetos de tipo RangoPesoDiamante.
 *
 * @author ecancino
 */
public final class RangoPesoDiamanteFactory {

    private static final String TAMANIO_INFERIOR_NULO = "El tamanio inferior no debe ser nulo.";
    private static final String TAMANIO_SUPERIOR_NULO = "El tamanio superior no debe ser nulo.";



    // METODOS

    /**
     * Permite crear una entidad de tipo RangoPesoDiamante con base en los argumentos recibidos.
     *
     * @param tamanioInferior Tamaño inferior en quilates que abarca el diamante.
     * @param tamanioSuperior Tamaño superior en quilates que abarca el diamante.
     * @return La entidad creada.
     */
    public static RangoPesoDiamante create(BigDecimal tamanioInferior, BigDecimal tamanioSuperior) {
        Assert.notNull(tamanioInferior, TAMANIO_INFERIOR_NULO);
        Assert.notNull(tamanioSuperior, TAMANIO_SUPERIOR_NULO);

        ValidadorNumero.validarPositivo(tamanioInferior);
        ValidadorNumero.validarPositivo(tamanioSuperior);
        return new RangoPesoDiamante(tamanioInferior, tamanioSuperior);
    }

}
