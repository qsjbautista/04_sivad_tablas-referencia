/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;

import org.joda.time.LocalDate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Expone los metodos de acceso a datos para la entidad ValorComercialDiamanteJPA.
 *
 * @author ngonzalez, ecancino
 */
@Repository
public interface ValorComercialDiamanteJPARepository extends JpaRepository<ValorComercialDiamanteJPA, Long> {

    /**
     * Utilizado para obtener la entidad que coincida exactamente con los atributos "corte", "color", "claridad",
     * "quilatesDesde" y "quilatesHasta" indicados.
     *
     * @param corte El tipo de corte del diamante.
     * @param color El tipo de color del diamante.
     * @param claridad El tipo de claridad del diamante.
     * @param quilatesDesde El valor inferior en quilates del diamante.
     * @param quilatesHasta El valor inferior en quilates del diamante.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    @Query("SELECT vcd FROM ValorComercialDiamanteJPA vcd " +
        "WHERE vcd.corte = :corte " +
        "AND vcd.color = :color " +
        "AND vcd.claridad = :claridad " +
        "AND vcd.tamanioInferior <= :quilatesDesde " +
        "AND vcd.tamanioSuperior >= :quilatesHasta")
    @Cacheable("ValorComercialDiamanteJPARepository.obtenerValorComercial")
    ValorComercialDiamanteJPA obtenerValorComercial(
        @Param("corte") String corte, @Param("color") String color, @Param("claridad") String claridad,
        @Param("quilatesDesde") BigDecimal quilatesDesde, @Param("quilatesHasta") BigDecimal quilatesHasta);

}
