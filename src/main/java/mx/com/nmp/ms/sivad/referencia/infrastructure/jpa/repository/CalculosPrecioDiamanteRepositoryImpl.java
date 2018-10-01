/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository;

import mx.com.nmp.ms.sivad.referencia.conector.ConsultaTipoCambio;
import mx.com.nmp.ms.sivad.referencia.conector.Convertidor;
import mx.com.nmp.ms.sivad.referencia.conector.TipoCambioEnum;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.CastigoRangoPesoNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.FactorDepreciacionNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.exception.TipoCambioNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.DiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.CalculosPrecioDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.CastigoRangoPesoDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactorDepreciacionDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.PrecioCorteDetalleBatch;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.TipoNuevoRegistro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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

    /* (non-Javadoc)
     * @see mx.com.nmp.ms.sivad.referencia.dominio.repository.CalculosPrecioDiamanteRepository#calcularPreciosNuevosRegistros(mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.PrecioCorteDetalleBatch)
     */


    /**
     * {@inheritDoc}
     */
    @Override
    public Diamante calcularColumnas(PrecioCorteDetalleBatch precioCorteDetalle) {
        Diamante diamante;

        //0. Calcula precio para los nuevos rangos color y quilates
        calcularPreciosNuevosRegistros(precioCorteDetalle);

        //CALCULOS DE LOS VALORES

        //1. TIPO DE CAMBIO
        BigDecimal tipoCambio = consultaTipoCambio.valorPorUnidad(
            TipoCambioEnum.USD.getTipo(), TipoCambioEnum.MXN.getTipo());

        if (ObjectUtils.isEmpty(tipoCambio)) {
            String msg = "No existe un tipo de cambio.";
            LOGGER.warn(msg);
            throw new TipoCambioNoEncontradoException(ConsultaTipoCambio.class, msg);
        }


        //2. MONTOVBD (Valor base de datos convertido a pesos con depreciacion), CON LA FORMULA 'VBD = VR * 100 * TC * FD

        //VR: VR (Valor del Rapaport)
        BigDecimal montoVbd = precioCorteDetalle.getPrecio();

        //VR * 100 * TC: CONVERTIR A PESOS, EL MONTO POR EL TC (Tipo de cambio, precio dolar)
        montoVbd = convertidor.convertir(
            TipoCambioEnum.USD.getTipo(), TipoCambioEnum.MXN.getTipo(), montoVbd);

        //VR * 100 * TC * FD: APLICAR EL FD (Factor de depreciacion)
        FactorDepreciacionDiamanteJPA factorDepreciacionDiamanteJPA = factorDepreciacionDiamanteJPARepository.obtenerFactorDepreciacion();

        if (ObjectUtils.isEmpty(factorDepreciacionDiamanteJPA)) {
            String msg = "No existe un factor de depreciacion.";
            LOGGER.warn(msg);
            throw new FactorDepreciacionNoEncontradoException(FactorDepreciacionDiamanteJPA.class, msg);
        }

        if (factorDepreciacionDiamanteJPA.getFactor().compareTo(BigDecimal.ZERO) > 0) {
            montoVbd = montoVbd.multiply(factorDepreciacionDiamanteJPA.getFactor());
        }


        //3. MONTOFCASTIGOXRANGO: CAMPO montoVbd, APLICANDO EL CASTIGO POR RANGO DE PESO
        CastigoRangoPesoDiamanteJPA castigoRangoPesoDiamanteJPA = castigoRangoPesoDiamanteJPARepository.obtenerCastigoRangoPeso(
            precioCorteDetalle.getTamanioInferior(), precioCorteDetalle.getTamanioSuperior());

        if (ObjectUtils.isEmpty(castigoRangoPesoDiamanteJPA)) {
            String msg = "No existe un porcentaje de castigo por rango de peso para las características de diamante solicitadas.";
            LOGGER.warn(msg);
            throw new CastigoRangoPesoNoEncontradoException(CastigoRangoPesoDiamanteJPA.class, msg);
        }

        BigDecimal montofCastigoxRango = montoVbd;
        if (castigoRangoPesoDiamanteJPA.getFactor().compareTo(BigDecimal.ZERO) > 0) {
            montofCastigoxRango = montofCastigoxRango.multiply(castigoRangoPesoDiamanteJPA.getFactor());
        }


        diamante = DiamanteFactory.create(precioCorteDetalle.getCorte(),
            precioCorteDetalle.getColor(),
            precioCorteDetalle.getClaridad(),
            precioCorteDetalle.getTamanioInferior(),
            precioCorteDetalle.getTamanioSuperior(),
            precioCorteDetalle.getPrecio(),
            tipoCambio.setScale(4, BigDecimal.ROUND_HALF_UP),
            montoVbd.setScale(4, BigDecimal.ROUND_HALF_UP),
            montofCastigoxRango.setScale(4, BigDecimal.ROUND_HALF_UP));

        return diamante;
    }

    /**
     * Si es un nuevo registro de quilates o color se calcula el precio usando el % factor configurado
     * @param precioCorteDetalle
     */
    private void calcularPreciosNuevosRegistros(PrecioCorteDetalleBatch precioCorteDetalle) {

		// ---> multiplicar por el factor

		if (precioCorteDetalle.isNuevoRegistro(TipoNuevoRegistro.QUILATES)) {

			precioCorteDetalle.setPrecio(
					precioCorteDetalle.getPrecio().multiply(precioCorteDetalle.getFactorQuilates()));

		}

		if (precioCorteDetalle.isNuevoRegistro(TipoNuevoRegistro.COLOR)) {

			precioCorteDetalle.setPrecio(
					precioCorteDetalle.getPrecio().multiply(precioCorteDetalle.getFactorColor()));

		}

		if (precioCorteDetalle.isNuevoRegistro(TipoNuevoRegistro.QUILATES_COLOR)) {

			// Factor quilataje
			precioCorteDetalle.setPrecio(
					precioCorteDetalle.getPrecio().multiply(precioCorteDetalle.getFactorQuilates()));

			// Factor color
			precioCorteDetalle.setPrecio(
					precioCorteDetalle.getPrecio().multiply(precioCorteDetalle.getFactorColor()));

		}

    }

}
