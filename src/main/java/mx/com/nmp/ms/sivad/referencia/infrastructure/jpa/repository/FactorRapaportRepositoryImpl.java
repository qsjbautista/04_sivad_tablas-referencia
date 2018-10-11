package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactorRapaportNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorRapaportDiamanteJPA;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.FactorRapaportRepository;

@Repository
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class FactorRapaportRepositoryImpl implements FactorRapaportRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(FactorRapaportRepositoryImpl.class);

	@Inject
	private FactorRapaportDiamanteJPARepository factorRapaportJPARepository;


	@Override
	public FactorRapaportDiamanteJPA save(FactorRapaportDiamanteJPA elemento) {
        elemento.setFecha(DateTime.now());
		return factorRapaportJPARepository.save(elemento);
	}


	public FactorRapaportDiamanteJPA update(Long idFactor, BigDecimal factor) {

        FactorRapaportDiamanteJPA pq = this.factorRapaportJPARepository.findOne(idFactor);

        if(ObjectUtils.isEmpty(pq)) {
            String msg = "El catalogo FactorRapaport no contiene un elemento con el identificador [" + idFactor + "].";
            throw new FactorRapaportNoEncontradoException(FactorRapaportDiamanteJPA.class, msg);

        }

        if (ObjectUtils.isEmpty(factor)) {
            LOGGER.warn("{}.factor = null, se deja valor anterior {}");
        } else {
            pq.setFactor(factor);
        }

        pq.setFecha(DateTime.now());

		return factorRapaportJPARepository.saveAndFlush(pq);
	}


	@Override
	public FactorRapaportDiamanteJPA delete(Long idFactor) {
        FactorRapaportDiamanteJPA fd = this.factorRapaportJPARepository.findOne(idFactor);

        if(ObjectUtils.isEmpty(fd)) {
            String mensaje = "El catalogo FactorRapaport no contiene un elemento con el identificador [" + idFactor + "].";
            throw new FactorRapaportNoEncontradoException(FactorRapaportDiamanteJPA.class, mensaje);
        }

		factorRapaportJPARepository.delete(fd);

		return fd;
	}

	@Override
	public List<FactorRapaportDiamanteJPA> getAll() {
		return factorRapaportJPARepository.findAll();
	}

    public FactorRapaportDiamanteJPA obtenerElemento(Long idFactor) {
        return factorRapaportJPARepository.findOne(idFactor);
    }

}
