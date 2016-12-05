package mx.com.nmp.ms.sivad.referencia.adminapi.ws;

import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.factory.ModificadorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.*;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.ValorComercialDiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ValorComercialDiamanteRepository;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.datatypes.ValorComercial;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.ReferenciaListasDiamanteService;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.*;
import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author osanchez
 */
public class ReferenciaListasDiamantesServiceEndpoint implements ReferenciaListasDiamanteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciaListasDiamantesServiceEndpoint.class);
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
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    public Void actualizarListaValorComercial(ActualizarListaValorComercialRequest parameters) {
        LOGGER.info(">> actualizarListaValorComercial({})", parameters);
        Set<Diamante> diamantes = new HashSet<>();
        for (PrecioCorte precioCorte : parameters.getListado().getPreciosCorte()) {
            for(PrecioCorteDetalle  precioCorteDetalle: precioCorte.getPrecioCorte()) {
                Diamante diamante = DiamanteFactory.create(precioCorte.getCorte(), precioCorteDetalle.getColor(),
                    precioCorteDetalle.getClaridad(), precioCorteDetalle.getTamanioInferior(), precioCorteDetalle.getTamanioSuperior(),
                    precioCorteDetalle.getPrecio());
                diamantes.add(diamante);
            }
        }
        ListadoValorComercialDiamante listadoValorComercialDiamante = ListadoValorComercialDiamanteFactory.create(LocalDate.now(),
            diamantes);
        listadoValorComercialDiamante.actualizar();
        return new Void();
    }

    /**
     * @param parameters
     * @return returns mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.Void
     */
    @Override
    public Void actualizarListaFactor(ActualizarListaFactorRequest parameters) {
        LOGGER.info(">> actualizarListaFactor({})", parameters);

        FactorValorDiamante vo = factorValorDiamanteFactory
            .crearCon(parameters.getFactorDiamante().getFactorMinimo(), parameters.getFactorDiamante().getFactorMedio(),
                parameters.getFactorDiamante().getFactorMaximo());
        ModificadorValorDiamante modificadorValorDiamante = modificadorValorDiamanteFactory.crearPersistibleCon(DateTime.now(), LocalDate.now(), vo);
        modificadorValorDiamante.actualizar();
        return new Void();
    }
}
