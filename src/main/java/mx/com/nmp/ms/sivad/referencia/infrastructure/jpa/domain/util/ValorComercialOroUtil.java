/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialOroJPA;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase de utilería utilizada para realizar conversiones entre objetos de dominio y de persistencia
 * referentes a valor comercial oro.
 *
 * @author ngonzalez
 */
public final class ValorComercialOroUtil {




    // METODOS

    /**
     * Constructor.
     */
    private ValorComercialOroUtil() {
        // Utility classes should always be final and have a private constructor.
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado vigente (JPA) en listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialOro convertToListadoDeDominio(
        ListadoValorComercialOroJPA listado) {

        Set<Oro> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialOroJPA vco : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    OroFactory.create(vco.getColor(), vco.getCalidad(), vco.getPrecio()));
            }
        }

        return ListadoValorComercialOroFactory.create(
            listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado histórico (JPA) en listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialOro convertToListadoDeDominio(
        HistListadoValorComercialOroJPA listado) {

        Set<Oro> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (HistValorComercialOroJPA hvco : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    OroFactory.create(hvco.getColor(), hvco.getCalidad(), hvco.getPrecio()));
            }
        }

        return ListadoValorComercialOroFactory.create(
            listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado vigente (JPA) en listado histórico (JPA).
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en histórico.
     */
    public static HistListadoValorComercialOroJPA convertToListadoHistoricoJPA(
        ListadoValorComercialOroJPA listado) {

        HistListadoValorComercialOroJPA result = new HistListadoValorComercialOroJPA();
        result.setUltimaActualizacion(listado.getUltimaActualizacion());

        Set<HistValorComercialOroJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialOroJPA vco : listado.getValoresComerciales()) {
                HistValorComercialOroJPA hvco = new HistValorComercialOroJPA();
                hvco.setColor(vco.getColor());
                hvco.setCalidad(vco.getCalidad());
                hvco.setPrecio(vco.getPrecio());
                valoresComerciales.add(hvco);
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
    public static ListadoValorComercialOroJPA convertToListadoVigenteJPA(
        ListadoValorComercialOro listado) {

        ListadoValorComercialOroJPA result = new ListadoValorComercialOroJPA();
        result.setUltimaActualizacion(listado.getUltimaActualizacion());

        Set<ValorComercialOroJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (Oro oro : listado.getValoresComerciales()) {
                ValorComercialOroJPA vco = new ValorComercialOroJPA();
                vco.setColor(oro.getColor());
                vco.setCalidad(oro.getCalidad());
                vco.setPrecio(oro.obtenerValorGramo());
                valoresComerciales.add(vco);
            }
        }

        result.setValoresComerciales(valoresComerciales);
        return result;
    }

}
