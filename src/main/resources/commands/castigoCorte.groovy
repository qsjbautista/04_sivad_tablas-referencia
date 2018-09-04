/*
 *
 * Microservicios - Catálogos
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package commands

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoCorteDiamanteJPA
import mx.com.nmp.ms.sivad.referencia.dominio.repository.CastigoCorteDiamanteRepository
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
                @Usage("Factor") @Required @Option(names = ["f", "factor"]) BigDecimal factor) {
        def ctc = new CastigoCorteDiamanteJPA([corte: corte, factor: factor])

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
