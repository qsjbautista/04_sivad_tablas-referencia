package mx.com.nmp.ms.sivad.referencia.dominio.factory;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Interface que define el contrato para la fabricación de entidades tipo {@link ModificadorValorDiamante}
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public interface ModificadorValorDiamanteFactory {
    /**
     * Crea una entidad a partir del valor de los argumentos.
     *
     * @param fechaCarga Fecha en la que se almacena la entidad.
     * @param fechaListado Fecha de vigencia de la lista de factores.
     * @param factor Factores de valor de diamantes.
     *
     * @return La entidad creada.
     */
    ModificadorValorDiamante createWith(DateTime fechaCarga, LocalDate fechaListado, FactorValorDiamante factor);

    /**
     * Crea una entidad a partir del valor de los argumentos. a esta entidad le será inyectado
     * el repositorio de entidades para que se pueda persistir.
     *
     * @param fechaCarga Fecha en la que se almacena la entidad.
     * @param fechaListado Fecha de vigencia de la lista de factores.
     * @param factor Factores de valor de diamantes.
     *
     * @return La entidad creada.
     */
    ModificadorValorDiamante createPersistentWith(DateTime fechaCarga, LocalDate fechaListado,
                                                  FactorValorDiamante factor);

    /**
     * Crea una entidad a partir de un objeto constructor.
     *
     * @param builder Referencia al objeto constructor.
     *
     * @return La entidad creada.
     */
    ModificadorValorDiamante createFrom(ModificadorValorDiamante.Builder builder);

    /**
     * Crea una entidad a partir de un objeto constructor. a esta entidad le será inyectado
     * el repositorio de entidades para que se pueda persistir.
     *
     * @param builder Referencia al constructor.
     *
     * @return La entidad creada.
     */
    ModificadorValorDiamante createPersistentFrom(ModificadorValorDiamante.Builder builder);
}
