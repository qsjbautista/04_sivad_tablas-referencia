/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Clase abstracta que factoriza los atributos comunes de los listados de modificadores certificado.
 *
 * @author mmarquez
 */
@MappedSuperclass
public abstract class AbstractListadoModificadorCertificadoJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fecha_carga", nullable = false)
    private Date fechaCarga;

    @Column(name = "fecha_listado", nullable = false)
    private Date fechaListado;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<ModificadorCertificadoJPA> modificadoresCertificado;

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

    public Set<ModificadorCertificadoJPA> getModificadoresCertificado() {
        return modificadoresCertificado;
    }

    public void setModificadoresCertificado(Set<ModificadorCertificadoJPA> modificadorCertificado) {
        this.modificadoresCertificado = modificadorCertificado;
    }
}
