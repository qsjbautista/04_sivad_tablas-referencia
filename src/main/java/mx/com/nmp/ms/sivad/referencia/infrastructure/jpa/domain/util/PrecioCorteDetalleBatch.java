package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.ws.diamantes.listas.datatypes.PrecioCorteDetalle;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

/**
 * Utilizado para el procesamiento de registros de precio
 *
 * @author osanchez
 */
public class PrecioCorteDetalleBatch extends PrecioCorteDetalle {
    	
	private String corte;
    
    // Inicio ---> Nuevos valores de interfaz

    private BigDecimal factor;
    private TipoNuevoRegistro tipo;


    // Fin ---> Nuevos valores de interfaz

    public PrecioCorteDetalleBatch(String corte, PrecioCorteDetalle pcd) {
        this.corte = corte;
        BeanUtils.copyProperties(pcd, this);
    }
    
//    public PrecioCorteDetalleBatch(String corte, BigDecimal pQuilatajeBaseDesde, BigDecimal pQuilatajeBaseHasta,
//			String fRangoColor, BigDecimal factorColor, BigDecimal factorParametros, Boolean nuevoRegistroBase,
//			Boolean nuevoRegistroColor, PrecioCorteDetalle pcd) {
//		super();
//		this.corte = corte;
//		this.pQuilatajeBaseDesde = pQuilatajeBaseDesde;
//		this.pQuilatajeBaseHasta = pQuilatajeBaseHasta;
//		this.fRangoColor = fRangoColor;
//		this.factorColor = factorColor;
//		this.factorParametros = factorParametros;
//		this.nuevoRegistroBase = nuevoRegistroBase;
//		this.nuevoRegistroColor = nuevoRegistroColor;
//		BeanUtils.copyProperties(pcd, this);
//	}

	public void setNuevoRegistro(TipoNuevoRegistro tipo, BigDecimal factor) {
		this.tipo = tipo;
		this.factor = factor;
	}

	public boolean isNuevoRegistro(TipoNuevoRegistro tipo) {
		return this.tipo != null && this.tipo.equals(tipo);
	}


    public String getCorte() {
        return corte;
    }

	public void setCorte(String corte) {
        this.corte = corte;
    }

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public TipoNuevoRegistro getTipo() {
		return tipo;
	}

	public void setTipo(TipoNuevoRegistro tipo) {
		this.tipo = tipo;
	}

}
