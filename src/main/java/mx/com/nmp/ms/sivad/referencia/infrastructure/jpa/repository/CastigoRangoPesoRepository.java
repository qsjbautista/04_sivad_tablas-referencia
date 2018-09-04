package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoRangoPesoDiamanteJPA;

public interface CastigoRangoPesoRepository {
	
	public List<CastigoRangoPesoDiamanteJPA> obtenerTodo();
	
	public List<CastigoRangoPesoDiamanteJPA> obtenerTodoUltimaActualizacion();
	
	public CastigoRangoPesoDiamanteJPA obtenerCastigoRango(BigDecimal quilatesDesde, BigDecimal quilatesHasta);
	
	public CastigoRangoPesoDiamanteJPA guardaActualizaCastigoRango(CastigoRangoPesoDiamanteJPA castigoRangoPesoDiamanteJPA);

}
