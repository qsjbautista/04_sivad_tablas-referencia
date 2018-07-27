/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase de utilería utilizada para realizar conversiones entre objetos de dominio y de persistencia
 * referentes a valor comercial diamante.
 *
 * @author ngonzalez
 */
public final class ValorComercialDiamanteUtil {



    // METODOS

    /**
     * Constructor.
     */
    private ValorComercialDiamanteUtil() {
        // Utility classes should always be final and have a private constructor.
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado vigente (JPA) en listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialDiamante convertToListadoDeDominio(
        ListadoValorComercialDiamanteJPA listado) {

        Set<Diamante> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialDiamanteJPA vcd : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    DiamanteFactory.create(vcd.getCorte(), vcd.getColor(), vcd.getClaridad(),
                        vcd.getTamanioInferior(), vcd.getTamanioSuperior(), vcd.getPrecio(),
                        vcd.getTipoCambio(), vcd.getMontoVbd(), vcd.getMontofCastigoxRango()));
            }
        }

        return ListadoValorComercialDiamanteFactory.create(
            listado.getFechaCarga(), listado.getFechaListado(), valoresComerciales);
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado histórico (JPA) en listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    public static ListadoValorComercialDiamante convertToListadoDeDominio(
        HistListadoValorComercialDiamanteJPA listado) {

        Set<Diamante> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (HistValorComercialDiamanteJPA hvcd : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    DiamanteFactory.create(hvcd.getCorte(), hvcd.getColor(), hvcd.getClaridad(),
                        hvcd.getTamanioInferior(), hvcd.getTamanioSuperior(), hvcd.getPrecio(),
                        hvcd.getTipoCambio(), hvcd.getMontoVbd(), hvcd.getMontofCastigoxRango()));
            }
        }

        return ListadoValorComercialDiamanteFactory.create(
            listado.getFechaCarga(), listado.getFechaListado(), valoresComerciales);
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado vigente (JPA) en listado histórico (JPA).
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en histórico.
     */
    public static HistListadoValorComercialDiamanteJPA convertToListadoHistoricoJPA(
        ListadoValorComercialDiamanteJPA listado) {

        HistListadoValorComercialDiamanteJPA result = new HistListadoValorComercialDiamanteJPA();
        result.setFechaCarga(listado.getFechaCarga());
        result.setFechaListado(listado.getFechaListado());

        Set<HistValorComercialDiamanteJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialDiamanteJPA vcd : listado.getValoresComerciales()) {
                HistValorComercialDiamanteJPA hvcd = new HistValorComercialDiamanteJPA();
                hvcd.setCorte(vcd.getCorte());
                hvcd.setColor(vcd.getColor());
                hvcd.setClaridad(vcd.getClaridad());
                hvcd.setTamanioInferior(vcd.getTamanioInferior());
                hvcd.setTamanioSuperior(vcd.getTamanioSuperior());
                hvcd.setPrecio(vcd.getPrecio());
                valoresComerciales.add(hvcd);
            }
        }

        result.setValoresComerciales(valoresComerciales);
        return result;
    }

    /**
     * Metodo auxiliar utilizado para convertir el listado histórico (JPA) en listado vigente (JPA).
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en vigente.
     */
    public static ListadoValorComercialDiamanteJPA convertToListadoVigenteJPA(
        HistListadoValorComercialDiamanteJPA listado) {

        ListadoValorComercialDiamanteJPA result = new ListadoValorComercialDiamanteJPA();
        result.setFechaCarga(listado.getFechaCarga());
        result.setFechaListado(listado.getFechaListado());

        Set<ValorComercialDiamanteJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (HistValorComercialDiamanteJPA hvcd : listado.getValoresComerciales()) {
                ValorComercialDiamanteJPA vcd = new ValorComercialDiamanteJPA();
                vcd.setCorte(hvcd.getCorte());
                vcd.setColor(hvcd.getColor());
                vcd.setClaridad(hvcd.getClaridad());
                vcd.setTamanioInferior(hvcd.getTamanioInferior());
                vcd.setTamanioSuperior(hvcd.getTamanioSuperior());
                vcd.setPrecio(hvcd.getPrecio());
                valoresComerciales.add(vcd);
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
    public static ListadoValorComercialDiamanteJPA convertToListadoVigenteJPA(
        ListadoValorComercialDiamante listado) {

        ListadoValorComercialDiamanteJPA result = new ListadoValorComercialDiamanteJPA();
        result.setFechaCarga(listado.getFechaCarga());
        result.setFechaListado(listado.getFechaListado());

        Set<ValorComercialDiamanteJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (Diamante diamante : listado.getValoresComerciales()) {
                ValorComercialDiamanteJPA vcd = new ValorComercialDiamanteJPA();
                vcd.setCorte(diamante.getCorte());
                vcd.setColor(diamante.getColor());
                vcd.setClaridad(diamante.getClaridad());
                vcd.setTamanioInferior(diamante.getTamanioInferior());
                vcd.setTamanioSuperior(diamante.getTamanioSuperior());
                vcd.setPrecio(diamante.getPrecio());
                valoresComerciales.add(vcd);
            }
        }

        result.setValoresComerciales(valoresComerciales);
        return result;
    }

}
