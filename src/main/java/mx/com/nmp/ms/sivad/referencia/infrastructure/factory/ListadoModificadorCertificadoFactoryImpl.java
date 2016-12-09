/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.factory;

import mx.com.nmp.ms.arquetipo.journal.util.ApplicationContextProvider;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoModificadorCertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoModificadorCertificado;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

import static mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ConstructorUtil.getConstructor;
import static mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ConstructorUtil.getInstancia;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoModificadorCertificado.
 *
 * @author mmarquez
 */
@Component
public class ListadoModificadorCertificadoFactoryImpl implements ListadoModificadorCertificadoFactory {

    /**
     * Referencias al constructor de la entidad.
     */
    private final Constructor<ListadoModificadorCertificado> constructor;

    /**
     * Referencia al repositorio de entidades.
     */
    private ModificadorCertificadoRepository repositorio;

    public ListadoModificadorCertificadoFactoryImpl() {
        super();
        constructor = getConstructor(ListadoModificadorCertificado.class, ListadoModificadorCertificado.Builder.class,
            ModificadorCertificadoRepository.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoModificadorCertificado crear(final DateTime ultimaActualizacion, final LocalDate fechaListado, final Set<Certificado> certificados) {
        final ListadoModificadorCertificado.Builder builder = getBuilder(ultimaActualizacion, fechaListado, certificados);

        return crearDesde(builder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoModificadorCertificado crearPersistible(final DateTime ultimaActualizacion, final LocalDate fechaListado, final Set<Certificado> certificados) {
        final ListadoModificadorCertificado.Builder builder = getBuilder(ultimaActualizacion, fechaListado, certificados);

        return crearPersistibleDesde(builder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoModificadorCertificado crear(final LocalDate fechaListado, final Set<Certificado> certificados) {
        final ListadoModificadorCertificado.Builder builder = getBuilder(null, fechaListado, certificados);

        return crearDesde(builder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoModificadorCertificado crearPersistible(final LocalDate fechaListado, final Set<Certificado> certificados) {
        final ListadoModificadorCertificado.Builder builder = getBuilder(null, fechaListado, certificados);

        return crearPersistibleDesde(builder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoModificadorCertificado crearPersistibleDesde(final ListadoModificadorCertificado.Builder builder) {
        return crear(builder, getRepositorio());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoModificadorCertificado crearDesde(final ListadoModificadorCertificado.Builder builder) {
        return crear(builder, null);
    }

    /**
     * Se encarga de crear una entidad tipo ListadoModificadorCertificado
     *
     * @param builder Referencia al objeto constructor.
     * @param repositorio Referencia al repositorio de la entidad.
     *
     * @return Entidad creada.
     */
    private ListadoModificadorCertificado crear(final ListadoModificadorCertificado.Builder builder,
                                                ModificadorCertificadoRepository repositorio) {

        validarBuilder(builder);
        return getInstancia(constructor, builder, repositorio);
    }

    /**
     * Recupera el repositorio de la entidad.
     *
     * @return Repositorio de la entidad.
     */
    private ModificadorCertificadoRepository getRepositorio() {
        if (ObjectUtils.isEmpty(repositorio)) {
            repositorio = ApplicationContextProvider.get().getBean(ModificadorCertificadoRepository.class);
        }

        return repositorio;
    }

    /**
     * Crea un objeto constructor a partir del valor de los argumentos.
     *
     * @param ultimaActualizacion Fecha de vigencia de la lista de factores.
     * @param fechaListado Fecha de origen de la información.
     * @param certificados Lista de modificadores de certificados.
     *
     * @return Objeto constructor creado.
     */
    private static ListadoModificadorCertificado.Builder getBuilder(final DateTime ultimaActualizacion, final LocalDate fechaListado,
                                                               final Set<Certificado> certificados) {
        return new ListadoModificadorCertificado.Builder() {
            @Override
            public Set<Certificado> getCertificados() {
                return certificados;
            }

            @Override
            public DateTime getUltimaActualizacion() {
                return ultimaActualizacion;
            }

            @Override
            public LocalDate getFechaListado() {
                return fechaListado;
            }
        };
    }

    /**
     * Valida que los datos con los que va a ser creada la entidad sean correctos.
     *
     * @param builder Objeto constructor de la entidad.
     *
     */
    private static void validarBuilder(final ListadoModificadorCertificado.Builder builder) {
        Assert.notNull(builder, "El objeto constructor no debe ser nulo.");

        Assert.notNull(builder.getCertificados(), "Certificados no debe ser nulo");
        Assert.notNull(builder.getUltimaActualizacion(), "Fecha carga no debe ser nula");
    }

}
