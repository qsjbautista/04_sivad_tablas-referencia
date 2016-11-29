/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import java.util.Date;
import java.util.Set;

/**
 * Entidad que representa la lista del valor comercial del diamante.
 *
 * @author ngonzalez
 */
public class ListadoValorComercialDiamante {

    /**
     * Fecha en que se realiza la última actualización (fecha de vigencia).
     */
    private Date fechaCarga;

    /**
     * La fecha de origen de la información.
     */
    private Date fechaListado;

    /**
     * Lista de valores comerciales de diamantes.
     */
    private Set<Diamante> valoresComerciales;

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    @Inject
    private ValorComercialDiamanteRepository valorComercialDiamanteRepository;



    // METODOS

    /**
     * Constructor.
     *
     * @param valoresComerciales Lista de valores comerciales de diamantes.
     */
    ListadoValorComercialDiamante(Set<Diamante> valoresComerciales) {
        this.valoresComerciales = valoresComerciales;
    }

    /**
     * Constructor.
     *
     * @param fechaCarga Fecha en que se realiza la última actualización (fecha de vigencia).
     * @param fechaListado La fecha de origen de la información.
     * @param valoresComerciales Lista de valores comerciales de diamantes.
     */
    ListadoValorComercialDiamante(Date fechaCarga, Date fechaListado, Set<Diamante> valoresComerciales) {
        this.fechaCarga = fechaCarga;
        this.fechaListado = fechaListado;
        this.valoresComerciales = valoresComerciales;
    }

    /**
     * Permite actualizar el listado de precios de diamantes.
     *
     * @param listado El listado de valores comerciales del diamante, con el cual se desea reemplazar la lista vigente.
     */
    public void actualizar(ListadoValorComercialDiamante listado) {
        valorComercialDiamanteRepository.actualizarListado(listado);
    }

    /**
     * Permite restaurar el listado de precios de diamantes anterior al listado vigente.
     *
     * @return El nuevo listado restaurado.
     */
    public ListadoValorComercialDiamante restaurarAnterior() {
        return valorComercialDiamanteRepository.restaurarListadoAnterior();
    }

    /**
     * Permite restaurar el listado de precios de diamantes de la fecha de vigencia indicada.
     *
     * @param fechaVigencia La fecha de vigencia del listado que se desea restaurar.
     * @return El nuevo listado restaurado.
     */
    public ListadoValorComercialDiamante restaurarPorFecha(LocalDate fechaVigencia) {
        return valorComercialDiamanteRepository.restaurarListadoPorFechaVigencia(fechaVigencia);
    }



    // GETTERS

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public Date getFechaListado() {
        return fechaListado;
    }

    public Set<Diamante> getValoresComerciales() {
        return valoresComerciales;
    }

}
