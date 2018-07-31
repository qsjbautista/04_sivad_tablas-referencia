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
 * Entidad JPA que permite mapear los porcentajes de castigo por tipo
 * de corte a una tabla persistente.
 *
 * @author ecancino
 */
@Entity
@Table(name = "cfg_diamante_porcentaje_castigo_x_tipo_corte")
public class CastigoCorteDiamanteJPA {

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
     * El tipo de corte del diamante con base en el catalogo de cortes
     */
    @Column(name = "corte", nullable = false)
    private String corte;

    /**
     * Factor por tipo de corte
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

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
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
        if (!(o instanceof CastigoCorteDiamanteJPA)) return false;

        CastigoCorteDiamanteJPA that = (CastigoCorteDiamanteJPA) o;

        if (!id.equals(that.id)) return false;
        if (!fecha.equals(that.fecha)) return false;
        if (!corte.equals(that.corte)) return false;
        if (!factor.equals(that.factor)) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, corte, factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RangoPesoDiamanteJPA{" +
            "id=" + id + '\'' +
            ", fecha='" + fecha + '\'' +
            ", corte='" + corte + '\'' +
            ", factor='" + factor +
            '}';
    }
}
