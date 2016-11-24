/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoFactorAlhajaJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Expone los metodos de acceso a datos para el listado de factores alhaja.
 *
 * @author mmarquez
 */
@Repository
public interface ListadoFactorAlhajaRepositoryJPA extends
    JpaRepository<ListadoFactorAlhajaJPA, Long> {

    /**
     * Permite obtener el listado de valor comercial del oro vigente.
     *
     * @return Listado vigente.
     */
    @Query("SELECT lfa FROM ListadoFactorAlhajaJPA lfa")
    ListadoFactorAlhajaJPA obtenerListadoVigente();

    /**
     * Permite obtener los listados de factores alhaja que correspondan a la fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la carga.
     * @param fechaFinal La fecha de fin de la carga.
     * @return La lista de entidades que coincidan con la fecha indicada.
     */
    @Query("SELECT lfa FROM ListadoFactorAlhajaJPA lfa " +
        "WHERE lfa.fechaCarga BETWEEN :fechaInicial AND :fechaFinal")
    List<ListadoFactorAlhajaJPA> obtenerListadoPorFechaCarga(
        @Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);

}
