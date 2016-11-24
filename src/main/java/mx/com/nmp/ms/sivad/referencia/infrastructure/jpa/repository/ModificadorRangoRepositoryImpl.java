/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.*;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.*;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * Implementación de ModificadorRangoRepository.
 *
 * @author mmarquez
 */
@Repository
public class ModificadorRangoRepositoryImpl implements ModificadorRangoRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModificadorRangoRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoFactorAlhajaJPARepository.
     */
    //@Inject
    private HistListadoFactorAlhajaJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoFactorAlhajaRepositoryJPA.
     */
    //@Inject
    private ListadoFactorAlhajaRepositoryJPA listadoJpaRepository;

    /**
     * Referencia al repositorio de FactorAlhajaRepositoryJPA.
     */
    //@Inject
    private FactorAlhajaRepositoryJPA factorAlhajaRepositoryJPA;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public FactorAlhaja obtenerFactor(@NotNull FactorAlhaja factorAlhaja) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> obtenerFactor [metal: [{}], calidad: [{}], rango: [{}]]", factorAlhaja.getMetal(),
                factorAlhaja.getCalidad(), factorAlhaja.getRango());
        }

        FactorAlhajaJPA factorAlhajaJPA =
            factorAlhajaRepositoryJPA.findByMetalAndCalidadAndRango(factorAlhaja.getMetal(), factorAlhaja.getCalidad(),
                factorAlhaja.getRango());

        if (ObjectUtils.isEmpty(factorAlhajaJPA)) {
            // TODO Excepción
        }

        return FactorAlhajaFactory.create(factorAlhajaJPA.getId(), factorAlhajaJPA.getCalidad(),
            factorAlhajaJPA.getFactor(), factorAlhajaJPA.getMetal(), factorAlhajaJPA.getRango(), factorAlhajaJPA.getFactorComercial(),
            factorAlhajaJPA.getLimiteInferior(), factorAlhajaJPA.getLimiteSuperior(), factorAlhajaJPA.getUltimaActualizacion());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoRango consultarListadoVigente() {
        LOGGER.info(">> consultarListadoVigente");

        ListadoFactorAlhajaJPA listadoJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (ObjectUtils.isEmpty(listadoJPA)) {
            // TODO Excepción
        }

        return convertirAListadoRango(listadoJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ListadoRango> consultarListadoPorFechaCarga(@NotNull Date fechaCarga) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarListadoPorFechaCarga: [{}]",
                (fechaCarga != null) ? fechaCarga.toString() : "NULL");
        }

        Date fechaCargaInicio = DateUtil.getStartOfDay(fechaCarga);
        Date fechaCargaFin = DateUtil.getEndOfDay(fechaCarga);

        List<ListadoFactorAlhajaJPA> listaVigentes =
            listadoJpaRepository.obtenerListadoPorFechaCarga(fechaCargaInicio, fechaCargaFin);
        List<HistListadoFactorAlhajaJPA> listaHistoricos =
            histListadoJpaRepository.obtenerListadoPorFechaCarga(fechaCargaInicio, fechaCargaFin);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            // TODO Excepción
        }

        List<ListadoRango> result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(listaVigentes)) {
            for (ListadoFactorAlhajaJPA listadoFactorAlhajaJPA : listaVigentes) {
                result.add(convertirAListadoRango(listadoFactorAlhajaJPA));
            }
        }

        if (!ObjectUtils.isEmpty(listaHistoricos)) {
            for (HistListadoFactorAlhajaJPA histListadoFactorAlhajaJPA : listaHistoricos) {
                result.add(convertirAListadoRango(histListadoFactorAlhajaJPA));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarListado(ListadoRango listado) {
        LOGGER.info(">> actualizarListado");

        ListadoFactorAlhajaJPA listadoFactorAlhajaJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoFactorAlhajaJPA)) {
            HistListadoFactorAlhajaJPA histlistado =
                new HistListadoFactorAlhajaJPA(listadoFactorAlhajaJPA.getFactoresAlhaja());
            histListadoJpaRepository.save(histlistado);

            listadoJpaRepository.delete(listadoFactorAlhajaJPA.getId());
        }

        ListadoFactorAlhajaJPA listadoNuevo = new ListadoFactorAlhajaJPA();
        Set<FactorAlhajaJPA> factoresAlhajaJPA = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getFactorAlhajas())) {
            for (FactorAlhaja factorAlhaja : listado.getFactorAlhajas()) {
                FactorAlhajaJPA factorAlhajaJPA = new FactorAlhajaJPA();
                factorAlhajaJPA.setFactor(factorAlhaja.getFactor());
                factorAlhajaJPA.setRango(factorAlhaja.getRango());
                factorAlhajaJPA.setCalidad(factorAlhaja.getCalidad());
                factorAlhajaJPA.setMetal(factorAlhaja.getMetal());
                factorAlhajaJPA.setFactorComercial(factorAlhaja.getFactorComercial());
                factorAlhajaJPA.setLimiteInferior(factorAlhaja.getLimiteInferior());
                factorAlhajaJPA.setLimiteSuperior(factorAlhaja.getLimiteSuperior());
                factorAlhajaJPA.setUltimaActualizacion(factorAlhaja.getUltimaActualizacion());
                factorAlhajaJPA.setListado(listadoNuevo);

                factoresAlhajaJPA.add(factorAlhajaJPA);
            }
        }

        listadoNuevo.setFactoresAlhaja(factoresAlhajaJPA);
        listadoNuevo.setFechaCarga(new Date());
        listadoJpaRepository.save(listadoNuevo);
    }

    /**
     * Metodo auxiliar para convertir el listado de factores alhajas JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoRango convertirAListadoRango(AbstractListadoFactorAlhajaJPA listado) {
        Set<FactorAlhaja> factoresAlhaja = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getFactoresAlhaja())) {
            for (FactorAlhajaJPA factorAlhajaJPA : listado.getFactoresAlhaja()) {
                factoresAlhaja.add(
                    FactorAlhajaFactory.create(factorAlhajaJPA.getId(), factorAlhajaJPA.getCalidad(),
                        factorAlhajaJPA.getFactor(), factorAlhajaJPA.getMetal(), factorAlhajaJPA.getRango(), factorAlhajaJPA.getFactorComercial(),
                        factorAlhajaJPA.getLimiteInferior(), factorAlhajaJPA.getLimiteSuperior(), factorAlhajaJPA.getUltimaActualizacion()));
            }
        }

        return ListadoRangoFactory.create(
            listado.getId(), listado.getFechaCarga(), listado.getFechaListado(), factoresAlhaja);
    }


}
