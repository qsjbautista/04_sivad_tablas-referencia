/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoRangoPesoDiamanteJPA
import mx.com.nmp.ms.sivad.referencia.dominio.repository.CastigoRangoPesoRepository
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoCorteNoEncontradoException
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
                @Usage("quilatesDesde") @Required @Option(names = ["d", "quilatesDesde"]) BigDecimal quilatesDesde,
                @Usage("quilatesHasta") @Required @Option(names = ["h", "quilatesHasta"]) BigDecimal quilatesHasta,
                @Usage("Factor") @Required @Option(names = ["f", "factor"]) BigDecimal factor) {
        def ctc = new CastigoRangoPesoDiamanteJPA([quilatesDesde: quilatesDesde, quilatesHasta: quilatesHasta, factor: factor])

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
