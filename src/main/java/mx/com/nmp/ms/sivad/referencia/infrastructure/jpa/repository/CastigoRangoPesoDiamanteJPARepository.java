/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoRangoPesoDiamanteJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Expone los metodos de acceso a datos para la entidad CastigoRangoPesoDiamanteJPA.
 *
 * @author ecancino
 */
@Repository
public interface CastigoRangoPesoDiamanteJPARepository extends JpaRepository<CastigoRangoPesoDiamanteJPA, Long> {

    /**
     * Utilizado para obtener el factor que se va a aplicar al valor comercial por concepto del rango
     * de peso del diamante.
     *
     * @param quilatesDesde El valor en quilates inferior del diamante.
     * @param quilatesHasta El valor en quilates superior del diamante.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    @Query("SELECT cpd FROM CastigoRangoPesoDiamanteJPA cpd " +
        "WHERE cpd.quilatesDesde <= :quilatesDesde " +
        "AND cpd.quilatesHasta >= :quilatesHasta " +
        "AND cpd.fecha = (SELECT MAX(cpd2.fecha) FROM CastigoRangoPesoDiamanteJPA cpd2)")
    CastigoRangoPesoDiamanteJPA obtenerCastigoRangoPeso(@Param("quilatesDesde") BigDecimal quilatesDesde,
                                                    @Param("quilatesHasta") BigDecimal quilatesHasta);


	@Query("SELECT crpd FROM CastigoRangoPesoDiamanteJPA crpd "
			+ "WHERE crpd.fecha = (SELECT MAX(crpd2.fecha) FROM CastigoRangoPesoDiamanteJPA crpd2)")
	List<CastigoRangoPesoDiamanteJPA> busquedaUltimaActualizacion();
}
