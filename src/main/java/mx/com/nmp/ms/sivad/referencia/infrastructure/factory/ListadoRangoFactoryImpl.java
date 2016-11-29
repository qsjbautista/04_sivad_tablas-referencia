/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.factory;

import mx.com.nmp.ms.arquetipo.journal.util.ApplicationContextProvider;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoRangoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhaja;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoRango;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

import static mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ConstructorUtil.getConstructor;
import static mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ConstructorUtil.getInstancia;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoRango.
 *
 * @author mmarquez
 */
@Component
public class ListadoRangoFactoryImpl implements ListadoRangoFactory {

    /**
     * Referencias al constructor de la entidad.
     */
    private final Constructor<ListadoRango> constructor;

    /**
     * Referencia al repositorio de entidades.
     */
    private ModificadorRangoRepository repositorio;

    public ListadoRangoFactoryImpl() {
        super();

        constructor = getConstructor(ListadoRango.class, ListadoRango.Builder.class,
            ModificadorRangoRepository.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoRango crear(final DateTime fechaCarga, final LocalDate fechaListado, Set<FactorAlhaja> factorAlhajas) {
        final ListadoRango.Builder builder = getBuilder(fechaCarga, fechaListado, factorAlhajas);

        return crearDesde(builder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoRango crearPersistible(final DateTime fechaCarga, final LocalDate fechaListado, Set<FactorAlhaja> factorAlhajas) {
        final ListadoRango.Builder builder = getBuilder(fechaCarga, fechaListado, factorAlhajas);

        return crearPersistibleDesde(builder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoRango crearPersistibleDesde(final ListadoRango.Builder builder) {
        return crear(builder, getRepositorio());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListadoRango crearDesde(final ListadoRango.Builder builder) {
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
    private ListadoRango crear(final ListadoRango.Builder builder,
                               ModificadorRangoRepository repositorio) {

        return getInstancia(constructor, builder, repositorio);
    }

    /**
     * Recupera el repositorio de la entidad.
     *
     * @return Repositorio de la entidad.
     */
    private ModificadorRangoRepository getRepositorio() {
        if (ObjectUtils.isEmpty(repositorio)) {
            repositorio = ApplicationContextProvider.get().getBean(ModificadorRangoRepository.class);
        }

        return repositorio;
    }

    /**
     * Crea un objeto constructor a partir del valor de los argumentos.
     *
     * @param fechaCarga Fecha de vigencia de la lista de factores.
     * @param fechaListado Fecha de origen de la información.
     * @param factorAlhajas Lista de modificadores de factores Alhajas.
     *
     * @return Objeto constructor creado.
     */
    private static ListadoRango.Builder getBuilder(final DateTime fechaCarga, final LocalDate fechaListado,
                                                                    final Set<FactorAlhaja> factorAlhajas) {
        return new ListadoRango.Builder() {
            @Override
            public Set<FactorAlhaja> getFactorAlhaja() { return factorAlhajas; }

            @Override
            public DateTime getFechaCarga() {
                return fechaCarga;
            }

            @Override
            public LocalDate getFechaListado() {
                return fechaListado;
            }

        };
    }

}
