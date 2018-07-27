/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.RangoPesoDiamanteJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Expone los metodos de acceso a datos para la entidad RangoPesoDiamanteJPA.
 *
 * @author ecancino
 */
@Repository
public interface RangoPesoDiamanteJPARepository extends JpaRepository<RangoPesoDiamanteJPA, Long> {

    /**
     * Utilizado para obtener el rango de peso del diamante que coincida con el atributo 'quilates'
     * indicado
     *
     * @param quilates El valor en quilates del diamante.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    @Query("SELECT rpd FROM RangoPesoDiamanteJPA rpd " +
        "WHERE rpd.quilatesDesde <= :quilates " +
        "AND rpd.quilatesHasta >= :quilates " +
        "AND rpd.fecha = (SELECT MAX(rpd2.fecha) FROM RangoPesoDiamanteJPA rpd2)")
    RangoPesoDiamanteJPA obtenerRangoPeso(@Param("quilates") BigDecimal quilates);
}
