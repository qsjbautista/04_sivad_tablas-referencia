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
 * Entidad JPA que permite mapear los factores de depreciacion a una tabla
 * persistente.
 *
 * @author ecancino
 */
@Entity
@Table(name = "cfg_diamante_factor_depreciacion")
public class FactorDepreciacionDiamanteJPA {

    /**
     * Identificador del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Fecha de carga del factor
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "fecha")
    @JournalData
    private DateTime fecha;

    /**
     * Factor de depreciacion
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
        if (!(o instanceof FactorDepreciacionDiamanteJPA)) return false;

        FactorDepreciacionDiamanteJPA that = (FactorDepreciacionDiamanteJPA) o;

        if (!id.equals(that.id)) return false;
        if (!fecha.equals(that.fecha)) return false;
        if (!factor.equals(that.factor)) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FactorDepreciacionDiamanteJPA{" +
            "id=" + id + '\'' +
            ", fecha='" + fecha + '\'' +
            ", factor='" + factor +
            '}';
    }
}
