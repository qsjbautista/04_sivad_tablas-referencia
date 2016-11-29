/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoSinElementosException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.OroVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.ValorComercialUtil;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.*;

/**
 * Implementación de ValorComercialOroRepository.
 *
 * @author ngonzalez
 */
@Component
public class ValorComercialOroRepositoryImpl implements ValorComercialOroRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialOroRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoValorComercialOroJPARepository.
     */
    @Inject
    private HistListadoValorComercialOroJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoValorComercialOroJPARepository.
     */
    @Inject
    private ListadoValorComercialOroJPARepository listadoJpaRepository;

    /**
     * Referencia al repositorio de ValorComercialOroJPARepository.
     */
    @Inject
    private ValorComercialOroJPARepository valorComercialOroJPARepository;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Oro consultarOroVigente(@NotNull OroVO oroVO) {
        LOGGER.info(">> consultarOroVigente({})", oroVO);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("'color: [{}]', 'calidad: [{}]'", oroVO.getColor(), oroVO.getCalidad());
        }

        ValorComercialOroJPA valorComercialOroJPA =
            valorComercialOroJPARepository.findByColorAndCalidad(oroVO.getColor(), oroVO.getCalidad());

        if (ObjectUtils.isEmpty(valorComercialOroJPA)) {
            String msg = "No existe un valor gramo para las características de oro solicitadas.";
            LOGGER.warn(msg);
            throw new ValorGramoNoEncontradoException(msg, ValorComercialOroJPA.class);
        }

        return OroFactory.create(valorComercialOroJPA.getColor(),
            valorComercialOroJPA.getCalidad(), valorComercialOroJPA.getPrecio());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoValorComercialOro consultarListadoVigente() {
        LOGGER.info(">> consultarListadoVigente()");

        ListadoValorComercialOroJPA listadoValorComercialOroJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (ObjectUtils.isEmpty(listadoValorComercialOroJPA)) {
            String msg = "No existe un listado de valor gramo oro vigente.";
            LOGGER.warn(msg);
            throw new ListadoValorGramoNoEncontradoException(msg, ListadoValorComercialOroJPA.class);
        }

        return ValorComercialUtil.convertToListadoValorComercialOro(listadoValorComercialOroJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ListadoValorComercialOro> consultarListadoPorFechaVigencia(@NotNull LocalDate fechaVigencia) {
        LOGGER.info(">> consultarListadoPorFechaVigencia({})", fechaVigencia);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("'fechaVigencia: [{}]'", fechaVigencia.toString());
        }

        if (DateUtil.isGreaterThanNow(fechaVigencia.toDate())) {
            String msg = "La fecha de vigencia solicitada no puede ser una fecha futura.";
            LOGGER.error(msg);
            throw new FechaVigenciaFuturaException(msg, ListadoValorComercialOroJPA.class);
        }

        Date fechaVigenciaInicio = DateUtil.getStartOfDay(fechaVigencia.toDate());
        Date fechaVigenciaFin = DateUtil.getEndOfDay(fechaVigencia.toDate());

        List<ListadoValorComercialOroJPA> listaVigentes =
            listadoJpaRepository.obtenerListadoPorFechaVigencia(fechaVigenciaInicio, fechaVigenciaFin);
        List<HistListadoValorComercialOroJPA> listaHistoricos =
            histListadoJpaRepository.obtenerListadoPorFechaVigencia(fechaVigenciaInicio, fechaVigenciaFin);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            String msg = "Fecha de vigencia solicitada no existe.";
            LOGGER.warn(msg);
            throw new ListadoValorGramoNoEncontradoException(msg, ListadoValorComercialOroJPA.class);
        }

        List<ListadoValorComercialOro> result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(listaVigentes)) {
            for (ListadoValorComercialOroJPA listadoValorComercialOroJPA : listaVigentes) {
                result.add(ValorComercialUtil.convertToListadoValorComercialOro(listadoValorComercialOroJPA));
            }
        }

        if (!ObjectUtils.isEmpty(listaHistoricos)) {
            for (HistListadoValorComercialOroJPA histListadoValorComercialOroJPA : listaHistoricos) {
                result.add(ValorComercialUtil.convertToListadoValorComercialOro(histListadoValorComercialOroJPA));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void actualizarListado(@NotNull ListadoValorComercialOro listado) {
        LOGGER.info(">> actualizarListado({})", listado);

        if (ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            String msg = "El listado con el cual se desea reemplazar la lista vigente no tiene elementos.";
            LOGGER.error(msg);
            throw new ListadoValorGramoSinElementosException(msg, ListadoValorComercialOroJPA.class);
        }

        ListadoValorComercialOroJPA listadoValorComercialOroJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoValorComercialOroJPA)) {
            HistListadoValorComercialOroJPA histListadoValorComercialOroJPA = new HistListadoValorComercialOroJPA();
            Set<HistValorComercialOroJPA> histValoresComerciales = new HashSet<>();
            if (!ObjectUtils.isEmpty(listadoValorComercialOroJPA.getValoresComerciales())) {
                for (ValorComercialOroJPA valorComercialOroJPA : listadoValorComercialOroJPA.getValoresComerciales()) {
                    HistValorComercialOroJPA histValorComercialOroJPA = new HistValorComercialOroJPA();
                    histValorComercialOroJPA.setColor(valorComercialOroJPA.getColor());
                    histValorComercialOroJPA.setCalidad(valorComercialOroJPA.getCalidad());
                    histValorComercialOroJPA.setPrecio(valorComercialOroJPA.getPrecio());
                    histValorComercialOroJPA.setListado(histListadoValorComercialOroJPA);

                    histValoresComerciales.add(histValorComercialOroJPA);
                }
            }

            histListadoValorComercialOroJPA.setValoresComerciales(histValoresComerciales);
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

}
