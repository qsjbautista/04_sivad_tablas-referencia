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
 * Entidad utilizada para representar los históricos del valor comercial de un metal (diferente de oro).
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "hist_cfg_alhaja_valor_comercial_metal")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistValorComercialMetalJPA extends AbstractValorComercialMetalJPA {

    /**
     * Listado al que está asociado el valor comercial.
     */
    @ManyToOne
    @JoinColumn(name = "listado")
    private HistListadoValorComercialMetalJPA listado;



    // GETTERS Y SETTERS

    public HistListadoValorComercialMetalJPA getListado() {
        return listado;
    }

    public void setListado(HistListadoValorComercialMetalJPA listado) {
        this.listado = listado;
    }

    @Override
    public String toString() {
        return "HistValorComercialMetalJPA{" +
            "id=" + id +
            ", metal='" + metal + '\'' +
            ", calidad='" + calidad + '\'' +
            ", precio=" + precio +
            '}';
    }

}
