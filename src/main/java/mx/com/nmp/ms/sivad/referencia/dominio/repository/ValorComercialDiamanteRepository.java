/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.DiamanteVO;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Set;

/**
 * Utilizado para canalizar el acceso a datos de valores comerciales del diamante.
 *
 * @author ngonzalez
 */
public interface ValorComercialDiamanteRepository {

    /**
     * Permite obtener la entidad Diamante que corresponda a las características indicadas.
     *
     * @param diamanteVO Value Object con las características del diamante.
     * @return La entidad obtenida con el precio en pesos.
     */
    Diamante obtenerValorComercial(DiamanteVO diamanteVO);

    /**
     * Permite obtener la entidad ListadoValorComercialDiamante vigente.
     *
     * @return La entidad listado obtenida.
     */
    ListadoValorComercialDiamante consultarListadoVigente();

    /**
     * Permite obtener la lista de entidades ListadoValorComercialDiamante que correspondan a la fecha de
     * vigencia indicada.
     *
     * @param fechaVigencia La fecha de vigencia.
     * @return La lista de entidades obtenidas.
     */
    Set<ListadoValorComercialDiamante> consultarListadoPorFechaVigencia(LocalDate fechaVigencia);

    /**
     * Permite actualizar el listado vigente con la información del listado que se recibe como parámetro.
     *
     * @param listado El nuevo listado.
     */
    void actualizarListado(ListadoValorComercialDiamante listado);

    /**
     * Permite restaurar el listado de precios de diamantes anterior al listado vigente.
     *
     * @return El nuevo listado restaurado.
     */
    ListadoValorComercialDiamante restaurarListadoAnterior();

    /**
     * Permite restaurar el listado de precios de diamantes de la fecha de vigencia indicada.
     *
     * @param fechaVigencia La fecha de vigencia del listado que se desea restaurar.
     * @return El nuevo listado restaurado.
     */
    ListadoValorComercialDiamante restaurarListadoPorFechaVigencia(LocalDate fechaVigencia);

    /**
     * Permite actualizar el listado vigente con la información del listado que se recibe como parámetro.
     *
     * @param listado El nuevo listado.
     */
    void actualizarListadoSinHist(ListadoValorComercialDiamante listado);

}
