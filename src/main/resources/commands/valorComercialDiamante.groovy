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
@Usage("Administración del Valor Comercial del Diamante")
class valorComercialDiamante {

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar todos los elementos del catálogo")
    @Command
    def consultar(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar yyyy-mm-dd") @Required @Argument String fecha) {
        LocalDate fechaFormat

        try {
            fechaFormat = ConvertirAFechaUtil.convertirAFecha(fecha)
        } catch (IllegalArgumentException e) {
            out.println("${e.getMessage()}")
            return
        }

        try {
            def elementos = getValorComercialDiamanteRepository(context).consultarListadoPorFechaVigencia(fechaFormat)
            mostrarTablaResultados(elementos)
        } catch (ListadoValorComercialNoEncontradoException e) {
            e.printStackTrace()
            "No existe un Listado de Valor Comercial Diamante para la fecha solicitada."
        }
    }

    /**
     * Permite restaurar el listado de precios de diamantes anterior al listado vigente.
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite restaurar el listado de precios de diamantes anterior al listado vigente.")
    @Command
    def restaurarAnterior(InvocationContext context) {
        try {
            def elementos = getValorComercialDiamanteRepository(context).restaurarListadoAnterior()
            out.println("El Listado de Valor Comercial Diamante fue restaurado exitosamente a la fecha anterior.\n")
            mostrarTablaResultados(elementos)
        } catch (Exception e) {
            out.println("No es posible restaurar el Listado de Valor Comercial Diamante a la fecha anterior.")
            e.printStackTrace()
        }
    }

    /**
     * Permite restaurar el listado de precios de diamantes de la fecha de vigencia indicada.
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite restaurar el listado de precios de diamantes de la fecha de vigencia indicada.")
    @Command
    def restaurarPorFecha(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar yyyy-mm-dd:") @Required @Argument String fecha) {

        if (ObjectUtils.isEmpty(fecha)) {
            out.println("Se requiere la fecha para consultar ")
        } else if (!fecha.matches(/\d{4}-\d{2}-\d{2}/)) {
            out.println(/El formato de la fecha no es correcto debe de cumplir yyyy-mm-dd  /)
        } else
            try {
                LocalDate fechaFormat = new LocalDate(fecha)
                def elementos = getValorComercialDiamanteRepository(context).restaurarListadoPorFechaVigencia(fechaFormat)
                out.println("El Listado de Valor Comercial Diamante fue restaurado exitosamente a la fecha: [${fecha}].\n")
                mostrarTablaResultados(elementos)
            } catch (Exception e) {
                out.println("No es posible restaurar el Listado de Valor Comercial Diamante a la fecha: [${fecha}].")
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
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ValorComercialMetalRepository
     */
    private static ValorComercialDiamanteRepository getValorComercialDiamanteRepository(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ValorComercialDiamanteRepository)
    }



}
