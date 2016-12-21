/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.conector.Convertidor;
import mx.com.nmp.ms.sivad.referencia.conector.TipoCambioEnum;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.DiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.ValorComercialDiamanteUtil;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementación de ValorComercialDiamanteRepository.
 *
 * @author ngonzalez
 */
@Component
public class ValorComercialDiamanteRepositoryImpl implements ValorComercialDiamanteRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialDiamanteRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoValorComercialDiamanteJPARepository.
     */
    @Inject
    private HistListadoValorComercialDiamanteJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoValorComercialDiamanteJPARepository.
     */
    @Inject
    private ListadoValorComercialDiamanteJPARepository listadoJpaRepository;

    /**
     * Referencia al repositorio de ValorComercialDiamanteJPARepository.
     */
    @Inject
    private ValorComercialDiamanteJPARepository valorComercialDiamanteJPARepository;

    /**
     * Referencia al conector con microservicio de tipo cambiario.
     */
    @Inject
    private Convertidor convertidor;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Diamante obtenerValorComercial(@NotNull DiamanteVO diamanteVO) {
        LOGGER.info(">> obtenerValorComercial({})", diamanteVO);
        LOGGER.info(diamanteVO.toString());

        ValorComercialDiamanteJPA valorComercialDiamanteJPA =
            valorComercialDiamanteJPARepository.obtenerValorComercial(diamanteVO.getCorte(), diamanteVO.getColor(),
                diamanteVO.getClaridad(), diamanteVO.getQuilatesCt());

        if (ObjectUtils.isEmpty(valorComercialDiamanteJPA)) {
            String msg = "No existe un valor comercial para las características de diamante solicitadas.";
            LOGGER.warn(msg);
            throw new ValorComercialNoEncontradoException(ValorComercialDiamanteJPA.class, msg);
        }

        BigDecimal precioDiamanteEnPesos = convertidor.convertir(
            TipoCambioEnum.USD.getTipo(), TipoCambioEnum.MXN.getTipo(), valorComercialDiamanteJPA.getPrecio());

        return DiamanteFactory.create(valorComercialDiamanteJPA.getCorte(), valorComercialDiamanteJPA.getColor(),
            valorComercialDiamanteJPA.getClaridad(), valorComercialDiamanteJPA.getTamanioInferior(),
            valorComercialDiamanteJPA.getTamanioSuperior(), precioDiamanteEnPesos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoValorComercialDiamante consultarListadoVigente() {
        LOGGER.info(">> consultarListadoVigente()");

        ListadoValorComercialDiamanteJPA listadoValorComercialDiamanteJPA;

        try {
            listadoValorComercialDiamanteJPA = listadoJpaRepository.obtenerListadoVigente();
        } catch (IncorrectResultSizeDataAccessException e) {
            String msg = "Inconsistencia de datos; existe más de un resultado.";
            LOGGER.error(msg);
            e.printStackTrace();
            throw e;
        }

        if (ObjectUtils.isEmpty(listadoValorComercialDiamanteJPA)) {
            String msg = "No existe un listado de valor comercial diamante vigente.";
            LOGGER.warn(msg);
            throw new ListadoValorComercialNoEncontradoException(msg, ListadoValorComercialDiamanteJPA.class);
        }

        return ValorComercialDiamanteUtil.convertToListadoDeDominio(listadoValorComercialDiamanteJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ListadoValorComercialDiamante> consultarListadoPorFechaVigencia(@NotNull LocalDate fechaVigencia) {
        LOGGER.info(">> consultarListadoPorFechaVigencia({})", fechaVigencia);

        if (DateUtil.isGreaterThanNow(fechaVigencia.toDate())) {
            String msg = "La fecha de vigencia solicitada no puede ser una fecha futura.";
            LOGGER.error(msg);
            throw new FechaVigenciaFuturaException(msg, ListadoValorComercialDiamanteJPA.class);
        }

        DateTime fechaVigenciaInicio = fechaVigencia.toDateTimeAtStartOfDay();
        DateTime fechaVigenciaFin = fechaVigencia.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue();

        Set<ListadoValorComercialDiamanteJPA> listaVigentes =
            listadoJpaRepository.findByFechaCargaBetween(fechaVigenciaInicio, fechaVigenciaFin);
        Set<HistListadoValorComercialDiamanteJPA> listaHistoricos =
            histListadoJpaRepository.findByFechaCargaBetween(fechaVigenciaInicio, fechaVigenciaFin);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            String msg = "Fecha de vigencia solicitada no existe.";
            LOGGER.warn(msg);
            throw new ListadoValorComercialNoEncontradoException(msg, ListadoValorComercialDiamanteJPA.class);
        }

        Set<ListadoValorComercialDiamante> result = new HashSet<>();
        if (!ObjectUtils.isEmpty(listaVigentes)) {
            for (ListadoValorComercialDiamanteJPA listadoValorComercialDiamanteJPA : listaVigentes) {
                result.add(ValorComercialDiamanteUtil.convertToListadoDeDominio(listadoValorComercialDiamanteJPA));
            }
        }

        if (!ObjectUtils.isEmpty(listaHistoricos)) {
            for (HistListadoValorComercialDiamanteJPA histListadoValorComercialDiamanteJPA : listaHistoricos) {
                result.add(ValorComercialDiamanteUtil.convertToListadoDeDominio(histListadoValorComercialDiamanteJPA));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void actualizarListado(@NotNull ListadoValorComercialDiamante listado) {
        LOGGER.info(">> actualizarListado({})", listado);

        ListadoValorComercialDiamanteJPA listadoVigente = listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoVigente)) {

            // SE CONVIERTE EL LISTADO VIGENTE EN HISTÓRICO.
            HistListadoValorComercialDiamanteJPA listadoHistorico =
                ValorComercialDiamanteUtil.convertToListadoHistoricoJPA(listadoVigente);
            histListadoJpaRepository.save(listadoHistorico);

            // SE ELIMINA EL LISTADO VIGENTE.
            listadoJpaRepository.delete(listadoVigente.getId());
        }


        // SE CONVIERTE EL LISTADO DE DOMINIO EN VIGENTE.
        ListadoValorComercialDiamanteJPA listadoNuevo = ValorComercialDiamanteUtil.convertToListadoVigenteJPA(listado);
        Set<ValorComercialDiamanteJPA> listadoVCD = valorComercialDiamanteJPARepository.obtenerValoresComercialesDiamanteBatch();
        listadoNuevo.setValoresComerciales(valorComercialDiamanteJPARepository.obtenerValoresComercialesDiamanteBatch());
        listadoNuevo.setFechaCarga(DateTime.now());
        listadoJpaRepository.save(listadoNuevo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ListadoValorComercialDiamante restaurarListadoAnterior() {
        LOGGER.info(">> restaurarListadoAnterior()");

        HistListadoValorComercialDiamanteJPA listadoAnterior =
            histListadoJpaRepository.findFirstByOrderByFechaCargaDesc();

        if (ObjectUtils.isEmpty(listadoAnterior) ||
            ObjectUtils.isEmpty(listadoAnterior.getValoresComerciales())) {
            String msg = "No existe un listado de precios anterior.";
            LOGGER.error(msg);
            throw new ListadoValorComercialNoEncontradoException(msg, HistListadoValorComercialDiamanteJPA.class);
        }

        ListadoValorComercialDiamanteJPA listadoVigente =
            listadoJpaRepository.obtenerListadoVigente();

        if (ObjectUtils.isEmpty(listadoVigente) ||
            ObjectUtils.isEmpty(listadoVigente.getValoresComerciales())) {
            String msg = "No existe un listado de precios vigente.";
            LOGGER.error(msg);
            throw new ListadoValorComercialNoEncontradoException(msg, ListadoValorComercialDiamanteJPA.class);
        }

        // SE CONVIERTE EL LISTADO VIGENTE EN HISTÓRICO.
        HistListadoValorComercialDiamanteJPA listadoHistorico =
            ValorComercialDiamanteUtil.convertToListadoHistoricoJPA(listadoVigente);
        histListadoJpaRepository.save(listadoHistorico);

        // SE ELIMINA EL LISTADO VIGENTE.
        listadoJpaRepository.delete(listadoVigente.getId());

        // SE CONVIERTE EL LISTADO HISTÓRICO EN VIGENTE.
        ListadoValorComercialDiamanteJPA listadoNuevo =
            ValorComercialDiamanteUtil.convertToListadoVigenteJPA(listadoAnterior);
        listadoNuevo.setFechaCarga(DateTime.now());
        return ValorComercialDiamanteUtil.convertToListadoDeDominio(listadoJpaRepository.save(listadoNuevo));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ListadoValorComercialDiamante restaurarListadoPorFechaVigencia(@NotNull LocalDate fechaVigencia) {
        LOGGER.info(">> restaurarListadoPorFecha({})", fechaVigencia);

        if (DateUtil.isGreaterThanNow(fechaVigencia.toDate())) {
            String msg = "La fecha de vigencia solicitada no puede ser una fecha futura.";
            LOGGER.error(msg);
            throw new FechaVigenciaFuturaException(msg, ListadoValorComercialDiamanteJPA.class);
        }

        DateTime fechaVigenciaInicio = fechaVigencia.toDateTimeAtStartOfDay();
        DateTime fechaVigenciaFin = fechaVigencia.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue();

        HistListadoValorComercialDiamanteJPA listadoFechaVigencia =
            histListadoJpaRepository.findFirstByFechaCargaBetweenOrderByFechaCargaDesc(
                fechaVigenciaInicio, fechaVigenciaFin);

        if (ObjectUtils.isEmpty(listadoFechaVigencia) ||
            ObjectUtils.isEmpty(listadoFechaVigencia.getValoresComerciales())) {
            String msg = "Fecha de vigencia solicitada no existe.";
            LOGGER.error(msg);
            throw new ListadoValorComercialNoEncontradoException(msg, HistListadoValorComercialDiamanteJPA.class);
        }

        ListadoValorComercialDiamanteJPA listadoVigente =
            listadoJpaRepository.obtenerListadoVigente();

        if (ObjectUtils.isEmpty(listadoVigente) ||
            ObjectUtils.isEmpty(listadoVigente.getValoresComerciales())) {
            String msg = "No existe un listado de precios vigente.";
            LOGGER.error(msg);
            throw new ListadoValorComercialNoEncontradoException(msg, ListadoValorComercialDiamanteJPA.class);
        }

        // SE CONVIERTE EL LISTADO VIGENTE EN HISTÓRICO.
        HistListadoValorComercialDiamanteJPA listadoHistorico =
            ValorComercialDiamanteUtil.convertToListadoHistoricoJPA(listadoVigente);
        histListadoJpaRepository.save(listadoHistorico);

        // SE ELIMINA EL LISTADO VIGENTE.
        listadoJpaRepository.delete(listadoVigente.getId());

        // SE CONVIERTE EL LISTADO HISTÓRICO (DE LA FECHA DE VIGENCIA INDICADA) EN VIGENTE.
        ListadoValorComercialDiamanteJPA listadoNuevo =
            ValorComercialDiamanteUtil.convertToListadoVigenteJPA(listadoFechaVigencia);
        listadoNuevo.setFechaCarga(DateTime.now());

        return ValorComercialDiamanteUtil.convertToListadoDeDominio(listadoJpaRepository.save(listadoNuevo));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void actualizarListadoSinHist(@NotNull ListadoValorComercialDiamante listado) {
        LOGGER.info(">> actualizarListadoSinHist({})", listado);

        // SE CONVIERTE EL LISTADO DE DOMINIO EN VIGENTE.
        ListadoValorComercialDiamanteJPA listadoNuevo = ValorComercialDiamanteUtil.convertToListadoVigenteJPA(listado);
        valorComercialDiamanteJPARepository.save(listadoNuevo.getValoresComerciales());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void rollBackBatch() {
        Set<ValorComercialDiamanteJPA> listadoVCD = valorComercialDiamanteJPARepository
            .obtenerValoresComercialesDiamanteBatch();
        valorComercialDiamanteJPARepository.delete(listadoVCD);
    }

}
