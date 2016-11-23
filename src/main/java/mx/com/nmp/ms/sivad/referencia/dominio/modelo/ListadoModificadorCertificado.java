/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.Set;

/**
 * Entidad que representa la lista del modificador de certificado de diamantes.
 *
 * @author mmarquez
 */
public class ListadoModificadorCertificado {

    /**
     * Identificador del registro.
     */
    private Long id;

    /**
     * Fecha en que se realizo la carga.
     */
    private Date fechaCarga;

    /**
     * Fecha en que se realizo el listado.
     */
    private Date fechaListado;

    /**
     * Lista de modificadores de certificados.
     */
    private Set<Certificado> certificados;

    /**
     * Referencia al repositorio de ModificadorCertificadoRepository.
     */
    @Inject
    private ModificadorCertificadoRepository modificadorCertificadoRepository;



    // METODOS

    /**
     * Constructor.
     *
     * @param certificados Lista de los certificados del diamante.
     */
    ListadoModificadorCertificado(Set<Certificado> certificados) {
        this.certificados = certificados;
    }

    /**
     * Constructor.
     *
     * @param id Identificador del registro.
     * @param fechaCarga Fecha en que se realiza la carga.
     * @param fechaListado Fecha en del listado.
     * @param certificados Lista de los certificados del diamante.
     */
    ListadoModificadorCertificado(Long id, Date fechaCarga, Date fechaListado, Set<Certificado> certificados) {
        this.id = id;
        this.fechaCarga = fechaCarga;
        this.fechaListado = fechaListado;
        this.certificados = certificados;
    }

    /**
     * Permite actualizar el listado de certificados diamante.
     *
     * @param listado Lista de los certificados del diamante, con el cual se desea reemplazar la lista vigente.
     */
    public void actualizar(ListadoModificadorCertificado listado) {
        modificadorCertificadoRepository.actualizarListado(listado);
    }



    // GETTERS

    public Long getId() {
        return id;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public Date getFechaListado() {
        return fechaListado;
    }

    public Set<Certificado> getCertificados() {
        return certificados;
    }

}
