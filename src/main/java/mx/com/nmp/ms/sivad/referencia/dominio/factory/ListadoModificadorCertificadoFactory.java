/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.factory;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoModificadorCertificado;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoModificadorCertificado.
 *
 * @author mmarquez
 */
public interface ListadoModificadorCertificadoFactory {


    /**
     * Permite crear una entidad de tipo ListadoModificadorCertificado con base en los argumentos recibidos.
     *
     * @param fechaCarga Fecha en que se realiza la carga.
     * @param fechaListado Fecha en del listado.
     * @param certificados Lista de los certificados del diamante.
     * @return La entidad creada.
     */
    ListadoModificadorCertificado crear(DateTime fechaCarga, LocalDate fechaListado, Set<Certificado> certificados);

    /**
     * Permite crear una entidad de tipo ListadoModificadorCertificado con base en los argumentos recibidos y con la
     * inyección del repositorio de entidades para que se pueda persistir..
     *
     * @param fechaCarga Fecha en que se realiza la carga.
     * @param fechaListado Fecha en del listado.
     * @param certificados Lista de los certificados del diamante.
     * @return La entidad creada.
     */
    ListadoModificadorCertificado crearPersistible(DateTime fechaCarga, LocalDate fechaListado, Set<Certificado> certificados);

    /**
     * Crea una entidad a partir de un objeto constructor. a esta entidad le será inyectado
     * el repositorio de entidades para que se pueda persistir.
     *
     * @param builder Referencia al constructor.
     * @return La entidad creada.
     */
    ListadoModificadorCertificado crearPersistibleDesde(ListadoModificadorCertificado.Builder builder);

    /**
     * Crea una entidad a partir de un objeto constructor.
     *
     * @param builder Referencia al objeto constructor.
     *
     * @return La entidad creada.
     */
    ListadoModificadorCertificado crearDesde(ListadoModificadorCertificado.Builder builder);

}
