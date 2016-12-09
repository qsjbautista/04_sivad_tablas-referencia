/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamanteFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetal
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialMetalFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Metal
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.MetalFactory
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialMetalRepository
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
@Usage("Administración del Valor Comercial del Metal")
class valorComercialMetal {

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Valor del Oro mediante un archivo")
    @Command
    def actualizar(InvocationContext context, @Usage("Contenido a procesar:") @Required @Argument  String contenido) {
        ListadoValorComercialMetal listadoValorComercialMetal = null
        if (ObjectUtils.isEmpty(contenido)) {
            out.println("Se requiere ell contenido")
        } else {
            try {
                listadoValorComercialMetal = crearListado(contenido)
            } catch (Exception e){
                 e.printStackTrace()
                out.println("no se creo e listdado hubi un error ")
            }

                try {
                    getValorComercialMetalRepository(context).actualizarListado(listadoValorComercialMetal)
                    out.println("Se actualizo correctamente ")
                }catch(Exception e) {
                    e.printStackTrace()
                    out.println("no se pudo actualizar ")
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
    def consultar(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar YYYY-dd-mm:") @Required @Argument String fecha) {

        if (ObjectUtils.isEmpty(fecha)) {
            out.println("Se requiere la fecha para consultar ")
        } else if (!fecha.matches(/\d{4}-\d{2}-\d{2}/)) {
            out.println(/El formato de la fecha no es correcto debe de cumplir YYYY-dd-mm  /)
        } else
            try {
                LocalDate fechaFormat = new LocalDate(fecha)
                def elementos = getValorComercialMetalRepository(context).consultarListadoPorFechaVigencia(fechaFormat)
                mostrarTablaResultados(elementos)
            } catch (Exception e) {
                out.println("No hay resultados para la fecha: [${fecha}]")
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
                label('Metal')
                label('Calidad')
                label('Precio')
            }

            elementos.each { elemento ->
                elemento.valoresComerciales.each { metal ->
                    row {
                        label(metal.metal, foreground: white)
                        label(metal.calidad, foreground: white)
                        label(metal.precio, foreground: white)
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
     * @return ListadoValorcomercialMetalFactory
     */
    private def crearListado(String contenido) {

        String calidad
        BigDecimal precio
        Metal metal
        String metalS
        def dataList = []
        def infoTxt = [:]
        def metalSet = new HashSet<>()
        def m = "Metal"
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

        if (infoTxt) {
            dataList << infoTxt
        }


        dataList.eachWithIndex { it, index ->
            out.println("Carga $index")
            it.each { k, v ->
                out.println("$k": "$v")
                if (m == k)
                    metalS = v.toString()
                if (ca == k)
                    calidad = v.toString()
                if (p == k)
                    precio = new BigDecimal(v.toString())
            }
            try {

                metal = MetalFactory.create(metalS, calidad, precio)
                out.println(metal.toString())
            } catch (Exception e) {
                out.println("No se pudo crear el Metal")
                e.printStackTrace()
            }
            metalSet.add(metal)
            out.println(metalSet.size())
        }


        ListadoValorComercialMetal listadoValorComercialMetal = null
            try {
                listadoValorComercialMetal = ListadoValorComercialMetalFactory.create(metalSet)
            }catch(Exception e){
                e.printStackTrace()
            }

        return listadoValorComercialMetal
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

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase ListadoValorComercialMetal.
     */
    private static ListadoValorComercialMetal getListadoValorComercialMetal(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ListadoValorComercialMetal)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase ListadoModificadorCertificadoFactory.
     */
    private static ListadoValorComercialMetalFactory getListadoValorComercialMetalFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ListadoValorComercialMetalFactory)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase MetalFactory
     */
    private static MetalFactory getMetalFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(MetalFactory)
    }

}
