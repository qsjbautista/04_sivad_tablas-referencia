package mx.com.nmp.ms.sivad.referencia.config;

import mx.com.nmp.ms.sivad.referencia.adminapi.ws.ReferenciaListasDiamantesServiceEndpoint;
import mx.com.nmp.ms.sivad.referencia.api.ws.ReferenciaAlhajaServiceEndpoint;
import mx.com.nmp.ms.sivad.referencia.api.ws.ReferenciaDiamantesServiceEndpoint;
import mx.com.nmp.ms.sivad.referencia.ws.alhajas.ReferenciaAlhajaService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.ReferenciaDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.ReferenciaListasDiamanteService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * Configuración de web services.
 * La configuración actual hace que estén accesibles en http://servidor:puerto/soap-api/*
 */
@Configuration
public class WebServiceConfiguration {

    /**
     * Configura la ruta en la que serán expuestos los web services
     *
     * @return servlet de web services
     */
    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap-api/*");
    }

    /**
     * Bus CXF
     *
     * @return
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        final SpringBus springBus = new SpringBus();
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        loggingFeature.initialize(springBus);
        springBus.getFeatures().add(loggingFeature);

        return springBus;
    }

    /**
     * Bean con endpoint de alhajas
     *
     * @return bean
     */
    @Bean
    public ReferenciaAlhajaService referenciaAlhajaService() {
        return new ReferenciaAlhajaServiceEndpoint();
    }

    /**
     * Bean con endpoint de diamantes
     *
     * @return bean
     */
    @Bean
    public ReferenciaDiamanteService referenciaDiamanteService() {
        return new ReferenciaDiamantesServiceEndpoint();
    }

    /**
     * Bean con endpoint de listas de diamantes
     *
     * @return bean
     */
    @Bean
    public ReferenciaListasDiamanteService referenciaListasDiamanteService() {
        return new ReferenciaListasDiamantesServiceEndpoint();
    }

    /**
     * Configuración del WS de Alhajas
     *
     * @return endpoint de alhajas
     */
    @Bean
    public Endpoint endpointReferenciaAlhaja() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), referenciaAlhajaService());
        endpoint.publish("/ReferenciaAlhajaService");
        endpoint.setWsdlLocation("ReferenciaAlhaja.wsdl");
        return endpoint;
    }

    /**
     * Configuración del WS de Diamantes
     *
     * @return endpoint de diamantes
     */
    @Bean
    public Endpoint endpointReferenciaDiamante() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), referenciaDiamanteService());
        endpoint.publish("/ReferenciaDiamanteService");
        endpoint.setWsdlLocation("ReferenciaDiamante.wsdl");
        return endpoint;
    }

    /**
     * Configuración del WS de Listas de Diamantes
     *
     * @return endpoint de listas de diamantes
     */
    @Bean
    public Endpoint endpointReferenciaListasDiamante() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), referenciaListasDiamanteService());
        endpoint.publish("/ReferenciaListasDiamanteService");
        endpoint.setWsdlLocation("ReferenciaListasDiamante.wsdl");
        return endpoint;
    }
}
