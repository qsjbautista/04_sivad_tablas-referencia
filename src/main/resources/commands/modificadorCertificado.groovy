/**
 * Proyecto:        NMP - Microservicio de Catálogos
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package commands

import commands.util.ConvertirAFechaUtil
import commands.util.MostrarResultadosUtil
import commands.util.ReadObjecstFromString
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CertificadoNoEncontradoException
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoModificadorCertificadoFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.CertificadoFactory
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoModificadorCertificado
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.joda.time.LocalDate

/**
 * Utilizada por la consola CRaSH para la administración del modificador tipo certifcado
 *
 * @author roramirez
 */
@SuppressWarnings("GroovyUnusedDeclaration")
@Usage("Administraci\u00f3n del Listado de Modificador Tipo Certificado")
class modificadorTipoCertificado {
    private static final String NOMBRE_CERTIFICADO = "nombre certificado"
    private static final String FACTOR = "factor"
    private static final List<String>  PROPIEDADES_CERTIFICADO = ["certificado", "factor"]
    private static final List<String>  NOMBRE_PROPIEDADES_CERTIFICADO = [NOMBRE_CERTIFICADO, FACTOR]
    private static final List<String>  HEADERS = ["Nombre Certificado", "Factor"]

    /**
     * Permite obtener tipo de certificado
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite actualizar el Listado de Modificador Tipo Certificado")
    @Command
    def actualizar(InvocationContext context,
                   @Usage("Nuevo contenido del Listado de Modificador Tipo Certificado")
                   @Required @Argument String contenido) {
        ListadoModificadorCertificado listadoModificadorCertificado

        try {
            ReadObjecstFromString rof = new ReadObjecstFromString(contenido, 2, NOMBRE_PROPIEDADES_CERTIFICADO);
            List<Map<String, String>> objects = rof.readObjects()
            listadoModificadorCertificado = crearListado(objects, context)
        } catch (IllegalArgumentException e) {
            out.println("${e.getMessage()}")
            return
        }

        try{
            getModificadorCertificadoRepository(context).actualizarListado(listadoModificadorCertificado)
            out.println("El Listado de Certificados fue actualizado correctamente.")
        }catch(Exception e ){
            e.printStackTrace()
            out.println("Ocurri\u00f3 un error inesperado al actualizar el Listado de Certificados.")
        }
    }

    /**
     * Permite obtener el valor comercial del oro
     *
     * @param context El contexto de la invocación.
     * @return Lista de elementos
     */
    @Usage("Permite recuperar el Listado de Modificador Tipo Certificado vigente o de alguna fecha de vigencia espec\u00edfica")
    @Command
    def consultar(InvocationContext context,
                  @Usage("Fecha de vigencia a consultar con formato yyyy-mm-dd")
                  @Option(names = ["f", "fecha"]) String fecha,
                  @Usage("Indica si el resultado se muestra en formato de lista")
                  @Option(names = ["l", "mostrarEnLista"]) Boolean mostrarEnLista) {
        LocalDate fechaFormat = null

        if (fecha) {
            try {
                fechaFormat = ConvertirAFechaUtil.convertirAFecha(fecha)
            } catch (IllegalArgumentException e) {
                out.println("${e.getMessage()}")
                return
            }
        }

        try {
            List<ListadoModificadorCertificado> elementos = recuperarElementos(context, fechaFormat);
            mostrarResultados(elementos, mostrarEnLista)
        } catch (CertificadoNoEncontradoException e) {
            e.printStackTrace()
            procesarMensajeError(fechaFormat)
        }
    }

    /**
     * Recupera los elemtos del catálogo vigentes o por fecha especificada.
     *
     context El contexto de la invocación.
     * @param fecha Fecha de consulta.
     *
     * @return Lista de elemetos
     */
    private static List<ListadoModificadorCertificado> recuperarElementos(InvocationContext context, LocalDate fecha) {
        if (fecha) {
            getModificadorCertificadoRepository(context).consultarListadoPorUltimaActualizacion(fecha).collect()
        } else {
            [getModificadorCertificadoRepository(context).consultarListadoVigente()]
        }
    }

    /**
     * Muestra los resultados de la consulta según el formato especificado.
     *
     * @param elementos Elementos a mostrar.
     * @param mostrarEnLista Indica el formato de salida.
     */
    @SuppressWarnings("GroovyAssignabilityCheck")
    private void mostrarResultados(List<ListadoModificadorCertificado> elementos, Boolean mostrarEnLista) {
        Collections.sort(elementos, new Comparator<ListadoModificadorCertificado>() {
            @Override
            int compare(ListadoModificadorCertificado o1, ListadoModificadorCertificado o2) {
                return o2.ultimaActualizacion <=> o1.ultimaActualizacion
            }
        })

        elementos.each {
            out.println("Fecha Vigencia: ${ConvertirAFechaUtil.convertirAString(it.ultimaActualizacion)}", green)
            out.println(MostrarResultadosUtil
                .mostrarResultados(HEADERS, it.certificados, PROPIEDADES_CERTIFICADO, mostrarEnLista))
        }
    }

    /**
     * Crea el mensaje cuando se presenta un error en la consulta del catálogo
     *
     * @param fecha Fecha de consulta.
     *
     * @return Mesaje de error.
     */
    private static def procesarMensajeError(LocalDate fecha) {
        String msj

        if (fecha) {
            msj = "para la fecha solicitada."
        } else {
            msj = "vigente."
        }

        "No existe un Listado de Certificados $msj"
    }

    /**
     * Utilizado para crear el listado
     *
     * @param objects Contenido a procesar.
     * @param context El contexto de la invocación.
     *
     * @return Listado a actualizar
     */
    private static ListadoModificadorCertificado crearListado(List<Map<String, String>> objects, InvocationContext context) {
        Set<Certificado> certificadoSet = new HashSet<>()

        objects.eachWithIndex { Map<String, String> entry, int i ->
            BigDecimal factor

            try {
                factor = entry[FACTOR].toBigDecimal()
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "El formato del $FACTOR [${entry[FACTOR]}] no es valido.\nEn $entry", e);
            }

            Certificado certificado = CertificadoFactory.crear(entry[NOMBRE_CERTIFICADO], factor)
            certificadoSet.add(certificado)
        }

        getListadoModificadorCertificadoFactory(context).crear(LocalDate.now(), certificadoSet)
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
     * @return Referencia a la clase ListadoModificadorCertificadoFactory.
     */
    private static ListadoModificadorCertificadoFactory getListadoModificadorCertificadoFactory(InvocationContext context) {
        context.attributes['spring.beanfactory'].getBean(ListadoModificadorCertificadoFactory)
    }

}

