/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

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
    @Usage("Permite actualizar el Valor del Oro mediante un archivo")
    @Command
    def actualizar(InvocationContext context, @Usage("URL donde se encuentra el archivo:") @Required @Argument String url) {

        if (ObjectUtils.isEmpty(url)) {
            out.println("Se requiere la url ")
        }else{
            File file = new File(url)
            if(!file.exists()){
                out.println("El archivo no existe")
            }else{
                ListadoValorComercialOro listadoValorComercialOro = crearListado(file, context)
                getListadoValorComercialOro(context).actualizar(listadoValorComercialOro)
                out.println("Se actualizo correctamente ")
            }
        }
    }

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar todos los elementos del catálogo")
    @Command
    def consultar(InvocationContext context, @Usage("Fecha de vigencia a consultar YYYY-dd-mm:") @Required @Argument String fecha) {

        if (ObjectUtils.isEmpty(fecha)) {
            out.println("Se requiere la fecha para consultar ")
        }else if(!fecha.matches(/\d{4}-\d{2}-\d{2}/)) {
            out.println("El formato de la fecha no es correcto debe de cumplir YYYY-dd-mm  ")
        }else
            try {
                LocalDate fechaFormat = new LocalDate(fecha)
                def elementos = getConsultaListado(context).consultarListadoPorFechaVigencia(fechaFormat)
                mostrarTablaResultados(elementos)
            } catch (Exception e) {
                out.println("La fecha: [${fecha}] no regresa resultados ")
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
                label('Color')
                label('Calidad')
                label('Precio')
            }

            elementos.each { elemento ->
                elemento.valoresComerciales.each { valorcomercial ->
                    row {
                        label(valorcomercial.color, foreground: green)
                        label(valorcomercial.calidad, foreground: red)
                        label(valorcomercial.precio, foreground: blue)
                    }
                }
            }
        }
    }

    /**
     * Utilizado para listas
     *
     * @param file archivp que se recibe
     * @param context El contexto de la invocación
     * @return ListadoValorcomercialOroFactory
     */
    private static def crearListado(File file, InvocationContext context){

        def lines = file.readLines()
        def oroSet = new HashSet<>()
        String color
        int calidad
        BigDecimal precio
        Oro oroNuevo

        for (line in lines) {
            def item = line.split(",")
            color = item[1]//color
            calidad = Integer.parseInt(item[2]) //calidad
            precio = new BigDecimal(item[3]) //precio
            oroNuevo = getOroFactory(context).create(color, calidad, precio)
            oroSet.add(oroNuevo)
        }

        return getListadoValorComercialOroFactory(context).create(oroSet)
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

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase ListadoValorComercialOro.
     */
   private static ListadoValorComercialOro getListadoValorComercialOro(InvocationContext context) {
       context.attributes['spring.beanfactory'].getBean(ListadoValorComercialOro)
   }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase ListadoValorComercialOroFactory.
     */
    private static ListadoValorComercialOroFactory getListadoValorComercialOroFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ListadoValorComercialOroFactory)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase OroFactory
     */
    private static OroFactory getOroFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(OroFactory)
    }

}
