/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactorAlhajaNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoRangoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhaja;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhajaFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoRango;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorAlhajaVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorAlhajaJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistFactorAlhajaJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoFactorAlhajaJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoFactorAlhajaJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementación de ModificadorRangoRepository.
 *
 * @author mmarquez
 */
@Repository
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ModificadorRangoRepositoryImpl implements ModificadorRangoRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModificadorRangoRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoFactorAlhajaJPARepository.
     */
    @Inject
    private HistListadoFactorAlhajaJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoFactorAlhajaRepositoryJPA.
     */
    @Inject
    private ListadoFactorAlhajaRepositoryJPA listadoJpaRepository;

    /**
     * Referencia al repositorio de FactorAlhajaRepositoryJPA.
     */
    @Inject
    private FactorAlhajaRepositoryJPA factorAlhajaRepositoryJPA;

    /**
     * Referencia a la fabrica de entidades.
     */
    @Inject
    private ListadoRangoFactory fabricaEntidad;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public FactorAlhaja obtenerFactor(@NotNull FactorAlhajaVO factorAlhaja) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> obtenerFactor [metal: [{}], calidad: [{}], rango: [{}]]", factorAlhaja.getMetal(),
                factorAlhaja.getCalidad(), factorAlhaja.getRango());
        }
        FactorAlhajaJPA factorAlhajaJPA;
        if(ObjectUtils.isEmpty(factorAlhaja.getCalidad())) {
            factorAlhajaJPA =
                factorAlhajaRepositoryJPA.findByMetalAndRango(factorAlhaja.getMetal(),
                    factorAlhaja.getRango());
        } else {
            factorAlhajaJPA =
                factorAlhajaRepositoryJPA.findByMetalAndCalidadAndRango(factorAlhaja.getMetal(),
                    factorAlhaja.getCalidad(), factorAlhaja.getRango());
        }
        if (ObjectUtils.isEmpty(factorAlhajaJPA)) {
            String msg = "No existe un Factor con las caracteristicas de busqueda.";
            LOGGER.warn(msg);
            throw new FactorAlhajaNoEncontradoException(msg, FactorAlhajaJPA.class);
        }

        return FactorAlhajaFactory.crear(factorAlhajaJPA.getMetal(), factorAlhajaJPA.getCalidad(), factorAlhajaJPA.getRango(),
            factorAlhajaJPA.getFactor(), factorAlhajaJPA.getDesplazamiento_limiteInferior(),factorAlhajaJPA.getDesplazamiento_limiteSuperior(),
            factorAlhajaJPA.getDesplazamiento_incremento(),factorAlhajaJPA.getLimiteInferior(),factorAlhajaJPA.getLimiteSuperior(), 
            factorAlhajaJPA.getIncremento(),factorAlhajaJPA.getUltimaActualizacion());
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
            String msg = "No existe un Listado de Factores vigente.";
            LOGGER.warn(msg);
            throw new FactorAlhajaNoEncontradoException(msg, ListadoFactorAlhajaJPA.class);
        }

        return convertirAListadoRango(listadoJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ListadoRango> consultarListadoPorFechaCarga(@NotNull LocalDate ultimaActualizacion) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarListadoPorFechaCarga: [{}]",
                (ultimaActualizacion != null) ? ultimaActualizacion.toString() : "NULL");
        }

        DateTime fechaUltimaActualizacionInicio = ultimaActualizacion.toDateTimeAtStartOfDay();
        DateTime fechaUltimaActualizacionFin = ultimaActualizacion.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue();;

        Set<ListadoFactorAlhajaJPA> listaVigentes =
            listadoJpaRepository.obtenerListadoPorUltimaActualizacion(fechaUltimaActualizacionInicio, fechaUltimaActualizacionFin);
        Set<HistListadoFactorAlhajaJPA> listaHistoricos =
            histListadoJpaRepository.obtenerListadoPorUltimaActualizacion(fechaUltimaActualizacionInicio, fechaUltimaActualizacionFin);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            String msg = "No existe un Listado de Factores para la fecha solicitada.";
            LOGGER.warn(msg);
            throw new FactorAlhajaNoEncontradoException(msg, ListadoFactorAlhajaJPA.class);
        }

        Set<ListadoRango> result = new HashSet<>();
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
    public ListadoRango actualizarListado(@NotNull ListadoRango listado) {
        LOGGER.info(">> actualizarListado");

        ListadoFactorAlhajaJPA listadoFactorAlhajaJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoFactorAlhajaJPA)) {
            HistListadoFactorAlhajaJPA histListadoFactorAlhajaJPA = new HistListadoFactorAlhajaJPA();
            Set<HistFactorAlhajaJPA> histListadoFactoresAlhajaJPA = new HashSet<>();
            if (!ObjectUtils.isEmpty(listadoFactorAlhajaJPA.getFactoresAlhaja())) {
                for (FactorAlhajaJPA factorAlhajaJPA : listadoFactorAlhajaJPA.getFactoresAlhaja()) {
                    HistFactorAlhajaJPA histFactorAlhajaJPA = new HistFactorAlhajaJPA();
                    histFactorAlhajaJPA.setMetal(factorAlhajaJPA.getMetal());
                    histFactorAlhajaJPA.setCalidad(factorAlhajaJPA.getCalidad());
                    histFactorAlhajaJPA.setRango(factorAlhajaJPA.getRango());
                    histFactorAlhajaJPA.setFactor(factorAlhajaJPA.getFactor());
                    histFactorAlhajaJPA.setDesplazamiento_limiteInferior(factorAlhajaJPA.getDesplazamiento_limiteInferior());
                    histFactorAlhajaJPA.setDesplazamiento_limiteSuperior(factorAlhajaJPA.getDesplazamiento_limiteSuperior());
                    histFactorAlhajaJPA.setDesplazamiento_incremento(factorAlhajaJPA.getDesplazamiento_incremento());
                    histFactorAlhajaJPA.setLimiteInferior(factorAlhajaJPA.getLimiteInferior());
                    histFactorAlhajaJPA.setLimiteSuperior(factorAlhajaJPA.getLimiteSuperior());
                    histFactorAlhajaJPA.setIncremento(factorAlhajaJPA.getIncremento());
                    histFactorAlhajaJPA.setUltimaActualizacion(factorAlhajaJPA.getUltimaActualizacion());

                    histFactorAlhajaJPA.setListado(histListadoFactorAlhajaJPA);

                    histListadoFactoresAlhajaJPA.add(histFactorAlhajaJPA);
                }
            }

            histListadoFactorAlhajaJPA.setFactoresAlhaja(histListadoFactoresAlhajaJPA);
            histListadoFactorAlhajaJPA.setUltimaActualizacion(listadoFactorAlhajaJPA.getUltimaActualizacion());
            histListadoFactorAlhajaJPA.setFechaListado(listadoFactorAlhajaJPA.getFechaListado());
            histListadoJpaRepository.save(histListadoFactorAlhajaJPA);

            listadoJpaRepository.delete(listadoFactorAlhajaJPA.getId());
        }

        ListadoFactorAlhajaJPA listadoNuevo = new ListadoFactorAlhajaJPA();
        Set<FactorAlhajaJPA> factoresAlhaja = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getFactorAlhajas())) {
            for (FactorAlhaja factorAlhaja : listado.getFactorAlhajas()) {
                FactorAlhajaJPA factorAlhajaJPA = new FactorAlhajaJPA();
                factorAlhajaJPA.setMetal(factorAlhaja.getMetal());
                factorAlhajaJPA.setCalidad(factorAlhaja.getCalidad());
                factorAlhajaJPA.setRango(factorAlhaja.getRango());
                factorAlhajaJPA.setFactor(factorAlhaja.getFactor());
                factorAlhajaJPA.setDesplazamiento_limiteInferior(factorAlhaja.getDesplazamiento_limite_inferior());
                factorAlhajaJPA.setDesplazamiento_limiteSuperior(factorAlhaja.getDesplazamiento_limite_superior());
                factorAlhajaJPA.setDesplazamiento_incremento(factorAlhaja.getDesplazamiento_incremento());
                factorAlhajaJPA.setLimiteInferior(factorAlhaja.getLimiteInferior());
                factorAlhajaJPA.setLimiteSuperior(factorAlhaja.getLimiteSuperior());
                factorAlhajaJPA.setIncremento(factorAlhaja.getIncremento());
                factorAlhajaJPA.setUltimaActualizacion(DateTime.now());

                factorAlhajaJPA.setListado(listadoNuevo);

                factoresAlhaja.add(factorAlhajaJPA);
            }
        }

        listadoNuevo.setFactoresAlhaja(factoresAlhaja);
        listadoNuevo.setUltimaActualizacion(DateTime.now());
        listadoNuevo.setFechaListado(listado.getFechaListado());
        return convertirAListadoRango(listadoJpaRepository.save(listadoNuevo));
    }

    /**
     * Metodo auxiliar para convertir el listado de factores alhajas JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoRango convertirAListadoRango(ListadoFactorAlhajaJPA listado) {
        Set<FactorAlhaja> factoresAlhaja = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getFactoresAlhaja())) {
            for (FactorAlhajaJPA factorAlhajaJPA : listado.getFactoresAlhaja()) {
                factoresAlhaja.add(
                    FactorAlhajaFactory.crear(factorAlhajaJPA.getMetal(), factorAlhajaJPA.getCalidad(), factorAlhajaJPA.getRango(),
                        factorAlhajaJPA.getFactor(), factorAlhajaJPA.getDesplazamiento_limiteInferior(),factorAlhajaJPA.getDesplazamiento_limiteSuperior(),
                        factorAlhajaJPA.getDesplazamiento_incremento(),factorAlhajaJPA.getLimiteInferior(),
                        factorAlhajaJPA.getLimiteSuperior(),factorAlhajaJPA.getIncremento(), factorAlhajaJPA.getUltimaActualizacion()));
            }
        }

        return fabricaEntidad.crear(listado.getUltimaActualizacion(), listado.getFechaListado(), factoresAlhaja);
    }

    /**
     * Metodo auxiliar para convertir el listado de factores alhajas JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoRango convertirAListadoRango(HistListadoFactorAlhajaJPA listado) {
        Set<FactorAlhaja> factoresAlhaja = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getFactoresAlhaja())) {
            for (HistFactorAlhajaJPA factorAlhajaJPA : listado.getFactoresAlhaja()) {
                factoresAlhaja.add(
                    FactorAlhajaFactory.crear(factorAlhajaJPA.getMetal(), factorAlhajaJPA.getCalidad(), factorAlhajaJPA.getRango(),
                        factorAlhajaJPA.getFactor(), factorAlhajaJPA.getDesplazamiento_limiteInferior(),factorAlhajaJPA.getDesplazamiento_limiteSuperior(),
                        factorAlhajaJPA.getDesplazamiento_incremento(),factorAlhajaJPA.getLimiteInferior(),
                        factorAlhajaJPA.getLimiteSuperior(),factorAlhajaJPA.getIncremento(), factorAlhajaJPA.getUltimaActualizacion()));
            }
        }

        return fabricaEntidad.crear(listado.getUltimaActualizacion(), listado.getFechaListado(), factoresAlhaja);
    }


}
