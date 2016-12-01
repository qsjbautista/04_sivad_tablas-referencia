/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.factory;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhaja;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoRango;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoModificadorCertificado.
 *
 * @author mmarquez
 */
public interface ListadoRangoFactory {


    /**
     * Permite crear una entidad de tipo ListadoRango con base en los argumentos recibidos.
     *
     * @param ultimaActualizacion Fecha en que se realiza la carga.
     * @param fechaListado Fecha en del listado.
     * @param factorAlhajas Lista de los certificados del diamante.
     * @return La entidad creada.
     */
    ListadoRango crear(DateTime ultimaActualizacion, LocalDate fechaListado, Set<FactorAlhaja> factorAlhajas);

    /**
     * Permite crear una entidad de tipo ListadoRango con base en los argumentos recibidos.
     *
     * @param factorAlhajas Lista de los certificados del diamante.
     * @return La entidad creada.
     */
    ListadoRango crear(Set<FactorAlhaja> factorAlhajas);

    /**
     * Permite crear una entidad de tipo ListadoRango con base en los argumentos recibidos y con la
     * inyección del repositorio de entidades para que se pueda persistir..
     *
     * @param ultimaActualizacion Fecha en que se realiza la carga.
     * @param fechaListado Fecha en del listado.
     * @param factorAlhajas Lista de los certificados del diamante.
     * @return La entidad creada.
     */
    ListadoRango crearPersistible(DateTime ultimaActualizacion, LocalDate fechaListado, Set<FactorAlhaja> factorAlhajas);

    /**
     * Crea una entidad a partir de un objeto constructor. a esta entidad le será inyectado
     * el repositorio de entidades para que se pueda persistir.
     *
     * @param builder Referencia al constructor.
     * @return La entidad creada.
     */
    ListadoRango crearPersistibleDesde(ListadoRango.Builder builder);

    /**
     * Crea una entidad a partir de un objeto constructor.
     *
     * @param builder Referencia al objeto constructor.
     *
     * @return La entidad creada.
     */
    ListadoRango crearDesde(ListadoRango.Builder builder);

}
