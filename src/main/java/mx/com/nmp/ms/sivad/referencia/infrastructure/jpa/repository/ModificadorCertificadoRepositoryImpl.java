/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.*;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.*;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * Implementación de ModificadorCertificadoRepository.
 *
 * @author mmarquez
 */
@Repository
public class ModificadorCertificadoRepositoryImpl implements ModificadorCertificadoRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModificadorCertificadoRepositoryImpl.class);

    /**
     * Referencia al repositorio de HistListadoModificadorCertificadoJPARepository.
     */
    //@Inject
    private HistListadoModificadorCertificadoJPARepository histListadoJpaRepository;

    /**
     * Referencia al repositorio de ListadoModificadorCertificadoRepositoryJPA.
     */
    //@Inject
    private ListadoModificadorCertificadoRepositoryJPA listadoJpaRepository;

    /**
     * Referencia al repositorio de ModificadorCertificadoRepositoryJPA.
     */
    //@Inject
    private ModificadorCertificadoRepositoryJPA modificadorCertificadoRepositoryJPA;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Certificado consultarModificadorCertificadoVigente(@NotNull Certificado certificado) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarModificadorCertificadoVigente [certificado: [{}]]", certificado.getCertificado());
        }

        ModificadorCertificadoJPA modificadorCertificadoJPA =
            modificadorCertificadoRepositoryJPA.findByCertificado(certificado.getCertificado());

        if (ObjectUtils.isEmpty(modificadorCertificadoJPA)) {
            // TODO Excepción
        }

        return CertificadoFactory.create(modificadorCertificadoJPA.getId(), modificadorCertificadoJPA.getCertificado(),
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
            // TODO Excepción
        }

        return convertirAListadoModificadorCertificado(listadoJPA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ListadoModificadorCertificado> consultarListadoPorFechaCarga(@NotNull Date fechaCarga) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(">> consultarListadoPorFechaCarga: [{}]",
                (fechaCarga != null) ? fechaCarga.toString() : "NULL");
        }

        Date fechaCargaInicio = DateUtil.getStartOfDay(fechaCarga);
        Date fechaCargaFin = DateUtil.getEndOfDay(fechaCarga);

        List<ListadoModificadorCertificadoJPA> listaVigentes =
            listadoJpaRepository.obtenerListadoPorFechaCarga(fechaCargaInicio, fechaCargaFin);
        List<HistListadoModificadorCertificadoJPA> listaHistoricos =
            histListadoJpaRepository.obtenerListadoPorFechaCarga(fechaCargaInicio, fechaCargaFin);

        if (ObjectUtils.isEmpty(listaVigentes) && ObjectUtils.isEmpty(listaHistoricos)) {
            // TODO Excepción
        }

        List<ListadoModificadorCertificado> result = new ArrayList<>();
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
    public void actualizarListado(ListadoModificadorCertificado listado) {
        LOGGER.info(">> actualizarListado");

        ListadoModificadorCertificadoJPA listadoModificadorCertificadoJPA =
            listadoJpaRepository.obtenerListadoVigente();

        if (!ObjectUtils.isEmpty(listadoModificadorCertificadoJPA)) {
            HistListadoModificadorCertificadoJPA histlistado =
                new HistListadoModificadorCertificadoJPA(listadoModificadorCertificadoJPA.getModificadoresCertificado());
            histListadoJpaRepository.save(histlistado);

            listadoJpaRepository.delete(listadoModificadorCertificadoJPA.getId());
        }

        ListadoModificadorCertificadoJPA listadoNuevo = new ListadoModificadorCertificadoJPA();
        Set<ModificadorCertificadoJPA> certificados = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getCertificados())) {
            for (Certificado certificado : listado.getCertificados()) {
                ModificadorCertificadoJPA modificadorCertificadoJPA = new ModificadorCertificadoJPA();
                modificadorCertificadoJPA.setCertificado(certificado.getCertificado());
                modificadorCertificadoJPA.setFactor(certificado.getFactor());
                modificadorCertificadoJPA.setListado(listadoNuevo);

                certificados.add(modificadorCertificadoJPA);
            }
        }

        listadoNuevo.setModificadoresCertificado(certificados);
        listadoNuevo.setFechaCarga(new Date());
        listadoJpaRepository.save(listadoNuevo);
    }

    /**
     * Metodo auxiliar para convertir el listado de modificadores certificados JPA en entidad listado de dominio.
     *
     * @param listado El listado a convertir.
     * @return El listado convertido en entidad de dominio.
     */
    private ListadoModificadorCertificado convertirAListadoModificadorCertificado(AbstractListadoModificadorCertificadoJPA listado) {
        Set<Certificado> certificados = new HashSet<>();
        if (!ObjectUtils.isEmpty(listado.getModificadoresCertificado())) {
            for (ModificadorCertificadoJPA certificado : listado.getModificadoresCertificado()) {
                certificados.add(
                    CertificadoFactory.create(certificado.getId(), certificado.getCertificado(), certificado.getFactor()));
            }
        }

        return ListadoModificadorCertificadoFactory.create(
            listado.getId(), listado.getFechaCarga(), listado.getFechaListado(), certificados);
    }


}
