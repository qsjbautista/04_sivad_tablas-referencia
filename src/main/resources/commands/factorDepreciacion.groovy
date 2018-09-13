/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactorDepreciacionNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorDepreciacionDiamanteJPA
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
@Usage("Administración de Factor Depreciacion")
class factorDepreciacion {
    Logger LOGGER = LoggerFactory.getLogger(factorDepreciacion.class)

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
                @Usage("Identificador del factor")
    			@Required @Argument int idFactor) {
        def catalogo = getServicio(context).obtenerElemento(idFactor)

        if (catalogo) {
            mostrarTablaResultados(catalogo)
        } else {
            out.println("El elemento del catálogo con el identificador [${idFactor}] no existe.")
        }
    }

    @Usage("Permite agregar un nuevo elemento al catálogo")
    @Command
    def agregar(InvocationContext context,
                @Usage("Factor") @Required @Option(names = ["f", "factor"]) String factor) {
        BigDecimal factorConvert
        try {
            factorConvert = new BigDecimal(factor)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del factor [$factor] no es valido.\n${e.getLocalizedMessage()}")
        }

        def fd = new FactorDepreciacionDiamanteJPA([factor: factorConvert])

        try {
            def elemento = getServicio(context).save(fd)
            out.println("El elemento con [${factor}] fue agregado correctamente al catálogo.")
            mostrarTablaResultados([elemento])
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${factor}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ocurrió un error al guardar el elemento FactorDepreciacionDiamanteJPA(${factor}).")
        }
    }

    @Usage("Permite actualizar un elemento del catálogo")
    @Command
    def modificar(InvocationContext context,
    			@Usage("Identificador del factor") @Required @Argument int idFactor,
                @Usage("Factor") @Required @Option(names = ["f", "factor"]) String factor) {
        if (ObjectUtils.isEmpty(idFactor) || ObjectUtils.isEmpty(factor)) {
            out.println("Se requiere al menos uno de los atributos ([f, factor]) " +
                "para realizar la actualización.")
            return
        }

        BigDecimal factorConvert
        try {
            factorConvert = new BigDecimal(factor)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del factor [$factor] no es valido.\n${e.getLocalizedMessage()}")
        }

        def fd = new FactorDepreciacionDiamanteJPA([factor: factorConvert])

        try {
            def elemento = getServicio(context).update(idFactor, factorConvert)
            out.println("El elemento con [" + idFactor + "," + factor + "] ha sido modificado.")
            mostrarTablaResultados([elemento])
        } catch (FactorDepreciacionNoEncontradoException e) {
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
        } catch (FactorDepreciacionNoEncontradoException e) {
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
                label('Factor')
            }

            elementos.each { elemento ->
                row {
                    label(elemento.id, foreground: green)
                    label(elemento.fecha, foreground: white)
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
    private static FactorDepreciacionRepository getServicio(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(FactorDepreciacionRepository)
    }
}
