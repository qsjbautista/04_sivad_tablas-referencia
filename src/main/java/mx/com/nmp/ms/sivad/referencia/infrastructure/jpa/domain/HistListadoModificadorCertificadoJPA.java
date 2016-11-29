/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad utilizada para representar los históricos de la lista de Modificadores Certificados.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "hist_cfg_diamante_listado_valor_certificado")
public class HistListadoModificadorCertificadoJPA extends AbstractListadoModificadorJPA {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<HistModificadorCertificadoJPA> modificadoresCertificado;


    public Set<HistModificadorCertificadoJPA> getModificadoresCertificado() {
        return modificadoresCertificado;
    }

    public void setModificadoresCertificado(Set<HistModificadorCertificadoJPA> modificadoresCertificado) {
        this.modificadoresCertificado = modificadoresCertificado;
    }
}
