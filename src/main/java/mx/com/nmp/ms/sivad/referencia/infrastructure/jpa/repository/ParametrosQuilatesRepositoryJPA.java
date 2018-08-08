package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ParametrosQuilatesJPA;

@Repository
public interface ParametrosQuilatesRepositoryJPA extends JpaRepository<ParametrosQuilatesJPA, Long>{
	
	/**
	 * Utilizado para obtener todos los registros con la fecha mas actual.
	 * @return Todas las entidades con la fecha mas actual
	 */
	@Query("SELECT pq FROM ParametrosQuilatesJPA pq "
			+ "WHERE pq.fecha = (SELECT MAX(pq2.fecha) FROM ParametrosQuilatesJPA pq2)")
	  List<ParametrosQuilatesJPA> busquedaUltimaActualizacion();
	
	@Query("SELECT pq FROM ParametrosQuilatesJPA pq " +
	        "WHERE pq.quilatesDesde = :quilatesDesde " +
	        "AND pq.quilatesHasta = :quilatesHasta " +
	        "AND pq.fecha = (SELECT MAX(pq2.fecha) FROM ParametrosQuilatesJPA pq2)")
	List<ParametrosQuilatesJPA> busquedaQdQh(@Param("quilatesDesde") BigDecimal quilatesDesde, @Param("quilatesHasta") BigDecimal quilatesHasta);
	
	@Query("SELECT pq FROM ParametrosQuilatesJPA pq " +
	        "WHERE pq.quilatesBaseDesde = :quilatesBaseDesde " +
	        "AND pq.quilatesBaseHasta = :quilatesBaseHasta " +
	        "AND pq.fecha = (SELECT MAX(pq2.fecha) FROM ParametrosQuilatesJPA pq2)")
	List<ParametrosQuilatesJPA> busquedaQbDQbH(@Param("quilatesBaseDesde") BigDecimal quilatesBaseDesde, @Param("quilatesBaseHasta") BigDecimal quilatesBaseHasta);

}
