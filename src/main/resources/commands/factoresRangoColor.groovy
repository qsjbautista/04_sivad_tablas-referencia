/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactoresRangoColorJPA
import mx.com.nmp.ms.sivad.referencia.dominio.repository.CastigoCorteDiamanteRepository
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactoresRangoColorRepository
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.crsh.text.ui.Overflow
import org.crsh.text.ui.UIBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.util.ObjectUtils

/**
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
@Usage("Administración de Porcentaje Castigo X Rango Color")
class castigoRangoColor {
    Logger LOGGER = LoggerFactory.getLogger(castigoRangoColor.class)

    @Usage("Permite recuperar todos los elementos del catálogo.")
    @Command
    def elementos(InvocationContext context) {
        def catalogo = getServicio(context).obtenerTodo()

        if (catalogo) {
            mostrarTablaResultados(catalogo)
        } else {
            out.println("El catálogo no contiene elementos.")
        }
    }

    @Usage("Permite agregar un nuevo elemento al catálogo")
    @Command
    def agregar(InvocationContext context,
                @Usage("Color") @Required @Option(names = ["d", "color"]) String color,
                @Usage("Color Base") @Required @Option(names = ["b", "colorBase"]) String colorHasta,
                @Usage("Factor") @Required @Option(names = ["f", "factor"]) String factor) {
        def ctc = new FactoresRangoColorJPA([colorDesde: color, colorHasta: color, factor: factor])

        try {
            def elemento = getServicio(context).guardaActualizaFactorRangoColor(ctc)
            out.println("El elemento con [${color}, ${colorBase}, ${factor}] fue agregado correctamente al catálogo.")
            mostrarTablaResultados([elemento])
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${color}, ${colorBase}, ${factor}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ocurrió un error al guardar el elemento FactoresRangoColorJPA(${color}, ${colorBase}, ${factor}).")
        }
    }

    /**
     * Genera la tabla para mostrar los resultados.
     *
     * @param elementos Elementos a incluir en la tabla
     *
     * @return Tabla a mostrar con los elementos
     */
    private def mostrarTablaResultados(elementos) {
        new UIBuilder().table(separator: dashed, overflow: Overflow.HIDDEN, rightCellPadding: 1) {
            header(decoration: bold, foreground: black, background: white) {
                label('Id')
                label('Fecha')
                label('Color Desde')
                label('Color Hasta')
                label('Color Base')
                label('Factor')
            }

            elementos.each { elemento ->
                row {
                    label(elemento.id, foreground: green)
                    label(elemento.fecha, foreground: white)
                    label(elemento.colorDesde, foreground: yellow)
                    label(elemento.colorHasta, foreground: yellow)
                    label(elemento.colorBase, foreground: yellow)
                    label(elemento.factor, foreground: yellow)
                }
            }
        }
    }

    /**
     * Regresa el servicio a utilizar segun el valor el contexto
     *
     * @return Servicio a utilizar.
     */
    private static FactoresRangoColorRepository getServicio(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(FactoresRangoColorRepository)
    }
}
