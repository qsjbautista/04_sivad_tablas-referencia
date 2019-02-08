/**
 * Proyecto:        NMP - Sistema de Operacion Prendaria Emergente
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.TipoHechuraNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.TipoHechura;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.MetalVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.TipoHechuraAlhajaRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.TipoHechuraJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialMetalJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementación de TipoHechuraAlhajaRepository.
 *
 * @author ecancino
 */
@Component
public class TipoHechuraAlhajaRepositoryImpl implements TipoHechuraAlhajaRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TipoHechuraAlhajaRepositoryImpl.class);

    /**
     * Referencia al repositorio de ValorComercialMetalJPARepository.
     */
    @Inject
    private TipoHechuraJPARepository tipoHechuraJPARepository;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TipoHechura> consultarTiposHechura(MetalVO metalVO) {
        LOGGER.info(">> consultarTiposHechura({})", metalVO);

        List<TipoHechura> listadoTiposHechura;

        if (ObjectUtils.isEmpty(metalVO.getCalidad())) {
            listadoTiposHechura =
                tipoHechuraJPARepository.findByMetalAndSubramo(metalVO.getMetal(), metalVO.getSubramo());
        } else {
            listadoTiposHechura =
                tipoHechuraJPARepository.findByMetalAndSubramoAndCalidad(metalVO.getMetal(), metalVO.getSubramo(), metalVO.getCalidad());
        }

        if (ObjectUtils.isEmpty(listadoTiposHechura)) {
            String msg = "No existen tipos de hechura para las características de metal solicitadas.";
            LOGGER.warn(msg);
            throw new TipoHechuraNoEncontradoException(msg, TipoHechuraJPA.class);
        }

        return listadoTiposHechura;
    }
}
