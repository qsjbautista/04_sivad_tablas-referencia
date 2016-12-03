/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.arquetipo.journal.util.ApplicationContextProvider;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorFecha;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoValorComercialDiamante.
 *
 * @author ngonzalez
 */
public final class ListadoValorComercialDiamanteFactory {

    private static final String FECHA_CARGA_NULA = "La fecha de carga no debe ser nula.";
    private static final String FECHA_CARGA_FUTURA = "La fecha de carga no debe ser posterior a la fecha actual.";
    private static final String FECHA_LISTADO_NULA = "La fecha de listado no debe ser nula.";
    private static final String FECHA_LISTADO_FUTURA = "La fecha de listado no debe ser posterior a la fecha actual.";
    private static final String VALORES_COMERCIALES_NULOS = "La lista de valores comerciales no debe ser nula.";
    private static final String VALORES_COMERCIALES_VACIO = "La lista de valores comerciales no debe estar vacia.";

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    private static ValorComercialDiamanteRepository repositorio;



    // METODOS

    /**
     * Permite crear una entidad de tipo ListadoValorComercialDiamante con base en los argumentos recibidos.
     *
     * @param fechaListado La fecha de origen de la información.
     * @param valoresComerciales Lista de valores comerciales de diamantes.
     * @return La entidad creada.
     */
    public static ListadoValorComercialDiamante create(LocalDate fechaListado, Set<Diamante> valoresComerciales) {
        Assert.notNull(fechaListado, FECHA_LISTADO_NULA);
        Assert.notNull(valoresComerciales, VALORES_COMERCIALES_NULOS);
        Assert.notEmpty(valoresComerciales, VALORES_COMERCIALES_VACIO);

        ValidadorFecha.validarFechaFutura(fechaListado, FECHA_LISTADO_FUTURA);
        return new ListadoValorComercialDiamante(fechaListado, valoresComerciales, getRepositorio());
    }

    /**
     * Permite crear una entidad de tipo ListadoValorComercialDiamante con base en los argumentos recibidos.
     *
     * @param fechaCarga Fecha en que se realiza la última actualización (fecha de vigencia).
     * @param fechaListado La fecha de origen de la información.
     * @param valoresComerciales Lista de valores comerciales de diamantes.
     * @return La entidad creada.
     */
    public static ListadoValorComercialDiamante create(DateTime fechaCarga, LocalDate fechaListado, Set<Diamante> valoresComerciales) {
        Assert.notNull(fechaCarga, FECHA_CARGA_NULA);
        Assert.notNull(fechaListado, FECHA_LISTADO_NULA);
        Assert.notNull(valoresComerciales, VALORES_COMERCIALES_NULOS);
        Assert.notEmpty(valoresComerciales, VALORES_COMERCIALES_VACIO);

        ValidadorFecha.validarFechaFutura(fechaCarga, FECHA_CARGA_FUTURA);
        ValidadorFecha.validarFechaFutura(fechaListado, FECHA_LISTADO_FUTURA);
        return new ListadoValorComercialDiamante(fechaCarga, fechaListado, valoresComerciales, getRepositorio());
    }

    /**
     * Permite obtener la referencia al repositorio de ValorComercialDiamanteRepository.
     *
     * @return Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    private static ValorComercialDiamanteRepository getRepositorio() {
        if (ObjectUtils.isEmpty(repositorio)) {
            repositorio = ApplicationContextProvider.get().getBean(ValorComercialDiamanteRepository.class);
        }

        return repositorio;
    }

}
