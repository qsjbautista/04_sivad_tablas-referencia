package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad utilizada para representar los históricos de la lista de valores comerciales de diamantes.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "hist_cfg_diamante_listado_valor_comercial")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistListadoValorComercialDiamanteJPA extends AbstractListadoValorComercialDiamanteJPA {

    /**
     * Lista de valores comerciales de diamantes.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<HistValorComercialDiamanteJPA> valoresComerciales;



    // GETTERS Y SETTERS

    public Set<HistValorComercialDiamanteJPA> getValoresComerciales() {
        return valoresComerciales;
    }

    public void setValoresComerciales(Set<HistValorComercialDiamanteJPA> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

}
