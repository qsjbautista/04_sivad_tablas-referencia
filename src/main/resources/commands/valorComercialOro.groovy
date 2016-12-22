/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.exception.ListadoValorGramoNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOro
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialOroFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Oro
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.OroFactory
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialOroRepository
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
 * Utilizada por la consola CRaSH para la administración del valor comercial del oro
 *
 * @author roramirez
 */
@Usage("Administración del Valor Comercial del Oro")
class valorComercialOro {

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Valor del Oro")
    @Command
    def actualizar(InvocationContext context, @Usage("Contenido a procesar:") @Required @Argument String contenido) {
        ListadoValorComercialOro  listadoValorComercialOro= null
        if (ObjectUtils.isEmpty(contenido)) {
            out.println("Se requiere el contenido a procesar")
        }else{
                try {
                    listadoValorComercialOro = crearListado(contenido)
                }catch(Exception e){
                    e.stackTrace
                    out.println("No es posible crear listado ")
                }

                getConsultaListado(context).actualizarListado(listadoValorComercialOro)
                out.println("El Listado Valor Comercial Oro fue actualizado correctamente.")
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
    def consultar(InvocationContext context, @Usage("Fecha de vigencia a consultar yyyy-mm-dd:") @Required @Argument String fecha) {
        LocalDate fechaFormat

        try {
            fechaFormat = ConvertirAFechaUtil.convertirAFecha(fecha)
        } catch (IllegalArgumentException e) {
            out.println("${e.getMessage()}")
            return
        }

        try {
            def elementos = getConsultaListado(context).consultarListadoPorFechaVigencia(fechaFormat)
            mostrarTablaResultados(elementos)
        } catch (ListadoValorGramoNoEncontradoException e) {
            e.printStackTrace()
            "No existe un Listado Valor Comercial Oro para la fecha solicitada."
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
                label('Color')
                label('Calidad')
                label('Precio')
            }

            elementos.each { elemento ->
                elemento.valoresComerciales.each { valorcomercial ->
                    row {
                        label(valorcomercial.color, foreground: white)
                        label(valorcomercial.calidad, foreground: white)
                        label(valorcomercial.precio, foreground: white)
                    }
                }
            }
        }
    }

    /**
     * Utilizado para listas
     *
     * @param contenido Contenido a procesar
     * @param context El contexto de la invocación
     * @return ListadoValorcomercialOroFactory
     */
    private def crearListado(String contenido){

        String color
        String calidad
        BigDecimal precio
        Oro oroNuevo
        def dataList = []
        def infoTxt = [:]
        def oroSet = new HashSet<>()
        def c = "Color"
        def ca = "Calidad"
        def p = "Precio"

        contenido.eachLine { line ->
            if (line.trim()) {
                def (key, value) = line.split(':').collect() { it.trim() }
                infoTxt."$key" = value
            } else {
                if (infoTxt) {
                    dataList << infoTxt
                    infoTxt = [:]
                }
            }

        }

        if(infoTxt){
            dataList << infoTxt
        }

        dataList.eachWithIndex{ it , index ->
            //out.println("Carga $index")
            it.each {k, v ->
                out.println("$k" : "$v")
                if(c == k)
                    color = v.toString()
                if(ca == k)
                    calidad = v.toString()
                if(p == k)
                    precio = new BigDecimal(v.toString())
            }
            try{
                oroNuevo = OroFactory.create(color, calidad, precio)
            }catch(Exception e){
                out.println("No se pudo crear el oro" )
                e.stackTrace
            }
            oroSet.add(oroNuevo)
        }

        ListadoValorComercialOro listadoValorComercialOro = null
        try {
            //out.println("La Lista tiene un tamanio de: " + oroSet.size()+ " elementos")
            listadoValorComercialOro = ListadoValorComercialOroFactory.create(oroSet)
            //out.println("la lista tiene " + listadoValorComercialOro.getValoresComerciales().size())
        }catch(Exception e){
            e.stackTrace
        }

        return listadoValorComercialOro
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
