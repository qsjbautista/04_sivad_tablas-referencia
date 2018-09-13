/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactoresRangoColorNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactoresRangoColorJPA
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.FactoresRangoColorRepository
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
                @Usage("Color Desde") @Required @Option(names = ["d", "colorDesde"]) String colorDesde,
                @Usage("Color Hasta") @Required @Option(names = ["h", "colorHasta"]) String colorHasta,
                @Usage("Color Base") @Required @Option(names = ["b", "colorBase"]) String colorBase,
                @Usage("Factor") @Required @Option(names = ["f", "factor"]) String factor) {

        BigDecimal factorConvert
        try {
            factorConvert = new BigDecimal(factor)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del factor [$factor] no es valido.\n${e.getLocalizedMessage()}")
        }

        def ctc = new FactoresRangoColorJPA([colorDesde: colorDesde, colorHasta: colorHasta, rangoColorBase: colorBase, factor: factorConvert])

        try {
            def elemento = getServicio(context).guardaActualizaFactorRangoColor(ctc)
            out.println("El elemento con [${colorDesde}, ${colorHasta}, ${colorBase}, ${factor}] fue agregado correctamente al catálogo.")
            mostrarTablaResultados([elemento])
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${colorDesde}, ${colorHasta}, ${colorBase}, ${factor}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ocurrió un error al guardar el elemento FactoresRangoColorJPA(${colorDesde}, ${colorHasta}, ${colorBase}, ${factor}).")
        }
    }

    @Usage("Permite recuperar el elemento del catálogo que coincida con el identificador")
    @Command
    def elemento(InvocationContext context,
                 @Usage("Identificador del factor")
                 @Required @Argument int idFactor) {
        def catalogo = getServicio(context).obtenerElemento(idFactor)

        if (catalogo) {
            mostrarTablaResultados(catalogo)
        } else {
            out.println("El elemento del catálogo con el identificador [${idFactor}] no existe.")
        }
    }

    @Usage("Permite actualizar un elemento del catálogo")
    @Command
    def modificar(InvocationContext context,
                  @Usage("Identificador del factor") @Required @Argument int idFactor,
                  @Usage("Color Desde") @Option(names = ["d", "colorDesde"]) String colorDesde,
                  @Usage("Color Hasta") @Option(names = ["h", "colorHasta"]) String colorHasta,
                  @Usage("Color Base") @Option(names = ["b", "colorBase"]) String colorBase,
                  @Usage("Factor") @Option(names = ["f", "factor"]) String factor) {
        if (ObjectUtils.isEmpty(colorDesde) && ObjectUtils.isEmpty(colorHasta) && ObjectUtils.isEmpty(colorBase)
            && ObjectUtils.isEmpty(factor) || ObjectUtils.isEmpty(idFactor)) {
            out.println("Se requiere al menos uno de los atributos ([d, colorDesde], [h, colorHasta], [b, colorBase] o [f, factor] y " +
                "[Identificador del elemento a actualizar]) para realizar la actualización.")
            return
        }

        BigDecimal factorConvert
        try {
            if (!ObjectUtils.isEmpty(factor)) {
                factorConvert = new BigDecimal(factor)
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del factor [$factor] no es valido.\n${e.getLocalizedMessage()}")
        }

        def fd = new FactoresRangoColorJPA([id: idFactor, colorDesde: colorDesde, colorHasta: colorHasta, rangoColorBase: colorBase, factor: factorConvert])

        try {
            def elemento = getServicio(context).guardaActualizaFactorRangoColor(fd)
            out.println("El elemento con [" + idFactor + "," + factor + "] ha sido modificado.")
            mostrarTablaResultados([elemento])
        } catch (FactoresRangoColorNoEncontradoException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("El elemento del catálogo con [${idFactor}, ${factor}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${idFactor}, ${factor}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ocurrió un error al actualizar el elemento FactorDepreciacionDiamanteJPA(${idFactor}, ${factor}).")
        }
    }

    @Usage("Permite eliminar un elemento del catálogo.")
    @Command
    def eliminar(InvocationContext context,
                 @Usage("Identificador del parametro") @Required @Argument int idFactor) {
        try {
            getServicio(context).delete(idFactor)
            out.println("El elemento con idFactor [${idFactor}] fue eliminado correctamente del cat\u00e1logo.")
        } catch (FactoresRangoColorNoEncontradoException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("El elemento del cat\u00e1logo con idFactor [${idFactor}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento idFactor: ${idFactor}")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento con idFactor: ${idFactor}")
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
                    label(elemento.rangoColorBase, foreground: yellow)
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
