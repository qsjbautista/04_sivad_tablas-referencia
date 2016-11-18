/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad utilizada para representar la lista de valores comerciales del oro.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "cfg_alhaja_listado_valor_comercial_oro")
public class ListadoValorComercialOroJPA extends AbstractListadoValorComercialJPA {

    /**
     * Lista de valores comerciales del oro.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<ValorComercialOroJPA> valoresComerciales;



    // GETTERS Y SETTERS

    public Set<ValorComercialOroJPA> getValoresComerciales() {
        return valoresComerciales;
    }

    public void setValoresComerciales(Set<ValorComercialOroJPA> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

}
