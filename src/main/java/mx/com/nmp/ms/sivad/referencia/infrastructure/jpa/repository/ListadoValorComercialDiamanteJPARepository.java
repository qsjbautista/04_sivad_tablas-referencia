/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialDiamanteJPA;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Expone los metodos de acceso a datos para el listado del valor comercial del diamante.
 *
 * @author ngonzalez
 */
@Repository
public interface ListadoValorComercialDiamanteJPARepository extends
    JpaRepository<ListadoValorComercialDiamanteJPA, Long> {

    /**
     * Permite obtener el listado de valor comercial del diamante vigente.
     *
     * @return Listado vigente.
     */
    @Query("SELECT lvcd FROM ListadoValorComercialDiamanteJPA lvcd")
    ListadoValorComercialDiamanteJPA obtenerListadoVigente();

    /**
     * Genera una lista vigente de valores comerciales de diamantes.
     *
     * @param fecha Fecha en que se genero el listado por parte del proveedor.
     */
    @Procedure(name = "generar_vigente")
    void generarVigente(@Param("_fechaListado") Date fecha);

    /**
     * Permite obtener los listados de valor comercial del diamante que correspondan a la fecha indicada.
     *
     * @param fechaInicial La fecha de inicio de la vigencia.
     * @param fechaFinal La fecha de fin de la vigencia.
     * @return La lista de históricos que coincidan con la fecha indicada.
     */
    Set<ListadoValorComercialDiamanteJPA> findByFechaCargaBetween(DateTime fechaInicial, DateTime fechaFinal);
    
    
	/**
	 * Permite obtener el listado de valor comercial del diamante vigente.
	 *
	 * @return Listado vigente.
	 */
	@Query("SELECT lvcd FROM ListadoValorComercialDiamanteJPA lvcd" + "WHERE lvcd.tamanioInferior = :tamanioInferior"
			+ "AND lvcd.tamanioSuperior = :tamanioSuperior")
	ListadoValorComercialDiamanteJPA obtListadoVigente(@Param("tamanioInferior") BigDecimal tamanioInferior,
			@Param("tamanioSuperior") BigDecimal tamanioSuperior);

}
