/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Utilizado para canalizar el acceso a datos de valores comerciales del metal (diferente de oro).
 *
 * @author ngonzalez
 */
@Component
public interface ValorComercialMetalRepository {

    /**
     * Permite obtener la entidad Metal vigente, que corresponda a las características indicadas en las propiedades
     * del parámetro.
     *
     * @param metal Entidad con las características de búsqueda.
     * @return La entidad obtenida.
     */
    Metal consultarMetalVigente(Metal metal);

    /**
     * Permite obtener la entidad ListadoValorComercialMetal vigente.
     *
     * @return La entidad listado obtenida.
     */
    ListadoValorComercialMetal consultarListadoVigente();

    /**
     * Permite obtener la lista de entidades ListadoValorComercialMetal que correspondan a la fecha de vigencia indicada.
     *
     * @param fechaVigencia La fecha de vigencia.
     * @return La lista de entidades obtenidas.
     */
    List<ListadoValorComercialMetal> consultarListadoPorFechaVigencia(LocalDate fechaVigencia);

    /**
     * Permite actualizar el listado vigente con la información del listado que se recibe como parámetro.
     *
     * @param listado El nuevo listado.
     */
    void actualizarListado(ListadoValorComercialMetal listado);

}
