/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import commands.util.ConvertirAFechaUtil
import commands.util.MostrarResultadosUtil
import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository
import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.joda.time.DateTime

/**
 * Utilizada por la consola CRaSH para la administración del Modificador Valor Diamante
 *
 * @author mmarquez
 */
@SuppressWarnings("GroovyUnusedDeclaration")
@Usage("Administraci\u00f3n del Listado de Modificador Valor Diamante")
class modificadorValorDiamante {
    private static final List<String>  PROPIEDADES_FACTOR_VALOR_DIAMANTE =
        ["minimo", "medio", "maximo"]
    private static final List<String>  HEADERS = ["Minimo", "Medio", "Máximo"]

    /**
     * Permite obtener el Modificador Valor Diamante
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar el Listado de Modificador Valor Diamante vigente o de alguna fecha de vigencia espec\u00edfica")
    @Command
    def consultar(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar con formato yyyy-mm-dd")
                  @Option(names = ["f", "fecha"]) String fecha,
                  @Usage("Indica si el resultado se muestra en formato de lista")
                  @Option(names = ["l", "mostrarEnLista"]) Boolean mostrarEnLista) {
        DateTime fechaFormat = null

        if (fecha) {
            try {
                fechaFormat = ConvertirAFechaUtil.convertirAFecha(fecha).toDateTimeAtStartOfDay()
            } catch (IllegalArgumentException e) {
                out.println("${e.getMessage()}")
                return
            }
        }

        try {
            List<ModificadorValorDiamante> elementos = recuperarElementos(context, fechaFormat)
            mostrarResultados(elementos, mostrarEnLista)
        } catch (ValorComercialNoEncontradoException e) {
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
    private static List<ModificadorValorDiamante> recuperarElementos(InvocationContext context, DateTime fecha) {
        if (fecha) {
            getModificadorValorDiamanteRepository(context).consultar(fecha)
        } else {
            [getModificadorValorDiamanteRepository(context).consultar()]
        }
    }

    /**
     * Muestra los resultados de la consulta según el formato especificado.
     *
     * @param elementos Elementos a mostrar.
     * @param mostrarEnLista Indica el formato de salida.
     */
    @SuppressWarnings("GroovyAssignabilityCheck")
    private void mostrarResultados(List<ModificadorValorDiamante> elementos, Boolean mostrarEnLista) {
        Collections.sort(elementos, new Comparator<ModificadorValorDiamante>() {
            @Override
            int compare(ModificadorValorDiamante o1, ModificadorValorDiamante o2) {
                return o2.fechaCarga <=> o1.fechaCarga
            }
        })

        elementos.each {
            out.println("Fecha Vigencia: ${ConvertirAFechaUtil.convertirAString(it.fechaCarga)}", green)
            out.println(MostrarResultadosUtil
                .mostrarResultados(HEADERS, it.factor, PROPIEDADES_FACTOR_VALOR_DIAMANTE, mostrarEnLista))
        }
    }

    /**
     * Crea el mensaje cuando se presenta un error en la consulta del catálogo
     *
     * @param fecha Fecha de consulta.
     *
     * @return Mesaje de error.
     */
    private static def procesarMensajeError(DateTime fecha) {
        String msj

        if (fecha) {
            msj = "para la fecha solicitada."
        } else {
            msj = "vigente."
        }

        "No existe un Listado de Factores de Valor Diamante $msj"
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
