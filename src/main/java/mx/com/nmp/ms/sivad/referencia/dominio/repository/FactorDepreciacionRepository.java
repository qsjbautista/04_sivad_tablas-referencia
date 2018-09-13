package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorDepreciacionDiamanteJPA;

public interface FactorDepreciacionRepository {

    /**
     * Permite agregar un elemento nuevo al catálogo.
     *
     * @param elemento Elemento a guardar.
     *
     * @return El objeto {@link T} que fue creado.
     */
    public FactorDepreciacionDiamanteJPA save(@Valid FactorDepreciacionDiamanteJPA elemento);

    /**
     * Permite modificar un elemento del catálogo.
     *
     * @return El objeto {@link T} que fue actualizado.
     *
     * @throws CatalogoNotFoundException Cuando no existe el elemento a actualizar.
     */
    public FactorDepreciacionDiamanteJPA update(@NotNull Long idFactor, @NotNull BigDecimal factor);

    /**
     * Permite eliminar un elemento del catálogo.
     *
     * @return El objeto {@link T} que fue eliminado.
     *
     * @throws CatalogoNotFoundException Cuando no existe el elemento a eliminar.
     */
    public FactorDepreciacionDiamanteJPA delete(@NotNull Long idFactor);

    public List<FactorDepreciacionDiamanteJPA> getAll();

    public FactorDepreciacionDiamanteJPA obtenerElemento(Long idFactor);

}
