/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.TipoHechura;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.TipoHechuraJPA;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Expone los metodos de acceso a datos para el listado del valor comercial del metal.
 *
 * @author ecancino
 */
@Repository
public interface TipoHechuraJPARepository extends
    JpaRepository<TipoHechuraJPA, Long> {

    /**
     * Utilizado para obtener el listado de entidades TipoHechura que coincidan con el metal y subramo indicados.
     * @param metal El tipo de metal.
     * @param subramo El subramo de la alhaja.
     * @return Listado de tipos de hechura para el metal y subramo indicados.
     */
    @Query(value = "SELECT " +
        "new mx.com.nmp.ms.sivad.referencia.dominio.modelo.TipoHechura(mcsr.tipoHechura.id, mcsr.tipoHechura.abreviatura, mcsr.tipoHechura.descripcion) " +
        "FROM MetalCalidadRangoSubramoJPA mcsr " +
        "WHERE mcsr.metalCalidadSubramo.metal = :metal " +
        "AND mcsr.metalCalidadSubramo.subramo = :subramo")
    @Cacheable("TipoHechuraJPARepository.findByMetalAndSubramo")
    List<TipoHechura> findByMetalAndSubramo(@Param("metal") String metal, @Param("subramo") String subramo);

    /**
     * Utilizado para obtener el listado de entidades TipoHechura que coincidan con el metal, subramo y calidad indicados.
     * @param metal El tipo de metal.
     * @param subramo El subramo de la alhaja.
     * @param calidad La calidad del metal.
     * @return Listado de tipos de hechura para el metal, subramo y calidad indicados.
     */
    @Query(value = "SELECT " +
        "new mx.com.nmp.ms.sivad.referencia.dominio.modelo.TipoHechura(mcsr.tipoHechura.id, mcsr.tipoHechura.abreviatura, mcsr.tipoHechura.descripcion) " +
        "FROM MetalCalidadRangoSubramoJPA mcsr " +
        "WHERE mcsr.metalCalidadSubramo.metal = :metal " +
        "AND mcsr.metalCalidadSubramo.subramo = :subramo " +
        "AND mcsr.metalCalidadSubramo.calidad = :calidad")
    @Cacheable("TipoHechuraJPARepository.findByMetalAndSubramoAndCalidad")
    List<TipoHechura> findByMetalAndSubramoAndCalidad(@Param("metal") String metal, @Param("subramo") String subramo, @Param("calidad") String calidad);
}
