package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad que representa la tabla ModificadorCertificadoJPA.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "cfg_diamante_listado_valor_certificado")
public class ListadoModificadorCertificadoJPA extends AbstractListadoModificadorJPA {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listado")
    private Set<ModificadorCertificadoJPA> modificadoresCertificado;

    public Set<ModificadorCertificadoJPA> getModificadoresCertificado() {
        return modificadoresCertificado;
    }

    public void setModificadoresCertificado(Set<ModificadorCertificadoJPA> modificadoresCertificado) {
        this.modificadoresCertificado = modificadoresCertificado;
    }
}
