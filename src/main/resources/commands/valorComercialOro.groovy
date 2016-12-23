/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import commands.util.ConvertirAFechaUtil
import commands.util.MostrarResultadosUtil
import commands.util.ReadObjecstFromString
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOroFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.joda.time.LocalDate

/**
 * Utilizada por la consola CRaSH para la administración del valor comercial del oro
 *
 * @author roramirez
 */
@Usage("Administración del Valor Comercial del Oro")
class valorComercialOro {
    private static final String COLOR = "color"
    private static final String CALIDAD = "calidad"
    private static final String PRECIO = "precio"
    private static final List<String>  PROPIEDADES_ORO = [COLOR, CALIDAD, PRECIO]
    private static final List<String>  HEADERS = ["Color", "Calidad", "Precio"]

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Valor del Oro")
    @Command
    def actualizar(InvocationContext context, @Usage("Contenido a procesar") @Required @Argument String contenido) {
        ListadoValorComercialOro  listadoValorComercialOro

        try {
            ReadObjecstFromString rof = new ReadObjecstFromString(contenido, 2, PROPIEDADES_ORO);
            List<Map<String, String>> objects = rof.readObjects()
            listadoValorComercialOro = crearListado(objects)
        } catch (IllegalArgumentException e) {
            out.println("${e.getMessage()}")
            return
        }

        try {
            getConsultaListado(context).actualizarListado(listadoValorComercialOro)
            out.println("El Listado Valor Comercial Oro fue actualizado correctamente.")
        }catch(Exception e) {
            e.printStackTrace()
            "Ocurrio un error inesperado al actualizar el Listado Valor Comercial Oro."
        }
    }


    /**
     * Permite obtener el valor Comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar todos los elementos del catálogo")
    @Command
    def consultar(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar yyyy-mm-dd") @Option(names = ["f", "fecha"]) String fecha,
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
            def elementos = recuperarElementos(context, fechaFormat)
            mostrarResultados(elementos, mostrarEnLista)
        } catch (ListadoValorGramoNoEncontradoException e) {
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
    private static def recuperarElementos(InvocationContext context, LocalDate fecha) {
        if (fecha) {
            Set<Oro> valoresComerciales = new HashSet<>()
            getConsultaListado(context).consultarListadoPorFechaVigencia(fecha).each {
                valoresComerciales.addAll(it.valoresComerciales)
            }

            valoresComerciales
        } else {
            getConsultaListado(context).consultarListadoVigente().valoresComerciales
        }
    }

    /**
     * Muestra los resultados de la consulta según el formato especificado.
     *
     * @param elementos Elementos a mostrar.
     * @param mostrarEnLista Indica el formato de salida.
     *
     * @return espliegue visual en la consola con los elementos del catálogo.
     */
    private static def mostrarResultados(def elementos, Boolean mostrarEnLista) {
        MostrarResultadosUtil.mostrarResultados(HEADERS, elementos, PROPIEDADES_ORO, mostrarEnLista)
    }

    /**
     * Crea el mensaje cuando se presenta un error en la consulta del catálogo
     *
     * @param fecha Fecha de consulta.
     *
     * @return Mesaje de error.
     */
    private static def procesarMensajeError(LocalDate fecha) {
        String msj

        if (fecha) {
            msj = "para la fecha solicitada."
        } else {
            msj = "vigente."
        }

        "No existe un Listado Valor Comercial Oro $msj"
    }

    /**
     * Utilizado para crear el listado
     *
     * @param objects Contenido a procesar
     *
     * @return Listado a actualizar
     */
    private static ListadoValorComercialOro crearListado(List<Map<String, String>> objects) {
        Set<Oro> oroSet = new HashSet<>()

        objects.eachWithIndex { Map<String, String> entry, int i ->
            BigDecimal precio

            try {
                precio = entry[PRECIO].toBigDecimal()
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "El formato del $PRECIO [${entry[PRECIO]}] no es valido.\nEn $entry", e);
            }

            Oro metal = OroFactory.create(entry[COLOR], entry[CALIDAD], precio)
            oroSet.add(metal)
        }

        ListadoValorComercialOroFactory.create(oroSet)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ValorComercialOroRepository
     */
    private static ValorComercialOroRepository getConsultaListado(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ValorComercialOroRepository)
    }


}
