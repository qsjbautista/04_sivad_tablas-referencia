/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorValorDiamanteJpa;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación en JPA de la interface {@link ModificadorValorDiamanteRepository} permite recuperar de las unidades
 * persistentes el Value Object {@link FactorValorDiamante} con los factores vigentes.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
@Repository
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ModificadorValorDiamanteRepositoryJpa implements ModificadorValorDiamanteRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModificadorValorDiamanteRepositoryJpa.class);

    /**
     * Referencia al repositorio JPA.
     */
    @Inject
    private FactorValorDiamanteJpaRepository repositorio;

    /**
     * Referencia a la fabrica de entidades.
     */
    @Inject
    private ModificadorValorDiamanteFactory fabricaEntidad;

    /**
     * Referencia a la fabrica de Value Object
     */
    @Inject
    private FactorValorDiamanteFactory fabricaVO;

    /**
     * Constructor.
     */
    public ModificadorValorDiamanteRepositoryJpa() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(cacheNames = "ModificadorValorDiamanteRepositoryJpa.consultar", allEntries = true)
    public ModificadorValorDiamante actualizar(@NotNull ModificadorValorDiamante entidad) {
        LOGGER.info(">> actualizar({})", entidad);

        FactorValorDiamante vo = entidad.getFactor();
        FactorValorDiamanteJpa factor = new FactorValorDiamanteJpa();

        factor.setMinimo(vo.getMinimo());
        factor.setMedio(vo.getMedio());
        factor.setMaximo(vo.getMaximo());
        factor.setFechaCarga(entidad.getFechaCarga());
        factor.setFechaListado(entidad.getFechaListado());

        factor.setFabrica(fabricaVO);

        repositorio.saveAndFlush(factor);

        LOGGER.debug("creando entidad {} desde {}", getClass().getSimpleName(), factor);

        return fabricaEntidad.crearDesde(factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable("ModificadorValorDiamanteRepositoryJpa.consultar")
    public ModificadorValorDiamante consultar() {
        LOGGER.info(">> consultar()");

        FactorValorDiamanteJpa factor = repositorio.findFirstByOrderByFechaCargaDesc();

        if (ObjectUtils.isEmpty(factor)) {
            throw new ValorComercialNoEncontradoException(
                FactorValorDiamante.class, "No hay factores de valor de diamante vigentes");
        }

        factor.setFabrica(fabricaVO);

        LOGGER.debug("creando entidad {} desde {}", getClass().getSimpleName(), factor);

        return fabricaEntidad.crearDesde(factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ModificadorValorDiamante> consultar(@NotNull DateTime fecha) {
        validarFechaFutura(fecha);

        LOGGER.info(">> consultar({})", fecha);

        DateTime fechaFinal = fecha.millisOfDay().withMaximumValue();

        List<FactorValorDiamanteJpa> factores = repositorio.findByFechaCargaBetween(fecha, fechaFinal);

        if (ObjectUtils.isEmpty(factores)) {
            throw new ValorComercialNoEncontradoException(
                FactorValorDiamante.class, "No hay factores de valor de diamante en la fecha especificada");
        }

        List<ModificadorValorDiamante> resultado = new ArrayList<>();

        for (FactorValorDiamanteJpa factor : factores) {
            factor.setFabrica(fabricaVO);
            resultado.add(fabricaEntidad.crearDesde(factor));
        }

        LOGGER.debug("creando lista de entidades tipo {}, {}", getClass().getSimpleName(), resultado);

        return resultado;
    }


    /**
     * Verifica que la fecha no sea del futuro.
     *
     * @param fecha Fecha a validar.
     */
    private void validarFechaFutura(DateTime fecha) {
        if (fecha.isAfterNow()) {
            throw new FechaVigenciaFuturaException("La fecha de vigencia no puede ser una fecha futura",
                ModificadorValorDiamante.class);
        }
    }
}
