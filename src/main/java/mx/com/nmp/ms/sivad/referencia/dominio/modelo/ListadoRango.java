/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * Entidad que representa la lista factor alhaja.
 *
 * @author mmarquez
 */
public class ListadoRango {

    /**
     * Identificador del registro.
     */
    private Long id;

    /**
     * Fecha en que se realizo la carga.
     */
    private DateTime ultimaActualizacion;

    /**
     * Fecha en que se realizo el listado.
     */
    private LocalDate fechaListado;

    /**
     * Lista factores de alhaja.
     */
    private Set<FactorAlhaja> factorAlhajas;

    /**
     * Referencia al repositorio de ModificadorRangoRepository.
     */
    private ModificadorRangoRepository modificadorRangoRepository;

    /**
     * Interface que define el contrato para crear entidades ListadoRango
     */
    public interface Builder {
        DateTime getUltimaActualizacion();

        LocalDate getFechaListado();

        Set<FactorAlhaja> getFactorAlhaja();
    }


    /**
     * Constructor. Privado para que solo la fabrica tenga acceso a crear objetos.
     *
     * @param builder Referencia al objeto que contiene los datos necesarios para construir la entidad.
     * @param modificadorRangoRepository Referencia al repositorio de entidades.
     */
    ListadoRango(Builder builder, ModificadorRangoRepository modificadorRangoRepository) {
        super();

        ultimaActualizacion = builder.getUltimaActualizacion();
        fechaListado = builder.getFechaListado();
        factorAlhajas = builder.getFactorAlhaja();

        this.modificadorRangoRepository = modificadorRangoRepository;
    }

    /**
     * Permite actualizar el listado factores de la alhaja.
     *
     * @param listado Lista de los factores alhaja, con el cual se desea reemplazar la lista vigente.
     */
    public void actualizar(ListadoRango listado) {
        modificadorRangoRepository.actualizarListado(listado);
    }



    // GETTERS


    public Long getId() {
        return id;
    }

    public DateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public LocalDate getFechaListado() {
        return fechaListado;
    }

    public Set<FactorAlhaja> getFactorAlhajas() {
        return factorAlhajas;
    }

}
