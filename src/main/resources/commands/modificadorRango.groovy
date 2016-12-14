/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoRangoFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhaja
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.FactorAlhajaFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoRango
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorRangoRepository
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.crsh.text.ui.Overflow
import org.crsh.text.ui.UIBuilder
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.springframework.util.ObjectUtils

/**
 * Utilizada por la consola CRaSH para la administración del modificador tipo rango
 *
 * @author roramirez
 */
@Usage("Administración del Modificador tipo Rango")
class modificadorTipoRango {

    /**
     * Permite obtener el valor del tipo Rango
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Factor Alhaja")
    @Command
    def actualizar(InvocationContext context, @Usage("contenido ") @Required @Argument String contenido) {
        ListadoRango listadoRango  = null
            if (ObjectUtils.isEmpty(contenido)) {
                out.println("Se requiere el contenido a procesar ")
            }else{
                try{
                 listadoRango = crearListado(contenido, context)
                }catch(Exception e){
                    e.printStackTrace()
                    out.println("No es posible crear la lista")
                }

                try{
                    getModificadorRangoRepository(context).actualizarListado(listadoRango)
                    out.println("Se actualizó correctamente")
                }catch(Exception e){
                    e.stackTrace
                    out.println("No se pudo crear la lista")
                }

            }
    }


    /**
     * Permite obtener el factor Alhaja vigente
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
                def elementos = getModificadorRangoRepository(context).consultarListadoPorFechaCarga(fechaFormat)
                mostrarTablaResultados(elementos)
            } catch (Exception e) {
                out.println("No existen resultados para la fecha: [${fecha}]")
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
                label('Rango')
                label('Factor')
                label('Desplazamiento')
                label('Limite Inferior')
                label('Limite Superior')
            }

            elementos.each { elemento ->
                elemento.factorAlhajas.each { rango ->
                    row {
                        label(rango.metal, foreground: white)
                        label(rango.calidad, foreground: white)
                        label(rango.rango, foreground: white)
                        label(rango.factor, foreground: white)
                        label(rango.desplazamiento, foreground: white)
                        label(rango.limiteInferior, foreground: white)
                        label(rango.limiteSuperior, foreground: white)
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
     * @return ListadoModificadorRango
     */
    private def crearListado(String contenido, InvocationContext context){

        BigDecimal factor
        FactorAlhaja factorAlhaja
        String metalRango
        String calidad
        String rango
        BigDecimal desplazamiento
        BigDecimal limiteInferior
        BigDecimal limiteSuperior
        def nMetal = "Metal"
        def nCalidad = "Calidad"
        def nRango = "Rango"
        def nFactor = "Factor"
        def nDesplazamiento = "Desplazamiento"
        def nLimiteInferior = "LimiteInferior"
        def nLimiteSuperior = "limiteSuperior"


        def dataList = []
        def infoTxt = [:]
        def factorAlhajaSet = new HashSet<>()

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
            out.println("Carga $index")
            it.each {k, v ->
                out.println("$k" : "$v")
                if(nMetal == k)
                    metalRango = v.toString()
                if(nCalidad == k)
                    calidad = v.toString()
                if(nRango == k)
                    rango = new BigDecimal(v.toString())
                if(nFactor == k)
                    factor = new BigDecimal(v.toString())
                if(nDesplazamiento == k)
                    desplazamiento = new BigDecimal(v.toString())
                if(nLimiteInferior == k)
                    limiteInferior = new BigDecimal(v.toString())
                if(nLimiteSuperior == k)
                    limiteSuperior = new BigDecimal(v.toString())
            }
            try{
                factorAlhaja = FactorAlhajaFactory.crear(metalRango, calidad, rango, factor, desplazamiento, limiteInferior, limiteSuperior, new DateTime())
                //out.println("Se creao el factor Alhaja")
            }catch(Exception e){
                out.println("No se pudo crear el Factor alhaja ")
                e.stackTrace
            }

            factorAlhajaSet.add(factorAlhaja)
            //out.println("El tamanio del set es : "+ factorAlhajaSet.size())
        }
        ListadoRango listadoRango = null
            try {
                listadoRango  = getListadoRangoFactory(context).crear(DateTime.now(), LocalDate.now(),factorAlhajaSet)
                //out.println("el tamaño del listado es : "+ listadoRango.getFactorAlhajas().size())
            }catch(Exception e){
                e.printStackTrace()
                out.println("No es posible crear el listadoFactory")
            }

        return listadoRango
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ModificadorRangoRepository
     */
    private static ModificadorRangoRepository getModificadorRangoRepository(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ModificadorRangoRepository)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase ListadoRangoFactory.
     */
    private static ListadoRangoFactory getListadoRangoFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ListadoRangoFactory)
    }

}
