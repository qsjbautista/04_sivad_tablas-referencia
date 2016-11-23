/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.util.Date;
import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoModificadorCertificado.
 *
 * @author mmarquez
 */
public class ListadoModificadorCertificadoFactory {

    /**
     * Permite crear una entidad de tipo ListadoModificadorCertificado con base en los argumentos recibidos.
     *
     * @param certificados Lista de los certificados.
     * @return La entidad creada.
     */
    public static ListadoModificadorCertificado create(Set<Certificado> certificados) {
        return new ListadoModificadorCertificado(certificados);
    }

    /**
     * Permite crear una entidad de tipo ListadoModificadorCertificado con base en los argumentos recibidos.
     *
     * @param id Identificador del registro.
     * @param fechaCarga Fecha en que se realiza la carga.
     * @param fechaListado Fecha en del listado.
     * @param certificados Lista de los certificados del diamante.
     */
    public static ListadoModificadorCertificado create(Long id, Date fechaCarga, Date fechaListado, Set<Certificado> certificados) {
        return new ListadoModificadorCertificado(id, fechaCarga, fechaListado, certificados);
    }

}
