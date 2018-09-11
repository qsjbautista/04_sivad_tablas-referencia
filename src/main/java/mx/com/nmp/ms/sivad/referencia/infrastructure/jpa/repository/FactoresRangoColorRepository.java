package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.util.List;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactoresRangoColorJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;

public interface FactoresRangoColorRepository {

	public List<ValorComercialDiamanteJPA> obtenerColorValorComercial();

	public List<FactoresRangoColorJPA> obtenerTodo();

	public FactoresRangoColorJPA guardaActualizaFactorRangoColor(FactoresRangoColorJPA castigoRangoColorDiamanteJPA);

    public FactoresRangoColorJPA delete(@NotNull Long idFactor);

    public FactoresRangoColorJPA obtenerElemento(Long idFactor);
}
