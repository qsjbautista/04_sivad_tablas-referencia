/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad utilizada para representar el valor comercial de un diamante.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "cfg_diamante_valor_comercial")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValorComercialDiamanteJPA extends AbstractValorComercialDiamanteJPA {

    @ManyToOne
    @JoinColumn(name = "listado")
    private ListadoValorComercialDiamanteJPA listado;

    public ListadoValorComercialDiamanteJPA getListado() {
        return listado;
    }

    public void setListado(ListadoValorComercialDiamanteJPA listado) {
        this.listado = listado;
    }

    // METODOS

    @Override
    public String toString() {
        return "ValorComercialDiamanteJPA{" +
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
