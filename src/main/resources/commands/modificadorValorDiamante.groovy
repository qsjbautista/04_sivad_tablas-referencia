/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository
import mx.com.nmp.ms.sivad.referencia.infrastructure.factory.FactorValorDiamanteFactoryImpl
import mx.com.nmp.ms.sivad.referencia.infrastructure.factory.ModificadorValorDiamanteFactoryImpl
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.crsh.text.ui.Overflow
import org.crsh.text.ui.UIBuilder
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.springframework.util.ObjectUtils

/**
 * Utilizada por la consola CRaSH para la administración del Modificador Valor Diamante
 *
 * @author mmarquez
 */
@Usage("Administración del Modificador Valor Diamante")
class modificadorValorDiamante {

    /**
     * Permite actualizar el Modificador Valor Diamante mediante un archivo
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Modificador Valor Diamante mediante un archivo. Factor Minimo: ? Factor Medio: ? Factor Maximo: ?")
    @Command
    def actualizar(InvocationContext context, @Usage("Contenido a procesar:") @Required @Argument  String contenido) {
        ModificadorValorDiamante modificadorValorDiamante = null
        if (ObjectUtils.isEmpty(contenido)) {
            out.println("Se requiere el contenido")
        } else {
            try {
                modificadorValorDiamante = crearModificador(contenido)
                getModificadorValorDiamanteRepository(context).actualizar(modificadorValorDiamante)
                out.println("Se actualizo correctamente ")
            }catch(Exception e) {
                e.printStackTrace()
                out.println("no se pudo actualizar ")
            }
        }
    }


    /**
     * Permite obtener el Modificador Valor Diamante
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar los Modificador Valor Diamante vigentes de la fecha dada")
    @Command
    def consultar(InvocationContext context, @Usage("Fecha de vigencia a consultar YYYY-mm-dd:") @Required @Argument String fecha) {

        if (ObjectUtils.isEmpty(fecha)) {
            out.println("Se requiere la fecha para consultar ")
        }else if(!fecha.matches(/\d{4}-\d{2}-\d{2}/)) {
            out.println("El formato de la fecha no es correcto debe de cumplir YYYY-mm-dd  ")
        }else
            try {
                LocalDate fechaFormat = new LocalDate(fecha)
                out.println("Fecha"+ fechaFormat.toDateTimeAtStartOfDay().toString())
                def elementos = getModificadorValorDiamanteRepository(context).consultar(fechaFormat.toDateTimeAtStartOfDay())
                out.println("elementos"+elementos.toString())
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
 * Utilizado para listas
 *
 * @param file archivp que se recibe
 * @param context El contexto de la invocación
 * @return ListadoValorcomercialMetalFactory
 */
    private def crearModificador(String contenido) {

        BigDecimal factorMinimo
        BigDecimal factorMedio
        BigDecimal factorMaximo
        FactorValorDiamante factorValorDiamante;
        ModificadorValorDiamante modificadorValorDiamante
        def dataList = []
        def infoTxt = [:]
        def fMin = "Factor Minimo"
        def fMed = "Factor Medio"
        def fMax = "Factor Maximo"

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
                if (fMin == k)
                    factorMinimo = new BigDecimal(v.toString())
                if (fMed == k)
                    factorMedio = new BigDecimal(v.toString())
                if (fMax == k)
                    factorMaximo = new BigDecimal(v.toString())
            }
            try {
                factorValorDiamante = getFactorValorDiamanteFactory(context).crearCon(factorMinimo, factorMedio, factorMaximo)
                modificadorValorDiamante = getModificadorValorDiamanteFactory(context).crearCon(DateTime.now(), LocalDate.now(),
                    factorValorDiamante)
            } catch (Exception e) {
                out.println("No se pudo crear el Modificador Valor del Diamante")
                e.printStackTrace()
            }
        }

        return modificadorValorDiamante
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

    /**
     * Permite obtener la instancia del factory
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ModificadorValorDiamanteFactory
     */
    private static ModificadorValorDiamanteFactory getModificadorValorDiamanteFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ModificadorValorDiamanteFactory)
    }

    /**
     * Permite obtener la instancia del factory
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository FactorValorDiamanteFactory
     */
    private static FactorValorDiamanteFactory getFactorValorDiamanteFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(FactorValorDiamanteFactory)
    }

}
