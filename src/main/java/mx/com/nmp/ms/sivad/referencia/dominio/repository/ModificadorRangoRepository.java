/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhaja;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoRango;

import java.util.Date;
import java.util.List;

/**
 * Utilizado el acceso a datos de modificadores de factores de alhajas.
 *
 * @author mmarquez
 */
public interface ModificadorRangoRepository {

    /**
     * Permite obtener la entidad factorAhaja, que corresponda a las características indicadas en las propiedades
     * del parámetro.
     *
     * @param factorAlhaja Entidad con las características de búsqueda.
     * @return La entidad obtenida.
     */
    FactorAlhaja obtenerFactor(FactorAlhaja factorAlhaja);

    /**
     * Permite obtener la entidad ListadoRango vigente.
     *
     * @return La entidad listado obtenida.
     */
    ListadoRango consultarListadoVigente();

    /**
     * Permite obtener la lista de entidades ListadoRango que correspondan a la fecha de carga indicada.
     *
     * @param fechaCarga La fecha de carga.
     * @return La lista de entidades obtenidas.
     */
    List<ListadoRango> consultarListadoPorFechaCarga(Date fechaCarga);

    /**
     * Permite actualizar el listado vigente con la información del listado que se recibe como parámetro.
     *
     * @param listado El nuevo listado.
     */
    void actualizarListado(ListadoRango listado);

}
