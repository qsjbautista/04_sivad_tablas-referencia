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
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.MetalFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.MetalVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialMetalJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.ValorComercialMetalUtil;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementación de ValorComercialMetalRepository.
 *
 * @author ngonzalez
 */
@Component
public class ValorComercialMetalRepositoryImpl implements ValorComercialMetalRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValorComercialMetalRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoValorComercialMetalJPARepository.
     */
    @Inject
    private HistListadoValorComercialMetalJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoValorComercialMetalJPARepository.
     */
    @Inject
    private ListadoValorComercialMetalJPARepository listadoJpaRepository;

    /**
     * Referencia al repositorio de ValorComercialMetalJPARepository.
     */
    @Inject
    private ValorComercialMetalJPARepository valorComercialMetalJPARepository;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Metal consultarMetalVigente(@NotNull MetalVO metalVO) {
        LOGGER.info(">> consultarMetalVigente({})", metalVO);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("'metal: [{}]', 'calidad: [{}]'", metalVO.getMetal(), metalVO.getCalidad());
        }

        ValorComercialMetalJPA valorComercialMetalJPA =
            valorComercialMetalJPARepository.findByMetalAndCalidad(metalVO.getMetal(), metalVO.getCalidad());

        if (ObjectUtils.isEmpty(valorComercialMetalJPA)) {
            String msg = "No existe un valor gramo para las características de metal solicitadas.";
            LOGGER.warn(msg);
            throw new ValorGramoNoEncontradoException(msg, ValorComercialMetalJPA.class);
        }

        return MetalFactory.create(valorComercialMetalJPA.getMetal(),
            valorComercialMetalJPA.getCalidad(), valorComercialMetalJPA.getPrecio());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoValorComercialMetal consultarListadoVigente() {
        LOGGER.info(">> consultarListadoVigente()");

        ListadoValorComercialMetalJPA listadoValorComercialMetalJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (ObjectUtils.isEmpty(listadoValorComercialMetalJPA)) {
            String msg = "No existe un listado de valor gramo metal vigente.";
            LOGGER.warn(msg);
            throw new ListadoValorGramoNoEncontradoException(msg, ListadoValorComercialMetalJPA.class);
        }

        return ValorComercialMetalUtil.convertToListadoDeDominio(listadoValorComercialMetalJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ListadoValorComercialMetal> consultarListadoPorFechaVigencia(@NotNull LocalDate fechaVigencia) {
        LOGGER.info(">> consultarListadoPorFechaVigencia({})", fechaVigencia);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("'fechaVigencia: [{}]'", fechaVigencia.toString());
        }

        if (DateUtil.isGreaterThanNow(fechaVigencia.toDate())) {
            String msg = "La fecha de vigencia solicitada no puede ser una fecha futura.";
            LOGGER.error(msg);
            throw new FechaVigenciaFuturaException(msg, ListadoValorComercialMetalJPA.class);
        }

        Date fechaVigenciaInicio = DateUtil.getStartOfDay(fechaVigencia.toDate());
        Date fechaVigenciaFin = DateUtil.getEndOfDay(fechaVigencia.toDate());

        Set<ListadoValorComercialMetalJPA> listaVigentes =
            listadoJpaRepository.obtenerListadosPorFechaVigencia(fechaVigenciaInicio, fechaVigenciaFin);
        Set<HistListadoValorComercialMetalJPA> listaHistoricos =
            histListadoJpaRepository.obtenerListadosPorFechaVigencia(fechaVigenciaInicio, fechaVigenciaFin);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            String msg = "Fecha de vigencia solicitada no existe.";
            LOGGER.warn(msg);
            throw new ListadoValorGramoNoEncontradoException(msg, ListadoValorComercialMetalJPA.class);
        }

        Set<ListadoValorComercialMetal> result = new HashSet<>();
        if (!ObjectUtils.isEmpty(listaVigentes)) {
            for (ListadoValorComercialMetalJPA listadoValorComercialMetalJPA : listaVigentes) {
                result.add(ValorComercialMetalUtil.convertToListadoDeDominio(listadoValorComercialMetalJPA));
            }
        }

        if (!ObjectUtils.isEmpty(listaHistoricos)) {
            for (HistListadoValorComercialMetalJPA histListadoValorComercialMetalJPA : listaHistoricos) {
                result.add(ValorComercialMetalUtil.convertToListadoDeDominio(histListadoValorComercialMetalJPA));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void actualizarListado(@NotNull ListadoValorComercialMetal listado) {
        LOGGER.info(">> actualizarListado({})", listado);

        if (ObjectUtils.isEmpty(listado.getValoresComerciales())) {
            String msg = "El listado con el cual se desea reemplazar la lista vigente no tiene elementos.";
            LOGGER.error(msg);
            throw new ListadoValorGramoSinElementosException(msg, ListadoValorComercialMetalJPA.class);
        }

        ListadoValorComercialMetalJPA listadoVigente = listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoVigente)) {

            // SE CONVIERTE EL LISTADO VIGENTE EN HISTÓRICO.
            HistListadoValorComercialMetalJPA listadoHistorico =
                ValorComercialMetalUtil.convertToListadoHistoricoJPA(listadoVigente);
            histListadoJpaRepository.save(listadoHistorico);

            // SE ELIMINA EL LISTADO VIGENTE.
            listadoJpaRepository.delete(listadoVigente.getId());
        }

        // SE CONVIERTE EL LISTADO DE DOMINIO EN VIGENTE.
        ListadoValorComercialMetalJPA listadoNuevo = ValorComercialMetalUtil.convertToListadoVigenteJPA(listado);
        listadoNuevo.setUltimaActualizacion(new Date());
        listadoJpaRepository.save(listadoNuevo);
    }

}