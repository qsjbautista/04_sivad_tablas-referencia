/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad utilizada para representar los históricos de la lista de valores comerciales del oro.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "hist_cfg_alhaja_listado_valor_comercial_oro")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistListadoValorComercialOroJPA extends AbstractListadoValorComercialJPA {

    /**
     * Lista de valores comerciales del oro.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<HistValorComercialOroJPA> valoresComerciales;



    // GETTERS Y SETTERS

    public Set<HistValorComercialOroJPA> getValoresComerciales() {
        return valoresComerciales;
    }

    public void setValoresComerciales(Set<HistValorComercialOroJPA> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

}
