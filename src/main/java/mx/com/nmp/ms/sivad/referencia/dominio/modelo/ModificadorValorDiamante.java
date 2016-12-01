package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Objects;

/**
 * Entidad de dominio (DDD) usada para administra la lista de factores aplicables sobre el valor del Diamante.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public class ModificadorValorDiamante {

    /**
     * Factores de valor de diamantes.
     */
    private FactorValorDiamante factor;

    /**
     * Fecha de vigencia de la lista de factores.
     */
    private DateTime fechaCarga;

    /**
     * Fecha de origen de la información.
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
         * Recupera el valor de {@link FactorValorDiamante}
         *
         * @return {@link FactorValorDiamante}
         */
        FactorValorDiamante getFactor();

        /**
         * Recupera la fecha de vigencia de la lista de factores.
         *
         * @return Fecha de vigencia de la lista de factores.
         */
        DateTime getFechaCarga();

        /**
         * Recupera la fecha de origen de la información.
         *
         * @return Fecha de origen de la información.
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
     * Regresa la fecha de vigencia de la lista de factores.
     *
     * @return Fecha de vigencia de la lista de factores.
     */
    public DateTime getFechaCarga() {
        return fechaCarga;
    }

    /**
     * Regresa la fecha de origen de la información.
     *
     * @return Fecha de origen de la información.
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

        return Objects.equals(fechaCarga, that.fechaCarga);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(fechaCarga);
    }

    @Override
    public String toString() {
        return String.format("%s{fechaCarga=%s, factor=%s}", getClass().getSimpleName(), fechaCarga, factor);
    }
}
