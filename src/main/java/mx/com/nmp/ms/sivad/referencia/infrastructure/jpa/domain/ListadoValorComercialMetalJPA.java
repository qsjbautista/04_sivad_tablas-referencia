/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad utilizada para representar la lista de valores comerciales de metales (diferentes de oro).
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "cfg_alhaja_listado_valor_comercial_metal")
public class ListadoValorComercialMetalJPA extends AbstractListadoValorComercialJPA {

    /**
     * Lista de valores comerciales de metales (diferentes de oro).
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<ValorComercialMetalJPA> valoresComerciales;



    // GETTERS Y SETTERS

    public Set<ValorComercialMetalJPA> getValoresComerciales() {
        return valoresComerciales;
    }

    public void setValoresComerciales(Set<ValorComercialMetalJPA> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

}
