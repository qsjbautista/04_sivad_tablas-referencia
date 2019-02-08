/**
 * Proyecto:        NMP - Sistema de Operacion Prendaria Emergente
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad utilizada para representar la relacion el valor comercial de un metal (diferente de oro).
 *
 * @author ecancino
 */
@Entity
@Table(name = "cfg_metal_calidad_subramo_rango")
public class MetalCalidadRangoSubramoJPA {

    /**
     * Identificador del registro
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Metal-Calidad-Subramo de la alhaja
     */
    @ManyToOne
    @JoinColumn(name = "metal_calidad_subramo", nullable = false)
    private MetalCalidadSubramoJPA metalCalidadSubramo;

    /**
     * Rango de la alhaja
     */
    @OneToOne
    @JoinColumn(name = "tipo_hechura", nullable = false)
    private TipoHechuraJPA tipoHechura;



    // GETTERS Y SETTERS

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetalCalidadSubramoJPA getMetalCalidadSubramo() {
        return this.metalCalidadSubramo;
    }

    public void setMetalCalidadSubramo(MetalCalidadSubramoJPA metalCalidadSubramo) {
        this.metalCalidadSubramo = metalCalidadSubramo;
    }

    public TipoHechuraJPA getTipoHechura() {
        return this.tipoHechura;
    }

    public void setTipoHechura(TipoHechuraJPA tipoHechura) {
        this.tipoHechura = tipoHechura;
    }



    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetalCalidadRangoSubramoJPA)) return false;

        MetalCalidadRangoSubramoJPA that = (MetalCalidadRangoSubramoJPA) o;

        if (!id.equals(that.id)) return false;
        if (!metalCalidadSubramo.equals(that.metalCalidadSubramo)) return false;
        if (!tipoHechura.equals(that.tipoHechura)) return false;

        return true;
    }

    public int hashCode() {
        return Objects.hash(id, metalCalidadSubramo, tipoHechura);
    }

    public String toString() {
        return "MetalCalidadRangoSubramoJPA{" +
            "id=" + id + '\'' +
            ", metalCalidadSubramo='" + metalCalidadSubramo + '\'' +
            ", tipoHechura='" + tipoHechura +
            '}';
    }
}
