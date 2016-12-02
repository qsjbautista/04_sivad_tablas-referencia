/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository;
import org.joda.time.DateTime;

import javax.inject.Inject;
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
    private DateTime ultimaActualizacion;

    /**
     * Lista de valores comerciales de metales.
     */
    private Set<Metal> valoresComerciales;

    /**
     * Referencia al repositorio de ValorComercialMetalRepository.
     */
    private ValorComercialMetalRepository valorComercialMetalRepository;



    // METODOS

    /**
     * Constructor.
     *
     * @param valoresComerciales Lista de valores comerciales de metales.
     * @param valorComercialMetalRepository Referencia al repositorio de ValorComercialMetalRepository.
     */
    ListadoValorComercialMetal(Set<Metal> valoresComerciales,
                               ValorComercialMetalRepository valorComercialMetalRepository) {
        this.valoresComerciales = valoresComerciales;
        this.valorComercialMetalRepository = valorComercialMetalRepository;
    }

    /**
     * Constructor.
     *
     * @param ultimaActualizacion Fecha en que se realiza la última actualización.
     * @param valoresComerciales Lista de valores comerciales de metales.
     * @param valorComercialMetalRepository Referencia al repositorio de ValorComercialMetalRepository.
     */
    ListadoValorComercialMetal(DateTime ultimaActualizacion, Set<Metal> valoresComerciales,
                               ValorComercialMetalRepository valorComercialMetalRepository) {
        this.ultimaActualizacion = ultimaActualizacion;
        this.valoresComerciales = valoresComerciales;
        this.valorComercialMetalRepository = valorComercialMetalRepository;
    }

    /**
     * Permite actualizar el listado de precios de metal.
     */
    public void actualizar() {
        valorComercialMetalRepository.actualizarListado(this);
    }



    // GETTERS

    public DateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public Set<Metal> getValoresComerciales() {
        return valoresComerciales;
    }

    @Override
    public String toString() {
        return "ListadoValorComercialMetal{" +
            "ultimaActualizacion=" + ultimaActualizacion +
            ", valoresComerciales=" + valoresComerciales +
            '}';
    }

}
