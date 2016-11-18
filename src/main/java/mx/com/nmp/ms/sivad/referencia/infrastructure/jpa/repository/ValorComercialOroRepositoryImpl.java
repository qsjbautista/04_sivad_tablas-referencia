/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialOroJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.*;

/**
 * Implementación de ValorComercialOroRepository.
 *
 * @author ngonzalez
 */
@Repository
public class ValorComercialOroRepositoryImpl implements ValorComercialOroRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialOroRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoValorComercialOroJPARepository.
     */
    //@Inject
    private HistListadoValorComercialOroJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoValorComercialOroJPARepository.
     */
    //@Inject
    private ListadoValorComercialOroJPARepository listadoJpaRepository;

    /**
     * Referencia al repositorio de ValorComercialOroJPARepository.
     */
    //@Inject
    private ValorComercialOroJPARepository valorComercialOroJPARepository;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Oro consultarOroVigente(@NotNull Oro oro) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarVigente - color: [{}], calidad: [{}]", oro.getColor(), oro.getCalidad());
        }

        ValorComercialOroJPA valorComercialOroJPA =
            valorComercialOroJPARepository.findByColorAndCalidad(oro.getColor(), oro.getCalidad());

        if (ObjectUtils.isEmpty(valorComercialOroJPA)) {
            // TODO Excepción
        }

        return OroFactory.create(valorComercialOroJPA.getId(), valorComercialOroJPA.getColor(),
            valorComercialOroJPA.getCalidad(), valorComercialOroJPA.getPrecio());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoValorComercialOro consultarListadoVigente() {
        LOGGER.info(">> consultarListadoVigente");

        ListadoValorComercialOroJPA listadoValorComercialOroJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (ObjectUtils.isEmpty(listadoValorComercialOroJPA)) {
            // TODO Excepción
        }

        return convertirAListadoValorComercialOro(listadoValorComercialOroJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ListadoValorComercialOro> consultarListadoPorFechaVigencia(@NotNull Date fechaVigencia) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarListadoPorFechaVigencia: [{}]",
                (fechaVigencia != null) ? fechaVigencia.toString() : "NULL");
        }

        Date fechaInicioVigencia = new Date(
            fechaVigencia.getYear(),
            fechaVigencia.getMonth(),
            fechaVigencia.getDate(), 0, 0, 0);
        Date fechaFinVigencia = new Date(
            fechaVigencia.getYear(),
            fechaVigencia.getMonth(),
            fechaVigencia.getDate(), 23, 59, 59);

        List<ListadoValorComercialOroJPA> listaVigentes =
            listadoJpaRepository.obtenerListadoPorFechaVigencia(fechaInicioVigencia, fechaFinVigencia);
        List<HistListadoValorComercialOroJPA> listaHistoricos =
            histListadoJpaRepository.obtenerListadoPorFechaVigencia(fechaInicioVigencia, fechaFinVigencia);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            // TODO Excepción
        }

        List<ListadoValorComercialOro> result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(listaVigentes)) {
            for (ListadoValorComercialOroJPA listadoValorComercialOroJPA : listaVigentes) {
                result.add(convertirAListadoValorComercialOro(listadoValorComercialOroJPA));
            }
        }

        if (!ObjectUtils.isEmpty(listaHistoricos)) {
            for (HistListadoValorComercialOroJPA histListadoValorComercialOroJPA : listaHistoricos) {
                result.add(convertirAListadoValorComercialOro(histListadoValorComercialOroJPA));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarListado(ListadoValorComercialOro listado) {
        LOGGER.info(">> actualizarListado");

        ListadoValorComercialOroJPA listadoValorComercialOroJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoValorComercialOroJPA)) {
            HistListadoValorComercialOroJPA histListadoValorComercialOroJPA =
                new HistListadoValorComercialOroJPA(listadoValorComercialOroJPA.getValoresComerciales());
            histListadoValorComercialOroJPA.setUltimaActualizacion(listadoValorComercialOroJPA.getUltimaActualizacion());
            histListadoJpaRepository.save(histListadoValorComercialOroJPA);

            listadoJpaRepository.delete(listadoValorComercialOroJPA.getId());
        }

        ListadoValorComercialOroJPA listadoNuevo = new ListadoValorComercialOroJPA();
        Set<ValorComercialOroJPA> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (Oro oro : listado.getValoresComerciales()) {
                ValorComercialOroJPA valorComercialOroJPA = new ValorComercialOroJPA();
                valorComercialOroJPA.setColor(oro.getColor());
                valorComercialOroJPA.setCalidad(oro.getCalidad());
                valorComercialOroJPA.setPrecio(oro.obtenerValorGramo());
                valorComercialOroJPA.setListado(listadoNuevo);

                valoresComerciales.add(valorComercialOroJPA);
            }
        }

        listadoNuevo.setValoresComerciales(valoresComerciales);
        listadoNuevo.setUltimaActualizacion(new Date());
        listadoJpaRepository.save(listadoNuevo);
    }

    /**
     * Metodo auxiliar para convertir el listado de valores comerciales del oro JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoValorComercialOro convertirAListadoValorComercialOro(ListadoValorComercialOroJPA listado) {
        Set<Oro> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (ValorComercialOroJPA vco : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    OroFactory.create(vco.getId(), vco.getColor(), vco.getCalidad(), vco.getPrecio()));
            }
        }

        return ListadoValorComercialOroFactory.create(
            listado.getId(), listado.getUltimaActualizacion(), valoresComerciales);
    }

    /**
     * Metodo auxiliar para convertir el histórico de listado de valores comerciales del oro JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoValorComercialOro convertirAListadoValorComercialOro(HistListadoValorComercialOroJPA listado) {
        Set<Oro> valoresComerciales = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            for (HistValorComercialOroJPA vco : listado.getValoresComerciales()) {
                valoresComerciales.add(
                    OroFactory.create(vco.getId(), vco.getColor(), vco.getCalidad(), vco.getPrecio()));
            }
        }

        return ListadoValorComercialOroFactory.create(
            listado.getId(), listado.getUltimaActualizacion(), valoresComerciales);
    }

}
