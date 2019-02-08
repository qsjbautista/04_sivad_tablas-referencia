/**
 * Proyecto:        NMP - Sistema de Operacion Prendaria Emergente
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.TablasReferenciaApplication;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.TipoHechuraNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.*;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.MetalVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.TipoHechuraAlhajaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Clase de prueba para el repositorio TipoHechuraAlhajaRepository.
 *
 * @author ecancino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TablasReferenciaApplication.class)
public class TipoHechuraAlhajaRepositoryIntTest {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TipoHechuraAlhajaRepositoryIntTest.class);

    private static final String CALIDAD_METAL_EXISTE = "8_Q";
    private static final String CALIDAD_METAL_NO_EXISTE = "0.125";
    private static final String TIPO_METAL_EXISTE = "AU";
    private static final String TIPO_METAL_EXISTE_2 = "PD";
    private static final String TIPO_METAL_NO_EXISTE = "Cobre";
    private static final String SUBRAMO_EXISTE = "AL";
    private static final String SUBRAMO_NO_EXISTE = "TE";

    /**
     * Referencia al repositorio de TipoHechuraAlhajaRepository.
     */
    @Inject
    private TipoHechuraAlhajaRepository tipoHechuraAlhajaRepository;



    // METODOS

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuyo tipo de metal, calidad
     * y subramo no existen.
     */
    @Test(expected = TipoHechuraNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest01() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_NO_EXISTE, CALIDAD_METAL_NO_EXISTE, SUBRAMO_NO_EXISTE);
        tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuyo tipo de metal y subramo
     * no existen.
     */
    @Test(expected = TipoHechuraNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest02() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_NO_EXISTE, CALIDAD_METAL_EXISTE, SUBRAMO_NO_EXISTE);
        tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuya calidad y subramo no existen.
     */
    @Test(expected = TipoHechuraNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest03() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_EXISTE, CALIDAD_METAL_NO_EXISTE, SUBRAMO_NO_EXISTE);
        tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuyo subramo no existe.
     */
    @Test(expected = TipoHechuraNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest04() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_EXISTE, CALIDAD_METAL_EXISTE, SUBRAMO_NO_EXISTE);
        tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuya calidad no existe.
     */
    @Test(expected = TipoHechuraNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest05() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_EXISTE, CALIDAD_METAL_NO_EXISTE, SUBRAMO_EXISTE);
        tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuyo tipo de metal no existe.
     */
    @Test(expected = TipoHechuraNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest06() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_NO_EXISTE, CALIDAD_METAL_EXISTE, SUBRAMO_EXISTE);
        tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuyo tipo de metal y calidad
     * no existen.
     */
    @Test(expected = TipoHechuraNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest07() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_NO_EXISTE, CALIDAD_METAL_NO_EXISTE, SUBRAMO_EXISTE);
        tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuyo tipo de metal, calidad y
     * subramo existen.
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest08() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_EXISTE, CALIDAD_METAL_EXISTE, SUBRAMO_EXISTE);
        List<TipoHechura> result = tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);

        assertNotNull(result);
        assertTrue(result.size() == 4);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuyo tipo de metal no existe y
     * la calidad es nula.
     */
    @Test(expected = TipoHechuraNoEncontradoException.class)
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest09() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_NO_EXISTE, null, SUBRAMO_EXISTE);
        tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);
    }

    /**
     * Utilizado para consultar un listado de tipos de hechura o rangos de metal, cuyo tipo de metal y subramo existen
     * y la calidad es nula.
     */
    @Test
    @Transactional
    @Sql("/bd/test-data-tipo_hechura-h2.sql")
    public void obtenerTiposHechuraTest10() {
        MetalVO metalVO = new MetalVO(TIPO_METAL_EXISTE_2, null, SUBRAMO_EXISTE);
        List<TipoHechura>  result = tipoHechuraAlhajaRepository.consultarTiposHechura(metalVO);

        assertNotNull(result);
        assertTrue(result.size() == 1);
    }
}
