/**
 * Proyecto:        NMP - Sistema de Operacion Prendaria Emergente
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad utilizada para representar el tipo de hechura.
 *
 * @author ecancino
 */
@Entity
@Table(name="cat_tipo_hechura")
public class TipoHechuraJPA {

	/**
	 * Identificador del registro.
	 */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	/**
	 * Abreviatura del rango
	 */
    @Column(name="descripcion", length=80, nullable = false)
	private String descripcion;

	/**
	 * Descripcion del rango
	 */
    @Column(name="abreviatura", length=3, nullable = false)
	private String abreviatura;



    // GETTERS Y SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}



    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoHechuraJPA)) return false;

        TipoHechuraJPA that = (TipoHechuraJPA) o;

        if (!id.equals(that.id)) return false;
        if (!abreviatura.equals(that.abreviatura)) return false;
        if (!descripcion.equals(that.descripcion)) return false;

        return true;
    }

    public int hashCode() {
        return Objects.hash(id, abreviatura, descripcion);
    }

    public String toString() {
        return "TipoHechuraJPA{" +
            "id=" + id + '\'' +
            ", abreviatura='" + abreviatura + '\'' +
            ", descripcion='" + descripcion +
            '}';
    }
}
