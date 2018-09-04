package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactoresRangoColorNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoRangoPesoNoEncontradoException;
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

	@Override
	public List<FactoresRangoColorJPA> obtenerTodo() {
		List<FactoresRangoColorJPA> elementos = factoresRangoColorJPARepository.findAll();

		return elementos;

	}

	@Override
	public FactoresRangoColorJPA guardaActualizaFactorRangoColor(FactoresRangoColorJPA castigoRangoColorDiamanteJPA) {

		FactoresRangoColorJPA entidad = null;

		if (castigoRangoColorDiamanteJPA.getId() != null && castigoRangoColorDiamanteJPA.getId() > 0) {

			entidad = factoresRangoColorJPARepository.findOne(castigoRangoColorDiamanteJPA.getId());

			if (entidad == null) {

				String msg = "No existe un id para Rango Colors";
				throw new CastigoRangoPesoNoEncontradoException(FactoresRangoColorJPA.class, msg);

			}

			entidad.setColorDesde(castigoRangoColorDiamanteJPA.getColorDesde() != null
					? castigoRangoColorDiamanteJPA.getColorDesde()
					: entidad.getColorDesde());
			entidad.setColorHasta(castigoRangoColorDiamanteJPA.getColorHasta() != null
					? castigoRangoColorDiamanteJPA.getColorHasta()
					: entidad.getColorHasta());
			entidad.setFactor(castigoRangoColorDiamanteJPA.getFactor() != null ? castigoRangoColorDiamanteJPA.getFactor()
					: entidad.getFactor());
			entidad.setRangoColorBase(castigoRangoColorDiamanteJPA.getRangoColorBase() != null ? castigoRangoColorDiamanteJPA.getRangoColorBase()
					: entidad.getRangoColorBase());
			entidad.setFecha(DateTime.now());

			factoresRangoColorJPARepository.save(entidad);

		} else {

			if (castigoRangoColorDiamanteJPA.getColorDesde() == null)
				throw new FactoresRangoColorNoEncontradoException(FactoresRangoColorJPA.class,
						"No debe ser nulo el campo Color Desde.");

			if (castigoRangoColorDiamanteJPA.getColorHasta() == null)
				throw new FactoresRangoColorNoEncontradoException(FactoresRangoColorJPA.class,
						"No debe ser nulo el campo Color Hasta.");

			if (castigoRangoColorDiamanteJPA.getFactor() == null)
				throw new FactoresRangoColorNoEncontradoException(FactoresRangoColorJPA.class,
						"No debe ser nulo el campo Factor.");

			if (castigoRangoColorDiamanteJPA.getRangoColorBase() == null)
				throw new FactoresRangoColorNoEncontradoException(FactoresRangoColorJPA.class,
						"No debe ser nulo el campo Color Base.");

			castigoRangoColorDiamanteJPA.setId(null);
			castigoRangoColorDiamanteJPA.setFecha(DateTime.now());

			entidad = factoresRangoColorJPARepository.save(castigoRangoColorDiamanteJPA);
		}

		return entidad;

	}

}
