/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoModificadorCertificadoJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.joda.time.DateTime;
import java.util.Set;

/**
 * Expone los metodos de acceso a datos para el histórico del listado de modificadores de certificado.
 *
 * @author mmarquez
 */
@Repository
public interface HistListadoModificadorCertificadoJPARepository extends
    JpaRepository<HistListadoModificadorCertificadoJPA, Long> {

    /**
     * Permite obtener los históricos de listado de modificadores de certificado que correspondan a la fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la carga.
     * @param fechaFinal La fecha de fin de la carga.
     * @return La lista de entidades que coincidan con la fecha indicada.
     */
    @Query("SELECT hlmc FROM HistListadoModificadorCertificadoJPA hlmc " +
        "WHERE hlmc.fechaCarga BETWEEN :fechaInicial AND :fechaFinal")
    Set<HistListadoModificadorCertificadoJPA> obtenerListadoPorFechaCarga(
        @Param("fechaInicial") DateTime fechaInicial, @Param("fechaFinal") DateTime fechaFinal);

    Set<HistListadoModificadorCertificadoJPA> findByFechaCargaBetween(DateTime fechaInicial, DateTime fechaFinal);
}
