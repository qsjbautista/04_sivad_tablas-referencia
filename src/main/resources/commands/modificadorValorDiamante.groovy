/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository
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
     * Permite obtener el Modificador Valor Diamante
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar los Modificador Valor Diamante vigentes de la fecha dada")
    @Command
    def consultar(InvocationContext context, @Usage("Fecha de vigencia a consultar YYYY-dd-mm:") @Required @Argument String fecha) {

        if (ObjectUtils.isEmpty(fecha)) {
            out.println("Se requiere la fecha para consultar ")
        }else if(!fecha.matches(/\d{4}-\d{2}-\d{2}/)) {
            out.println("El formato de la fecha no es correcto debe de cumplir YYYY-dd-mm  ")
        }else
            try {
                LocalDate fechaFormat = new LocalDate(fecha)
                //LocalDate fechaFormat = new LocalDate(fecha)
//                DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-dd-mm")
//                DateTime fechaFormat = formatter.parseDateTime(fecha)
                out.println("Fecha"+ fechaFormat.toDateTimeAtStartOfDay().toString())
                def elementos = getConsultaListado(context).consultar(fechaFormat.toDateTimeAtStartOfDay())
                out.println("elementos"+elementos.toString())
                mostrarTablaResultados(elementos)
            } catch (Exception e) {
                out.println("La fecha: [${fecha}] no regresa resultados ")
                e.printStackTrace()
            }
    }

/**
 * Factor valor Mínimo.
 */
    private BigDecimal minimo;

    /**
     * Factor valor Medio.
     */
    private BigDecimal medio;

    /**
     * Factor valor Máximo.
     */
    private BigDecimal maximo;

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
     * @return Referencia al repository ValorComercialOroRepository
     */
    private static ModificadorValorDiamanteRepository getConsultaListado(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ModificadorValorDiamanteRepository)
    }



}
