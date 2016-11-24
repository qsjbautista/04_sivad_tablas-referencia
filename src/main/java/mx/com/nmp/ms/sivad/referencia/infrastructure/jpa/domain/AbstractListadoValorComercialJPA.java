/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    protected Date ultimaActualizacion;



    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

}
