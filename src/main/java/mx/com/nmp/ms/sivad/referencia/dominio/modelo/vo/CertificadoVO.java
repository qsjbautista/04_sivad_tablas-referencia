/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo;

import java.math.BigDecimal;

/**
 * Value Object con la información del certificado diamante.
 *
 * @author mmarquez
 */
public class CertificadoVO {

    /**
     * Descripcion del certificado
     */
    private String certificado;


    // METODOS

    /**
     * Constructor.
     *
     * @param certificado La descripcion del certificado
     */
    public CertificadoVO(String certificado) {
        this.certificado = certificado;
    }

    public String getCertificado() {
        return certificado;
    }
}
