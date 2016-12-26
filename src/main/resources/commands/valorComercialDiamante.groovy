/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import commands.util.ConvertirAFechaUtil
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorComercialNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.crsh.text.ui.Overflow
import org.crsh.text.ui.UIBuilder
import org.joda.time.LocalDate
import org.springframework.util.ObjectUtils

/**
 * Utilizada por la consola CRaSH para la administración del valor comercial del diamante
 *
 * @author roramirez
 */
@Usage("Administraci\u00f3n del Listado de Valor Comercial Diamante")
class valorComercialDiamante {

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar el Listado de Valor Comercial Diamante vigente o de alguna fecha de vigencia espec\u00edfica")
    @Command
    def consultar(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar con formato yyyy-mm-dd")
                  @Option(names = ["f", "fecha"]) String fecha) {
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
            mostrarTablaResultados(elementos)
        } catch (ListadoValorComercialNoEncontradoException e) {
            e.printStackTrace()
            out.println("No existe un Listado de Valor Comercial Diamante para la fecha solicitada.")
        }
    }

    /**
     * Permite restaurar el listado de precios de diamantes anterior al listado vigente.
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite restaurar el Listado de Valor Comercial Diamante anterior al listado vigente")
    @Command
    def restaurarAnterior(InvocationContext context) {
        try {
            def elementos = getValorComercialDiamanteRepository(context).restaurarListadoAnterior()
            out.println("El Listado de Valor Comercial Diamante fue restaurado exitosamente a la fecha anterior.\n")
            mostrarTablaResultados(elementos)
        } catch (ListadoValorComercialNoEncontradoException e) {
            out.println("No existe un Listado de Valor Comercial Diamante con fecha anterior.")
            e.printStackTrace()
        } catch (Exception e) {
            out.println("Ocurri\u00f3 un error inesperado al restaurar el Listado de Valor Comercial Diamante a la fecha anterior.")
            e.printStackTrace()
        }
    }

    /**
     * Permite restaurar el listado de precios de diamantes de la fecha de vigencia indicada.
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite restaurar el Listado de Valor Comercial Diamante de la fecha de vigencia indicada")
    @Command
    def restaurarPorFecha(InvocationContext context,
                  @Usage("Fecha de vigencia a restaurar con formato yyyy-mm-dd")
                  @Required @Argument String fecha) {
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
            def elementos = getValorComercialDiamanteRepository(context).restaurarListadoPorFechaVigencia(fechaFormat)
            out.println("El Listado de Valor Comercial Diamante fue restaurado exitosamente a la fecha: [${fecha}].\n")
            mostrarTablaResultados(elementos)
        } catch (ListadoValorComercialNoEncontradoException e) {
            out.println("No existe un Listado de Valor Comercial Diamante con fecha: [${fecha}].")
            e.printStackTrace()
        } catch (Exception e) {
            out.println("Ocurri\u00f3 un error inesperado al restaurar el Listado de Valor Comercial Diamante a la fecha: [${fecha}].")
            e.printStackTrace()
        }
    }

    /**
     * Utilizado para representar los elementos del catálogo en un formato de tabla.
     *
     * @param elementos Lista de elementos del catálogo.
     * @return Despliegue visual en la consola con los elementos del catálogo.
     */
    private def mostrarTablaResultados(elementos) {
        new UIBuilder().table(separator: dashed, overflow: Overflow.HIDDEN, rightCellPadding: 1) {
            header(decoration: bold, foreground: black, background: white) {
                label('Corte')
                label('Color')
                label('Claridad')
                label('Tamanio Inferior')
                label('Tamanio Superior')
                label('Precio')
            }

            elementos.each { elemento ->
                elemento.valoresComerciales.each { diamante ->
                    row {
                        label(diamante.corte, foreground: white)
                        label(diamante.color, foreground: white)
                        label(diamante.claridad, foreground: white)
                        label(diamante.tamanioInferior, foreground: white)
                        label(diamante.tamanioSuperior, foreground: white)
                        label(diamante.precio, foreground: white)
                    }
                }
            }
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
            getValorComercialDiamanteRepository(context).consultarListadoPorFechaVigencia(fecha)
        } else {
            getValorComercialDiamanteRepository(context).consultarListadoVigente()
        }
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ValorComercialMetalRepository
     */
    private static ValorComercialDiamanteRepository getValorComercialDiamanteRepository(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ValorComercialDiamanteRepository)
    }

}
