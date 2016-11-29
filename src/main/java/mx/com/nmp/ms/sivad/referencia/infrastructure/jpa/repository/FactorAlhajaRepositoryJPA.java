package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorAlhajaJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Expone los metodos de acceso a datos para el repository de factor alhaja.
 *
 * @author mmarquez
 */
@Repository
public interface FactorAlhajaRepositoryJPA extends JpaRepository<FactorAlhajaJPA, Long>{

    /**
     * Utilizado para obtener el un factor alhaja por el metal, calidad y rango.
     *
     * @param metal Metal de la alhaja.
     * @param calidad Calidad de la alhaja.
     * @param rango Rango de la alhaja.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    FactorAlhajaJPA findByMetalAndCalidadAndRango(String metal, String calidad, String rango);

    /**
     * Utilizado para obtener el un factor alhaja por el metal, calidad y rango.
     *
     * @param metal Metal de la alhaja.
     * @param rango Rango de la alhaja.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    FactorAlhajaJPA findByMetalAndRango(String metal, String rango);
}
