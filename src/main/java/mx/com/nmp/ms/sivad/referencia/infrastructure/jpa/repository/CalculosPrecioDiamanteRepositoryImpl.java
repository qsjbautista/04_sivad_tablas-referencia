/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.conector.ConsultaTipoCambio;
import mx.com.nmp.ms.sivad.referencia.conector.Convertidor;
import mx.com.nmp.ms.sivad.referencia.conector.TipoCambioEnum;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.CalculosPrecioDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.PrecioCorteDetalleBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Implementación de CalculosPrecioDiamanteRepository.
 *
 * @author ecancino
 */
@Component
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class CalculosPrecioDiamanteRepositoryImpl implements CalculosPrecioDiamanteRepository {

    /**
     * Utilizada para manipular los mensajes informativos y de error.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculosPrecioDiamanteRepositoryImpl.class);

    private static final BigDecimal FACTOR = BigDecimal.valueOf(100);

    /**
     * Referencia al repositorio de FactorDepreciacionDiamanteJPARepository.
     */
    @Inject
    private FactorDepreciacionDiamanteJPARepository factorDepreciacionDiamanteJPARepository;

    /**
     * Referencia al repositorio de CastigoRangoPesoDiamanteJPARepository.
     */
    @Inject
    private CastigoRangoPesoDiamanteJPARepository castigoRangoPesoDiamanteJPARepository;

    /**
     * Referencia al conector con microservicio de tipo cambiario.
     */
    @Inject
    private Convertidor convertidor;

    /**
     * Referencia al consultor de tipo de cambio en el microservicio de tipo cambiario.
     */
    @Inject
    private ConsultaTipoCambio consultaTipoCambio;



    // METODOS

    /**
     * {@inheritDoc}
     */
    @Override
    public Diamante calcularColumnas(PrecioCorteDetalleBatch precioCorteDetalle) {
        Diamante diamante = null;

        //CALCULOS DE LOS VALORES

        //1. TIPO DE CAMBIO
        BigDecimal tipoCambio = consultaTipoCambio.valorPorUnidad(
            TipoCambioEnum.USD.getTipo(), TipoCambioEnum.MXN.getTipo());


        //2. MONTOVBD (Valor base de datos convertido a pesos con depreciacion), CON LA FORMULA 'VBD = VR * 100 * TC * FD

        //VR * 100: MULTIPLICAR VR (Valor del Rapaport) * 100
        BigDecimal montoVbd = precioCorteDetalle.getPrecio().multiply(FACTOR);

        //VR * 100 * TC: CONVERTIR A PESOS, EL MONTO POR EL TC (Tipo de cambio, precio dolar)
        montoVbd = convertidor.convertir(
            TipoCambioEnum.USD.getTipo(), TipoCambioEnum.MXN.getTipo(), montoVbd);

        //VR * 100 * TC * FD: APLICAR EL FD (Factor de depreciacion)
        montoVbd = montoVbd.multiply(factorDepreciacionDiamanteJPARepository.obtenerFactorDepreciacion().getFactor());


        //3. MONTOFCASTIGOXRANGO: CAMPO montoVbd, APLICANDO EL CASTIGO POR RANGO DE PESO
        BigDecimal montofCastigoxRango = montoVbd.multiply(
            castigoRangoPesoDiamanteJPARepository.obtenerCastigoRangoPeso(precioCorteDetalle.getTamanioInferior(),
                precioCorteDetalle.getTamanioSuperior()).getFactor());


        diamante = DiamanteFactory.create(precioCorteDetalle.getCorte(),
            precioCorteDetalle.getColor(),
            precioCorteDetalle.getClaridad(),
            precioCorteDetalle.getTamanioInferior(),
            precioCorteDetalle.getTamanioSuperior(),
            precioCorteDetalle.getPrecio(),
            tipoCambio,
            montoVbd,
            montofCastigoxRango);

        return diamante;
    }
}
