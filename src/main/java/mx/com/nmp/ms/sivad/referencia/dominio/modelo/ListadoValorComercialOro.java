/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.Set;

/**
 * Entidad que representa la lista del valor comercial del oro.
 *
 * @author ngonzalez
 */
public class ListadoValorComercialOro {

    /**
     * Identificador del registro.
     */
    private Long id;

    /**
     * Fecha en que se realiza la última actualización.
     */
    private Date ultimaActualizacion;

    /**
     * Lista de valores comerciales del oro.
     */
    private Set<Oro> valoresComerciales;

    /**
     * Referencia al repositorio de ValorComercialOroRepository.
     */
    @Inject
    private ValorComercialOroRepository valorComercialOroRepository;



    // METODOS

    /**
     * Constructor.
     *
     * @param valoresComerciales Lista de valores comerciales del oro.
     */
    ListadoValorComercialOro(Set<Oro> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

    /**
     * Constructor.
     *
     * @param id Identificador del registro.
     * @param ultimaActualizacion Fecha en que se realiza la última actualización.
     * @param valoresComerciales Lista de valores comerciales del oro.
     */
    ListadoValorComercialOro(Long id, Date ultimaActualizacion, Set<Oro> valoresComerciales) {
        this.id = id;
        this.ultimaActualizacion = ultimaActualizacion;
        this.valoresComerciales = valoresComerciales;
    }

    /**
     * Permite actualizar el listado de precios de oro.
     *
     * @param listado El listado de valores comerciales del oro, con el cual se desea reemplazar la lista vigente.
     */
    public void actualizar(ListadoValorComercialOro listado) {
        valorComercialOroRepository.actualizarListado(listado);
    }



    // GETTERS

    public Long getId() {
        return id;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public Set<Oro> getValoresComerciales() {
        return valoresComerciales;
    }

}
