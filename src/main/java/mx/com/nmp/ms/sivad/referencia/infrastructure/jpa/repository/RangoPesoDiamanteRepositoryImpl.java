/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.RangoPesoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.RangoPesoDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.RangoPesoDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.RangoPesoDiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.RangoPesoDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.RangoPesoDiamanteJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;

/**
 * Implementación de RangoPesoDiamanteRepository.
 *
 * @author ecancino
 */
@Component
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class RangoPesoDiamanteRepositoryImpl implements RangoPesoDiamanteRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RangoPesoDiamanteRepositoryImpl.class);

    /**
     * Referencia al repositorio de RangoPesoDiamanteJPARepository.
     */
    @Inject
    private RangoPesoDiamanteJPARepository rangoPesoDiamanteJPARepository;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public RangoPesoDiamante obtenerRangoPeso(@NotNull RangoPesoDiamanteVO rangoPesoDiamanteVO) {
        LOGGER.info(">> obtenerRangoPeso({})", rangoPesoDiamanteVO);

        //SE EJECUTA EL QUERY PARA OBTENER EL RANGO DE PESO POR EL QUILATAJE
        RangoPesoDiamanteJPA rangoPesoDiamanteJPA = rangoPesoDiamanteJPARepository.obtenerRangoPeso(rangoPesoDiamanteVO.getQuilates());

        if (ObjectUtils.isEmpty(rangoPesoDiamanteJPA)) {
            String msg = "No existe un rango de peso para los quilates del diamante solicitado.";
            LOGGER.warn(msg);
            throw new RangoPesoNoEncontradoException(RangoPesoDiamanteJPA.class, msg);
        }

        return RangoPesoDiamanteFactory.create(rangoPesoDiamanteJPA.getQuilatesDesde(),
            rangoPesoDiamanteJPA.getQuilatesHasta());
    }

}
