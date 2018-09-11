package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.util.List;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoCorteNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoCorteDiamanteJPA;

public interface CastigoCorteDiamanteRepository {

	public List<CastigoCorteDiamanteJPA> obtenerTodo();

	public List<CastigoCorteDiamanteJPA> obtenerTodoUltimaActualizacion();

	public CastigoCorteDiamanteJPA obtenerCastigoCorte(String corte);

	public CastigoCorteDiamanteJPA guardaActualizaCastigoCorte(CastigoCorteDiamanteJPA castigoCorteDiamanteJPA);

    public CastigoCorteDiamanteJPA delete(Long idCastigoCorte) throws CastigoCorteNoEncontradoException;

    public CastigoCorteDiamanteJPA obtenerElemento(Long idCastigoCorte);

}
