/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoModificadorCertificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.CertificadoVO;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * Utilizado el acceso a datos de modificadores de certificados de diamantes.
 *
 * @author mmarquez
 */
public interface ModificadorCertificadoRepository {

    /**
     * Permite obtener la entidad certificado, que corresponda a las características indicadas en las propiedades
     * del parámetro.
     *
     * @param certificado Entidad con las características de búsqueda.
     * @return La entidad obtenida.
     */
    Certificado consultarModificadorCertificadoVigente(CertificadoVO certificado);

    /**
     * Permite obtener la entidad ListadoModificadorCertificado vigente.
     *
     * @return La entidad listado obtenida.
     */
    ListadoModificadorCertificado consultarListadoVigente();

    /**
     * Permite obtener la lista de entidades ListadoModificadorCertificado que correspondan a la fecha de carga indicada.
     *
     * @param ultimaActualizacion La fecha de carga.
     * @return La lista de entidades obtenidas.
     */
    Set<ListadoModificadorCertificado> consultarListadoPorUltimaActualizacion(LocalDate ultimaActualizacion);

    /**
     * Permite actualizar el listado vigente con la información del listado que se recibe como parámetro.
     *
     * @param listado El nuevo listado.
     */
    ListadoModificadorCertificado actualizarListado(ListadoModificadorCertificado listado);

}
