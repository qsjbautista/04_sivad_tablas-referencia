package mx.com.nmp.ms.sivad.referencia.adminapi.ws;

import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionCodes;
import mx.com.nmp.ms.sivad.referencia.adminapi.exception.WebServiceExceptionFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DiamanteItemReader;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.DiamanteItemWriter;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.ValorComercialDiamanteBatchProcessor;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author osanchez
 */
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
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    @Inject
    private ValorComercialDiamanteRepository valorComercialDiamanteRepository;

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

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    @Transactional()
    public Void actualizarListaValorComercial(ActualizarListaValorComercialRequest parameters) {
        LOGGER.info(">> actualizarListaValorComercial({})", parameters);

        try {
            LOGGER.debug("iniciando ejecución...");

            // Reader
            List<PrecioCorte> listaPrecios = parameters.getListado().getPreciosCorte();

                // Reader
                ItemReader diamanteItemReader = new DiamanteItemReader(listaPrecios);

                // Step
                Step procesaPrecioStep = stepBuilderFactory.get("procesarPreciosDiamantes")
                    //Se guardan cada 1000 registros
                    .<PrecioCorteDetalle, Diamante>chunk(1000)
                    .reader(diamanteItemReader)
                    .processor(diamantesProcessor)
                    .writer(diamanteItemWriter)
                    .build();

                // Job
                Job diamantesJob = jobBuilderFactory.get("importarPreciosDiamantes")
                    .listener(jobCompletionNotificationListener)
                    .incrementer(new RunIdIncrementer())
                    .flow(procesaPrecioStep)
                    .end()
                    .build();

                // Execution
            JobParametersBuilder builder = new JobParametersBuilder();
            //Se agrega un timer para que no se repita nunca el job.
            builder.addLong("time", System.currentTimeMillis());
            //Se agrega la fecha de listado como parametro para que el listener la reciba.
            builder.addDate("fechaListado", parameters.getListado().getFecha().toGregorianCalendar().getTime());
            JobParameters jobParameters = builder.toJobParameters();
            JobExecution execution = jobLauncher.run(diamantesJob, jobParameters);
            LOGGER.info("Codigo de salida: {}", execution.getStatus());
           //}
        } catch (Exception e) {
            LOGGER.info("<< " + WebServiceExceptionCodes.NMPR004.getMessageException() + "." + e.getMessage());
            throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getMessageException(), e.getMessage());
        }

        return new Void();
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    public Void actualizarListaFactor(ActualizarListaFactorRequest parameters) {
        LOGGER.info(">> actualizarListaFactor({})", parameters);

        if (!ObjectUtils.isEmpty(parameters) && !ObjectUtils.isEmpty(parameters.getFactorDiamante())
            && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMinimo()) && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMedio())
            && !ObjectUtils.isEmpty(parameters.getFactorDiamante().getFactorMaximo())) {
            try {
                FactorValorDiamante vo = factorValorDiamanteFactory
                    .crearCon(parameters.getFactorDiamante().getFactorMinimo(), parameters.getFactorDiamante().getFactorMedio(),
                        parameters.getFactorDiamante().getFactorMaximo());
                ModificadorValorDiamante modificadorValorDiamante = modificadorValorDiamanteFactory.crearPersistibleCon(DateTime.now(), LocalDate.now(), vo);
                modificadorValorDiamante.actualizar();
            } catch (Exception e) {
                LOGGER.info("<< " + WebServiceExceptionCodes.NMPR004.getMessageException() + "." + e.getMessage());
                throw WebServiceExceptionFactory.crearWebServiceExceptionCon(WebServiceExceptionCodes.NMPR004.getMessageException(), e.getMessage());
            }
        } else {
            LOGGER.info("Valores nulos o vacios, parameters: ({}), minimo: ({}), medio: ({}), maximo: ({}) ", parameters,
                parameters.getFactorDiamante().getFactorMinimo(), parameters.getFactorDiamante().getFactorMedio(), parameters.getFactorDiamante().getFactorMaximo());
        }
        return new Void();
    }
}
