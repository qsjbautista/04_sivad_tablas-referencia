/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Clase abstracta que factoriza los atributos comunes de los listados de valores comerciales de Diamante.
 *
 * @author ngonzalez
 */
@MappedSuperclass
public abstract class AbstractListadoValorComercialDiamanteJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    /**
     * Fecha en que se realiza la última actualización (fecha de vigencia).
     */
    @Column(name = "fecha_carga", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime fechaCarga;

    /**
     * La fecha de origen de la información.
     */
    @Column(name = "fecha_listado", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    protected LocalDate fechaListado;



    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(DateTime fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public LocalDate getFechaListado() {
        return fechaListado;
    }

    public void setFechaListado(LocalDate fechaListado) {
        this.fechaListado = fechaListado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractListadoValorComercialDiamanteJPA)) return false;

        AbstractListadoValorComercialDiamanteJPA that = (AbstractListadoValorComercialDiamanteJPA) o;

        if (!fechaCarga.equals(that.fechaCarga)) return false;
        if (!fechaListado.equals(that.fechaListado)) return false;
        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaCarga, fechaListado);
    }

}
