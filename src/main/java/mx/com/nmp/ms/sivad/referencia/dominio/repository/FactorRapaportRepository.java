package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import mx.com.nmp.ms.arquetipo.annotation.validation.NotNull;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorRapaportDiamanteJPA;

public interface FactorRapaportRepository {

    /**
     * Permite agregar un elemento nuevo al catálogo.
     *
     * @param elemento Elemento a guardar.
     *
     * @return El objeto {@link FactorRapaportDiamanteJPA} que fue creado.
     */
    public FactorRapaportDiamanteJPA save(@Valid FactorRapaportDiamanteJPA elemento);

    /**
     * Permite modificar un elemento del catálogo.
     *
     * @return El objeto {@link Long} que fue actualizado.
     *
     */
    public FactorRapaportDiamanteJPA update(@NotNull Long idFactor, @NotNull BigDecimal factor);

    /**
     * Permite eliminar un elemento del catálogo.
     *
     * @return El objeto {@link Long} que fue eliminado.
     *
     */
    public FactorRapaportDiamanteJPA delete(@NotNull Long idFactor);

    public List<FactorRapaportDiamanteJPA> getAll();

    public FactorRapaportDiamanteJPA obtenerElemento(Long idFactor);

}
