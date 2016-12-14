/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoModificadorCertificadoFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.CertificadoFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoModificadorCertificado
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.crsh.text.ui.Overflow
import org.crsh.text.ui.UIBuilder
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.springframework.util.ObjectUtils

/**
 * Utilizada por la consola CRaSH para la administración del modificador tipo certifcado
 *
 * @author roramirez
 */
@Usage("Administración del Modificador tipo certificado")
class modificadorTipoCertificado {

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Valor del Oro mediante un archivo")
    @Command
    def actualizar(InvocationContext context, @Usage("Contenido a procesar") @Required @Argument String contenido) {
        ListadoModificadorCertificado listadoModificadorCertificado = null
            if (ObjectUtils.isEmpty(contenido)) {
                out.println("Se requiere el contenido a procesar ")
            }

            try{
                listadoModificadorCertificado = crearListado(contenido, context)
                out.println("Se imprimen el listado " + listadoModificadorCertificado.toString())
            } catch (Exception e) {
                out.println("No se creo el listado")
                e.printStackTrace()
            }

        try{
            getModificadorCertificadoRepository(context).actualizarListado(listadoModificadorCertificado)
            out.println("Se actualizo correctamente ")
        }catch(Exception e ){
            out.println("No persitieron los Certificados")
            e.printStackTrace()
        }
    }

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar todos los elementos del catálogo")
    @Command
    def consultar(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar YYYY-dd-mm:") @Required @Argument String fecha) {

        if (ObjectUtils.isEmpty(fecha)) {
            out.println("Se requiere la fecha para consultar ")
        } else if (!fecha.matches(/\d{4}-\d{2}-\d{2}/)) {
            out.println("El formato de la fecha no es correcto debe de cumplir YYYY-dd-mm  ")
        } else
            try {
                LocalDate fechaFormat = new LocalDate(fecha)
                def elementos = getModificadorCertificadoRepository(context).consultarListadoPorUltimaActualizacion(fechaFormat)
                mostrarTablaResultados(elementos)
            } catch (Exception e) {
                out.println("No hay resultados para la fecha: [${fecha}]")
                e.printStackTrace()
            }
    }

    /**
     * Utilizado para representar los elementos del catálogo en un formato de tabla.
     *
     * @param elementos Lista de elementos del catálogo.
     * @return Despliegue visual en la consola con los elementos del catálogo.
     */
    private def mostrarTablaResultados(elementos) {
        new UIBuilder().table(separator: dashed, overflow: Overflow.HIDDEN, rightCellPadding: 1) {
            header(decoration: bold, foreground: black, background: white) {
                label('Nombre Certificado')
                label('Factor')
            }

            elementos.each { elemento ->
                elemento.certificados.each { certificado ->
                    row {
                        label(certificado.certificado, foreground: white)
                        label(certificado.factor, foreground: white)
                    }
                }
            }
        }
    }

    /**
     * Utilizado para listas
     *
     * @param file archivp que se recibe
     * @param context El contexto de la invocación
     * @return ListadoValorcomercialOroFactory
     */
    private def crearListado(String contenido, InvocationContext context) {

        String nCertificado
        BigDecimal factor
        Certificado certificado
        def dataList = []
        def infoTxt = [:]
        def certificadoSet = new HashSet<>()
        def c = "Certificado"
        def f = "Factor"
        def fecha = new DateTime(new Date())

        contenido.eachLine { line ->
            if (line.trim()) {
                def (key, value) = line.split(':').collect() { it.trim() }
                infoTxt."$key" = value
            } else {
                if (infoTxt) {
                    dataList << infoTxt
                    infoTxt = [:]
                }
            }
        }

        if (infoTxt) {
            dataList << infoTxt
        }

        dataList.eachWithIndex { it, index ->
            out.println("Carga $index")
            it.each {k, v ->
                out.println("$k" : "$v")
                if(c == k)
                    nCertificado = v.toString()
                if(f == k)
                    factor = new BigDecimal(v.toString())
        }
                try{
                    certificado = CertificadoFactory.crear(nCertificado, factor)
                }catch(Exception e){
                    out.println("No se pudo crear el certificado" )
                    e.stackTrace
                }
            certificadoSet.add(certificado)

        }
        ListadoModificadorCertificado listadoModificadorCertificado = null
        LocalDate fechaLocal = new LocalDate(new Date())
        listadoModificadorCertificado = getListadoModificadorCertificadoFactory(context).crear(fechaLocal, certificadoSet)

        return listadoModificadorCertificado
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia al repository ModificadorCertificadoRepository
     */
    private static ModificadorCertificadoRepository getModificadorCertificadoRepository(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ModificadorCertificadoRepository)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase ListadoModificadorCertificado.
     */
    private static ListadoModificadorCertificado getListadoModificadorCertificado(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ListadoModificadorCertificado)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase ListadoModificadorCertificadoFactory.
     */
    private static ListadoModificadorCertificadoFactory getListadoModificadorCertificadoFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ListadoModificadorCertificadoFactory)
    }

    /**
     * Permite obtener la instancia
     *
     * @param context El contexto de la invocación.
     * @return Referencia a la clase CertificadoFactory
     */
    private static CertificadoFactory getCertificadoFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(CertificadoFactory)
    }
}

