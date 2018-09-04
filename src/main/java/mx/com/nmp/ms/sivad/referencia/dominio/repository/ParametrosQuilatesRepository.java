package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import java.math.BigDecimal;
import java.util.List;
import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ParametrosQuilatesJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;

public interface ParametrosQuilatesRepository {
	
	public List<ParametrosQuilatesJPA> obtenerTodo();
	
	public List<ParametrosQuilatesJPA> obtenerTodoUltimaActualizacion();
	
	public List<ParametrosQuilatesJPA> busquedaQuilatesDesdeQuilatesHasta(BigDecimal quilatesDesde, BigDecimal quilatesHasta);
	
	public List<ParametrosQuilatesJPA> busquedaQuilatesBaseDesdeQuilatesBaseHasta(BigDecimal quilatesBaseDesde, BigDecimal quilatesBaseHasta);
	
	public ParametrosQuilatesJPA guardaParametrosQuilates(ParametrosQuilatesJPA parametrosQuilatesJPA);

	public List<ValorComercialDiamanteJPA> obtenerValorComercial();

    public ParametrosQuilatesJPA update(@NotNull Long idParametro, @NotNull ParametrosQuilatesJPA elemento);

}
