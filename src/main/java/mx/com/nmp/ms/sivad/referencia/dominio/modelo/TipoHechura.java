/**
 * Proyecto:        NMP - Sistema de Operacion Prendaria Emergente
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

/**
 * Entidad que representa un tipo de hechura o rango.
 *
 * @author ecancino
 */
public class TipoHechura {

    /**
     * Identificador del tipo de hechura.
     */
    private Long id;

    /**
     * Abreviatura del tipo de hechura.
     */
    private String abreviatura;

    /**
     * Descripcion del tipo de hechura.
     */
    private String descripcion;



    // METODOS

    /**
     * Constructor.
     *
     * @param id Identificador del tipo de hechura.
     * @param abreviatura Abreviatura del tipo de hechura.
     * @param descripcion Descripcion del tipo de hechura.
     */
    public TipoHechura(Long id, String abreviatura, String descripcion) {
        this.id = id;
        this.abreviatura = abreviatura;
        this.descripcion = descripcion;
    }



    // GETTERS


    public Long getId() {
        return id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "TipoHechura{" +
            "id='" + id + '\'' +
            ", abreviatura='" + abreviatura + '\'' +
            ", descripcion=" + descripcion +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoHechura)) return false;

        TipoHechura tipoHechura1 = (TipoHechura) o;

        if (!id.equals(tipoHechura1.id)) return false;
        if (!abreviatura.equals(tipoHechura1.abreviatura)) return false;
        if (!descripcion.equals(tipoHechura1.descripcion)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + abreviatura.hashCode();
        result = 31 * result + descripcion.hashCode();
        return result;
    }

}
