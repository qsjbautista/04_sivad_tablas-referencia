/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import commands.util.ConvertirAFechaUtil
import commands.util.MostrarResultadosUtil
import commands.util.ReadObjecstFromString
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactorAlhajaNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoRangoFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhaja
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhajaFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoRango
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.TipoMetalEnum
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository
import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorCalidadMetal
import org.apache.commons.lang.StringUtils
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.joda.time.DateTime
import org.joda.time.LocalDate

/**
 * Utilizada por la consola CRaSH para la administración del modificador tipo rango
 *
 * @author roramirez
 */
@SuppressWarnings("GroovyUnusedDeclaration")
@Usage("Administraci\u00f3n del Listado de Modificador Tipo Rango")
class modificadorTipoRango {
    private static final String METAL = "metal"
    private static final String CALIDAD = "calidad"
    private static final String RANGO = "rango"
    private static final String FACTOR = "factor"
    private static final String DESPLAZAMIENTO = "desplazamiento"
    private static final String LIMITE_INFERIOR = "limite inferior"
    private static final String LIMITE_SUPERIOR = "limite superior"
    private static final List<String>  PROPIEDADES_RANGO =
        ["metal", "calidad", "rango", "factor", "desplazamiento", "limiteInferior", "limiteSuperior"]
    private static final List<String>  NOMBRE_PROPIEDADES_RANGO =
        [METAL, CALIDAD, RANGO, FACTOR, DESPLAZAMIENTO, LIMITE_INFERIOR, LIMITE_SUPERIOR]
    private static final List<String>  HEADERS =
        ["Metal", "Calidad", "Rango", "Factor", "Desplazamiento", "Limite Inferior", "Limite Superior"]

    /**
     * Permite obtener el valor del tipo Rango
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Listado de Modificador Tipo Rango")
    @Command
    void actualizar(InvocationContext context,
                   @Usage("Nuevo contenido del Listado de Modificador Tipo Rango")
                   @Required @Argument String contenido) {
        ListadoRango listadoRango

        try {
            ReadObjecstFromString rof = new ReadObjecstFromString(contenido, 6, NOMBRE_PROPIEDADES_RANGO)
            List<Map<String, String>> objects = rof.readObjects()
            listadoRango = crearListado(objects, context)
        } catch (IllegalArgumentException e) {
            out.println("${e.getMessage()}")
            return
        }

        try{
            getModificadorRangoRepository(context).actualizarListado(listadoRango)
            out.println("El Listado de Factores de Rango fue actualizado correctamente.")
        } catch(Exception e){
            e.printStackTrace()
            out.println("Ocurri\u00f3 un error inesperado al actualizar el Listado de Factores de Rango.")
        }
    }


    /**
     * Permite obtener el factor Alhaja vigente
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar el Listado de Modificador Tipo Rango vigente o de alguna fecha de vigencia espec\u00edfica")
    @Command
    void consultar(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar con formato yyyy-mm-dd")
                  @Option(names = ["f", "fecha"]) String fecha,
                  @Usage("Indica si el resultado se muestra en formato de lista")
                  @Option(names = ["l", "mostrarEnLista"]) Boolean mostrarEnLista) {
        LocalDate fechaFormat = null

        if (fecha) {
            try {
                fechaFormat = ConvertirAFechaUtil.convertirAFecha(fecha)
            } catch (IllegalArgumentException e) {
                out.println("${e.getMessage()}")
                return
            }
        }

        try {
            List<ListadoRango> elementos = recuperarElementos(context, fechaFormat)
            mostrarResultados(elementos, mostrarEnLista)
        } catch (FactorAlhajaNoEncontradoException e) {
            e.printStackTrace()
            procesarMensajeError(fechaFormat)
        }
    }

    /**
     * Recupera los elemtos del catálogo vigentes o por fecha especificada.
     *
     context El contexto de la invocación.
     * @param fecha Fecha de consulta.
     *
     * @return Lista de elemetos
     */
    private static List<ListadoRango> recuperarElementos(InvocationContext context, LocalDate fecha) {
        if (fecha) {
            getModificadorRangoRepository(context).consultarListadoPorFechaCarga(fecha).collect()
        } else {
            [getModificadorRangoRepository(context).consultarListadoVigente()]
        }
    }

    /**
     * Muestra los resultados de la consulta según el formato especificado.
     *
     * @param elementos Elementos a mostrar.
     * @param mostrarEnLista Indica el formato de salida.
     */
    @SuppressWarnings("GroovyAssignabilityCheck")
    private void mostrarResultados(List<ListadoRango> elementos, Boolean mostrarEnLista) {
        Collections.sort(elementos, new Comparator<ListadoRango>() {
            @Override
            int compare(ListadoRango o1, ListadoRango o2) {
                return o2.ultimaActualizacion <=> o1.ultimaActualizacion
            }
        })

        elementos.each {
            out.println("Fecha Vigencia: ${ConvertirAFechaUtil.convertirAString(it.ultimaActualizacion)}", green)
            out.println(MostrarResultadosUtil
                .mostrarResultados(HEADERS, it.factorAlhajas, PROPIEDADES_RANGO, mostrarEnLista))
        }
    }

    /**
     * Crea el mensaje cuando se presenta un error en la consulta del catálogo
     *
     * @param fecha Fecha de consulta.
     *
     * @return Mesaje de error.
     */
    private static String procesarMensajeError(LocalDate fecha) {
        String msj

        if (fecha) {
            msj = "para la fecha solicitada."
        } else {
            msj = "vigente."
        }

        "No existe un Listado de Factores de Rango $msj"
    }

    /**
     * Utilizado para crear el listado
     *
     * @param objects Contenido a procesar.
     * @param context El contexto de la invocación.
     *
     * @return Listado a actualizar
     */
    private static ListadoRango crearListado(List<Map<String, String>> objects, InvocationContext context) {
        Set<FactorAlhaja> factorAlhajaSet = new HashSet<>()

        objects.eachWithIndex { Map<String, String> entry, int i ->
            BigDecimal factor = convertirABigDecimal(entry[FACTOR], FACTOR, entry)
            BigDecimal desplazamiento = convertirABigDecimal(entry[DESPLAZAMIENTO], DESPLAZAMIENTO, entry)
            BigDecimal limiteInferior = convertirABigDecimal(entry[LIMITE_INFERIOR], LIMITE_INFERIOR, entry)
            BigDecimal limiteSuperior = convertirABigDecimal(entry[LIMITE_SUPERIOR], LIMITE_SUPERIOR, entry)

            if (!entry[METAL]) {
                throw new IllegalArgumentException(
                    "El $METAL del factor de rango es una propiedad requerida.\nEn $entry")
            }

            if (!ValidadorCalidadMetal.validar(entry[METAL], entry[CALIDAD])) {
                throw new IllegalArgumentException(
                    "La $CALIDAD es una propiedad requerida para el metal ${entry[METAL]}.\nEn $entry")
            }

            if (StringUtils.isNotBlank(entry[CALIDAD]) &&
                    !ValidadorCalidadMetal.validarMetalRequiereCalidad(entry[METAL])) {
                throw new IllegalArgumentException(
                    "El metal ${entry[METAL]}(${TipoMetalEnum.valueOf(entry[METAL]).getNombre()}) no debe contener " +
                        "la propiedad $CALIDAD.\nEn $entry")
            }

            if (!entry[RANGO]) {
                throw new IllegalArgumentException(
                    "El $RANGO del factor de rango es una propiedad requerida.\nEn $entry")
            }

            FactorAlhaja factorAlhaja = FactorAlhajaFactory.crear(entry[METAL], entry[CALIDAD], entry[RANGO], factor,
                desplazamiento, limiteInferior, limiteSuperior, DateTime.now())
            factorAlhajaSet.add(factorAlhaja)
        }

        getListadoRangoFactory(context).crear(DateTime.now(), LocalDate.now(),factorAlhajaSet)
    }

    private static BigDecimal convertirABigDecimal(String valor, String prop, Map<String, String> entry) {
        try {
            valor.toBigDecimal()
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "El formato del $prop [${entry[valor]}] no es valido.\nEn $entry", e)
        } catch(NullPointerException e) {
            throw new IllegalArgumentException("El $prop del factor de rango es una propiedad requerida.\nEn $entry", e)
        }
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ModificadorRangoRepository
     */
    private static ModificadorRangoRepository getModificadorRangoRepository(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ModificadorRangoRepository)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase ListadoRangoFactory.
     */
    private static ListadoRangoFactory getListadoRangoFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ListadoRangoFactory)
    }

}
