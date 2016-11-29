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
 * Entidad utilizada para representar los históricos del factor alhaja.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "hist_cfg_factor_alhaja")
public class HistFactorAlhajaJPA extends AbstractFactorAlhajaJPA {

    /**
     * Listado al que está asociado el valor comercial.
     */
    @ManyToOne
    @JoinColumn(name = "listado")
    private HistListadoFactorAlhajaJPA listado;


    // GETTERS Y SETTERS

    public HistListadoFactorAlhajaJPA getListado() {
        return listado;
    }

    public void setListado(HistListadoFactorAlhajaJPA listado) {
        this.listado = listado;
    }

}

