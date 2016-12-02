/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad utilizada para representar los históricos de la lista de valores comerciales de metales (diferentes de oro).
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "hist_cfg_alhaja_listado_valor_comercial_metal")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistListadoValorComercialMetalJPA extends AbstractListadoValorComercialJPA {

    /**
     * Lista de valores comerciales de metales (diferentes de oro).
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<HistValorComercialMetalJPA> valoresComerciales;



    // GETTERS Y SETTERS

    public Set<HistValorComercialMetalJPA> getValoresComerciales() {
        return valoresComerciales;
    }

    public void setValoresComerciales(Set<HistValorComercialMetalJPA> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

}
