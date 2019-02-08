/**
 * Proyecto:        NMP - Sistema de Operacion Prendaria Emergente
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad utilizada para representar las caracteristicas de la alhaja: metal, calidad, subramo.
 *
 * @author ecancino
 */
@Entity
@Table(name="cfg_metal_calidad_subramo")
public class MetalCalidadSubramoJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Metal de la alhaja.
     */
    @Column(name="metal", length=20, nullable = false)
    private String metal;

    /**
     * Calidad del metal de la alhaja.
     */
    @Column(name="calidad", length=20)
    private String calidad;

    /**
     * Subramo de la alhaja.
     */
    @Column(name="subramo", length=20, nullable = false)
    private String subramo;



    // GETTERS Y SETTERS

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetal() {
        return this.metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getCalidad() {
        return this.calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getSubramo() {
        return this.subramo;
    }

    public void setSubramo(String subramo) {
        this.subramo = subramo;
    }



    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetalCalidadSubramoJPA)) return false;

        MetalCalidadSubramoJPA that = (MetalCalidadSubramoJPA) o;

        if (!id.equals(that.id)) return false;
        if (!metal.equals(that.metal)) return false;
        if (!calidad.equals(that.calidad)) return false;
        if (!subramo.equals(that.subramo)) return false;

        return true;
    }

    public int hashCode() {
        return Objects.hash(id, metal, calidad, subramo);
    }

    public String toString() {
        return "MetalCalidadSubramoJPA{" +
            "id=" + id + '\'' +
            ", metal='" + metal + '\'' +
            ", subramo='" + subramo + '\'' +
            ", calidad='" + calidad +
            '}';
    }
}
