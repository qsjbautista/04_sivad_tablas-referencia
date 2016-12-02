/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.Set;

/**
 * Entidad que representa la lista del valor comercial del oro.
 *
 * @author ngonzalez
 */
public class ListadoValorComercialOro {

    /**
     * Fecha en que se realiza la última actualización.
     */
    private DateTime ultimaActualizacion;

    /**
     * Lista de valores comerciales del oro.
     */
    private Set<Oro> valoresComerciales;

    /**
     * Referencia al repositorio de ValorComercialOroRepository.
     */
    private ValorComercialOroRepository valorComercialOroRepository;



    // METODOS

    /**
     * Constructor.
     *
     * @param valoresComerciales Lista de valores comerciales del oro.
     * @param valorComercialOroRepository Referencia al repositorio de ValorComercialOroRepository.
     */
    ListadoValorComercialOro(Set<Oro> valoresComerciales,
                             ValorComercialOroRepository valorComercialOroRepository) {
        this.valoresComerciales = valoresComerciales;
        this.valorComercialOroRepository = valorComercialOroRepository;
    }

    /**
     * Constructor.
     *
     * @param ultimaActualizacion Fecha en que se realiza la última actualización.
     * @param valoresComerciales Lista de valores comerciales del oro.
     * @param valorComercialOroRepository Referencia al repositorio de ValorComercialOroRepository.
     */
    ListadoValorComercialOro(DateTime ultimaActualizacion, Set<Oro> valoresComerciales,
                             ValorComercialOroRepository valorComercialOroRepository) {
        this.ultimaActualizacion = ultimaActualizacion;
        this.valoresComerciales = valoresComerciales;
        this.valorComercialOroRepository = valorComercialOroRepository;
    }

    /**
     * Permite actualizar el listado de precios de oro.
     */
    public void actualizar() {
        valorComercialOroRepository.actualizarListado(this);
    }



    // GETTERS

    public DateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public Set<Oro> getValoresComerciales() {
        return valoresComerciales;
    }

    @Override
    public String toString() {
        return "ListadoValorComercialOro{" +
            "ultimaActualizacion=" + ultimaActualizacion +
            ", valoresComerciales=" + valoresComerciales +
            '}';
    }

}
