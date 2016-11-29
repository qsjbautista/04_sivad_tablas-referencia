/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import java.math.BigDecimal;

/**
 * Fábrica que se encarga de crear objetos de Certificado.
 *
 * @author mmarquez
 */
public class CertificadoFactory {

    /**
     * Permite crear una entidad de tipo Certificado con base en los argumentos recibidos.
     *
     * @param certificado descripcion del certificado.
     * @param factor cantidad del factor.
     * @return La entidad creada.
     */
    public static Certificado crear(String certificado, BigDecimal factor) {
        return new Certificado(certificado, factor);
    }

}
