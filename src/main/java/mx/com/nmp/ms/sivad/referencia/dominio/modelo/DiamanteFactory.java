/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.arquetipo.journal.util.ApplicationContextProvider;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorNumero;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * Fábrica que se encarga de crear objetos de tipo Diamante.
 *
 * @author ngonzalez
 */
public final class DiamanteFactory {

    private static final String CLARIDAD_NULA = "La claridad no debe ser nula.";
    private static final String COLOR_NULO = "El corte no debe ser nulo.";
    private static final String CORTE_NULO = "El corte no debe ser nulo.";
    private static final String PRECIO_NULO = "El precio no debe ser nulo.";
    private static final String TAMANIO_INFERIOR_NULO = "El tamanio inferior no debe ser nulo.";
    private static final String TAMANIO_SUPERIOR_NULO = "El tamanio superior no debe ser nulo.";

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    private static ModificadorValorDiamanteRepository repositorio;



    // METODOS

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
        Assert.notNull(corte, CORTE_NULO);
        Assert.notNull(color, COLOR_NULO);
        Assert.notNull(claridad, CLARIDAD_NULA);
        Assert.notNull(tamanioInferior, TAMANIO_INFERIOR_NULO);
        Assert.notNull(tamanioSuperior, TAMANIO_SUPERIOR_NULO);
        Assert.notNull(precio, PRECIO_NULO);

        ValidadorNumero.validarPositivo(tamanioInferior);
        ValidadorNumero.validarPositivo(tamanioSuperior);
        ValidadorNumero.validarPositivo(precio);
        return new Diamante(corte, color, claridad, tamanioInferior, tamanioSuperior, precio, repositorio);
    }



    // GETTERS

    public ModificadorValorDiamanteRepository getRepositorio() {
        if (ObjectUtils.isEmpty(repositorio)) {
            repositorio = ApplicationContextProvider.get().getBean(ModificadorValorDiamanteRepository.class);
        }

        return repositorio;
    }

}
