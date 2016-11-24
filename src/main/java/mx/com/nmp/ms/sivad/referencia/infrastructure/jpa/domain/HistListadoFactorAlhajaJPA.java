/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad utilizada para representar los históricos de la lista de factores alhaja.
 *
 * @author mmarquez
 */
@Entity
@Table(name = "hist_cfg_alhaja_listado_factor")
public class HistListadoFactorAlhajaJPA extends AbstractListadoFactorAlhajaJPA {

    /**
     * Constructor.
     *
     * @param factoresAlhaja Lista de factores alhaja.
     */
    public HistListadoFactorAlhajaJPA(Set<FactorAlhajaJPA> factoresAlhaja) {
        super.setFactoresAlhaja(factoresAlhaja);
    }


}
