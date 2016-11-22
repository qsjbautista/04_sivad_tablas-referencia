package mx.com.nmp.ms.sivad.referencia.infrastructure.factory;

import mx.com.nmp.ms.arquetipo.journal.util.ApplicationContextProvider;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Constructor;

import static mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ConstructorUtil.getConstructor;
import static mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ConstructorUtil.getInstancia;

/**
 * Fabrica para crear entidades tipo {@link ModificadorValorDiamante}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
@Component
public class ModificadorValorDiamanteFactoryImpl implements ModificadorValorDiamanteFactory {
    /**
     * Referencias al constructor de la entidad.
     */
    private final Constructor<ModificadorValorDiamante> constructor;

    /**
     * Referencia al repositorio de entidades.
     */
    private ModificadorValorDiamanteRepository repositorio;

    /**
     * Constructor.
     */
    public ModificadorValorDiamanteFactoryImpl() {
        super();

        constructor = getConstructor(ModificadorValorDiamante.class, ModificadorValorDiamante.Builder.class,
            ModificadorValorDiamanteRepository.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModificadorValorDiamante createWith(final DateTime fechaCarga, final LocalDate fechaListado,
            final FactorValorDiamante factor) {
        final ModificadorValorDiamante.Builder builder = getBuilder(fechaCarga, fechaListado, factor);

        return create(builder, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModificadorValorDiamante createPersistentWith(final DateTime fechaCarga, final LocalDate fechaListado,
            final FactorValorDiamante factor) {
        final ModificadorValorDiamante.Builder builder = getBuilder(fechaCarga, fechaListado, factor);

        return create(builder, getRepositorio());
    }

    @Override
    public ModificadorValorDiamante createFrom(final ModificadorValorDiamante.Builder builder) {
        return create(builder, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModificadorValorDiamante createPersistentFrom(final ModificadorValorDiamante.Builder builder) {
        return create(builder, getRepositorio());
    }

    /**
     * Se encarga de crear una entidad tipo {@link ModificadorValorDiamante}
     *
     * @param builder Referencia al objeto constructor.
     * @param repositorio Referencia al repositorio de la entidad.
     *
     * @return Entidad creada.
     */
    private ModificadorValorDiamante create(final ModificadorValorDiamante.Builder builder,
            ModificadorValorDiamanteRepository repositorio) {
        validarBuilder(builder);

        return getInstancia(constructor, builder, repositorio);
    }

    /**
     * Recupera el repositorio de la entidad.
     *
     * @return Repositorio de la entidad.
     */
    private ModificadorValorDiamanteRepository getRepositorio() {
        if (ObjectUtils.isEmpty(repositorio)) {
            repositorio = ApplicationContextProvider.get().getBean(ModificadorValorDiamanteRepository.class);
        }

        return repositorio;
    }

    /**
     * Crea un objeto constructor a partir del valor de los argumentos.
     *
     * @param fechaCarga Fecha en la que se almacena la entidad.
     * @param fechaListado Fecha de vigencia de la lista de factores.
     * @param factor Factores de valor de diamantes.
     *
     * @return Objeto constructor creado.
     */
    private static ModificadorValorDiamante.Builder getBuilder(final DateTime fechaCarga, final LocalDate fechaListado,
            final FactorValorDiamante factor) {
        return new ModificadorValorDiamante.Builder() {
            @Override
            public Long getId() {
                return null;
            }

            @Override
            public FactorValorDiamante getFactor() {
                return factor;
            }

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

    /**
     * Valida que los datos con los que va a ser creada la entidad sean correctos.
     *
     * @param builder Objeto constructor de la entidad.
     */
    private static void validarBuilder(final ModificadorValorDiamante.Builder builder) {
        Assert.notNull(builder.getFactor(), "Factores no debe ser nulo");
        Assert.notNull(builder.getFechaCarga(), "Fecha carga no debe ser nula");
        Assert.notNull(builder.getFechaListado(), "Fecha listado no debe ser nula");
    }
}
