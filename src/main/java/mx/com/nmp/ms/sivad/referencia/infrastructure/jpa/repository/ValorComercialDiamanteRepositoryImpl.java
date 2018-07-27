/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FechaVigenciaFuturaException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.DiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoCorteDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DiamanteJdbcBulkInsert;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.ValorComercialDiamanteUtil;
import org.hibernate.exception.GenericJDBCException;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementación de ValorComercialDiamanteRepository.
 *
 * @author ngonzalez, ecancino
 */
@Component
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ValorComercialDiamanteRepositoryImpl implements ValorComercialDiamanteRepository {

    private static final String TMP_TABLA = "tmp_diamante_valor_comercial";
    private static final String[] PROP = new  String[]{"corte", "color", "claridad",
        "tamanioInferior", "tamanioSuperior", "precio", "tipoCambio", "montoVbd", "montofCastigoxRango"};

    private static final String CLEAR_QUERY = "TRUNCATE tmp_diamante_valor_comercial";

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
     * Referencia al repositorio de CastigoCorteDiamanteJPARepository.
     */
    @Inject
    private CastigoCorteDiamanteJPARepository castigoCorteDiamanteJPARepository;


    /**
     * Referencia a la plantilla de JDBC
     */
    @Inject
    private JdbcTemplate jdbcTemplate;

    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Diamante obtenerValorComercial(@NotNull DiamanteVO diamanteVO) {
        LOGGER.info(">> obtenerValorComercial({})", diamanteVO);

        //SE EJECUTA EL QUERY
        ValorComercialDiamanteJPA valorComercialDiamanteJPA =
            valorComercialDiamanteJPARepository.obtenerValorComercial(diamanteVO.getColor(),
                diamanteVO.getClaridad(), diamanteVO.getQuilatesDesde(), diamanteVO.getQuilatesHasta());

        if (ObjectUtils.isEmpty(valorComercialDiamanteJPA)) {
            String msg = "No existe un valor comercial para las características de diamante solicitadas.";
            LOGGER.warn(msg);
            throw new ValorComercialNoEncontradoException(ValorComercialDiamanteJPA.class, msg);
        }

        //SE OBTIENE EL PORCENTAJE DE CASTIGO POR TIPO DE CORTE
        CastigoCorteDiamanteJPA castigoCorteDiamaneJPA =
            castigoCorteDiamanteJPARepository.obtenerCastigoCorte(diamanteVO.getCorte());

        //SE MULTIPLICA EL MONTOFCASTIGOXRANGO POR EL PORCENTAJE DE CASTIGO POR TIPO DE CORTE
        BigDecimal precioDiamante = valorComercialDiamanteJPA.getMontofCastigoxRango().multiply(castigoCorteDiamaneJPA.getFactor());

        return DiamanteFactory.create(valorComercialDiamanteJPA.getCorte(), valorComercialDiamanteJPA.getColor(),
            valorComercialDiamanteJPA.getClaridad(), valorComercialDiamanteJPA.getTamanioInferior(),
            valorComercialDiamanteJPA.getTamanioSuperior(), precioDiamante, valorComercialDiamanteJPA.getTipoCambio(),
            valorComercialDiamanteJPA.getMontoVbd(), valorComercialDiamanteJPA.getMontofCastigoxRango());
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
    @CacheEvict(cacheNames = "ValorComercialDiamanteJPARepository.obtenerValorComercial", allEntries = true)
    public void actualizarListado(@NotNull ListadoValorComercialDiamante listado) {
        LOGGER.info(">> actualizarListado({})", listado);
        // Invocación al stored procedure sp_diamante_valor_comercial_generar_vigente que será el encargado de
        // generar el histórico y la nueva lista vigente.
        listadoJpaRepository.generarVigente(listado.getFechaListado().toDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "ValorComercialDiamanteJPARepository.obtenerValorComercial", allEntries = true)
    public void restaurarListadoAnterior() {
        LOGGER.info(">> restaurarListadoAnterior()");

        try {
            histListadoJpaRepository.restaurarAnterior();
            LOGGER.info("<< restaurarListadoAnterior()");
        } catch (JpaSystemException e) {
            manejarJPASPExcepcion(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "ValorComercialDiamanteJPARepository.obtenerValorComercial", allEntries = true)
    public void restaurarListadoPorFechaVigencia(@NotNull LocalDate fechaVigencia) {
        LOGGER.info(">> restaurarListadoPorFecha({})", fechaVigencia);

        if (DateUtil.isGreaterThanNow(fechaVigencia.toDate())) {
            String msg = "La fecha de vigencia solicitada no puede ser una fecha futura.";
            LOGGER.error(msg);
            throw new FechaVigenciaFuturaException(msg, ListadoValorComercialDiamanteJPA.class);
        }

        DateTime fechaVigenciaInicio = fechaVigencia.toDateTimeAtStartOfDay();
        DateTime fechaVigenciaFin = fechaVigencia.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue();

        try {
            histListadoJpaRepository.restaurarFecha(fechaVigenciaInicio.toDate(), fechaVigenciaFin.toDate());
            LOGGER.info("<< restaurarListadoPorFecha({})", fechaVigencia);
        } catch (JpaSystemException e) {
            manejarJPASPExcepcion(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void procesarLista(@NotNull List<? extends Diamante> lista) {
        LOGGER.info(">> procesarLista({})", lista.size());

        DiamanteJdbcBulkInsert bulkInsertQuery = new DiamanteJdbcBulkInsert();

        bulkInsertQuery.withTableName(TMP_TABLA).usingValues(lista, PROP);
        jdbcTemplate.execute(bulkInsertQuery.generateQuery());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void rollBackBatch() {
        jdbcTemplate.execute(CLEAR_QUERY);
    }

    private static void manejarJPASPExcepcion(JpaSystemException jpaSystemException) {
        if (jpaSystemException.getCause() instanceof GenericJDBCException) {
            GenericJDBCException causa = (GenericJDBCException) jpaSystemException.getCause();
            SQLException ex = causa.getSQLException();

            if (ex.getSQLState().equals("02000") && ex.getErrorCode() == 1329) {
                LOGGER.error(ex.getMessage());
                throw new ListadoValorComercialNoEncontradoException(ex.getMessage(),
                    HistListadoValorComercialDiamanteJPA.class);
            }
        }
    }

}
