package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactoresRangoColorJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ParametrosQuilatesJPA;

/**
 * 
 * @author Quarksoft
 *
 */
@Repository
public interface FactoresRangoColorJPARepository extends JpaRepository<FactoresRangoColorJPA, Long>{
	
	
	/**
	 * Utilizado para obtener todos los registros con la fecha mas actual.
	 * @return Todas las entidades con la fecha mas actual
	 */
	@Query("SELECT frc FROM FactoresRangoColorJPA frc "
			+ "WHERE frc.fecha = (SELECT MAX(frc.fecha) FROM FactoresRangoColorJPA frc)")
	  List<FactoresRangoColorJPA> findByUltimaActualizacion();
}
