package mx.com.nmp.ms.sivad.referencia.adminapi.ws;

import com.codahale.metrics.annotation.Timed;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.validador.ValidadorFecha;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.FactoresRangoColorJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ParametrosQuilatesJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.ValorComercialDiamanteJPA;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DiamanteItemReader;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.PrecioCorteDetalleBatch;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.ValorComercialDiamanteBatchProcessor;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.FactoresRangoColorJPARepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.repository.ParametrosQuilatesRepositoryJPA;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.ReferenciaListasDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.*;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import javax.xml.datatype.XMLGregorianCalendar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author osanchez
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ReferenciaListasDiamantesServiceEndpoint implements ReferenciaListasDiamanteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaListasDiamantesServiceEndpoint.class);

    @Inject
    JobLauncher jobLauncher;

    @Inject
    public JobBuilderFactory jobBuilderFactory;

    @Inject
    public StepBuilderFactory stepBuilderFactory;

    @Qualifier("valorComercialDiamanteProcessor")
    @Autowired()
    ItemProcessor diamantesProcessor;

    @Qualifier("diamanteItemWriter")
    @Autowired
    ItemWriter diamanteItemWriter;

    @Qualifier("jobCompletionNotificationListener")
    @Autowired
    JobExecutionListenerSupport jobCompletionNotificationListener;

    /**
     * Referencia al repositorio de ModificadorValorDiamanteFactory.
     */
    @Inject
    ModificadorValorDiamanteFactory modificadorValorDiamanteFactory;

    /**
     * Referencia a la fabrica de Value Object
     */
    @Inject
    FactorValorDiamanteFactory factorValorDiamanteFactory;

    /**
     * Referencia a la fabrica de Value Object
     */
    @Inject
    ValorComercialDiamanteBatchProcessor valorComercialDiamanteBatchProcessor;

    @Inject
    private TaskExecutor taskExecutor;
    
    @Inject
	private ParametrosQuilatesRepositoryJPA parametrosQuilatesRepositoryJPA;
    
    @Inject
	private FactoresRangoColorJPARepository factoresRangoColorJPARepository;

    /**
     * @param parameters Parametros
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
	@Timed
	@Override
	public Void actualizarListaValorComercial(ActualizarListaValorComercialRequest parameters) {
		LOGGER.info(">> actualizarListaValorComercial({})", parameters);

		validarDatosEntrada(parameters.getListado());

		try {
			LOGGER.debug("iniciando ejecuciÃƒÆ’Ã‚Â³n...");

			// Preparar lista para el Reader
			List<PrecioCorte> listaPrecios = parameters.getListado().getPreciosCorte();
			Queue<PrecioCorteDetalleBatch> preciosDiamantes = new ConcurrentLinkedQueue<>();
			
			List<ParametrosQuilatesJPA> parametrosQuilates = parametrosQuilatesRepositoryJPA.findByUltimaActualizacion();
			List<FactoresRangoColorJPA> factoresRangoColor = factoresRangoColorJPARepository.findByUltimaActualizacion();
			

			for (PrecioCorte pc : listaPrecios) {
				for (PrecioCorteDetalle pcd : pc.getPrecioCorte()) {
					PrecioCorteDetalleBatch pcdb = new PrecioCorteDetalleBatch(pc.getCorte(), pcd);
					
					preciosDiamantes.add(pcdb);
					
					// --> Nuevos registros
					Queue<PrecioCorteDetalleBatch> pq = obtenerParametrosQuilates(pcd, pc, parametrosQuilates, preciosDiamantes);
					preciosDiamantes.addAll(pq);
					
					
					Queue<PrecioCorteDetalleBatch> frc = obtenerFactoresRangoColor(pcd, pc, factoresRangoColor, preciosDiamantes);
					preciosDiamantes.addAll(frc);
					
				}
			}

			// Reader
			ItemReader diamanteItemReader = new DiamanteItemReader(preciosDiamantes);

			// Step
			Step procesaPrecioStep = stepBuilderFactory.get("procesarPreciosDiamantes")
					// Se guardan cada 500 registros
					.<PrecioCorteDetalle, Diamante>chunk(500).reader(diamanteItemReader).processor(diamantesProcessor)
					.writer(diamanteItemWriter).taskExecutor(taskExecutor).build();

			// Job
			Job diamantesJob = jobBuilderFactory.get("importarPreciosDiamantes")
					.listener(jobCompletionNotificationListener).incrementer(new RunIdIncrementer())
					.flow(procesaPrecioStep).end().build();

			// Execution
			JobParametersBuilder builder = new JobParametersBuilder();
			// Se agrega un timer para que no se repita nunca el job.
			builder.addLong("time", System.currentTimeMillis());
			// Se agrega la fecha de listado como parametro para que el listener la reciba.
			builder.addDate("fechaListado", parameters.getListado().getFecha().toGregorianCalendar().getTime());
			JobParameters jobParameters = builder.toJobParameters();
			JobExecution execution = jobLauncher.run(diamantesJob, jobParameters);

			LOGGER.info("Codigo de salida: {}", execution.getStatus());
			if (execution.getStatus() == BatchStatus.FAILED) {
				String mensajesError = "";
				for (Throwable excepcion : execution.getAllFailureExceptions()) {
					mensajesError = mensajesError.concat(excepcion.getMessage());
				}
				throw WebServiceExceptionFactory.crearWebServiceExceptionCon(
						WebServiceExceptionCodes.NMPR004.getCodeException(), mensajesError);
			}
			// }

		} catch (Exception e) {
			LOGGER.info("<<" + WebServiceExceptionCodes.NMPR004.getMessageException() + "." + e.getMessage());
			throw WebServiceExceptionFactory.crearWebServiceExceptionCon(
					WebServiceExceptionCodes.NMPR004.getCodeException(),
					WebServiceExceptionCodes.NMPR004.getMessageException(), e);
		}

		return new Void();
	}

    /**
     * @param parameters Parametros
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    public Void actualizarListaFactor(ActualizarListaFactorRequest parameters) {
        LOGGER.info(">> actualizarListaFactor({})", parameters);

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getFactorDiamante())
            && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMinimo()) && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMedio())
            && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMaximo())) {
            if (parameters.getFactorDiamante().getFactorMinimo().compareTo(parameters.getFactorDiamante().getFactorMedio()) <= 0 &&
                parameters.getFactorDiamante().getFactorMedio().compareTo(parameters.getFactorDiamante().getFactorMaximo()) <= 0) {
                try {
                    FactorValorDiamante vo = factorValorDiamanteFactory
                        .crearCon(parameters.getFactorDiamante().getFactorMinimo(), parameters.getFactorDiamante().getFactorMedio(),
                            parameters.getFactorDiamante().getFactorMaximo());
                    ModificadorValorDiamante modificadorValorDiamante = modificadorValorDiamanteFactory.crearPersistibleCon(DateTime.now(), LocalDate.now(), vo);
                    modificadorValorDiamante.actualizar();
                } catch (Exception e) {
                    LOGGER.info("<< " + WebServiceExceptionCodes.NMPR004.getMessageException() + "." + e.getMessage());
                    throw WebServiceExceptionFactory
                        .crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getCodeException(),
                            WebServiceExceptionCodes.NMPR004.getMessageException(), e);
                }
            } else {
                LOGGER.info("Los valores de los parametros deben ser congruentes: ({}), minimo: ({}), medio: ({}), maximo: ({}) ", parameters,
                    parameters.getFactorDiamante().getFactorMinimo(), parameters.getFactorDiamante().getFactorMedio(), parameters.getFactorDiamante().getFactorMaximo());
                throwWebServiceException();
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), minimo: ({}), medio: ({}), maximo: ({}) ", parameters,
                parameters.getFactorDiamante().getFactorMinimo(), parameters.getFactorDiamante().getFactorMedio(), parameters.getFactorDiamante().getFactorMaximo());
            throwWebServiceException();
        }
        return new Void();
    }

    private static void validarDatosEntrada(PreciosDiamante datos) {
        validarFecha(datos.getFecha());
        if (ObjectUtils.isEmpty(datos.getPreciosCorte())) {
            throwWebServiceException();
        } else {
            for (PrecioCorte pc : datos.getPreciosCorte()) {
                if (ObjectUtils.isEmpty(pc.getPrecioCorte())) {
                    throwWebServiceException();
                }
            }
        }
    }

    private static void validarFecha(XMLGregorianCalendar fecha) {
        try {
            ValidadorFecha.validarFechaFutura(LocalDate.fromCalendarFields(
                fecha.toGregorianCalendar()), "La fecha de listado no debe ser posterior a la fecha actual.");
        } catch (IllegalArgumentException e) {
            throw WebServiceExceptionFactory
                .crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getCodeException(),
                    WebServiceExceptionCodes.NMPR004.getMessageException(), e.getMessage());
        }
    }

    private static void throwWebServiceException() {
        throw WebServiceExceptionFactory
            .crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getCodeException(),
                WebServiceExceptionCodes.NMPR004.getMessageException());
    }
    
	private Queue<PrecioCorteDetalleBatch> obtenerParametrosQuilates(PrecioCorteDetalle pcd, PrecioCorte pc, List<ParametrosQuilatesJPA> parametrosQuilates, Queue<PrecioCorteDetalleBatch> preciosDiamantes) {

		PrecioCorteDetalleBatch pcBase = new PrecioCorteDetalleBatch(pc.getCorte(), pcd);
		
		for (ParametrosQuilatesJPA param : parametrosQuilates) {

			if (param.getQuilatesBaseDesde().compareTo(pcd.getTamanioInferior()) == 0
					&& param.getQuilatesBaseHasta().compareTo(pcd.getTamanioSuperior()) == 0) {
				
				

				pcBase.setNuevoRegistroBase(true);
				pcBase.setClaridad("Claridad");
				pcBase.setColor("Color");
				pcBase.setTamanioInferior(param.getQuilatesDesde());
				pcBase.setTamanioSuperior(param.getQuilatesHasta());
				pcBase.setFactorParametros(param.getPorcentaje());
				pcBase.setPrecio(new BigDecimal("0"));

				preciosDiamantes.add(pcBase);

				break;

			}

		}
		return preciosDiamantes;

	}
    
	private Queue<PrecioCorteDetalleBatch> obtenerFactoresRangoColor(PrecioCorteDetalle pcd, PrecioCorte pc, List<FactoresRangoColorJPA> factoresRangoColor, Queue<PrecioCorteDetalleBatch> preciosDiamantes) {

		PrecioCorteDetalleBatch pcColor = new PrecioCorteDetalleBatch(pc.getCorte(), pcd);
		PrecioCorteDetalleBatch pColor = new PrecioCorteDetalleBatch(pc.getCorte(), pcd);
		
		for (FactoresRangoColorJPA param : factoresRangoColor) {

			if (param.getRangoColorBase() == pcd.getColor()) {

				
				
				pcColor.setNuevoRegistroColor(true);
				pcColor.setClaridad("claridad");
				pcColor.setFactorColor(param.getFactor());
				pcColor.setColor(param.getColorDesde());
				pcColor.setTamanioInferior(new BigDecimal("0"));
				pcColor.setTamanioSuperior(new BigDecimal("0"));
				pcColor.setPrecio(new BigDecimal("0"));
				
				
				preciosDiamantes.add(pcColor);
				
				

				pColor.setNuevoRegistroColor(true);
				pColor.setClaridad("claridad");
				pColor.setFactorColor(param.getFactor());
				pColor.setColor(param.getColorHasta());
				pColor.setTamanioInferior(new BigDecimal("0"));
				pColor.setTamanioSuperior(new BigDecimal("0"));
				pColor.setPrecio(new BigDecimal("0"));

				preciosDiamantes.add(pColor);

				break;

			}
		}

		return preciosDiamantes;
	}
}
