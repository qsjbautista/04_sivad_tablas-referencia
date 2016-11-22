package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Objects;

/**
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public class ModificadorValorDiamante {
    /**
     * Identificador de la entidad.
     */
    private Long id;

    /**
     * Factores de valor de diamantes.
     */
    private FactorValorDiamante factor;

    /**
     * Fecha en la que se almacena la entidad.
     */
    private DateTime fechaCarga;

    /**
     * Fecha de vigencia de la lista de factores.
     */
    private LocalDate fechaListado;

    /**
     * Referencia al repositorio de entidades.
     */
    private ModificadorValorDiamanteRepository repositorio;

    /**
     * Interface que define el contrato para crear entidades {@link ModificadorValorDiamante}
     */
    public interface Builder {
        /**
         * Recupera el identificador de la entidad.
         *
         * @return Identificador de la entidad.
         */
        Long getId();

        /**
         * Recupera el valor de {@link FactorValorDiamante}
         *
         * @return {@link FactorValorDiamante}
         */
        FactorValorDiamante getFactor();

        /**
         * Recupera la fecha en la que se almacena la entidad.
         *
         * @return Fecha en la que se almacena la entidad.
         */
        DateTime getFechaCarga();

        /**
         * Recupera la fecha de vigencia de la lista de factores.
         *
         * @return Fecha de vigencia de la lista de factores.
         */
        LocalDate getFechaListado();
    }

    /**
     * Constructor. Privado para que solo la fabrica tenga acceso a crear objetos.
     *
     * @param builder Referencia al objeto que contiene los datos necesarios para construir la entidad.
     * @param repositorio Referencia al repositorio de entidades.
     */
    private ModificadorValorDiamante(Builder builder, ModificadorValorDiamanteRepository repositorio) {
        super();

        id = builder.getId();
        factor = builder.getFactor();
        fechaCarga = builder.getFechaCarga();
        fechaListado = builder.getFechaListado();

        this.repositorio = repositorio;
    }

    /**
     * Permite actualizar el listado de factores de valor de diamante con esta entidad.
     */
    public ModificadorValorDiamante actualizar() {
         return repositorio.actualizar(this);
    }

    /**
     * Regresa los factores de valor de diamantes.
     *
     * @return Factores de valor de diamantes.
     */
    public FactorValorDiamante getFactor() {
        return factor;
    }

    /**
     * Regresa la fecha en la que se almacena la entidad.
     *
     * @return Fecha en la que se almacena la entidad.
     */
    public DateTime getFechaCarga() {
        return fechaCarga;
    }

    /**
     * Regresa la fecha de vigencia de la lista de factores.
     *
     * @return Fecha de vigencia de la lista de factores.
     */
    public LocalDate getFechaListado() {
        return fechaListado;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModificadorValorDiamante that = (ModificadorValorDiamante) o;

        return Objects.equals(id, that.id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
