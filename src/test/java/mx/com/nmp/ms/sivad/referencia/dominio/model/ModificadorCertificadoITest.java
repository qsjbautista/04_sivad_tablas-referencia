package mx.com.nmp.ms.sivad.referencia.dominio.model;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoModificadorCertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.CertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoModificadorCertificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Pruebas de integración para la entidad {@link ListadoModificadorCertificado}
 *
 * @author mmarquez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ModificadorCertificadoITest {
    /**
     * Referencia a la clase que será probada.
     */
    @Inject
    private ListadoModificadorCertificadoFactory fabrica;

    @Inject
    private ListadoModificadorCertificadoFactory fabricaListado;

    private Certificado certificado;

    private Set<Certificado> listaCertificados;

    /**
     * Constructor.
     */
    public ModificadorCertificadoITest() {
        super();
    }

    /**
     * Se ejecuta antes de cada método marcado con {@code @Test}
     *
     * @throws Exception Si ocurre algún error.
     */
    @Before
    public void setUp() throws Exception {
        certificado = CertificadoFactory.crear("certificado", BigDecimal.valueOf(0.20));
        listaCertificados = new HashSet<Certificado>();
        listaCertificados.add(certificado);
    }

    /**
     * (non-Javadoc)
     * @see ListadoModificadorCertificadoFactory#crearPersistible(DateTime, LocalDate, Set<Certificado>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteParametrosNulosTest() {
        fabricaListado.crearPersistible(null, null, null);
    }

    /**
     * (non-Javadoc)
     * @see ListadoModificadorCertificadoFactory#crearPersistible(DateTime, LocalDate, Set<Certificado>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteFechaCargaNulaTest() {
        fabricaListado.crearPersistible(null, LocalDate.now(), listaCertificados);
    }

    /**
     * (non-Javadoc)
     * @see ListadoModificadorCertificadoFactory#crearPersistible(DateTime, LocalDate, Set<Certificado>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteFechaListadoNulaTest() {
        fabricaListado.crearPersistible(DateTime.now(), null, listaCertificados);
    }

    /**
     * (non-Javadoc)
     * @see ListadoModificadorCertificadoFactory#crearPersistible(DateTime, LocalDate, Set<Certificado>)
     */
    @Test(expected = IllegalArgumentException.class)
    public void crearWithPersistenteVONuloTest() {
        fabricaListado.crearPersistible(DateTime.now(), LocalDate.now(), null);
    }

    /**
     * (non-Javadoc)
     * @see ListadoModificadorCertificadoFactory#crearPersistible(DateTime, LocalDate, Set<Certificado>)
     */
    @Test
    public void crearWithPersistenteTest(){
        ListadoModificadorCertificado test = fabricaListado.crearPersistible(DateTime.now(), LocalDate.now(), listaCertificados);

        assertNotNull(test);
        assertNotNull(test.getCertificados());
        assertEquals(test.getCertificados().size(), 1);
        assertNotNull(test.getUltimaActualizacion());
        assertNotNull(test.getFechaListado());
    }

    /**
     * (non-Javadoc)
     * @see ListadoModificadorCertificadoFactory#crearPersistible(DateTime, LocalDate, Set<Certificado>)
     * @see ModificadorValorDiamante#actualizar()
     */
    @Test
    @Transactional
    public void actualizarTest(){
        ListadoModificadorCertificado modificador = fabricaListado.crearPersistible(DateTime.now(), LocalDate.now(), listaCertificados);
        ListadoModificadorCertificado test = modificador.actualizar();

        assertNotNull(test);
        assertNotNull(test.getCertificados());
        assertNotNull(test.getUltimaActualizacion());
        assertNotNull(test.getFechaListado());
    }
}
