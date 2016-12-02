/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad utilizada para representar la lista de valores comerciales de diamantes.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "cfg_diamante_listado_valor_comercial")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ListadoValorComercialDiamanteJPA extends AbstractListadoValorComercialDiamanteJPA {

    /**
     * Lista de valores comerciales de diamantes.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<ValorComercialDiamanteJPA> valoresComerciales;



    // GETTERS Y SETTERS

    public Set<ValorComercialDiamanteJPA> getValoresComerciales() {
        return valoresComerciales;
    }

    public void setValoresComerciales(Set<ValorComercialDiamanteJPA> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

}
