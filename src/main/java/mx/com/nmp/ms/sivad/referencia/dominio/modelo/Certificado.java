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
     * @param certificado Nombre del certificado.
     * @param factor Factor del certificado.
     */
    public Certificado(String certificado, BigDecimal factor) {
        this.certificado = certificado;
        this.factor = factor;
    }

    public String getCertificado() {
        return certificado;
    }

    public BigDecimal getFactor() {
        return factor;
    }
}
