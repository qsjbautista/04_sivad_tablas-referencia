package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ModificadorCertificadoJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Expone los metodos de acceso a datos para Modificador de Certificado.
 *
 * @author mmarquez
 */
@Repository
public interface ModificadorCertificadoRepositoryJPA extends JpaRepository<ModificadorCertificadoJPA, Long>{

    /**
     * Utilizado para obtener el un certificado por su clave.
     *
     * @param certificado Clave del certificado.
     * @return La entidad que coincida con los valores de los atributos indicados.
     */
    ModificadorCertificadoJPA findByCertificado(String certificado);
}
