/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import commands.util.ConvertirAFechaUtil
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.crsh.text.ui.Overflow
import org.crsh.text.ui.UIBuilder
import org.joda.time.LocalDate

/**
 * Utilizada por la consola CRaSH para la administración del Modificador Valor Diamante
 *
 * @author mmarquez
 */
@Usage("Administración del Modificador Valor Diamante")
class modificadorValorDiamante {
    /**
     * Permite obtener el Modificador Valor Diamante
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar los Modificador Valor Diamante vigentes de la fecha dada")
    @Command
    def consultar(InvocationContext context, @Usage("Fecha de vigencia a consultar YYYY-mm-dd") @Required @Argument String fecha) {
        LocalDate fechaFormat

        try {
            fechaFormat = ConvertirAFechaUtil.convertirAFecha(fecha)
        } catch (IllegalArgumentException e) {
            out.println("${e.getMessage()}")
            return
        }

        try {
            def elementos = getModificadorValorDiamanteRepository(context).consultar(fechaFormat.toDateTimeAtStartOfDay())
            mostrarTablaResultados(elementos)
        } catch (ValorComercialNoEncontradoException e) {
            e.printStackTrace()
            "No existe un Listado de Factores de Valor Diamante para la fecha solicitada."
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
                label('Minimo')
                label('Medio')
                label('Máximo')
            }

            elementos.each { elemento ->
                row {
                    label(elemento.factor.minimo, foreground: green)
                    label(elemento.factor.medio, foreground: red)
                    label(elemento.factor.maximo, foreground: blue)
                }
            }
        }
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ModificadorValorDiamanteRepository
     */
    private static ModificadorValorDiamanteRepository getModificadorValorDiamanteRepository(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ModificadorValorDiamanteRepository)
    }
}
