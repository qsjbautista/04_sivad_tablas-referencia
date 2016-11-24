/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Interface que proporciona el contrato para recuperar el Value Object con los factores vigentes.
 * @see FactorValorDiamante
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public interface ModificadorValorDiamanteRepository {
    /**
     * Permite actualizar el listado de factores de valor de diamante.
     *
     * @param entidad Factores de valor de diamante con el cual se desea reemplazar los vigentes.
     *
     * @throws IllegalArgumentException Si {@code entidad} es {@literal null}
     */
    ModificadorValorDiamante actualizar(@NotNull ModificadorValorDiamante entidad);

    /**
     * Consulta los factores de valor de diamante (factor valor Mínimo, factor valor Medio, factor valor Máximo)
     * vigentes al momento de la consulta.
     *
     * @return Factores de valor de diamante vigentes.
     *
     * @throws ValorComercialNoEncontradoException Si no existen factores de valor de diamante vigentes.
     */
    ModificadorValorDiamante consultar();

    /**
     * Permite consultar el listado de factores de valor de diamante con base en una fecha de vigencia.
     *
     * @param fecha Fecha de vigencia.
     *
     * @return Lista de factores de valor de diamante en la fecha especificada.
     *
     * @throws IllegalArgumentException Si {@code fecha} es {@literal null}
     * @throws ValorComercialNoEncontradoException Si no existen factores de valor de diamante en la fecha especificada.
     */
    List<ModificadorValorDiamante> consultar(@NotNull DateTime fecha);
}
