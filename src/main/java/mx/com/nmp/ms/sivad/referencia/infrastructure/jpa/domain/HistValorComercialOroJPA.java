/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad utilizada para representar los históricos del valor comercial del oro.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "hist_cfg_alhaja_valor_comercial_oro")
public class HistValorComercialOroJPA extends AbstractValorComercialOroJPA {

    /**
     * Listado al que está asociado el valor comercial.
     */
    @ManyToOne
    @JoinColumn(name = "listado")
    private HistListadoValorComercialOroJPA listado;



    // GETTERS Y SETTERS

    public HistListadoValorComercialOroJPA getListado() {
        return listado;
    }

    public void setListado(HistListadoValorComercialOroJPA listado) {
        this.listado = listado;
    }

    @Override
    public String toString() {
        return "HistValorComercialOroJPA{" +
            "id=" + id +
            ", color='" + color + '\'' +
            ", calidad=" + calidad +
            ", precio=" + precio +
            '}';
    }

}

