/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import commands.util.ConvertirAFechaUtil
import commands.util.MostrarResultadosUtil
import commands.util.ReadObjecstFromString
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetal
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetalFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.MetalFactory
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository
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
@Usage("Administraci\u00f3n del Listado de Valor Comercial Metal")
class valorComercialMetal {
    private static final String METAL = "metal"
    private static final String CALIDAD = "calidad"
    private static final String PRECIO = "precio"
    private static final List<String>  PROPIEDADES_METAL = [METAL, CALIDAD, PRECIO]
    private static final List<String>  HEADERS = ["Metal", "Calidad", "Precio"]

    /**
     * Permite actualizar el valor comercial del Metal
     *
     * @param context El contexto de la invocación.
     * @param contenido Contenido que con el que se va actualizar
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Listado de Valor Comercial Metal")
    @Command
    def actualizar(InvocationContext context,
                   @Usage("Nuevo contenido del Listado de Valor Comercial Metal")
                   @Required @Argument  String contenido) {
        ListadoValorComercialMetal listadoValorComercialMetal

        try {
            ReadObjecstFromString rof = new ReadObjecstFromString(contenido, 2, PROPIEDADES_METAL);
            List<Map<String, String>> objects = rof.readObjects()
            listadoValorComercialMetal = crearListado(objects)
        } catch (IllegalArgumentException e) {
            out.println("${e.getMessage()}")
            return
        }

        try {
            getValorComercialMetalRepository(context).actualizarListado(listadoValorComercialMetal)
            out.println("El Listado Valor Comercial Metal fue actualizado correctamente.")
        }catch(Exception e) {
            e.printStackTrace()
            out.println("Ocurri\u00f3 un error inesperado al actualizar el Listado Valor Comercial Metal.")
        }
    }

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar el Listado de Valor Comercial Metal vigente o de alguna fecha de vigencia espec\u00edfica")
    @Command
    def consultar(InvocationContext context,
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
            def elementos = recuperarElementos(context, fechaFormat);
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
            Set<Metal> valoresComerciales = new HashSet<>()
            getValorComercialMetalRepository(context).consultarListadoPorFechaVigencia(fecha).each {
                valoresComerciales.addAll(it.valoresComerciales)
            }

            valoresComerciales
        } else {
            getValorComercialMetalRepository(context).consultarListadoVigente().valoresComerciales
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
        MostrarResultadosUtil.mostrarResultados(HEADERS, elementos, PROPIEDADES_METAL, mostrarEnLista)
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

        "No existe un Listado Valor Comercial Metal $msj"
    }

    /**
     * Utilizado para crear el listado
     *
     * @param objects Contenido a procesar
     *
     * @return Listado a actualizar
     */
    private static ListadoValorComercialMetal crearListado(List<Map<String, String>> objects) {
        Set<Metal> metalSet = new HashSet<>()

        objects.eachWithIndex { Map<String, String> entry, int i ->
            BigDecimal precio

            try {
                precio = entry[PRECIO].toBigDecimal()
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "El formato del $PRECIO [${entry[PRECIO]}] no es valido.\nEn $entry", e);
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("El precio del metal es una propiedad requerida.\nEn $entry", e);
            }

            try {
                Metal metal = MetalFactory.create(entry[METAL], entry[CALIDAD], precio)
                metalSet.add(metal)
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("El metal es una propiedad requerida.\nEn $entry", e);
            }
        }

        ListadoValorComercialMetalFactory.create(metalSet)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ValorComercialMetalRepository
     */
    private static ValorComercialMetalRepository getValorComercialMetalRepository(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ValorComercialMetalRepository)
    }

}
