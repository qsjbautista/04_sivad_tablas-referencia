package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad que representa la tabla ModificadorCertificadoJPA.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "cfg_diamante_valor_certificado")
public class ModificadorCertificadoJPA extends AbstractModificadorCertificadoJPA {

}
