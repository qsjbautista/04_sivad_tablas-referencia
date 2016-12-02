/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Objects;

/**
 * Clase abstracta que factoriza los atributos comunes de los listados de valores comerciales de Oro y Metal.
 *
 * @author ngonzalez
 */
@MappedSuperclass
public abstract class AbstractListadoValorComercialJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    /**
     * Fecha en que se realiza la última actualización.
     */
    @Column(name = "ultima_actualizacion", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime ultimaActualizacion;



    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(DateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractListadoValorComercialJPA)) return false;

        AbstractListadoValorComercialJPA that = (AbstractListadoValorComercialJPA) o;

        if (!id.equals(that.id)) return false;
        if (!ultimaActualizacion.equals(that.ultimaActualizacion)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ultimaActualizacion);
    }

}
