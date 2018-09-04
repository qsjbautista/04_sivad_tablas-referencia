/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoCorteDiamanteJPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Expone los metodos de acceso a datos para la entidad CastigoCorteDiamanteJPA.
 *
 * @author ecancino
 */
@Repository
public interface CastigoCorteDiamanteJPARepository extends JpaRepository<CastigoCorteDiamanteJPA, Long> {

    /**
     * Utilizado para obtener el factor que se va a aplicar de castigo por el tipo de corte.
     *
     * @param corte El corte del diamante.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    @Query("SELECT ccd FROM CastigoCorteDiamanteJPA ccd " +
        "WHERE ccd.corte = :corte " +
        "AND ccd.fecha = (SELECT MAX(ccd2.fecha) FROM CastigoCorteDiamanteJPA ccd2)")
    CastigoCorteDiamanteJPA obtenerCastigoCorte(@Param("corte") String corte);
    
    /**
     * Utilizado para obtner todos los factores de castigo por tipo de corte
     * @return totas las entidades.
     */
    @Query("SELECT ccd FROM CastigoCorteDiamanteJPA ccd " +
            "WHERE ccd.fecha = (SELECT MAX(ccd2.fecha) FROM CastigoCorteDiamanteJPA ccd2)")
    List<CastigoCorteDiamanteJPA> findByUltimaActualizacion();

    CastigoCorteDiamanteJPA findByCorte(String corte);
    
}
