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
 * Entidad utilizada para representar los históricos del valor comercial de un diamante.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "hist_cfg_diamante_valor_comercial")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistValorComercialDiamanteJPA extends AbstractValorComercialDiamanteJPA {

    /**
     * Listado al que está asociado el valor comercial.
     */
    @ManyToOne
    @JoinColumn(name = "listado")
    private HistListadoValorComercialDiamanteJPA listado;



    // GETTERS Y SETTERS

    public HistListadoValorComercialDiamanteJPA getListado() {
        return listado;
    }

    public void setListado(HistListadoValorComercialDiamanteJPA listado) {
        this.listado = listado;
    }

    @Override
    public String toString() {
        return "HistValorComercialDiamanteJPA{" +
            "id=" + id +
            ", corte='" + corte + '\'' +
            ", color='" + color + '\'' +
            ", claridad='" + claridad + '\'' +
            ", tamanioInferior=" + tamanioInferior +
            ", tamanioSuperior=" + tamanioSuperior +
            ", precio=" + precio +
            '}';
    }

}
