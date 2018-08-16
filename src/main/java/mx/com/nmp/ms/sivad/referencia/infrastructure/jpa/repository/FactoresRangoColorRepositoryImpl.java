package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactoresRangoColorNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactoresRangoColorJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;

public class FactoresRangoColorRepositoryImpl implements FactoresRangoColorRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FactoresRangoColorRepositoryImpl.class);
	
	@Inject
	private FactoresRangoColorJPARepository factoresRangoColorJPARepository;
	
	@Inject
	private ListadoValorComercialDiamanteJPARepository listadoJpaRepository;
	
	@Inject
    private ValorComercialDiamanteJPARepository valorComercialDiamanteJPARepository;

	@Override
	public List<ValorComercialDiamanteJPA> obtenerColorValorComercial() {

		// Ejecutar Query obteniendo información vigente
		List<FactoresRangoColorJPA> factoresRangoColor = factoresRangoColorJPARepository.findByUltimaActualizacion();

		// Se valida si existen regristros al ejecutar el query
		if (ObjectUtils.isEmpty(factoresRangoColor)) {
			String msg = "No existe ningun registro en Factores por Rango de Color.";
			LOGGER.warn(msg);
			throw new FactoresRangoColorNoEncontradoException(FactoresRangoColorJPA.class, msg);
		}

		List<ValorComercialDiamanteJPA> valorComerJPA = new ArrayList<ValorComercialDiamanteJPA>();

		for (FactoresRangoColorJPA factoresRangoColorJPA : factoresRangoColor) {

			Set<ValorComercialDiamanteJPA> listadoValorComercialDiamanteJPA = listadoJpaRepository
					.findByColor(factoresRangoColorJPA.getRangoColorBase());

			for (ValorComercialDiamanteJPA vComerDiamanteJPA : listadoValorComercialDiamanteJPA) {

				// Guarda registros en Valor Comercial
				ValorComercialDiamanteJPA vc = new ValorComercialDiamanteJPA();
				
				vc.setColor(factoresRangoColorJPA.getRangoColorBase());
				vc.setPrecio(vComerDiamanteJPA.getPrecio().multiply(factoresRangoColorJPA.getFactor()));

				valorComercialDiamanteJPARepository.save(valorComerJPA);

			}

		}

		return valorComerJPA;
	}

}
