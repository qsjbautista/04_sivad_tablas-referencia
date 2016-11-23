package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import mx.com.nmp.ms.arquetipo.annotation.journal.JournalData;
import mx.com.nmp.ms.arquetipo.journal.listener.JournalEntityListener;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entidad que representa la tabla ModificadorCertificadoJPA.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "cfg_diamante_valor_certificado")
public class ModificadorCertificadoJPA extends AbstractModificadorCertificadoJPA {


}
