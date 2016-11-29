package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad que representa la tabla ListadoFactorAlhajaJPA.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "cfg_alhaja_listado_factor")
public class ListadoFactorAlhajaJPA extends AbstractListadoModificadorJPA {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<FactorAlhajaJPA> factoresAlhaja;

    public Set<FactorAlhajaJPA> getFactoresAlhaja() {
        return factoresAlhaja;
    }

    public void setFactoresAlhaja(Set<FactorAlhajaJPA> factoresAlhaja) {
        this.factoresAlhaja = factoresAlhaja;
    }
}
