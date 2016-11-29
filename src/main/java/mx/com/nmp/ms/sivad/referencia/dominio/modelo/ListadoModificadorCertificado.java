/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import java.util.Set;

/**
 * Entidad que representa la lista del modificador de certificado de diamantes.
 *
 * @author mmarquez
 */
public class ListadoModificadorCertificado {

    /**
     * Fecha en que se realizo la carga.
     */
    private DateTime fechaCarga;

    /**
     * Fecha en que se realizo el listado.
     */
    private LocalDate fechaListado;

    /**
     * Lista de modificadores de certificados.
     */
    private Set<Certificado> certificados;

    /**
     * Referencia al repositorio de ModificadorCertificadoRepository.
     */
    private ModificadorCertificadoRepository modificadorCertificadoRepository;

    /**
     * Interface que define el contrato para crear entidades ListadoModificadorCertificado
     */
    public interface Builder {
        DateTime getFechaCarga();

        LocalDate getFechaListado();

        Set<Certificado> getCertificados();
    }


    // METODOS

    /**
     * Constructor. Privado para que solo la fabrica tenga acceso a crear objetos.
     *
     * @param builder Referencia al objeto que contiene los datos necesarios para construir la entidad.
     * @param modificadorCertificadoRepository Referencia al repositorio de entidades.
     */
    private ListadoModificadorCertificado(Builder builder, ModificadorCertificadoRepository modificadorCertificadoRepository) {
        super();

        fechaCarga = builder.getFechaCarga();
        fechaListado = builder.getFechaListado();
        certificados = builder.getCertificados();

        this.modificadorCertificadoRepository = modificadorCertificadoRepository;
    }

    /**
     * Permite actualizar el listado de certificados diamante.
     *
     */
    public ListadoModificadorCertificado actualizar() {
        return modificadorCertificadoRepository.actualizarListado(this);
    }



    // GETTERS


    public DateTime getFechaCarga() {
        return fechaCarga;
    }

    public LocalDate getFechaListado() {
        return fechaListado;
    }

    public Set<Certificado> getCertificados() {
        return certificados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListadoModificadorCertificado)) return false;

        ListadoModificadorCertificado that = (ListadoModificadorCertificado) o;

        if (!getFechaCarga().equals(that.getFechaCarga())) return false;
        if (!getCertificados().equals(that.getCertificados())) return false;
        return modificadorCertificadoRepository.equals(that.modificadorCertificadoRepository);
    }


    @Override
    public String toString() {
        return "ListadoModificadorCertificado{" +
            "fechaCarga=" + fechaCarga +
            ", fechaListado=" + fechaListado +
            ", certificados=" + certificados +
            ", modificadorCertificadoRepository=" + modificadorCertificadoRepository +
            '}';
    }
}
