package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import mx.com.nmp.ms.arquetipo.annotation.journal.JournalData;
import mx.com.nmp.ms.arquetipo.journal.listener.JournalEntityListener;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad que representa la tabla ModificadorCertificadoJPA.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "cfg_diamante_listado_valor_certificado")
public class ListadoModificadorCertificadoJPA extends AbstractListadoModificadorCertificadoJPA {

}
