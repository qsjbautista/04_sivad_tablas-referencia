/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetalFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.MetalFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.*;

/**
 * Implementación de ValorComercialMetalRepository.
 *
 * @author ngonzalez
 */
@Repository
public class ValorComercialMetalRepositoryImpl implements ValorComercialMetalRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialMetalRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoValorComercialMetalJPARepository.
     */
    //@Inject
    private HistListadoValorComercialMetalJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoValorComercialMetalJPARepository.
     */
    //@Inject
    private ListadoValorComercialMetalJPARepository listadoJpaRepository;

    /**
     * Referencia al repositorio de ValorComercialMetalJPARepository.
     */
    //@Inject
    private ValorComercialMetalJPARepository valorComercialMetalJPARepository;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Metal consultarMetalVigente(@NotNull Metal metal) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarVigente - metal: [{}], calidad: [{}]", metal.getMetal(), metal.getCalidad());
        }

        ValorComercialMetalJPA valorComercialMetalJPA =
            valorComercialMetalJPARepository.findByMetalAndCalidad(metal.getMetal(), metal.getCalidad());

        if (ObjectUtils.isEmpty(valorComercialMetalJPA)) {
            // TODO Excepción
        }

        return MetalFactory.create(valorComercialMetalJPA.getId(), valorComercialMetalJPA.getMetal(),
            valorComercialMetalJPA.getCalidad(), valorComercialMetalJPA.getPrecio());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoValorComercialMetal consultarListadoVigente() {
        LOGGER.info(">> consultarListadoVigente");

        ListadoValorComercialMetalJPA listadoValorComercialMetalJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (ObjectUtils.isEmpty(listadoValorComercialMetalJPA)) {
            // TODO Excepción
        }

        return convertirAListadoValorComercialMetal(listadoValorComercialMetalJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ListadoValorComercialMetal> consultarListadoPorFechaVigencia(@NotNull Date fechaVigencia) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarListadoPorFechaVigencia: [{}]",
                (fechaVigencia != null) ? fechaVigencia.toString() : "NULL");
        }

        Date fechaVigenciaInicio = DateUtil.getStartOfDay(fechaVigencia);
        Date fechaVigenciaFin = DateUtil.getEndOfDay(fechaVigencia);

        List<ListadoValorComercialMetalJPA> listaVigentes =
            listadoJpaRepository.obtenerListadoPorFechaVigencia(fechaVigenciaInicio, fechaVigenciaFin);
        List<HistListadoValorComercialMetalJPA> listaHistoricos =
            histListadoJpaRepository.obtenerListadoPorFechaVigencia(fechaVigenciaInicio, fechaVigenciaFin);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            // TODO Excepción
        }

        List<ListadoValorComercialMetal> result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(listaVigentes)) {
            for (ListadoValorComercialMetalJPA listadoValorComercialMetalJPA : listaVigentes) {
                result.add(convertirAListadoValorComercialMetal(listadoValorComercialMetalJPA));
            }
        }

        if (!ObjectUtils.isEmpty(listaHistoricos)) {
            for (HistListadoValorComercialMetalJPA histListadoValorComercialMetalJPA : listaHistoricos) {
                result.add(convertirAListadoValorComercialMetal(histListadoValorComercialMetalJPA));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarListado(ListadoValorComercialMetal listado) {
        LOGGER.info(">> actualizarListado");

        ListadoValorComercialMetalJPA listadoValorComercialMetalJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoValorComercialMetalJPA)) {
            HistListadoValorComercialMetalJPA histListadoValorComercialMetalJPA =
                new HistListadoValorComercialMetalJPA(listadoValorComercialMetalJPA.getValoresComerciales());
            histListadoValorComercialMetalJPA.setUltimaActualizacion(new Date());
            histListadoJpaRepository.save(histListadoValorComercialMetalJPA);

            listadoJpaRepository.delete(listadoValorComercialMetalJPA.getId());
        }

        ListadoValorComercialMetalJPA listadoNuevo = new ListadoValorComercialMetalJPA();
        Set<ValorComercialMetalJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (Metal metal : listado.getValoresComerciales()) {
                ValorComercialMetalJPA valorComercialMetalJPA = new ValorComercialMetalJPA();
                valorComercialMetalJPA.setMetal(metal.getMetal());
                valorComercialMetalJPA.setCalidad(metal.getCalidad());
                valorComercialMetalJPA.setPrecio(metal.obtenerValorGramo());
                valorComercialMetalJPA.setListado(listadoNuevo);

                valoresComerciales.add(valorComercialMetalJPA);
            }
        }

        listadoNuevo.setValoresComerciales(valoresComerciales);
        listadoNuevo.setUltimaActualizacion(new Date());
        listadoJpaRepository.save(listadoNuevo);
    }

    /**
     * Metodo auxiliar para convertir el listado de valores comerciales del metal JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoValorComercialMetal convertirAListadoValorComercialMetal(ListadoValorComercialMetalJPA listado) {
        Set<Metal> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialMetalJPA vcm : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    MetalFactory.create(vcm.getId(), vcm.getMetal(), vcm.getCalidad(), vcm.getPrecio()));
            }
        }

        return ListadoValorComercialMetalFactory.create(
            listado.getId(), listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar para convertir el histórico de listado de valores comerciales del metal JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoValorComercialMetal convertirAListadoValorComercialMetal(HistListadoValorComercialMetalJPA listado) {
        Set<Metal> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (HistValorComercialMetalJPA vcm : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    MetalFactory.create(vcm.getId(), vcm.getMetal(), vcm.getCalidad(), vcm.getPrecio()));
            }
        }

        return ListadoValorComercialMetalFactory.create(
            listado.getId(), listado.getUltimaActualizacion(), valoresComerciales);
    }

}
