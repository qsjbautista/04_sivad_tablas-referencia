/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.RangoPesoDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.RangoPesoDiamanteVO;

/**
 * Utilizado para canalizar el acceso a datos de rangos de peso del diamante.
 *
 * @author ecancino
 */
public interface RangoPesoDiamanteRepository {

    /**
     * Permite obtener la entidad RangoPesoDiamante que corresponda a las características indicadas.
     *
     * @param rangoPesoDiamanteVO Value Object con las características del peso del diamante.
     * @return La entidad obtenida con el peso del diamante.
     */
    RangoPesoDiamante obtenerRangoPeso(RangoPesoDiamanteVO rangoPesoDiamanteVO);

}
