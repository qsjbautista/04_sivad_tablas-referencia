/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialDiamanteJPA;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Expone los metodos de acceso a datos para el histórico del listado del valor comercial del diamante.
 *
 * @author ngonzalez
 */
@Repository
public interface HistListadoValorComercialDiamanteJPARepository extends
    JpaRepository<HistListadoValorComercialDiamanteJPA, Long> {

    /**
     * Permite obtener el histórico de listado de valor comercial del diamante, cuya fecha de carga sea la más reciente.
     *
     * @return El histórico que coincida con el criterio.
     */
    HistListadoValorComercialDiamanteJPA findFirstByOrderByFechaCargaDesc();

    /**
     * Permite obtener los históricos de listado de valor comercial del diamante que correspondan a la fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la vigencia.
     * @param fechaFinal La fecha de fin de la vigencia.
     * @return La lista de históricos que coincidan con la fecha indicada.
     */
    Set<HistListadoValorComercialDiamanteJPA> findByFechaCargaBetween(DateTime fechaInicial, DateTime fechaFinal);

    /**
     * Permite obtener el histórico de listado de valor comercial del diamante, cuya fecha de carga sea la más reciente
     * dentro de la fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la vigencia.
     * @param fechaFinal La fecha de fin de la vigencia.
     * @return El histórico que coincida con la fecha indicada.
     */
    HistListadoValorComercialDiamanteJPA findFirstByFechaCargaBetweenOrderByFechaCargaDesc(DateTime fechaInicial, DateTime fechaFinal);

}
