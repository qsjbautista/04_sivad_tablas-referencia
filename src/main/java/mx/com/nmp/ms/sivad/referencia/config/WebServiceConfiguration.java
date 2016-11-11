package mx.com.nmp.ms.sivad.referencia.config;

import mx.com.nmp.ms.sivad.referencia.adminapi.ws.ReferenciaListasDiamantesServiceEndpoint;
import mx.com.nmp.ms.sivad.referencia.api.ws.ReferenciaAlhajaServiceEndpoint;
import mx.com.nmp.ms.sivad.referencia.api.ws.ReferenciaDiamantesServiceEndpoint;
import mx.com.nmp.ms.sivad.referencia.ws.alhajas.ReferenciaAlhajaService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.ReferenciaDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.ReferenciaListasDiamanteService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfiguration {

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap-api/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public ReferenciaAlhajaService referenciaAlhajaService() {
    	return new ReferenciaAlhajaServiceEndpoint();
    }

    @Bean
    public ReferenciaDiamanteService referenciaDiamanteService() {
        return new ReferenciaDiamantesServiceEndpoint();
    }

    @Bean
    public ReferenciaListasDiamanteService referenciaListasDiamanteService() {
        return new ReferenciaListasDiamantesServiceEndpoint();
    }

    @Bean
    public Endpoint endpointReferenciaAlhaja() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), referenciaAlhajaService());
        endpoint.publish("/ReferenciaAlhajaService");
        endpoint.setWsdlLocation("ReferenciaAlhaja.wsdl");
        return endpoint;
    }

    @Bean
    public Endpoint endpointReferenciaDiamante() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), referenciaDiamanteService());
        endpoint.publish("/ReferenciaDiamanteService");
        endpoint.setWsdlLocation("ReferenciaDiamante.wsdl");
        return endpoint;
    }

    @Bean
    public Endpoint endpointReferenciaListasDiamante() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), referenciaListasDiamanteService());
        endpoint.publish("/ReferenciaListasDiamanteService");
        endpoint.setWsdlLocation("ReferenciaListasDiamante.wsdl");
        return endpoint;
    }
}
