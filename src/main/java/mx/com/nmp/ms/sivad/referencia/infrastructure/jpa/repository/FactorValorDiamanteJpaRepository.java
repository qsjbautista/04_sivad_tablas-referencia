/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorValorDiamanteJpa;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio que brinda la funcionalidad para administrar la entidad JPA {@link FactorValorDiamanteJpa}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public interface FactorValorDiamanteJpaRepository extends JpaRepository<FactorValorDiamanteJpa, Long> {
    /**
     * Recupera la lista de factores de valor de diamante vigentes al momento.
     *
     * @return Lista de factores de valor de diamante.
     */
    FactorValorDiamanteJpa findFirstByOrderByFechaListadoDescIdDesc();

    /**
     * PRecupera el listado de factores de valor de diamante con base en una fecha de vigencia.
     *
     * @param fecha Fecha de vigencia.
     *
     * @return Listado de factores de valor de diamante
     */
    List<FactorValorDiamanteJpa> findByFechaListado(LocalDate fecha);
}
