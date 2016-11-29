package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaCarga;

    /**
     * La fecha de origen de la información.
     */
    @Column(name = "fecha_listado", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaListado;



    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaListado() {
        return fechaListado;
    }

    public void setFechaListado(Date fechaListado) {
        this.fechaListado = fechaListado;
    }

}
