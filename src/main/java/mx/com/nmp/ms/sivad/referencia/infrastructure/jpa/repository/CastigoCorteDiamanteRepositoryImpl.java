package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoCorteNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoCorteDiamanteJPA;

@Repository
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class CastigoCorteDiamanteRepositoryImpl implements CastigoCorteDiamanteRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CastigoCorteDiamanteRepositoryImpl.class);

	@Inject
	private CastigoCorteDiamanteJPARepository castigoCorteDiamanteJPARepository;

	public List<CastigoCorteDiamanteJPA> obtenerTodo() {
		List<CastigoCorteDiamanteJPA> castigoCorte = castigoCorteDiamanteJPARepository.findAll();

		return castigoCorte;
	}

	public List<CastigoCorteDiamanteJPA> obtenerTodoUltimaActualizacion() {

		List<CastigoCorteDiamanteJPA> fechaUltimaActualizacion = castigoCorteDiamanteJPARepository
				.findByUltimaActualizacion();

		return fechaUltimaActualizacion;

	}

	public CastigoCorteDiamanteJPA obtenerCastigoCorte(String corte) {

		if (corte == null) {
			String msg = "No puede ir nulo el campo Corte.";
			throw new CastigoCorteNoEncontradoException(CastigoCorteDiamanteJPA.class, msg);
		}

		CastigoCorteDiamanteJPA obtieneCastCort = castigoCorteDiamanteJPARepository.obtenerCastigoCorte(corte);

		return obtieneCastCort;

	}

	public CastigoCorteDiamanteJPA guardaActualizaCastigoCorte(CastigoCorteDiamanteJPA castigoCorteDiamanteJPA) {

		CastigoCorteDiamanteJPA entidad = null;

		if (castigoCorteDiamanteJPA.getId() != null && castigoCorteDiamanteJPA.getId() > 0) {

			entidad = castigoCorteDiamanteJPARepository.findOne(castigoCorteDiamanteJPA.getId());

			if (entidad == null) {

				String msg = "No existe un id para Castigo por Tipo de Corte";
				throw new CastigoCorteNoEncontradoException(CastigoCorteDiamanteJPA.class, msg);

			}

			entidad.setCorte(castigoCorteDiamanteJPA.getCorte() != null ? castigoCorteDiamanteJPA.getCorte()
					: entidad.getCorte());
			entidad.setFactor(castigoCorteDiamanteJPA.getFactor() != null ? castigoCorteDiamanteJPA.getFactor()
					: entidad.getFactor());
			entidad.setFecha(DateTime.now());

			castigoCorteDiamanteJPARepository.save(entidad);

		} else {

			if (castigoCorteDiamanteJPA.getCorte() == null)
				throw new CastigoCorteNoEncontradoException(CastigoCorteDiamanteJPA.class,
						"No debe ser nulo el campo Corte.");

			if (castigoCorteDiamanteJPA.getFactor() == null)
				throw new CastigoCorteNoEncontradoException(CastigoCorteDiamanteJPA.class,
						"No debe ser nulo el campo Factor.");

			castigoCorteDiamanteJPA.setId(null);
			castigoCorteDiamanteJPA.setFecha(DateTime.now());

			entidad = castigoCorteDiamanteJPARepository.save(castigoCorteDiamanteJPA);
		}

		return entidad;

	}

}
