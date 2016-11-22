/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNotFoundException;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorValorDiamanteJpa;
import org.joda.time.LocalDate;
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
    public ModificadorValorDiamante actualizar(ModificadorValorDiamante entidad) {
        FactorValorDiamante vo = entidad.getFactor();
        FactorValorDiamanteJpa factor = new FactorValorDiamanteJpa();

        factor.setMinimo(vo.getMinimo());
        factor.setMedio(vo.getMedio());
        factor.setMaximo(vo.getMaximo());
        factor.setFechaCarga(entidad.getFechaCarga());
        factor.setFechaListado(entidad.getFechaListado());

        factor.setFabrica(fabricaVO);

        repositorio.saveAndFlush(factor);

        return fabricaEntidad.createFrom(factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModificadorValorDiamante consultar() {
        FactorValorDiamanteJpa factor = repositorio.findFirstByOrderByFechaListadoDescIdDesc();

        if (ObjectUtils.isEmpty(factor)) {
            throw new ValorComercialNotFoundException(
                FactorValorDiamante.class, "No hay factores de valor de diamante vigentes");
        }

        factor.setFabrica(fabricaVO);

        return fabricaEntidad.createFrom(factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ModificadorValorDiamante> consultar(LocalDate fecha) {
        List<FactorValorDiamanteJpa> factores = repositorio.findByFechaListado(fecha);

        if (ObjectUtils.isEmpty(factores)) {
            throw new ValorComercialNotFoundException(
                FactorValorDiamante.class, "No hay factores de valor de diamante en la fecha especificada");
        }

        List<ModificadorValorDiamante> resultado = new ArrayList<>();

        for (FactorValorDiamanteJpa factor : factores) {
            factor.setFabrica(fabricaVO);
            resultado.add(fabricaEntidad.createFrom(factor));
        }

        return resultado;
    }
}
