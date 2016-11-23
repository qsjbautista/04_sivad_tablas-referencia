/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad utilizada para representar los históricos de la lista de Modificadores Certificados.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "hist_cfg_diamante_listado_valor_certificado")
public class HistListadoModificadorCertificadoJPA extends AbstractListadoModificadorCertificadoJPA {

    /**
     * Constructor.
     *
     * @param modificadorCertificado Lista de modificadores certificados.
     */
    public HistListadoModificadorCertificadoJPA(Set<ModificadorCertificadoJPA> modificadorCertificado) {
        super.setModificadoresCertificado(modificadorCertificado);
    }


}
