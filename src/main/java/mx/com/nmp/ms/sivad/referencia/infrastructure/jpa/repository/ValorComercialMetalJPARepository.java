/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialMetalJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Expone los metodos de acceso a datos para la entidad ValorComercialMetalJPA.
 *
 * @author ngonzalez
 */
@Repository
public interface ValorComercialMetalJPARepository extends JpaRepository<ValorComercialMetalJPA, Long> {

    /**
     * Utilizado para obtener la entidad que coincida exactamente con los atributos "metal" y "calidad" indicados.
     *
     * @param metal El tipo de metal.
     * @param calidad La calidad del metal.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    ValorComercialMetalJPA findByMetalAndCalidad(String metal, String calidad);

}
