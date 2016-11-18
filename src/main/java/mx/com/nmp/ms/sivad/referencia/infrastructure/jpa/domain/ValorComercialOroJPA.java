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
 * Entidad utilizada para representar el valor comercial del oro.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "cfg_alhaja_valor_comercial_oro")
public class ValorComercialOroJPA extends AbstractValorComercialOroJPA {

    /**
     * Listado al que está asociado el valor comercial.
     */
    @ManyToOne
    @JoinColumn(name = "listado")
    private ListadoValorComercialOroJPA listado;



    // GETTERS Y SETTERS

    public ListadoValorComercialOroJPA getListado() {
        return listado;
    }

    public void setListado(ListadoValorComercialOroJPA listado) {
        this.listado = listado;
    }

}
