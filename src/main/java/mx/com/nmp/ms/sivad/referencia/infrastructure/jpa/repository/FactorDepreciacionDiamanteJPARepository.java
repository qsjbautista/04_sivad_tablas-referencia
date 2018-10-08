/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorDepreciacionDiamanteJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Expone los metodos de acceso a datos para la entidad FactorDepreciacionDiamanteJPA.
 *
 * @author ecancino
 */
@Repository
public interface FactorDepreciacionDiamanteJPARepository extends JpaRepository<FactorDepreciacionDiamanteJPA, Long> {

    /**
     * Utilizado para obtener el factor de depreciacion que se aplica al precio
     * del diamante.
     *
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    @Query("SELECT fd FROM FactorDepreciacionDiamanteJPA fd " +
        "WHERE fd.fecha = (SELECT MAX(fd2.fecha) FROM FactorDepreciacionDiamanteJPA fd2)")
    FactorDepreciacionDiamanteJPA obtenerFactorDepreciacion();
}
