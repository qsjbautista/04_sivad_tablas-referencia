/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CertificadoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoModificadorCertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.CertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoModificadorCertificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.CertificadoVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistListadoModificadorCertificadoJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.HistModificadorCertificadoJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoModificadorCertificadoJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ModificadorCertificadoJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import org.joda.time.DateTime;
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
 * Implementación de ModificadorCertificadoRepository.
 *
 * @author mmarquez
 */
@Repository
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ModificadorCertificadoRepositoryImpl implements ModificadorCertificadoRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModificadorCertificadoRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoModificadorCertificadoJPARepository.
     */
    @Inject
    private HistListadoModificadorCertificadoJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoModificadorCertificadoRepositoryJPA.
     */
    @Inject
    private ListadoModificadorCertificadoRepositoryJPA listadoJpaRepository;

    /**
     * Referencia al repositorio de ModificadorCertificadoRepositoryJPA.
     */
    @Inject
    private ModificadorCertificadoRepositoryJPA modificadorCertificadoRepositoryJPA;

    /**
     * Referencia a la fabrica de entidades.
     */
    @Inject
    private ListadoModificadorCertificadoFactory fabricaEntidad;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Certificado consultarModificadorCertificadoVigente(@NotNull CertificadoVO certificado) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarModificadorCertificadoVigente [certificado: [{}]]", certificado.getCertificado());
        }

        ModificadorCertificadoJPA modificadorCertificadoJPA =
            modificadorCertificadoRepositoryJPA.findByCertificado(certificado.getCertificado());

        if (ObjectUtils.isEmpty(modificadorCertificadoJPA)) {
            String msg = "No existe un certificado para las características solicitadas.";
            LOGGER.warn(msg);
            throw new CertificadoNoEncontradoException(msg, ModificadorCertificadoJPA.class);
        }

        return CertificadoFactory.crear(modificadorCertificadoJPA.getCertificado(),
            modificadorCertificadoJPA.getFactor());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoModificadorCertificado consultarListadoVigente() {
        LOGGER.info(">> consultarListadoVigente");

        ListadoModificadorCertificadoJPA listadoJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (ObjectUtils.isEmpty(listadoJPA)) {
            String msg = "No existe un ListadoModificadorCertificado vigente.";
            LOGGER.warn(msg);
            throw new CertificadoNoEncontradoException(msg, ListadoModificadorCertificadoJPA.class);
        }

        return convertirAListadoModificadorCertificado(listadoJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ListadoModificadorCertificado> consultarListadoPorUltimaActualizacion(@NotNull LocalDate ultimaActualizacion) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarListadoPorUltimaActualizacion: [{}]",
                (ultimaActualizacion != null) ? ultimaActualizacion.toString() : "NULL");
        }

        DateTime fechaUltimaActualizacionInicio = ultimaActualizacion.toDateTimeAtStartOfDay();
        DateTime fechaUltimaActualizacionFin = ultimaActualizacion.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue();

        Set<ListadoModificadorCertificadoJPA> listaVigentes =
            listadoJpaRepository.obtenerListadoPorUltimaActualizacion(fechaUltimaActualizacionInicio, fechaUltimaActualizacionFin);
        Set<HistListadoModificadorCertificadoJPA> listaHistoricos =
            histListadoJpaRepository.obtenerListadoPorUltimaActualizacion(fechaUltimaActualizacionInicio, fechaUltimaActualizacionFin);
        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            String msg = "No existe un ListadoModificadorCertificado para la fecha solicitada.";
            LOGGER.warn(msg);
            throw new CertificadoNoEncontradoException(msg, ListadoModificadorCertificadoJPA.class);
        }

        Set<ListadoModificadorCertificado> result = new HashSet<>();
        if (!ObjectUtils.isEmpty(listaVigentes)) {
            for (ListadoModificadorCertificadoJPA listadoModificadorCertificadoJPA : listaVigentes) {
                result.add(convertirAListadoModificadorCertificado(listadoModificadorCertificadoJPA));
            }
        }

        if (!ObjectUtils.isEmpty(listaHistoricos)) {
            for (HistListadoModificadorCertificadoJPA histListadoModificadorCertificadoJPA : listaHistoricos) {
                result.add(convertirAListadoModificadorCertificado(histListadoModificadorCertificadoJPA));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoModificadorCertificado actualizarListado(@NotNull ListadoModificadorCertificado listado) {
        LOGGER.info(">> actualizarListado"+ listado.toString());

        ListadoModificadorCertificadoJPA listadoModificadorCertificadoJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoModificadorCertificadoJPA)) {
            HistListadoModificadorCertificadoJPA histListadoModificadorCertificadoJPA = new HistListadoModificadorCertificadoJPA();
            Set<HistModificadorCertificadoJPA> histModificadoresCertificadoJPA = new HashSet<>();
            if (!ObjectUtils.isEmpty(listadoModificadorCertificadoJPA.getModificadoresCertificado())) {
                for (ModificadorCertificadoJPA modificadorCertificadoJPA : listadoModificadorCertificadoJPA.getModificadoresCertificado()) {
                    HistModificadorCertificadoJPA histModificadorCertificadoJPA = new HistModificadorCertificadoJPA();
                    histModificadorCertificadoJPA.setCertificado(modificadorCertificadoJPA.getCertificado());
                    histModificadorCertificadoJPA.setFactor(modificadorCertificadoJPA.getFactor());
                    //histModificadorCertificadoJPA.setListado(histListadoModificadorCertificadoJPA);

                    histModificadoresCertificadoJPA.add(histModificadorCertificadoJPA);
                }
            }
            histListadoModificadorCertificadoJPA.setModificadoresCertificado(histModificadoresCertificadoJPA);
            histListadoModificadorCertificadoJPA.setUltimaActualizacion(listadoModificadorCertificadoJPA.getUltimaActualizacion());
            histListadoModificadorCertificadoJPA.setFechaListado(listadoModificadorCertificadoJPA.getFechaListado());
            histListadoJpaRepository.save(histListadoModificadorCertificadoJPA);

            listadoJpaRepository.delete(listadoModificadorCertificadoJPA.getId());
        }

        ListadoModificadorCertificadoJPA listadoNuevo = new ListadoModificadorCertificadoJPA();
        Set<ModificadorCertificadoJPA> modificadoresCertificados = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getCertificados())) {
            for (Certificado certificado : listado.getCertificados()) {
                ModificadorCertificadoJPA modificadorCertificadoJPA = new ModificadorCertificadoJPA();
                modificadorCertificadoJPA.setCertificado(certificado.getCertificado());
                modificadorCertificadoJPA.setFactor(certificado.getFactor());
                //modificadorCertificadoJPA.setListado(listadoNuevo);

                modificadoresCertificados.add(modificadorCertificadoJPA);
            }
        }

        listadoNuevo.setModificadoresCertificado(modificadoresCertificados);
        listadoNuevo.setUltimaActualizacion(DateTime.now());
        listadoNuevo.setFechaListado(listado.getFechaListado());
        return convertirAListadoModificadorCertificado(listadoJpaRepository.save(listadoNuevo));
    }

    /**
     * Metodo auxiliar para convertir el listado de modificadores certificados JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoModificadorCertificado convertirAListadoModificadorCertificado(ListadoModificadorCertificadoJPA listado) {
        Set<Certificado> certificados = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getModificadoresCertificado())) {
            for (ModificadorCertificadoJPA certificado : listado.getModificadoresCertificado()) {
                certificados.add(
                    CertificadoFactory.crear(certificado.getCertificado(), certificado.getFactor()));
            }
        }

        return fabricaEntidad.crear(listado.getUltimaActualizacion(), listado.getFechaListado(), certificados);
    }

    /**
     * Metodo auxiliar para convertir el listado de modificadores certificados JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoModificadorCertificado convertirAListadoModificadorCertificado(HistListadoModificadorCertificadoJPA listado) {
        Set<Certificado> certificados = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getModificadoresCertificado())) {
            for (HistModificadorCertificadoJPA certificado : listado.getModificadoresCertificado()) {
                certificados.add(
                    CertificadoFactory.crear(certificado.getCertificado(), certificado.getFactor()));
            }
        }

        return fabricaEntidad.crear(listado.getUltimaActualizacion(), listado.getFechaListado(), certificados);
    }


}
