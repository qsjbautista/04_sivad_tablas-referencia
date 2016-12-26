/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorComercialSinElementosException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorGramoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.OroVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialOroJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.ValorComercialOroUtil;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

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
        LOGGER.info(oroVO.toString());

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

        ListadoValorComercialOroJPA listadoValorComercialOroJPA;

        try {
            listadoValorComercialOroJPA = listadoJpaRepository.obtenerListadoVigente();
        } catch (IncorrectResultSizeDataAccessException e) {
            String msg = "Inconsistencia de datos; existe más de un resultado.";
            LOGGER.error(msg);
            e.printStackTrace();
            throw e;
        }

        if (ObjectUtils.isEmpty(listadoValorComercialOroJPA)) {
            String msg = "No existe un listado de valor gramo oro vigente.";
            LOGGER.warn(msg);
            throw new ListadoValorGramoNoEncontradoException(msg, ListadoValorComercialOroJPA.class);
        }

        return ValorComercialOroUtil.convertToListadoDeDominio(listadoValorComercialOroJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ListadoValorComercialOro> consultarListadoPorFechaVigencia(@NotNull LocalDate fechaVigencia) {
        LOGGER.info(">> consultarListadoPorFechaVigencia({})", fechaVigencia);

        if (DateUtil.isGreaterThanNow(fechaVigencia.toDate())) {
            String msg = "La fecha de vigencia solicitada no puede ser una fecha futura.";
            LOGGER.error(msg);
            throw new FechaVigenciaFuturaException(msg, ListadoValorComercialOroJPA.class);
        }

        DateTime fechaVigenciaInicio = fechaVigencia.toDateTimeAtStartOfDay();
        DateTime fechaVigenciaFin = fechaVigencia.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue();

        Set<ListadoValorComercialOroJPA> listaVigentes =
            listadoJpaRepository.findByUltimaActualizacionBetween(fechaVigenciaInicio, fechaVigenciaFin);
        Set<HistListadoValorComercialOroJPA> listaHistoricos =
            histListadoJpaRepository.findByUltimaActualizacionBetween(fechaVigenciaInicio, fechaVigenciaFin);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            String msg = "Fecha de vigencia solicitada no existe.";
            LOGGER.warn(msg);
            throw new ListadoValorGramoNoEncontradoException(msg, ListadoValorComercialOroJPA.class);
        }

        Set<ListadoValorComercialOro> result = new HashSet<>();
        if (!ObjectUtils.isEmpty(listaVigentes)) {
            for (ListadoValorComercialOroJPA listadoValorComercialOroJPA : listaVigentes) {
                result.add(ValorComercialOroUtil.convertToListadoDeDominio(listadoValorComercialOroJPA));
            }
        }

        if (!ObjectUtils.isEmpty(listaHistoricos)) {
            for (HistListadoValorComercialOroJPA histListadoValorComercialOroJPA : listaHistoricos) {
                result.add(ValorComercialOroUtil.convertToListadoDeDominio(histListadoValorComercialOroJPA));
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

        if (ObjectUtils.isEmpty(listado) ||
            ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            String msg = "El nuevo listado no contiene elementos.";
            LOGGER.error(msg);
            throw new ListadoValorComercialSinElementosException(msg, ListadoValorComercialOroJPA.class);
        }

        ListadoValorComercialOroJPA listadoVigente = listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoVigente)) {

            // SE CONVIERTE EL LISTADO VIGENTE EN HISTÓRICO.
            HistListadoValorComercialOroJPA listadoHistorico =
                ValorComercialOroUtil.convertToListadoHistoricoJPA(listadoVigente);
            histListadoJpaRepository.save(listadoHistorico);

            // SE ELIMINA EL LISTADO VIGENTE.
            listadoJpaRepository.delete(listadoVigente.getId());
        }

        // SE CONVIERTE EL LISTADO DE DOMINIO EN VIGENTE.
        ListadoValorComercialOroJPA listadoNuevo = ValorComercialOroUtil.convertToListadoVigenteJPA(listado);
        listadoNuevo.setUltimaActualizacion(DateTime.now());
        listadoJpaRepository.save(listadoNuevo);
    }

}
