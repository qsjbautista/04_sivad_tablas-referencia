/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialMetalJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

/**
 * Expone los metodos de acceso a datos para el listado del valor comercial del metal.
 *
 * @author ngonzalez
 */
@Repository
public interface ListadoValorComercialMetalJPARepository extends
    JpaRepository<ListadoValorComercialMetalJPA, Long> {

    /**
     * Permite obtener el listado de valor comercial del metal (diferente de oro) vigente.
     *
     * @return Listado vigente.
     */
    @Query("SELECT lvcm FROM ListadoValorComercialMetalJPA lvcm")
    ListadoValorComercialMetalJPA obtenerListadoVigente();

    /**
     * Permite obtener los listados de valor comercial del metal (diferente de oro) que correspondan a la
     * fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la vigencia.
     * @param fechaFinal La fecha de fin de la vigencia.
     * @return La lista de históricos que coincidan con la fecha indicada.
     */
    @Query("SELECT lvcm FROM ListadoValorComercialMetalJPA lvcm " +
        "WHERE lvcm.ultimaActualizacion BETWEEN :fechaInicial AND :fechaFinal")
    Set<ListadoValorComercialMetalJPA> obtenerListadosPorFechaVigencia(
        @Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);

}
