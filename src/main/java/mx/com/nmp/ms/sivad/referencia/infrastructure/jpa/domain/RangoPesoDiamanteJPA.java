/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import mx.com.nmp.ms.arquetipo.annotation.journal.JournalData;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidad JPA que permite mapear los rangos de peso a una tabla
 * persistente.
 *
 * @author ecancino
 */
@Entity
@Table(name = "cfg_diamante_rango_pesos")
public class RangoPesoDiamanteJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Fecha de carga del rango de peso
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "fecha")
    @JournalData
    private DateTime fecha;

    /**
     * Rango inferior del peso
     */
    @Column(name = "quilates_desde", precision = 6, scale = 2, nullable = false)
    private BigDecimal quilatesDesde;

    /**
     * Rango superior del peso
     */
    @Column(name = "quilates_hasta", precision = 6, scale = 2, nullable = false)
    private BigDecimal quilatesHasta;



    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getFecha() {
        return fecha;
    }

    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getQuilatesDesde() {
        return quilatesDesde;
    }

    public void setQuilatesDesde(BigDecimal quilatesDesde) {
        this.quilatesDesde = quilatesDesde;
    }

    public BigDecimal getQuilatesHasta() {
        return quilatesHasta;
    }

    public void setQuilatesHasta(BigDecimal quilatesHasta) {
        this.quilatesHasta = quilatesHasta;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangoPesoDiamanteJPA)) return false;

        RangoPesoDiamanteJPA that = (RangoPesoDiamanteJPA) o;

        if (!id.equals(that.id)) return false;
        if (!fecha.equals(that.fecha)) return false;
        if (!quilatesDesde.equals(that.quilatesDesde)) return false;
        if (!quilatesHasta.equals(that.quilatesHasta)) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, quilatesDesde, quilatesHasta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RangoPesoDiamanteJPA{" +
            "id=" + id + '\'' +
            ", fecha='" + fecha + '\'' +
            ", quilatesDesde='" + quilatesDesde + '\'' +
            ", quilatesHasta='" + quilatesHasta +
            '}';
    }
}
