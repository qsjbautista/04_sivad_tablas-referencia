/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Entidad utilizada para representar los históricos de la lista de valores comerciales de diamantes.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "hist_cfg_diamante_listado_valor_comercial")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "restaurar_anterior",
        procedureName = "sp_diamante_valor_comercial_restaurar_anterior"),
    @NamedStoredProcedureQuery(name = "restaurar_fecha",
        procedureName = "sp_diamante_valor_comercial_restaurar_fecha",
        parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "_fechaIni", type = Date.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "_fechaFin", type = Date.class)})
})
public class HistListadoValorComercialDiamanteJPA extends AbstractListadoValorComercialDiamanteJPA {

    /**
     * Lista de valores comerciales de diamantes.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<HistValorComercialDiamanteJPA> valoresComerciales;



    // GETTERS Y SETTERS

    public Set<HistValorComercialDiamanteJPA> getValoresComerciales() {
        return valoresComerciales;
    }

    public void setValoresComerciales(Set<HistValorComercialDiamanteJPA> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

}
