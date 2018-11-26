/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia Fase 2
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.repository;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util.PrecioCorteDetalleBatch;

/**
 * Utilizado para aplicar las formulas necesarias para calcular los montos de
 * precio del diamante.
 *
 * @author ecancino
 */
public interface CalculosPrecioDiamanteRepository {

    /**
     * Permite calcular el valor de las columnas montoVbd y montofCastigoxRango.
     *
     * @param precioCorteDetalle Objecto con las características del diamante desde rapaport.
     * @return La entidad obtenida con el precio.
     */
    Diamante calcularColumnas(final PrecioCorteDetalleBatch precioCorteDetalle);

}
