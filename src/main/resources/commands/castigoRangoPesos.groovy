/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoRangoPesoNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoRangoPesoDiamanteJPA
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.CastigoRangoPesoRepository
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
@Usage("Administración de Porcentaje Castigo X Rango Peso")
class castigoRangoPesos {
    Logger LOGGER = LoggerFactory.getLogger(castigoRangoPesos.class)

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
                @Usage("quilatesDesde") @Required @Option(names = ["d", "quilatesDesde"]) String quilatesDesde,
                @Usage("quilatesHasta") @Required @Option(names = ["h", "quilatesHasta"]) String quilatesHasta,
                @Usage("Factor") @Required @Option(names = ["f", "factor"]) String factor) {
        BigDecimal quilatesDesdeConvert
        try {
            quilatesDesdeConvert = new BigDecimal(quilatesDesde)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del rango inferior del peso [$quilatesDesde] no es valido.\n${e.getLocalizedMessage()}")
        }

        BigDecimal quilatesHastaConvert
        try {
            quilatesHastaConvert = new BigDecimal(quilatesHasta)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del rango superior del peso [$quilatesHasta] no es valido.\n${e.getLocalizedMessage()}")
        }

        BigDecimal factorConvert
        try {
            factorConvert = new BigDecimal(factor)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del factor [$factor] no es valido.\n${e.getLocalizedMessage()}")
        }

        def ctc = new CastigoRangoPesoDiamanteJPA([quilatesDesde: quilatesDesdeConvert, quilatesHasta: quilatesHastaConvert, factor: factorConvert])

        try {
            def elemento = getServicio(context).guardaActualizaCastigoRango(ctc)
            out.println("El elemento con [${quilatesDesde}, ${quilatesHasta}, ${factor}] fue agregado correctamente al catálogo.")
            mostrarTablaResultados([elemento])
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${quilatesDesde}, ${quilatesHasta}, ${factor}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ocurrió un error al guardar el elemento (${quilatesDesde}, ${quilatesHasta}, ${factor}).")
        }
    }

    @Usage("Permite recuperar el elemento del catálogo que coincida con el identificador")
    @Command
    def elemento(InvocationContext context,
                 @Usage("Identificador del castigo por rango de peso")
                 @Required @Argument int idCastigoRangoPeso) {
        def catalogo = getServicio(context).obtenerElemento(idCastigoRangoPeso)

        if (catalogo) {
            mostrarTablaResultados(catalogo)
        } else {
            out.println("El elemento del catálogo con el identificador [${idCastigoRangoPeso}] no existe.")
        }
    }

    @Usage("Permite actualizar un elemento del catálogo")
    @Command
    def modificar(InvocationContext context,
                  @Usage("Identificador del castigo por rango de peso a actualizar") @Required @Argument int idCastigoRangoPeso,
                  @Usage("quilatesDesde") @Option(names = ["d", "quilatesDesde"]) String quilatesDesde,
                  @Usage("quilatesHasta") @Option(names = ["h", "quilatesHasta"]) String quilatesHasta,
                  @Usage("Factor") @Option(names = ["f", "factor"]) String factor) {
        if (ObjectUtils.isEmpty(factor) && ObjectUtils.isEmpty(quilatesDesde) && ObjectUtils.isEmpty(quilatesHasta) || ObjectUtils.isEmpty(idCastigoRangoPeso)) {
            out.println("Se requiere al menos uno de los atributos ([d, quilatesDesde], [h, quilatesHasta] o [f, factor] y " +
                "[Identificador del elemento a actualizar]) para realizar la actualización.")
            return
        }

        BigDecimal quilatesDesdeConvert
        try {
            if (!ObjectUtils.isEmpty(quilatesDesde)) {
                quilatesDesdeConvert = new BigDecimal(quilatesDesde)
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del rango inferior [$quilatesDesde] no es valido.\n${e.getLocalizedMessage()}")
        }

        BigDecimal quilatesHastaConvert
        try {
            if (!ObjectUtils.isEmpty(quilatesHasta)) {
                quilatesHastaConvert = new BigDecimal(quilatesHasta)
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del rango superior [$quilatesHasta] no es valido.\n${e.getLocalizedMessage()}")
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

        def ctc = new CastigoRangoPesoDiamanteJPA([id: idCastigoRangoPeso, quilatesDesde: quilatesDesdeConvert, quilatesHasta: quilatesHastaConvert, factor: factorConvert])

        try {
            def elemento = getServicio(context).guardaActualizaCastigoRango(ctc)

            out.println("El elemento con [" + idCastigoRangoPeso + "," + quilatesDesde + ", " + quilatesHasta + ", " + factorConvert + "] ha sido modificado.")
            mostrarTablaResultados([elemento])
        } catch (CastigoRangoPesoNoEncontradoException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("El elemento del catálogo con [${idCastigoRangoPeso}, ${quilatesDesde}, ${quilatesHasta}, ${factor}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${idCastigoRangoPeso}, ${quilatesDesde}, ${quilatesHasta}, ${factor}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ocurrió un error al actualizar el elemento CastigoRangoPesoDiamanteJPA(${idCastigoRangoPeso}, ${quilatesDesde}, ${quilatesHasta}, ${factor}).")
        }
    }

    @Usage("Permite eliminar un elemento del catálogo.")
    @Command
    def eliminar(InvocationContext context,
                 @Usage("Identificador del parametro") @Required @Argument int idCastigoRangoPeso) {
        try {
            getServicio(context).delete(idCastigoRangoPeso)
            out.println("El elemento con idCastigoRangoPeso [${idCastigoRangoPeso}] fue eliminado correctamente del cat\u00e1logo.")
        } catch (CastigoRangoPesoNoEncontradoException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("El elemento del cat\u00e1logo con idCastigoRangoPeso [${idCastigoRangoPeso}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento idCastigoRangoPeso: ${idCastigoRangoPeso}")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento con idCastigoRangoPeso: ${idCastigoRangoPeso}")
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
                label('Quilates Desde')
                label('Quilates Hasta')
                label('Factor')
            }

            elementos.each { elemento ->
                row {
                    label(elemento.id, foreground: green)
                    label(elemento.fecha, foreground: white)
                    label(elemento.quilatesDesde, foreground: yellow)
                    label(elemento.quilatesHasta, foreground: yellow)
                    label(elemento.factor, foreground: red)
                }
            }
        }
    }

    /**
     * Regresa el servicio a utilizar segun el valor el contexto
     *
     * @return Servicio a utilizar.
     */
    private static CastigoRangoPesoRepository getServicio(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(CastigoRangoPesoRepository)
    }
}
