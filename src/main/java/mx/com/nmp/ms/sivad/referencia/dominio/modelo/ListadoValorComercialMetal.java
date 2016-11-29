/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.Set;

/**
 * Entidad que representa la lista del valor comercial del metal.
 *
 * @author ngonzalez
 */
public class ListadoValorComercialMetal {

    /**
     * Fecha en que se realiza la última actualización.
     */
    private Date ultimaActualizacion;

    /**
     * Lista de valores comerciales de metales.
     */
    private Set<Metal> valoresComerciales;

    /**
     * Referencia al repositorio de ValorComercialMetalRepository.
     */
    @Inject
    private ValorComercialMetalRepository valorComercialMetalRepository;



    // METODOS

    /**
     * Constructor.
     *
     * @param valoresComerciales Lista de valores comerciales de metales.
     */
    ListadoValorComercialMetal(Set<Metal> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

    /**
     * Constructor.
     *
     * @param ultimaActualizacion Fecha en que se realiza la última actualización.
     * @param valoresComerciales Lista de valores comerciales de metales.
     */
    ListadoValorComercialMetal(Date ultimaActualizacion, Set<Metal> valoresComerciales) {
        this.ultimaActualizacion = ultimaActualizacion;
        this.valoresComerciales = valoresComerciales;
    }

    /**
     * Permite actualizar el listado de precios de metal.
     *
     * @param listado El listado de valores comerciales del metal, con el cual se desea reemplazar la lista vigente.
     */
    public void actualizar(ListadoValorComercialMetal listado) {
        valorComercialMetalRepository.actualizarListado(listado);
    }



    // GETTERS

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public Set<Metal> getValoresComerciales() {
        return valoresComerciales;
    }

}
