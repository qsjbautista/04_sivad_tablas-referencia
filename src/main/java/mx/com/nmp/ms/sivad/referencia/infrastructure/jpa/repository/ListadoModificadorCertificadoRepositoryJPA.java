/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoModificadorCertificadoJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.joda.time.DateTime;
import java.util.Set;

/**
 * Expone los metodos de acceso a datos para el listado del modificadores de certificado.
 *
 * @author mmarquez
 */
@Repository
public interface ListadoModificadorCertificadoRepositoryJPA extends
    JpaRepository<ListadoModificadorCertificadoJPA, Long> {

    /**
     * Permite obtener el listado de valor comercial del oro vigente.
     *
     * @return Listado vigente.
     */
    @Query("SELECT lmc FROM ListadoModificadorCertificadoJPA lmc")
    ListadoModificadorCertificadoJPA obtenerListadoVigente();

    /**
     * Permite obtener los listados de modificadores certificado que correspondan a la fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la carga.
     * @param fechaFinal La fecha de fin de la carga.
     * @return La lista de entidades que coincidan con la fecha indicada.
     */
    @Query("SELECT lmc FROM ListadoModificadorCertificadoJPA lmc " +
        "WHERE lmc.fechaCarga BETWEEN :fechaInicial AND :fechaFinal")
    Set<ListadoModificadorCertificadoJPA> obtenerListadoPorFechaCarga(
        @Param("fechaInicial") DateTime fechaInicial, @Param("fechaFinal") DateTime fechaFinal);

}
