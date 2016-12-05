/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad utilizada para representar el valor comercial del oro.
 *
 * @author ngonzalez
 */
@Entity
@Table(name = "cfg_alhaja_valor_comercial_oro")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValorComercialOroJPA extends AbstractValorComercialOroJPA {



    // GETTERS Y SETTERS

    @Override
    public String toString() {
        return "ValorComercialOroJPA{" +
            "id=" + id +
            ", color='" + color + '\'' +
            ", calidad=" + calidad +
            ", precio=" + precio +
            '}';
    }

}
