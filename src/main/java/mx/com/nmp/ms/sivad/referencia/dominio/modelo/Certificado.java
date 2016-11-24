/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;

/**
 * Entidad que representa el certificado de un diamante.
 *
 * @author mmarquez
 */
public class Certificado {

    /**
     * Identificador del certificado.
     */
    private Long id;

    /**
     * Nombre del certificado.
     */
    private String certificado;

    /**
     * Factor del certifcado del diamante
     */
    private BigDecimal factor;

    /**
     * Constructor.
     *
     * @param id Identificador del certificado.
     * @param certificado Nombre del certificado.
     * @param factor Factor del certificado.
     */
    public Certificado(Long id, String certificado, BigDecimal factor) {
        this.id = id;
        this.certificado = certificado;
        this.factor = factor;
    }

    /**
     * Constructor.
     *
     * @param certificado Nombre del certificado.
     * @param factor Factor del certificado.
     */
    public Certificado(String certificado, BigDecimal factor) {
        this.certificado = certificado;
        this.factor = factor;
    }

    public Long getId() {
        return id;
    }

    public String getCertificado() {
        return certificado;
    }

    public BigDecimal getFactor() {
        return factor;
    }
}
