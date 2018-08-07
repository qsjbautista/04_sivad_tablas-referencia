package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ParametrosQuilatesJPA;

public interface ParametrosQuilatesRepository {
	
	public List<ParametrosQuilatesJPA> ObtenerTodo();
	
	public List<ParametrosQuilatesJPA> ObtenerTodoUltimaActualizacion();
	
	public List<ParametrosQuilatesJPA> busquedaQuilatesDesdeQuilatesHasta(BigDecimal quilatesDesde, BigDecimal quilatesHasta);
	
	public List<ParametrosQuilatesJPA> busquedaQuilatesBaseDesdeQuilatesBaseHasta(BigDecimal quilatesBaseDesde, BigDecimal quilatesBaseHasta);
	
	public ParametrosQuilatesJPA guardaParametrosQuilates(ParametrosQuilatesJPA parametrosQuilatesJPA);

}
