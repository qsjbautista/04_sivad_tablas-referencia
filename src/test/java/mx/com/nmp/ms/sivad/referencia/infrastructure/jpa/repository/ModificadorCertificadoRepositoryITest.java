package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CertificadoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ListadoModificadorCertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Certificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.CertificadoFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoModificadorCertificado;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.CertificadoVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorCertificadoRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ListadoModificadorCertificadoJPA;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Pruebas de integración para el repositorio {@link ModificadorCertificadoRepository}
 *
 * @author mmarquez
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class ModificadorCertificadoRepositoryITest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModificadorCertificadoRepositoryITest.class);

    /**
     * Referencia a la clase que será probada
     */
    @Inject
    private ModificadorCertificadoRepository test;

    @Inject
    private ListadoModificadorCertificadoFactory fabricaListado;

    private CertificadoVO certificado;

    private Set<Certificado> listaCertificados;

    /**
     * Constructor.
     */
    public ModificadorCertificadoRepositoryITest() {
        super();
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#actualizarListado(ListadoModificadorCertificado)
     */
    @Test(expected = IllegalArgumentException.class)
    public void actualizarListadoNulaTest() {
        test.actualizarListado(null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoPorUltimaActualizacion(LocalDate)
     */
    @Test(expected = IllegalArgumentException.class)
    public void consultarListadoPorFechaCargaNulaTest() {
        test.consultarListadoPorUltimaActualizacion(null);
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoVigente()
     */
    @Test(expected = CertificadoNoEncontradoException.class)
    public void consultarVigentesNoDatosTest() {
        test.consultarListadoVigente();
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoVigente()
     */
    @Test
    public void consultarVigentesNoDatosCatchTest() {
        try {
            test.consultarListadoVigente();
        } catch (CertificadoNoEncontradoException e) {
            assertEquals(e.getClass().getClass(), CertificadoNoEncontradoException.class.getClass());
        }
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoPorUltimaActualizacion(LocalDate)
     */
    @Test(expected = CertificadoNoEncontradoException.class)
    public void consultarListadoPorFechaCargaNoDatosTest() {
        test.consultarListadoPorUltimaActualizacion(LocalDate.now());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarModificadorCertificadoVigente(CertificadoVO)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    public void consultarModificadorCertificadoVigenteTest() {
        certificado = new CertificadoVO("Certificado 1");
        Certificado entidad = test.consultarModificadorCertificadoVigente(certificado);

        assertNotNull(entidad);
        assertNotNull(entidad.getFactor());

        LOGGER.info(entidad.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoVigente()
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    public void consultarListadoVigenteTest() {
        ListadoModificadorCertificado entidad = test.consultarListadoVigente();

        assertNotNull(entidad);
        assertEquals(entidad.getCertificados().size(),4);

        LOGGER.info(entidad.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoPorUltimaActualizacion(LocalDate)
     */
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    @Test(expected = CertificadoNoEncontradoException.class)
    public void consultarPorFechaNoExisteTest() {
        test.consultarListadoPorUltimaActualizacion(LocalDate.now());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoPorUltimaActualizacion(LocalDate)
     */
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    @Test(expected = CertificadoNoEncontradoException.class)
    public void consultarPorFechaFuturaTest() {
        test.consultarListadoPorUltimaActualizacion(LocalDate.now().plusDays(1));
    }


    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoPorUltimaActualizacion(LocalDate)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    public void consultarPorFechaNoExisteCatchTest() {
        try {
            test.consultarListadoPorUltimaActualizacion(LocalDate.now());
        } catch (CertificadoNoEncontradoException e) {
            assertEquals(e.getEntidad().getClass(), ListadoModificadorCertificadoJPA.class.getClass());
        }
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoPorUltimaActualizacion(LocalDate)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    public void consultarPorFechaR2Test() {
        Set<ListadoModificadorCertificado> entidades = test.consultarListadoPorUltimaActualizacion(LocalDate.parse("2016-11-27"));

        assertNotNull(entidades);
        assertEquals(entidades.size(), 1);

        LOGGER.info(entidades.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#consultarListadoPorUltimaActualizacion(LocalDate)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    public void consultarPorFechaR1Test() {
        Set<ListadoModificadorCertificado> entidades = test.consultarListadoPorUltimaActualizacion(LocalDate.parse("2016-11-27"));

        assertNotNull(entidades);
        assertEquals(entidades.size(), 1);

        LOGGER.info(entidades.toString());
    }

    /**
     * (non-Javadoc)
     * @see ModificadorCertificadoRepository#actualizarListado(ListadoModificadorCertificado)
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-modificador_certificado-h2.sql")
    public void actualizarListadoTest() {
        ListadoModificadorCertificado listadoInicialIda = test.consultarListadoVigente();
        assertNotNull(listadoInicialIda);
        assertEquals(listadoInicialIda.getCertificados().size(), 4);

        Set<ListadoModificadorCertificado> histListadoInicialIda = test.consultarListadoPorUltimaActualizacion(LocalDate.parse("2016-11-26"));
        assertNotNull(histListadoInicialIda);
        assertEquals(histListadoInicialIda.size(), 4);

        Set<Certificado> certificados = new HashSet<>();
        Certificado cert1 = CertificadoFactory.crear("certificado 1", new BigDecimal(10));
        Certificado cert2 = CertificadoFactory.crear("certificado 2", new BigDecimal(15));
        Certificado cert3 = CertificadoFactory.crear("certificado 3", new BigDecimal(20));
        certificados.add(cert1);
        certificados.add(cert2);
        certificados.add(cert3);

        ListadoModificadorCertificado listado = fabricaListado.crear(DateTime.parse("2016-11-27T01"), LocalDate.now().plusDays(-1),certificados);
        listado = test.actualizarListado(listado);
        assertEquals(listado.getCertificados().size(), 3);
        ListadoModificadorCertificado resultListadoVigente = test.consultarListadoVigente();
        assertEquals(resultListadoVigente.getCertificados().size(), 3);
        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getUltimaActualizacion());
        assertNotNull(resultListadoVigente.getCertificados());
        assertFalse(resultListadoVigente.getCertificados().isEmpty());
        assertTrue(resultListadoVigente.getCertificados().size() == 3);

        histListadoInicialIda = test.consultarListadoPorUltimaActualizacion(LocalDate.parse("2016-11-26"));

        assertNotNull(histListadoInicialIda);
        assertNotNull(histListadoInicialIda.isEmpty());
        assertEquals(histListadoInicialIda.size(),4);

        Certificado cert4 = CertificadoFactory.crear("certificado 4", new BigDecimal(25));

        certificados.clear();
        certificados.add(cert4);
        listado = fabricaListado.crear(DateTime.now(), LocalDate.now(),certificados);
        listado = test.actualizarListado(listado);
        assertEquals(listado.getCertificados().size(),1);
        resultListadoVigente = test.consultarListadoVigente();
        assertEquals(resultListadoVigente.getCertificados().size(),1);

        histListadoInicialIda = test.consultarListadoPorUltimaActualizacion(LocalDate.parse("2016-11-27"));

        assertNotNull(histListadoInicialIda);
        assertNotNull(histListadoInicialIda.isEmpty());
        assertEquals(histListadoInicialIda.size(),1);

        assertNotNull(resultListadoVigente);
        assertNotNull(resultListadoVigente.getUltimaActualizacion());
        assertNotNull(resultListadoVigente.getCertificados());
        assertFalse(resultListadoVigente.getCertificados().isEmpty());
        assertTrue(resultListadoVigente.getCertificados().size() == 1);

        listado = test.consultarListadoVigente();

        assertNotNull(listado);
        assertNotNull(listado.getCertificados());
        assertEquals(listado.getCertificados().size(),1);
    }
}
