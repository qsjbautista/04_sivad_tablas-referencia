/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import commands.util.ConvertirAFechaUtil
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorComercialNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante
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

/**
 * Utilizada por la consola CRaSH para la administración del valor comercial del diamante
 *
 * @author roramirez
 */
@SuppressWarnings("GroovyUnusedDeclaration")
@Usage("Administraci\u00f3n del Listado de Valor Comercial Diamante")
class valorComercialDiamante {

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Command
    @SuppressWarnings("GroovyAssignabilityCheck")
    @Usage("Permite recuperar el Listado de Valor Comercial Diamante vigente o de alguna fecha de vigencia espec\u00edfica")
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
            List<ListadoValorComercialDiamante> elementos = recuperarElementos(context, fechaFormat)
            mostrarResultados(elementos)
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
    void restaurarAnterior(InvocationContext context) {
        try {
            getValorComercialDiamanteRepository(context).restaurarListadoAnterior()
            out.println("El Listado de Valor Comercial Diamante fue restaurado exitosamente a la fecha anterior.\n")
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
    void restaurarPorFecha(InvocationContext context,
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
            getValorComercialDiamanteRepository(context).restaurarListadoPorFechaVigencia(fechaFormat)
            out.println("El Listado de Valor Comercial Diamante fue restaurado exitosamente a la fecha: [${fecha}].\n")
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
                label('Tipo Cambio')
                label('Montovbd')
                label('Montofcastigoxrango')
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
                        label(diamante.tipoCambio, foreground: white)
                        label(diamante.montoVbd, foreground: white)
                        label(diamante.montofCastigoxRango, foreground: white)
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
    private static List<ListadoValorComercialDiamante> recuperarElementos(InvocationContext context, LocalDate fecha) {
        if (fecha) {
            getValorComercialDiamanteRepository(context).consultarListadoPorFechaVigencia(fecha).collect()
        } else {
            [getValorComercialDiamanteRepository(context).consultarListadoVigente()]
        }
    }

    /**
     * Muestra los resultados de la consulta según el formato especificado.
     *
     * @param elementos Elementos a mostrar.
     */
    @SuppressWarnings("GroovyAssignabilityCheck")
    private void mostrarResultados(List<ListadoValorComercialDiamante> elementos) {
        Collections.sort(elementos, new Comparator<ListadoValorComercialDiamante>() {
            @Override
            int compare(ListadoValorComercialDiamante o1, ListadoValorComercialDiamante o2) {
                return o2.fechaCarga <=> o1.fechaCarga
            }
        })

        elementos.each {
            out.println("Fecha Vigencia: ${ConvertirAFechaUtil.convertirAString(it.fechaCarga)}", green)
            out.println(mostrarTablaResultados(it))
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
