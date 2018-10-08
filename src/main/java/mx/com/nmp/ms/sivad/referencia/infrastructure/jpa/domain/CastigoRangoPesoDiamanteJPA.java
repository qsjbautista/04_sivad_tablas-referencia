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
 * Entidad JPA que permite mapear los porcentajes de castigo por rango
 * de peso a una tabla persistente.
 *
 * @author ecancino
 */
@Entity
@Table(name = "cfg_diamante_porcentaje_castigo_x_rango_de_pesos")
public class CastigoRangoPesoDiamanteJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Fecha de carga del castigo
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "fecha")
    @JournalData
    private DateTime fecha;

    /**
     * Valor del rango inferior del peso para aplicar porcentaje de castigo
     */
    @Column(name = "quilates_desde", precision = 6, scale = 2, nullable = false)
    private BigDecimal quilatesDesde;

    /**
     * Valor del rango superior del peso para aplicar porcentaje de castigo
     */
    @Column(name = "quilates_hasta", precision = 6, scale = 2, nullable = false)
    private BigDecimal quilatesHasta;

    /**
     * Factor aplicable al rango indicado en la tabla
     */
    @Column(name = "factor", precision = 10, scale = 3, nullable = false)
    private BigDecimal factor;



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

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CastigoRangoPesoDiamanteJPA)) return false;

        CastigoRangoPesoDiamanteJPA that = (CastigoRangoPesoDiamanteJPA) o;

        if (!id.equals(that.id)) return false;
        if (!fecha.equals(that.fecha)) return false;
        if (!quilatesDesde.equals(that.quilatesDesde)) return false;
        if (!quilatesHasta.equals(that.quilatesHasta)) return false;
        if (!factor.equals(that.factor)) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, quilatesDesde, quilatesHasta, factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CastigoRangoPesoDiamanteJPA{" +
            "id=" + id + '\'' +
            ", fecha='" + fecha + '\'' +
            ", quilatesDesde='" + quilatesDesde + '\'' +
            ", quilatesHasta='" + quilatesHasta + '\'' +
            ", factor=" + factor +
            '}';
    }
}
