package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;

/**
 * Entidad que representa la tabla FactorAlhajaJPA.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "cfg_factor_alhaja")
public class FactorAlhajaJPA extends AbstractFactorAlhajaJPA {

    @ManyToOne
    @JoinColumn(name = "listado")
    private ListadoFactorAlhajaJPA listado;

    public ListadoFactorAlhajaJPA getListado() {
        return listado;
    }

    public void setListado(ListadoFactorAlhajaJPA listado) {
        this.listado = listado;
    }
}
