/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.RangoPesoDiamanteJPA
import mx.com.nmp.ms.sivad.referencia.dominio.repository.FactorDepreciacionRepository
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
@Usage("Administración de Rango Pesos")
class rangoPesos{
    Logger LOGGER = LoggerFactory.getLogger(rangoPesos.class)

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
                @Usage("Identificador del rango pesos")
    			@Required @Argument int idRangoPesos) {
        def catalogo = getServicio(context).getOne(idRangoPesos)

        if (catalogo) {
            mostrarTablaResultados(catalogo)
        } else {
            out.println("El elemento del catálogo con el identificador [${idRangoPesos}] no existe.")
        }
    }

    @Usage("Permite agregar un nuevo elemento al catálogo")
    @Command
    def agregar(InvocationContext context,
                @Usage("Quilates Desde") @Required @Option(names = ["d", "quilatesDesde"]) BigDecimal quilatesDesde,
                @Usage("Quilates Hasta") @Required @Option(names = ["h", "quilatesHasta"]) BigDecimal quilatesHasta) {
        def rp = new RangoPesoDiamanteJPA([quilatesDesde: quilatesDesde, quilatesHasta: quilatesHasta])

        try {
            def elemento = getServicio(context).save(rp)
            out.println("El elemento con [${quilatesDesde}, ${quilatesHasta}] fue agregado correctamente al catálogo.")
            mostrarTablaResultados([elemento])
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${quilatesDesde}, ${quilatesHasta}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ocurrió un error al guardar el elemento RangoPesoDiamanteJPA(${quilatesDesde}, ${quilatesHasta}).")
        }
    }

    @Usage("Permite actualizar un elemento del catálogo")
    @Command
    def modificar(InvocationContext context,
    			@Usage("Identificador del rango peso") @Required @Argument int idRangoPesos,
                @Usage("Quilates Desde") @Required @Option(names = ["d", "quilatesDesde"]) BigDecimal quilatesDesde,
                @Usage("Quilates Hasta") @Required @Option(names = ["h", "quilatesHasta"]) BigDecimal quilatesHasta) {
        if (ObjectUtils.isEmpty(idRangoPesos) || ObjectUtils.isEmpty(quilatesDesde) || ObjectUtils.isEmpty(quilatesHasta)) {
            out.println("Se requiere al menos uno de los atributos ([d, quilatesDesde], [h, quilatesHasta]) " +
                "para realizar la actualización.")
            return
        }

        def rp = new RangoPesoDiamanteJPA([quilatesDesde: quilatesDesde, quilatesHasta: quilatesHasta])

        try {
            def elemento = getServicio(context).update(idRangoPesos, rp)
            out.println("El elemento con [" + idRangoPesos + "," + quilatesDesde + ", " + quilatesHasta + "] ha sido modificado.")
            mostrarTablaResultados([elemento])
        } catch (CatalogoNotFoundException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("El elemento del catálogo con [${idRangoPesos}, ${quilatesDesde}, ${quilatesHasta}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${idRangoPesos}, ${quilatesDesde}, ${quilatesHasta}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ocurrió un error al actualizar el elemento FactorDepreciacionDiamanteJPA(${idRangoPesos}, ${quilatesDesde}, ${quilatesHasta}).")
        }
    }

    @Usage("Permite eliminar un elemento del catálogo.")
    @Command
    def eliminar(InvocationContext context,
                 @Usage("Identificador del parametro") @Required @Argument int idRangoPesos) {
        try {
            getServicio(context).delete(idRangoPesos)
            out.println("El elemento con idFactor [${idRangoPesos}] fue eliminado correctamente del cat\u00e1logo.")
        } catch (CatalogoNotFoundException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("El elemento del cat\u00e1logo con idFactor [${idRangoPesos}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("""Ocurrió un error al eliminar el elemento idRangoPesos: ${idRangoPesos}
Violación de integridad referencial.
Existen referencias a éste elemento en el catálogo.""")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento con idFactor: ${idRangoPesos}")
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
            }

            elementos.each { elemento ->
                row {
                    label(elemento.id, foreground: green)
                    label(elemento.fecha, foreground: white)
                    label(elemento.quilatesDesde, foreground: yellow)
                    label(elemento.quilatesHasta, foreground: yellow)
                }
            }
        }
    }

    /**
     * Regresa el servicio a utilizar segun el valor el contexto
     *
     * @return Servicio a utilizar.
     */
    private static FactorDepreciacionRepository getServicio(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(FactorDepreciacionRepository)
    }
}
