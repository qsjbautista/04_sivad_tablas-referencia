/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialOroJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Expone los metodos de acceso a datos para la entidad ValorComercialOroJPA.
 *
 * @author ngonzalez
 */
@Repository
public interface ValorComercialOroJPARepository extends JpaRepository<ValorComercialOroJPA, Long> {

    /**
     * Utilizado para obtener la entidad que coincida exactamente con los atributos "color" y "calidad" indicados.
     *
     * @param color El color del oro.
     * @param calidad La calidad del oro.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    ValorComercialOroJPA findByColorAndCalidad(String color, Integer calidad);

}
