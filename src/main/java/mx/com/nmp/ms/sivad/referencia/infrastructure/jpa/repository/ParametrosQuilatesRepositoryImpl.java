package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ParametrosQuilatesNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ParametrosQuilatesRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ParametrosQuilatesJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;

@Repository
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ParametrosQuilatesRepositoryImpl implements ParametrosQuilatesRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParametrosQuilatesRepositoryImpl.class);

	@Inject
	private ParametrosQuilatesRepositoryJPA parametrosQuilatesRepositoryJPA;

    @Inject
    private ValorComercialDiamanteJPARepository valorComercialDiamanteJPARepository;

    @Inject
    private ListadoValorComercialDiamanteJPARepository listadoJpaRepository;

	public List<ParametrosQuilatesJPA> obtenerTodo() {
		List<ParametrosQuilatesJPA> parametrosQuilates = parametrosQuilatesRepositoryJPA.findAll();

		return parametrosQuilates;
	}

	public List<ParametrosQuilatesJPA> obtenerTodoUltimaActualizacion() {

		List<ParametrosQuilatesJPA> fechaUltimaActualizacion = parametrosQuilatesRepositoryJPA
				.findByUltimaActualizacion();

		return fechaUltimaActualizacion;

	}

	public List<ParametrosQuilatesJPA> busquedaQuilatesDesdeQuilatesHasta(BigDecimal quilatesDesde, BigDecimal quilatesHasta) {

		if (quilatesDesde == null)
			throw new ParametrosQuilatesNoEncontradoException("No puede ir nulo el campo QuilatesDesde.",
					ParametrosQuilatesJPA.class);

		if (quilatesHasta == null)
			throw new ParametrosQuilatesNoEncontradoException("No puede ir nulo el campo QuilatesHasta.",
					ParametrosQuilatesJPA.class);

		List<ParametrosQuilatesJPA> bQuilatesDesdeQuilatesHasta = parametrosQuilatesRepositoryJPA
				.findByQuilatesDesdeAndQuilatesHasta(quilatesDesde, quilatesHasta);

		return bQuilatesDesdeQuilatesHasta;

	}

	public List<ParametrosQuilatesJPA> busquedaQuilatesBaseDesdeQuilatesBaseHasta(BigDecimal quilatesBaseDesde, BigDecimal quilatesBaseHasta){

		if (quilatesBaseDesde == null)
			throw new ParametrosQuilatesNoEncontradoException("No puede ir nulo el campo QuilatesBaseDesde.",
					ParametrosQuilatesJPA.class);

		if (quilatesBaseHasta == null)
			throw new ParametrosQuilatesNoEncontradoException("No puede ir nulo el campo QuilatesBaseHasta.",
					ParametrosQuilatesJPA.class);

		List<ParametrosQuilatesJPA> bQuilatesBaseDesdeQuilatesBaseHasta = parametrosQuilatesRepositoryJPA
				.findByQuilatesBaseDesdeAndQuilatesBaseHasta(quilatesBaseDesde, quilatesBaseHasta);

		return bQuilatesBaseDesdeQuilatesBaseHasta;

	}

	public ParametrosQuilatesJPA guardaParametrosQuilates(ParametrosQuilatesJPA parametrosQuilatesJPA) {

		ParametrosQuilatesJPA entidad = null;

		if (parametrosQuilatesJPA.getId() != null && parametrosQuilatesJPA.getId() > 0) {

			if (parametrosQuilatesJPA.getQuilatesDesde() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo QuilatesDesde.",
						ParametrosQuilatesJPA.class);

			if (parametrosQuilatesJPA.getQuilatesHasta() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo QuilatesHasta.",
						ParametrosQuilatesJPA.class);

			if (parametrosQuilatesJPA.getQuilatesBaseDesde() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo QuilatesBaseDesde.",
						ParametrosQuilatesJPA.class);

			if (parametrosQuilatesJPA.getQuilatesBaseHasta() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo QuilatesBaseHasta.",
						ParametrosQuilatesJPA.class);

			if (parametrosQuilatesJPA.getPorcentaje() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo Porcentaje.",
						ParametrosQuilatesJPA.class);

			entidad = parametrosQuilatesRepositoryJPA.findOne(parametrosQuilatesJPA.getId());

			if (entidad == null)
				throw new ParametrosQuilatesNoEncontradoException("No existe un id Parametros Quilates.",
						ParametrosQuilatesJPA.class);

			entidad.setQuilatesDesde(
					parametrosQuilatesJPA.getQuilatesDesde() != null ? parametrosQuilatesJPA.getQuilatesDesde()
							: entidad.getQuilatesDesde());
			entidad.setQuilatesHasta(
					parametrosQuilatesJPA.getQuilatesHasta() != null ? parametrosQuilatesJPA.getQuilatesHasta()
							: entidad.getQuilatesHasta());
			entidad.setQuilatesBaseDesde(
					parametrosQuilatesJPA.getQuilatesBaseDesde() != null ? parametrosQuilatesJPA.getQuilatesBaseDesde()
							: entidad.getQuilatesBaseHasta());
			entidad.setQuilatesBaseHasta(
					parametrosQuilatesJPA.getQuilatesBaseHasta() != null ? parametrosQuilatesJPA.getQuilatesBaseHasta()
							: entidad.getQuilatesBaseHasta());
			entidad.setPorcentaje(parametrosQuilatesJPA.getPorcentaje() != null ? parametrosQuilatesJPA.getPorcentaje()
					: entidad.getPorcentaje());
			entidad.setFecha(DateTime.now());

			parametrosQuilatesRepositoryJPA.save(entidad);

		} else {
			if (parametrosQuilatesJPA.getQuilatesDesde() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo QuilatesDesde.",
						ParametrosQuilatesJPA.class);

			if (parametrosQuilatesJPA.getQuilatesHasta() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo QuilatesHasta.",
						ParametrosQuilatesJPA.class);

			if (parametrosQuilatesJPA.getQuilatesBaseDesde() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo QuilatesBaseDesde.",
						ParametrosQuilatesJPA.class);

			if (parametrosQuilatesJPA.getQuilatesBaseHasta() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo QuilatesBaseHasta.",
						ParametrosQuilatesJPA.class);

			if (parametrosQuilatesJPA.getPorcentaje() == null)
				throw new ParametrosQuilatesNoEncontradoException("No debe ser nulo el campo Porcentaje.",
						ParametrosQuilatesJPA.class);

			parametrosQuilatesJPA.setId(null);
			parametrosQuilatesJPA.setFecha(DateTime.now());

			entidad = parametrosQuilatesRepositoryJPA.save(parametrosQuilatesJPA);
		}

		return entidad;

	}


	@Override
	public List<ValorComercialDiamanteJPA> obtenerValorComercial() {
		LOGGER.info(">> obtenerValorComercial({})");

		List<ParametrosQuilatesJPA> parametrosQuilates = parametrosQuilatesRepositoryJPA.findByUltimaActualizacion();

		if (ObjectUtils.isEmpty(parametrosQuilates)) {
			String msg = "No existe ningun registro en Parametros Quilates.";
			LOGGER.warn(msg);
			throw new ParametrosQuilatesNoEncontradoException(msg, ParametrosQuilatesJPA.class);
		}

		List<ValorComercialDiamanteJPA> valorComerJPA = new ArrayList<ValorComercialDiamanteJPA>();

		for (ParametrosQuilatesJPA parametrosQuilatesJPA : parametrosQuilates) {

			Set<ValorComercialDiamanteJPA> listadoValorComercialDiamanteJPA = listadoJpaRepository.findByListadoVigente(
					parametrosQuilatesJPA.getQuilatesBaseDesde(), parametrosQuilatesJPA.getQuilatesBaseHasta());

			for (ValorComercialDiamanteJPA vComerDiamanteJPA : listadoValorComercialDiamanteJPA) {

				if (vComerDiamanteJPA.getTamanioInferior() != parametrosQuilatesJPA.getQuilatesBaseDesde()
						&& vComerDiamanteJPA.getTamanioSuperior() != parametrosQuilatesJPA.getQuilatesBaseHasta()) {

					// Insertar registro en valor comercial
					ValorComercialDiamanteJPA vc = new ValorComercialDiamanteJPA();

					vc.setTamanioInferior(parametrosQuilatesJPA.getQuilatesBaseDesde());
					vc.setTamanioSuperior(parametrosQuilatesJPA.getQuilatesBaseHasta());
					vc.setPrecio(vComerDiamanteJPA.getPrecio().multiply(parametrosQuilatesJPA.getPorcentaje()));

					valorComerJPA.add(vc);

					valorComercialDiamanteJPARepository.save(valorComerJPA);
				}

			}

		}

		return valorComerJPA;
	}

	@Override
	public ParametrosQuilatesJPA update(Long idParametro, ParametrosQuilatesJPA elemento) {
		ParametrosQuilatesJPA pq = this.parametrosQuilatesRepositoryJPA.findOne(idParametro);
        actualizarCatalogo(pq, elemento);        
        pq.setFecha(DateTime.now());
        
        // TODO: Validar duplicados con otro rango
        //ParametrosQuilatesJPA pqDup = this.parametrosQuilatesRepositoryJPA.findByQtesDesdeAndQtesHasta(elemento.getQuilatesDesde(), elemento.getQuilatesHasta());

        return this.parametrosQuilatesRepositoryJPA.saveAndFlush(pq);
	}

    /**
     * Actualiza un objeto {@link T} apartir de otro.
     *
     * @param original Objeto original a ser actualizado.
     * @param nuevo Objeto con las modificaciones.
     */
    private void actualizarCatalogo(ParametrosQuilatesJPA original, ParametrosQuilatesJPA nuevo) {
        if (ObjectUtils.isEmpty(nuevo.getQuilatesDesde())) {
            LOGGER.warn("{}.quilatesDesde = null, se deja valor anterior {}");
        } else {
            original.setQuilatesDesde(nuevo.getQuilatesDesde());
        }

        if (ObjectUtils.isEmpty(nuevo.getQuilatesHasta())) {
            LOGGER.warn("{}.quilatesHasta = null, se deja valor anterior {}");
        } else {
            original.setQuilatesHasta(nuevo.getQuilatesHasta());
        }

        if (ObjectUtils.isEmpty(nuevo.getQuilatesBaseDesde())) {
            LOGGER.warn("{}.quilatesBaseDesde = null, se deja valor anterior {}");
        } else {
            original.setQuilatesBaseDesde(nuevo.getQuilatesBaseDesde());
        }

        if (ObjectUtils.isEmpty(nuevo.getQuilatesBaseHasta())) {
            LOGGER.warn("{}.quilatesBaseHasta = null, se deja valor anterior {}");
        } else {
            original.setQuilatesBaseHasta(nuevo.getQuilatesBaseHasta());
        }

        if (ObjectUtils.isEmpty(nuevo.getPorcentaje())) {
            LOGGER.warn("{}.porcentaje = null, se deja valor anterior {}");
        } else {
            original.setPorcentaje(nuevo.getPorcentaje());
        }

    }

    public ParametrosQuilatesJPA delete(Long idFactor) {
        ParametrosQuilatesJPA fd = this.parametrosQuilatesRepositoryJPA.findOne(idFactor);

        if(ObjectUtils.isEmpty(fd)) {
            String mensaje = "El catalogo ParametrosQuilatesJPA no contiene un elemento con el identificador [" + idFactor + "].";
            throw new ParametrosQuilatesNoEncontradoException(mensaje, ParametrosQuilatesJPA.class);
        }

        parametrosQuilatesRepositoryJPA.delete(fd);

        return fd;
    }

    public ParametrosQuilatesJPA obtenerElemento(Long idFactor) {
        return parametrosQuilatesRepositoryJPA.findOne(idFactor);
    }

}
