/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad utilizada para representar el valor comercial de un metal (diferente de oro).
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "cfg_alhaja_valor_comercial_metal")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValorComercialMetalJPA extends AbstractValorComercialMetalJPA {

    /**
     * Listado al que está asociado el valor comercial.
     */
    @ManyToOne
    @JoinColumn(name = "listado")
    private ListadoValorComercialMetalJPA listado;



    // GETTERS Y SETTERS

    public ListadoValorComercialMetalJPA getListado() {
        return listado;
    }

    public void setListado(ListadoValorComercialMetalJPA listado) {
        this.listado = listado;
    }

    @Override
    public String toString() {
        return "ValorComercialMetalJPA{" +
            "id=" + id +
            ", metal='" + metal + '\'' +
            ", calidad='" + calidad + '\'' +
            ", precio=" + precio +
            '}';
    }

}
