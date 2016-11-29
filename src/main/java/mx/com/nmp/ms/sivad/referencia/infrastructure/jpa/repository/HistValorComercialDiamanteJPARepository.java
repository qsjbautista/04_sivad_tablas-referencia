/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistValorComercialDiamanteJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Expone los metodos de acceso a datos para la entidad HistValorComercialDiamanteJPA.
 *
 * @author ngonzalez
 */
@Repository
public interface HistValorComercialDiamanteJPARepository
    extends JpaRepository<HistValorComercialDiamanteJPA, Long> {

}
