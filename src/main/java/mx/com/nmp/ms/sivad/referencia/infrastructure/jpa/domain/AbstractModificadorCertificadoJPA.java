/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Clase abstracta que factoriza los atributos comunes de los modificadores certificado.
 *
 * @author mmarquez
 */
@MappedSuperclass
public abstract class AbstractModificadorCertificadoJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "certificado", nullable = false)
    private String certificado;

    @Column(name = "factor", nullable = false)
    private BigDecimal factor;

    @ManyToOne
    @JoinColumn(name = "listado")
    private ListadoModificadorCertificadoJPA listado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public ListadoModificadorCertificadoJPA getListado() {
        return listado;
    }

    public void setListado(ListadoModificadorCertificadoJPA listado) {
        this.listado = listado;
    }

}
