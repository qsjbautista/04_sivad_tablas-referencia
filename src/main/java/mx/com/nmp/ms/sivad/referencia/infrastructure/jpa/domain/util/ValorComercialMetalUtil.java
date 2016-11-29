/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetalFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.MetalFactory;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialMetalJPA;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase de utilería utilizada para realizar conversiones entre objetos de dominio y de persistencia
 * referentes a valor comercial metal.
 *
 * @author ngonzalez
 */
public final class ValorComercialMetalUtil {



    // METODOS

    /**
     * Constructor.
     */
    private ValorComercialMetalUtil() {
        // Utility classes should always be final and have a private constructor.
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado vigente (JPA) en listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialMetal convertToListadoDeDominio(
        ListadoValorComercialMetalJPA listado) {

        Set<Metal> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialMetalJPA vcm : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    MetalFactory.create(vcm.getMetal(), vcm.getCalidad(), vcm.getPrecio()));
            }
        }

        return ListadoValorComercialMetalFactory.create(
            listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado histórico (JPA) en listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialMetal convertToListadoDeDominio(
        HistListadoValorComercialMetalJPA listado) {

        Set<Metal> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (HistValorComercialMetalJPA hvcm : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    MetalFactory.create(hvcm.getMetal(), hvcm.getCalidad(), hvcm.getPrecio()));
            }
        }

        return ListadoValorComercialMetalFactory.create(
            listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado vigente (JPA) en listado histórico (JPA).
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en histórico.
     */
    public static HistListadoValorComercialMetalJPA convertToListadoHistoricoJPA(
        ListadoValorComercialMetalJPA listado) {

        HistListadoValorComercialMetalJPA result = new HistListadoValorComercialMetalJPA();
        result.setUltimaActualizacion(listado.getUltimaActualizacion());

        Set<HistValorComercialMetalJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialMetalJPA vcm : listado.getValoresComerciales()) {
                HistValorComercialMetalJPA hvcm = new HistValorComercialMetalJPA();
                hvcm.setMetal(vcm.getMetal());
                hvcm.setCalidad(vcm.getCalidad());
                hvcm.setPrecio(vcm.getPrecio());
                hvcm.setListado(result);
                valoresComerciales.add(hvcm);
            }
        }

        result.setValoresComerciales(valoresComerciales);
        return result;
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado de dominio en listado vigente (JPA).
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en vigente.
     */
    public static ListadoValorComercialMetalJPA convertToListadoVigenteJPA(
        ListadoValorComercialMetal listado) {

        ListadoValorComercialMetalJPA result = new ListadoValorComercialMetalJPA();
        result.setUltimaActualizacion(listado.getUltimaActualizacion());

        Set<ValorComercialMetalJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (Metal metal : listado.getValoresComerciales()) {
                ValorComercialMetalJPA vcm = new ValorComercialMetalJPA();
                vcm.setMetal(metal.getMetal());
                vcm.setCalidad(metal.getCalidad());
                vcm.setPrecio(metal.obtenerValorGramo());
                vcm.setListado(result);
                valoresComerciales.add(vcm);
            }
        }

        result.setValoresComerciales(valoresComerciales);
        return result;
    }

}
