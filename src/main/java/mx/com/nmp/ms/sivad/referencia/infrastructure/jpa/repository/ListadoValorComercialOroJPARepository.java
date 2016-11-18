/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialOroJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Expone los metodos de acceso a datos para el listado del valor comercial del oro.
 *
 * @author ngonzalez
 */
@Repository
public interface ListadoValorComercialOroJPARepository extends
    JpaRepository<ListadoValorComercialOroJPA, Long> {

    /**
     * Permite obtener el listado de valor comercial del oro vigente.
     *
     * @return Listado vigente.
     */
    @Query("SELECT lvco FROM ListadoValorComercialOroJPA lvco")
    ListadoValorComercialOroJPA obtenerListadoVigente();

    /**
     * Permite obtener los listados de valor comercial del oro que correspondan a la fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la vigencia.
     * @param fechaFinal La fecha de fin de la vigencia.
     * @return La lista de entidades que coincidan con la fecha indicada.
     */
    @Query("SELECT lvco FROM ListadoValorComercialOroJPA lvco " +
        "WHERE lvco.ultimaActualizacion BETWEEN :fechaInicial AND :fechaFinal")
    List<ListadoValorComercialOroJPA> obtenerListadoPorFechaVigencia(
        @Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);

}
