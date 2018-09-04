package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.FactorDepreciacionRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorDepreciacionDiamanteJPA;

@Repository
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class FactorDepreciacionRepositoryImpl implements FactorDepreciacionRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(FactorDepreciacionRepositoryImpl.class);

	@Inject
	private FactorDepreciacionDiamanteJPARepository factorDepreciacionJPARepository;


	@Override
	public FactorDepreciacionDiamanteJPA save(FactorDepreciacionDiamanteJPA elemento) {
		return factorDepreciacionJPARepository.save(elemento);
	}

	
	public FactorDepreciacionDiamanteJPA update(Long idFactor, BigDecimal factor) {
		
		FactorDepreciacionDiamanteJPA pq = this.factorDepreciacionJPARepository.findOne(idFactor);
        if (ObjectUtils.isEmpty(factor)) {
            LOGGER.warn("{}.factor = null, se deja valor anterior {}");
        } else {
            pq.setFactor(factor);
        }

		return factorDepreciacionJPARepository.saveAndFlush(pq);
	}


	@Override
	public FactorDepreciacionDiamanteJPA delete(Long idFactor) {
		FactorDepreciacionDiamanteJPA fd = this.factorDepreciacionJPARepository.getOne(idFactor);
		factorDepreciacionJPARepository.delete(fd);
		
		return fd;
	}

	@Override
	public List<FactorDepreciacionDiamanteJPA> getAll() {
		return factorDepreciacionJPARepository.findAll();
	}

}
