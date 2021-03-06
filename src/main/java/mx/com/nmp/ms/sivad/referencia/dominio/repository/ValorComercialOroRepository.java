/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.OroVO;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * Utilizado para canalizar el acceso a datos de valores comerciales del oro.
 *
 * @author ngonzalez
 */
public interface ValorComercialOroRepository {

    /**
     * Permite obtener la entidad Oro vigente, que corresponda a las características indicadas en las propiedades
     * del parámetro.
     *
     * @param oroVO Value Object con las características de búsqueda.
     * @return La entidad obtenida.
     */
    Oro consultarOroVigente(OroVO oroVO);

    /**
     * Permite obtener la entidad ListadoValorComercialOro vigente.
     *
     * @return La entidad listado obtenida.
     */
    ListadoValorComercialOro consultarListadoVigente();

    /**
     * Permite obtener la lista de entidades ListadoValorComercialOro que correspondan a la fecha de vigencia indicada.
     *
     * @param fechaVigencia La fecha de vigencia.
     * @return La lista de entidades obtenidas.
     */
    Set<ListadoValorComercialOro> consultarListadoPorFechaVigencia(LocalDate fechaVigencia);

    /**
     * Permite actualizar el listado vigente con la información del listado que se recibe como parámetro.
     *
     * @param listado El nuevo listado.
     */
    void actualizarListado(ListadoValorComercialOro listado);

}
