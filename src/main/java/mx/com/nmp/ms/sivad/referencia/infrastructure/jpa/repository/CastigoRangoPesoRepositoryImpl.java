package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoCorteNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoRangoPesoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoCorteDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoRangoPesoDiamanteJPA;

@Repository
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class CastigoRangoPesoRepositoryImpl implements CastigoRangoPesoRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CastigoRangoPesoRepositoryImpl.class);

	@Inject
	private CastigoRangoPesoDiamanteJPARepository castigoRangoPesoDiamanteJPARepository;

	public List<CastigoRangoPesoDiamanteJPA> obtenerTodo() {
		List<CastigoRangoPesoDiamanteJPA> castigoRango = castigoRangoPesoDiamanteJPARepository.findAll();

		return castigoRango;
	}

	public List<CastigoRangoPesoDiamanteJPA> obtenerTodoUltimaActualizacion() {

		List<CastigoRangoPesoDiamanteJPA> fechaUltimaActualizacion = castigoRangoPesoDiamanteJPARepository
				.findByUltimaActualizacion();

		return fechaUltimaActualizacion;

	}

	public CastigoRangoPesoDiamanteJPA obtenerCastigoRango(BigDecimal quilatesDesde, BigDecimal quilatesHasta) {

		if (quilatesDesde == null) {
			String msg = "No puede ir nulo el campo Quilates Desde.";
			throw new CastigoRangoPesoNoEncontradoException(CastigoCorteDiamanteJPA.class, msg);
		}

		if (quilatesHasta == null) {
			String msg = "No puede ir nulo el campo Quilates Hasta.";
			throw new CastigoRangoPesoNoEncontradoException(CastigoCorteDiamanteJPA.class, msg);
		}

		CastigoRangoPesoDiamanteJPA oCastigoRango = castigoRangoPesoDiamanteJPARepository
				.obtenerCastigoRangoPeso(quilatesDesde, quilatesHasta);

		return oCastigoRango;

	}

	public CastigoRangoPesoDiamanteJPA guardaActualizaCastigoRango(
			CastigoRangoPesoDiamanteJPA castigoRangoPesoDiamanteJPA) {

		CastigoRangoPesoDiamanteJPA entidad = null;

		if (castigoRangoPesoDiamanteJPA.getId() != null && castigoRangoPesoDiamanteJPA.getId() > 0) {

			entidad = castigoRangoPesoDiamanteJPARepository.findOne(castigoRangoPesoDiamanteJPA.getId());

			if (entidad == null) {

				String msg = "No existe un id para Porcentaje Castigo Por Rango de Pesos";
				throw new CastigoRangoPesoNoEncontradoException(CastigoRangoPesoDiamanteJPA.class, msg);

			}

			entidad.setQuilatesDesde(castigoRangoPesoDiamanteJPA.getQuilatesDesde() != null
					? castigoRangoPesoDiamanteJPA.getQuilatesDesde()
					: entidad.getQuilatesDesde());
			entidad.setQuilatesHasta(castigoRangoPesoDiamanteJPA.getQuilatesHasta() != null
					? castigoRangoPesoDiamanteJPA.getQuilatesHasta()
					: entidad.getQuilatesHasta());
			entidad.setFactor(castigoRangoPesoDiamanteJPA.getFactor() != null ? castigoRangoPesoDiamanteJPA.getFactor()
					: entidad.getFactor());
			entidad.setFecha(DateTime.now());

			castigoRangoPesoDiamanteJPARepository.save(entidad);

		} else {

			if (castigoRangoPesoDiamanteJPA.getQuilatesDesde() == null)
				throw new CastigoCorteNoEncontradoException(CastigoRangoPesoDiamanteJPA.class,
						"No debe ser nulo el campo Quilates Desde.");

			if (castigoRangoPesoDiamanteJPA.getQuilatesHasta() == null)
				throw new CastigoCorteNoEncontradoException(CastigoRangoPesoDiamanteJPA.class,
						"No debe ser nulo el campo Quilates Hasta.");

			if (castigoRangoPesoDiamanteJPA.getFactor() == null)
				throw new CastigoCorteNoEncontradoException(CastigoRangoPesoDiamanteJPA.class,
						"No debe ser nulo el campo Factor.");

			castigoRangoPesoDiamanteJPA.setId(null);
			castigoRangoPesoDiamanteJPA.setFecha(DateTime.now());

			entidad = castigoRangoPesoDiamanteJPARepository.save(castigoRangoPesoDiamanteJPA);
		}

		return entidad;

	}

}
