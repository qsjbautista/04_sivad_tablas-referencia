/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
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
     * @param fechaCarga Fecha de vigencia de la lista de factores.
     * @param fechaListado Fecha de origen de la información.
     * @param factor Factores de valor de diamantes.
     *
     * @return La entidad creada.
     *
     * @throws IllegalArgumentException Cuando algún argumento sea nulo.
     */
    ModificadorValorDiamante crearCon(DateTime fechaCarga, LocalDate fechaListado, FactorValorDiamante factor);

    /**
     * Crea una entidad a partir del valor de los argumentos. a esta entidad le será inyectado
     * el repositorio de entidades para que se pueda persistir.
     *
     * @param fechaCarga Fecha de vigencia de la lista de factores.
     * @param fechaListado Fecha de origen de la información.
     * @param factor Factores de valor de diamantes.
     *
     * @return La entidad creada.
     *
     * @throws IllegalArgumentException Cuando algún argumento sea nulo.
     */
    ModificadorValorDiamante crearPersistibleCon(DateTime fechaCarga, LocalDate fechaListado,
                                                 FactorValorDiamante factor);

    /**
     * Crea una entidad a partir de un objeto constructor.
     *
     * @param builder Referencia al objeto constructor.
     *
     * @return La entidad creada.
     *
     * @throws IllegalArgumentException Cuando algún valor del {@link ModificadorValorDiamante.Builder} es incorrecto.
     */
    ModificadorValorDiamante crearDesde(ModificadorValorDiamante.Builder builder);

    /**
     * Crea una entidad a partir de un objeto constructor. a esta entidad le será inyectado
     * el repositorio de entidades para que se pueda persistir.
     *
     * @param builder Referencia al constructor.
     *
     * @return La entidad creada.
     *
     * @throws IllegalArgumentException Cuando algún valor del {@link ModificadorValorDiamante.Builder} es incorrecto.
     */
    ModificadorValorDiamante crearPersistibleDesde(ModificadorValorDiamante.Builder builder);
}
