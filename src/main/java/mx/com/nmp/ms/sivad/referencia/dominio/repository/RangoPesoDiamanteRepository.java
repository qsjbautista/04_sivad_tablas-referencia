/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import java.util.List;

import javax.validation.Valid;

import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.RangoPesoDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.RangoPesoDiamanteVO;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.RangoPesoDiamanteJPA;

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

    /**
     * Permite agregar un elemento nuevo al catálogo.
     *
     * @param elemento Elemento a guardar.
     *
     * @return El objeto {@link T} que fue creado.
     */
    public RangoPesoDiamanteJPA save(@Valid RangoPesoDiamanteJPA elemento);

    /**
     * Permite modificar un elemento del catálogo.
     *
     * @return El objeto {@link T} que fue actualizado.
     *
     * @throws CatalogoNotFoundException Cuando no existe el elemento a actualizar.
     */
    public RangoPesoDiamanteJPA update(@NotNull Long idRangoPesos, @NotNull RangoPesoDiamanteJPA rangoPesos);

    /**
     * Permite eliminar un elemento del catálogo.
     *
     * @return El objeto {@link T} que fue eliminado.
     *
     * @throws CatalogoNotFoundException Cuando no existe el elemento a eliminar.
     */
    public RangoPesoDiamanteJPA delete(@NotNull Long idRangoPesos);

    /**
     * Obtiene todos los elementos del catalogo
     */
    public List<RangoPesoDiamanteJPA> getAll();

    public RangoPesoDiamanteJPA findOne(Long idRangoPesos);

}
