package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ListadoValorComercialDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    @Inject
    private ValorComercialDiamanteRepository valorComercialDiamanteRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info(">> beforeJob({})", jobExecution);

        jobExecution.getJobParameters().getDate("fechaListado");

        //Se crea la lista con la fecha de listado vacia, solo para que pase a historico y crear nueva lista
        ListadoValorComercialDiamante listadoValorComercialDiamante = ListadoValorComercialDiamanteFactory
            .create(new LocalDate(jobExecution.getJobParameters().getDate("fechaListado")), new HashSet());
        valorComercialDiamanteRepository.actualizarListado(listadoValorComercialDiamante);

        LOGGER.info("Se crea la lista y se guarda en historico la anterior");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info(">> afterJob({})", jobExecution);

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! El Procesamiento de Información termino con Exito");
        }
    }

}
