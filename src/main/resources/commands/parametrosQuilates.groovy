/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.exception.ParametrosQuilatesNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ParametrosQuilatesRepository
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ParametrosQuilatesJPA
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
@Usage("Administración de Parametros Quilates")
class parametrosQuilates {
    Logger LOGGER = LoggerFactory.getLogger(parametrosQuilates.class)

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

    @Usage("Permite recuperar el elemento del catálogo que coincida con el identificador")
    @Command
    def elemento(InvocationContext context,
                @Usage("Identificador del parametro")
    			@Required @Argument int idParametro) {
        def catalogo = getServicio(context).obtenerElemento(idParametro)

        if (catalogo) {
            mostrarTablaResultados(catalogo)
        } else {
            out.println("El elemento del catálogo con el identificador [${idParametro}] no existe.")
        }
    }

    @Usage("Permite agregar un nuevo elemento al catálogo")
    @Command
    def agregar(InvocationContext context,
                @Usage("Quilates Desde") @Required @Option(names = ["d", "quilatesDesde"]) String quilatesDesde,
                @Usage("Quilates Hasta") @Required @Option(names = ["h", "quilatesHasta"]) String quilatesHasta,
                @Usage("Quilates Base Desde") @Required @Option(names = ["e", "quilatesBaseDesde"]) String quilatesBaseDesde,
                @Usage("Quilates Base Hasta") @Required @Option(names = ["t", "quilatesBaseHasta"]) String quilatesBaseHasta,
                @Usage("Porcentaje") @Required @Option(names = ["p", "porcentaje"]) String porcentaje) {
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

        BigDecimal quilatesBaseDesdeConvert
        try {
            quilatesBaseDesdeConvert = new BigDecimal(quilatesBaseDesde)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del rango Base inferior del peso [$quilatesBaseDesde] no es valido.\n${e.getLocalizedMessage()}")
        }

        BigDecimal quilatesBaseHastaConvert
        try {
            quilatesBaseHastaConvert = new BigDecimal(quilatesBaseHasta)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del rango Base superior del peso [$quilatesBaseHasta] no es valido.\n${e.getLocalizedMessage()}")
        }
        BigDecimal porcentajeConvert
        try {
            porcentajeConvert = new BigDecimal(porcentaje)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del porcentaje [$porcentaje] no es valido.\n${e.getLocalizedMessage()}")
        }

        def pq = new ParametrosQuilatesJPA([quilatesDesde: quilatesDesdeConvert, quilatesHasta: quilatesHastaConvert, quilatesBaseDesde: quilatesBaseDesdeConvert,
                                            quilatesBaseHasta: quilatesBaseHastaConvert, porcentaje: porcentajeConvert])

        try {
            def elemento = getServicio(context).guardaParametrosQuilates(pq)
            out.println("El elemento con [${quilatesDesde}, ${quilatesHasta}, ${quilatesBaseDesde}, ${quilatesBaseHasta}, ${porcentaje}] fue agregado correctamente al catálogo.")
            mostrarTablaResultados([elemento])
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${quilatesDesde}, ${quilatesHasta}, ${quilatesBaseDesde}, ${quilatesBaseHasta}, ${porcentaje}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ocurrió un error al guardar el elemento ParametrosQuilatesJPA(${quilatesDesde}, ${quilatesHasta}, ${quilatesBaseDesde}, ${quilatesBaseHasta}, ${porcentaje}).")
        }
    }

    @Usage("Permite actualizar un elemento del catálogo")
    @Command
    def modificar(InvocationContext context,
    			@Usage("Identificador del parametro") @Required @Argument int idParametro,
                @Usage("Quilates Desde") @Option(names = ["d", "quilatesDesde"]) String quilatesDesde,
                @Usage("Quilates Hasta") @Option(names = ["h", "quilatesHasta"]) String quilatesHasta,
                @Usage("Quilates Base Desde") @Option(names = ["e", "quilatesBaseDesde"]) String quilatesBaseDesde,
                @Usage("Quilates Base Hasta") @Option(names = ["t", "quilatesBaseHasta"]) String quilatesBaseHasta,
                @Usage("Porcentaje") @Option(names = ["p", "porcentaje"]) String porcentaje) {
        if (ObjectUtils.isEmpty(quilatesDesde) && ObjectUtils.isEmpty(quilatesHasta) && ObjectUtils.isEmpty(quilatesBaseDesde) &&
        	ObjectUtils.isEmpty(quilatesBaseHasta) && ObjectUtils.isEmpty(porcentaje) || ObjectUtils.isEmpty(idParametro)) {
            out.println("Se requiere al menos uno de los atributos ([d, quilatesDesde] o [h, quilatesHasta] o [e, quilatesBaseDesde] o [t, quilatesBaseHasta] o [p, porcentaje]) " +
                "para realizar la actualización.")
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

        BigDecimal quilatesBaseDesdeConvert
        try {
            if (!ObjectUtils.isEmpty(quilatesBaseDesde)) {
                quilatesBaseDesdeConvert = new BigDecimal(quilatesBaseDesde)
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del rango base inferior [$quilatesBaseDesde] no es valido.\n${e.getLocalizedMessage()}")
        }

        BigDecimal quilatesBaseHastaConvert
        try {
            if (!ObjectUtils.isEmpty(quilatesBaseHasta)) {
                quilatesBaseHastaConvert = new BigDecimal(quilatesBaseHasta)
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del rango base superior [$quilatesBaseHasta] no es valido.\n${e.getLocalizedMessage()}")
        }

        BigDecimal porcentajeConvert
        try {
            if (!ObjectUtils.isEmpty(porcentaje)) {
                porcentajeConvert = new BigDecimal(porcentaje)
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del porcentaje [$porcentaje] no es valido.\n${e.getLocalizedMessage()}")
        }

        def pq = new ParametrosQuilatesJPA([id: idParametro, quilatesDesde: quilatesDesdeConvert, quilatesHasta: quilatesHastaConvert,
                                            quilatesBaseDesde: quilatesBaseDesdeConvert, quilatesBaseHasta: quilatesBaseHastaConvert, porcentaje: porcentajeConvert])

        try {
            def elemento = getServicio(context).update(idParametro, pq)
            out.println("El elemento con [" + idParametro + "," + quilatesDesde + "," + quilatesHasta + "," + quilatesBaseDesde + "," + quilatesBaseHasta + "," + porcentaje + "] ha sido modificado.")
            mostrarTablaResultados([elemento])
        } catch (ParametrosQuilatesNoEncontradoException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("El elemento del catálogo con [${quilatesDesde}, ${quilatesHasta}, ${quilatesBaseDesde}, ${quilatesBaseHasta}, ${porcentaje}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${quilatesDesde}, ${quilatesHasta}, ${quilatesBaseDesde}, ${quilatesBaseHasta}, ${porcentaje}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ocurrió un error al actualizar el elemento ParametrosQuilatesJPA(${quilatesDesde}, ${quilatesHasta}, ${quilatesBaseDesde}, ${quilatesBaseHasta}, ${porcentaje}).")
        }
    }

    @Usage("Permite eliminar un elemento del catálogo.")
    @Command
    def eliminar(InvocationContext context,
                 @Usage("Identificador del parametro") @Required @Argument int idParametro) {
        try {
            getServicio(context).delete(idParametro)
            out.println("El elemento con idParametro [${idParametro}] fue eliminado correctamente del cat\u00e1logo.")
        } catch (ParametrosQuilatesNoEncontradoException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("El elemento del cat\u00e1logo con idParametro [${idParametro}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento idParametro: ${idParametro} ")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento con idParametro: ${idParametro}")
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
                label('Quilates Base Desde')
                label('Quilates Base Hasta')
                label('Porcentaje')
            }

            elementos.each { elemento ->
                row {
                    label(elemento.id, foreground: green)
                    label(elemento.fecha, foreground: white)
                    label(elemento.quilatesDesde, foreground: yellow)
                    label(elemento.quilatesHasta, foreground: yellow)
                    label(elemento.quilatesBaseDesde, foreground: gray)
                    label(elemento.quilatesBaseHasta, foreground: gray)
                    label(elemento.porcentaje, foreground: green)
                }
            }
        }
    }

    /**
     * Regresa el servicio a utilizar segun el valor el contexto
     *
     * @return Servicio a utilizar.
     */
    private static ParametrosQuilatesRepository getServicio(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ParametrosQuilatesRepository)
    }
}
