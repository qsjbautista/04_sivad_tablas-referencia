/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository;

import javax.inject.Inject;
import java.util.Date;
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
    private Date fechaCarga;

    /**
     * Fecha en que se realizo el listado.
     */
    private Date fechaListado;

    /**
     * Lista factores de alhaja.
     */
    private Set<FactorAlhaja> factorAlhajas;

    /**
     * Referencia al repositorio de ModificadorRangoRepository.
     */
    @Inject
    private ModificadorRangoRepository modificadorRangoRepository;



    // METODOS

    /**
     * Constructor.
     *
     * @param factorAlhajas Lista de factores alhaja.
     */
    ListadoRango(Set<FactorAlhaja> factorAlhajas) {
        this.factorAlhajas = factorAlhajas;
    }

    /**
     * Constructor.
     *
     * @param id Identificador del registro.
     * @param fechaCarga Fecha en que se realiza la carga.
     * @param fechaListado Fecha en del listado.
     * @param factorAlhajas Lista de factores alhaja.
     */
    ListadoRango(Long id, Date fechaCarga, Date fechaListado, Set<FactorAlhaja> factorAlhajas) {
        this.id = id;
        this.fechaCarga = fechaCarga;
        this.fechaListado = fechaListado;
        this.factorAlhajas = factorAlhajas;
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

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public Date getFechaListado() {
        return fechaListado;
    }

    public Set<FactorAlhaja> getFactorAlhajas() {
        return factorAlhajas;
    }

}
