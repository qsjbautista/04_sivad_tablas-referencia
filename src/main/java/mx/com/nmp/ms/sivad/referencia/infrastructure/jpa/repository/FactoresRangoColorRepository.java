package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.util.List;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;

public interface FactoresRangoColorRepository {
	
	public List<ValorComercialDiamanteJPA> obtenerColorValorComercial();

}
