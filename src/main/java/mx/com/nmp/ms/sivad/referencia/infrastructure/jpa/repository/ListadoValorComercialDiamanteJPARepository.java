/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialDiamanteJPA;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Expone los metodos de acceso a datos para el listado del valor comercial del diamante.
 *
 * @author ngonzalez
 */
@Repository
public interface ListadoValorComercialDiamanteJPARepository extends
    JpaRepository<ListadoValorComercialDiamanteJPA, Long> {

    /**
     * Permite obtener el listado de valor comercial del diamante vigente.
     *
     * @return Listado vigente.
     */
    @Query("SELECT lvcd FROM ListadoValorComercialDiamanteJPA lvcd")
    ListadoValorComercialDiamanteJPA obtenerListadoVigente();

    /**
     * Permite obtener los listados de valor comercial del diamante que correspondan a la fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la vigencia.
     * @param fechaFinal La fecha de fin de la vigencia.
     * @return La lista de históricos que coincidan con la fecha indicada.
     */
    @Query("SELECT lvcd FROM ListadoValorComercialDiamanteJPA lvcd " +
        "WHERE lvcd.fechaCarga BETWEEN :fechaInicial AND :fechaFinal")
    Set<ListadoValorComercialDiamanteJPA> obtenerListadosPorFechaVigencia(
        @Param("fechaInicial") DateTime fechaInicial, @Param("fechaFinal") DateTime fechaFinal);

}
