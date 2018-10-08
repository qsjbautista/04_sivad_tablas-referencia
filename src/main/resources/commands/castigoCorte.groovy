/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoCorteNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoCorteDiamanteJPA
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.CastigoCorteDiamanteRepository
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
@Usage("Administración de Porcentaje Castigo X Tipo Corte")
class castigoCorte{
    Logger LOGGER = LoggerFactory.getLogger(castigoCorte.class)

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
                @Usage("Corte") @Required @Option(names = ["c", "corte"]) String corte,
                @Usage("Factor") @Required @Option(names = ["f", "factor"]) String factor) {
        BigDecimal factorConvert
        try {
            factorConvert = new BigDecimal(factor)
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "El formato del factor [$factor] no es valido.\n${e.getLocalizedMessage()}")
        }

        def ctc = new CastigoCorteDiamanteJPA([corte: corte, factor: factorConvert])

        try {
            def elemento = getServicio(context).guardaActualizaCastigoCorte(ctc)
            out.println("El elemento con [${corte}, ${factor}] fue agregado correctamente al catálogo.")
            mostrarTablaResultados([elemento])
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${corte}, ${factor}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al guardar el elemento", e)
            out.println("Ocurrió un error al guardar el elemento CastigoCorteDiamanteJPA(${corte}, ${factor}).")
        }
    }

    @Usage("Permite recuperar el elemento del catálogo que coincida con el identificador")
    @Command
    def elemento(InvocationContext context,
                @Usage("Identificador del castigo por corte")
    			@Required @Argument int idCastigoCorte) {
        def catalogo = getServicio(context).obtenerElemento(idCastigoCorte)

        if (catalogo) {
            mostrarTablaResultados(catalogo)
        } else {
            out.println("El elemento del catálogo con el identificador [${idCastigoCorte}] no existe.")
        }
    }

    @Usage("Permite actualizar un elemento del catálogo")
    @Command
    def modificar(InvocationContext context,
                  @Usage("Identificador del castigo por tipo de corte a actualizar") @Required @Argument int idCastigoCorte,
                  @Usage("Corte") @Option(names = ["c", "corte"]) String corte,
                  @Usage("Factor") @Option(names = ["f", "factor"]) String factor) {
        if (ObjectUtils.isEmpty(factor) && ObjectUtils.isEmpty(corte) || ObjectUtils.isEmpty(idCastigoCorte)) {
            out.println("Se requiere al menos uno de los atributos ([c, corte] o [f, factor] y [Identificador del elemento a actualizar]) " +
                "para realizar la actualización.")
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

        def ctc = new CastigoCorteDiamanteJPA([id: idCastigoCorte, corte: corte, factor: factorConvert])

        try {
            def elemento = getServicio(context).guardaActualizaCastigoCorte(ctc)

            out.println("El elemento con [" + idCastigoCorte + "," + corte + ", " + factorConvert + "] ha sido modificado.")
            mostrarTablaResultados([elemento])
        } catch (CastigoCorteNoEncontradoException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("El elemento del catálogo con [${idCastigoCorte}, ${corte}, ${factor}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ya existe un elemento del cat\u00e1logo con [${idCastigoCorte}, ${corte}, ${factor}].")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al actualizar el elemento", e)
            out.println("Ocurrió un error al actualizar el elemento CastigoCorteDiamanteJPA(${idCastigoCorte}, ${corte}, ${factor}).")
        }
    }

    @Usage("Permite eliminar un elemento del catálogo.")
    @Command
    def eliminar(InvocationContext context,
                 @Usage("Identificador del parametro") @Required @Argument int idCastigoCorte) {
        try {
            getServicio(context).delete(idCastigoCorte)
            out.println("El elemento con idCastigoCorte [${idCastigoCorte}] fue eliminado correctamente del cat\u00e1logo.")
        } catch (CastigoCorteNoEncontradoException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("El elemento del cat\u00e1logo con idCastigoCorte [${idCastigoCorte}] no existe.")
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento idCastigoCorte: ${idCastigoCorte}")
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error al eliminar el elemento", e)
            out.println("Ocurrió un error al eliminar el elemento con idCastigoCorte: ${idCastigoCorte}")
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
                label('Corte')
                label('Factor')
            }

            elementos.each { elemento ->
                row {
                    label(elemento.id, foreground: green)
                    label(elemento.fecha, foreground: white)
                    label(elemento.corte, foreground: yellow)
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
    private static CastigoCorteDiamanteRepository getServicio(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(CastigoCorteDiamanteRepository)
    }
}
