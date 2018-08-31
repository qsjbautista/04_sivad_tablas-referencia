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
    
    private BigDecimal pQuilatajeBaseDesde;
    
    private BigDecimal pQuilatajeBaseHasta;
    
    private String fRangoColor;
    
    private BigDecimal factorColor;
    
    private BigDecimal factorParametros;
    
    private Boolean nuevoRegistroBase;
    
    private Boolean nuevoRegistroColor;
    
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

    public String getCorte() {
        return corte;
    }

	public void setCorte(String corte) {
        this.corte = corte;
    }

	public BigDecimal getpQuilatajeBaseDesde() {
		return pQuilatajeBaseDesde;
	}

	public void setpQuilatajeBaseDesde(BigDecimal pQuilatajeBaseDesde) {
		this.pQuilatajeBaseDesde = pQuilatajeBaseDesde;
	}

	public BigDecimal getpQuilatajeBaseHasta() {
		return pQuilatajeBaseHasta;
	}

	public void setpQuilatajeBaseHasta(BigDecimal pQuilatajeBaseHasta) {
		this.pQuilatajeBaseHasta = pQuilatajeBaseHasta;
	}

	public String getfRangoColor() {
		return fRangoColor;
	}

	public void setfRangoColor(String fRangoColor) {
		this.fRangoColor = fRangoColor;
	}

	public Boolean getNuevoRegistroBase() {
		return nuevoRegistroBase;
	}

	public void setNuevoRegistroBase(Boolean nuevoRegistroBase) {
		this.nuevoRegistroBase = nuevoRegistroBase;
	}

	public Boolean getNuevoRegistroColor() {
		return nuevoRegistroColor;
	}

	public void setNuevoRegistroColor(Boolean nuevoRegistroColor) {
		this.nuevoRegistroColor = nuevoRegistroColor;
	}

	public BigDecimal getFactorColor() {
		return factorColor;
	}

	public void setFactorColor(BigDecimal factorColor) {
		this.factorColor = factorColor;
	}

	public BigDecimal getFactorParametros() {
		return factorParametros;
	}

	public void setFactorParametros(BigDecimal factorParametros) {
		this.factorParametros = factorParametros;
	}
    
}
