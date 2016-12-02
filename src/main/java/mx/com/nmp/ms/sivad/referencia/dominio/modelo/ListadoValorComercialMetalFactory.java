/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.arquetipo.journal.util.ApplicationContextProvider;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository;
import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorFecha;
import org.joda.time.DateTime;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Set;

/**
 * Fábrica que se encarga de crear objetos de tipo ListadoValorComercialMetal.
 *
 * @author ngonzalez
 */
public final class ListadoValorComercialMetalFactory {

    private static final String FECHA_ULTIMA_ACTUALIZACION_NULA = "La fecha de ultima actualizacion no debe ser nula.";
    private static final String FECHA_ULTIMA_ACTUALIZACION_FUTURA = "La fecha de ultima actualizacion no debe ser posterior a la fecha actual.";
    private static final String VALORES_COMERCIALES_NULOS = "La lista de valores comerciales no debe ser nula.";
    private static final String VALORES_COMERCIALES_VACIO = "La lista de valores comerciales no debe estar vacia.";

    /**
     * Referencia al repositorio de ValorComercialOroRepository.
     */
    private static ValorComercialMetalRepository repositorio;



    // METODOS

    /**
     * Permite crear una entidad de tipo ListadoValorComercialMetal con base en los argumentos recibidos.
     *
     * @param valoresComerciales Lista de valores comerciales de metales.
     * @return La entidad creada.
     */
    public static ListadoValorComercialMetal create(Set<Metal> valoresComerciales) {
        Assert.notNull(valoresComerciales, VALORES_COMERCIALES_NULOS);
        Assert.notEmpty(valoresComerciales, VALORES_COMERCIALES_VACIO);

        return new ListadoValorComercialMetal(valoresComerciales, repositorio);
    }

    /**
     * Permite crear una entidad de tipo ListadoValorComercialMetal con base en los argumentos recibidos.
     *
     * @param ultimaActualizacion Fecha en que se realiza la última actualización.
     * @param valoresComerciales Lista de valores comerciales de metales.
     * @return La entidad creada.
     */
    public static ListadoValorComercialMetal create(DateTime ultimaActualizacion, Set<Metal> valoresComerciales) {
        Assert.notNull(ultimaActualizacion, FECHA_ULTIMA_ACTUALIZACION_NULA);
        Assert.notNull(valoresComerciales, VALORES_COMERCIALES_NULOS);
        Assert.notEmpty(valoresComerciales, VALORES_COMERCIALES_VACIO);

        ValidadorFecha.validarFechaFutura(ultimaActualizacion, FECHA_ULTIMA_ACTUALIZACION_FUTURA);
        return new ListadoValorComercialMetal(ultimaActualizacion, valoresComerciales, repositorio);
    }



    // GETTERS

    public ValorComercialMetalRepository getRepositorio() {
        if (ObjectUtils.isEmpty(repositorio)) {
            repositorio = ApplicationContextProvider.get().getBean(ValorComercialMetalRepository.class);
        }

        return repositorio;
    }
}
