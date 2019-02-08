/**
 * Proyecto:        NMP - Sistema de Operacion Prendaria Emergente
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.TipoHechura;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.MetalVO;

import java.util.List;

/**
 * Utilizado para atender las solicitudes de tipos de hechura.
 *
 * @author ecancino
 */
public interface TipoHechuraAlhajaRepository {

    /**
     * Permite obtener el listado de tipos de hechura, que corresponda a las características indicadas en
     * las propiedades del parámetro.
     *
     * @param metalVO Value Object con las características de búsqueda.
     * @return Listado con la entidad obtenida.
     */
    List<TipoHechura> consultarTiposHechura(MetalVO metalVO);

}
