/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ParametrosQuilatesJPA
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.ParametrosQuilatesRepository
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
        def catalogo = getServicio(context).getAll()

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
        def catalogo = getServicio(context).getOne(idParametro)

        if (catalogo) {
            mostrarTablaResultados(catalogo)
        } else {
            out.println("El elemento del catálogo con el identificador [${idParametro}] no existe.")
        }
    }

    @Usage("Permite agregar un nuevo elemento al catálogo")
    @Command
    def agregar(InvocationContext context,
                @Usage("Quilates Desde") @Required @Option(names = ["d", "quilatesDesde"]) BigDecimal quilatesDesde,
                @Usage("Quilates Hasta") @Required @Option(names = ["h", "quilatesHasta"]) BigDecimal quilatesHasta,
                @Usage("Quilates Base Desde") @Required @Option(names = ["e", "quilatesBaseDesde"]) BigDecimal quilatesBaseDesde,
                @Usage("Quilates Base Hasta") @Required @Option(names = ["t", "quilatesBaseHasta"]) BigDecimal quilatesBaseHasta,
                @Usage("Porcentaje") @Required @Option(names = ["p", "porcentaje"]) BigDecimal porcentaje) {
        def pq = new ParametrosQuilatesJPA([quilatesDesde: quilatesDesde, quilatesHasta: quilatesHasta, quilatesBaseDesde: quilatesBaseDesde, quilatesBaseHasta: quilatesBaseHasta, porcentaje: porcentaje])

        try {
            def elemento = getServicio(context).save(gc)
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
                @Usage("Quilates Desde") @Required @Option(names = ["d", "quilatesDesde"]) BigDecimal quilatesDesde,
                @Usage("Quilates Hasta") @Required @Option(names = ["h", "quilatesHasta"]) BigDecimal quilatesHasta,
                @Usage("Quilates Base Desde") @Required @Option(names = ["e", "quilatesBaseDesde"]) BigDecimal quilatesBaseDesde,
                @Usage("Quilates Base Hasta") @Required @Option(names = ["t", "quilatesBaseHasta"]) BigDecimal quilatesBaseHasta,
                @Usage("Porcentaje") @Required @Option(names = ["p", "porcentaje"]) BigDecimal porcentaje) {
        if (ObjectUtils.isEmpty(idParametro) || ObjectUtils.isEmpty(quilatesDesde) || ObjectUtils.isEmpty(quilatesHasta) || ObjectUtils.isEmpty(quilatesBaseDesde) ||
        	ObjectUtils.isEmpty(quilatesBaseHasta) || ObjectUtils.isEmpty(porcentaje)) {
            out.println("Se requiere al menos uno de los atributos ([d, quilatesDesde] o [h, quilatesHasta] o [e, quilatesBaseDesde] o [t, quilatesBaseHasta] o [p, porcentaje]) " +
                "para realizar la actualización.")
            return
        }

        def pq = new ParametrosQuilatesJPA([quilatesDesde: quilatesDesde, quilatesHasta: quilatesHasta, quilatesBaseDesde: quilatesBaseDesde, quilatesBaseHasta: quilatesBaseHasta, porcentaje: porcentaje])

        try {
            def elemento = getServicio(context).update(idParametro, pq)
            out.println("El elemento con [" + idParametro + "," + quilatesDesde + "," + quilatesHasta + "," + quilatesBaseDesde + "," + quilatesBaseHasta + "," + porcentaje + "] ha sido modificado.")
            mostrarTablaResultados([elemento])
        } catch (CatalogoNotFoundException e) {
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
        } catch (CatalogoNotFoundException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("El elemento del cat\u00e1logo con idRango [${idRango}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("""Ocurrió un error al eliminar el elemento idParametro: ${idParametro}
Violación de integridad referencial.
Existen referencias a éste elemento en el catálogo.""")
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
