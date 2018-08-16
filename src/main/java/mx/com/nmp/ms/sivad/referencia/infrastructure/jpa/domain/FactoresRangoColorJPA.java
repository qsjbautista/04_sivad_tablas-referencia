package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import mx.com.nmp.ms.arquetipo.annotation.journal.JournalData;

@Entity
@Table(name = "cfg_diamante_factores_x_rango_de_color")
public class FactoresRangoColorJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2994114718689535442L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "color_desde", nullable = false)
	private String colorDesde;
	
	@Column(name = "color_hasta", nullable = false)
	private String colorHasta;
	
	@Column(name = "rango_color_base", nullable = false)
	private String rangoColorBase;
	
	@Column(name = "factor", precision = 10, scale = 4, nullable = false)
	private BigDecimal factor;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "fecha")
    @JournalData
	private DateTime fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColorDesde() {
		return colorDesde;
	}

	public void setColorDesde(String colorDesde) {
		this.colorDesde = colorDesde;
	}

	public String getColorHasta() {
		return colorHasta;
	}

	public void setColorHasta(String colorHasta) {
		this.colorHasta = colorHasta;
	}

	public String getRangoColorBase() {
		return rangoColorBase;
	}

	public void setRangoColorBase(String rangoColorBase) {
		this.rangoColorBase = rangoColorBase;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public DateTime getFecha() {
		return fecha;
	}

	public void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "FactoresRangoColorJPA [id=" + id + ", colorDesde=" + colorDesde + ", colorHasta=" + colorHasta
				+ ", rangoColorBase=" + rangoColorBase + ", factor=" + factor + ", fecha=" + fecha + "]";
	}

}
