/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.*;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.*;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase de utilería utilizada para realizar conversiones entre objetos de dominio y de persistencia
 * referentes al valor comercial del oro.
 *
 * @author ngonzalez
 */
public final class ValorComercialUtil {



    // METODOS

    /**
     * Constructor.
     */
    private ValorComercialUtil() {
        // Utility classes should always be final and have a private constructor.
    }

    /**
     * Metodo auxiliar para convertir el listado de valores comerciales del metal JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialMetal convertToListadoValorComercialMetal(ListadoValorComercialMetalJPA listado) {
        Set<Metal> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialMetalJPA vcm : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    MetalFactory.create(vcm.getId(), vcm.getMetal(), vcm.getCalidad(), vcm.getPrecio()));
            }
        }

        return ListadoValorComercialMetalFactory.create(
            listado.getId(), listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar para convertir el histórico de listado de valores comerciales del metal JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialMetal convertToListadoValorComercialMetal(HistListadoValorComercialMetalJPA listado) {
        Set<Metal> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (HistValorComercialMetalJPA vcm : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    MetalFactory.create(vcm.getId(), vcm.getMetal(), vcm.getCalidad(), vcm.getPrecio()));
            }
        }

        return ListadoValorComercialMetalFactory.create(
            listado.getId(), listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar para convertir el listado de valores comerciales del oro JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialOro convertToListadoValorComercialOro(ListadoValorComercialOroJPA listado) {
        Set<Oro> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialOroJPA vco : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    OroFactory.create(vco.getId(), vco.getColor(), vco.getCalidad(), vco.getPrecio()));
            }
        }

        return ListadoValorComercialOroFactory.create(
            listado.getId(), listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar para convertir el histórico de listado de valores comerciales del oro JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialOro convertToListadoValorComercialOro(HistListadoValorComercialOroJPA listado) {
        Set<Oro> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (HistValorComercialOroJPA vco : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    OroFactory.create(vco.getId(), vco.getColor(), vco.getCalidad(), vco.getPrecio()));
            }
        }

        return ListadoValorComercialOroFactory.create(
            listado.getId(), listado.getUltimaActualizacion(), valoresComerciales);
    }

}
