package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "cfg_diamante_parametros_quilates")
public class ParametrosQuilatesJPA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2294530582394823876L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
	
	@Column(name = "quilates_desde", precision = 10, scale = 4, nullable = false)
	private BigDecimal quilatesDesde;
	
	@Column(name = "quilates_hasta", precision = 10, scale = 4, nullable = false)
	private BigDecimal quilatesHasta;
	
	@Column(name = "quilates_base_desde", precision = 10, scale = 4, nullable = false)
	private BigDecimal quilatesBaseDesde;
	
	@Column(name = "quilates_base_hasta", precision = 10, scale = 4, nullable = false)
	private BigDecimal quilatesBaseHasta;
	
	@Column(name = "porcentaje", precision = 10, scale = 3, nullable = false)
	private BigDecimal porcentaje;
	
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

	public BigDecimal getQuilatesDesde() {
		return quilatesDesde;
	}

	public void setQuilatesDesde(BigDecimal quilatesDesde) {
		this.quilatesDesde = quilatesDesde;
	}

	public BigDecimal getQuilatesHasta() {
		return quilatesHasta;
	}

	public void setQuilatesHasta(BigDecimal quilatesHasta) {
		this.quilatesHasta = quilatesHasta;
	}

	public BigDecimal getQuilatesBaseDesde() {
		return quilatesBaseDesde;
	}

	public void setQuilatesBaseDesde(BigDecimal quilatesBaseDesde) {
		this.quilatesBaseDesde = quilatesBaseDesde;
	}

	public BigDecimal getQuilatesBaseHasta() {
		return quilatesBaseHasta;
	}

	public void setQuilatesBaseHasta(BigDecimal quilatesBaseHasta) {
		this.quilatesBaseHasta = quilatesBaseHasta;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public DateTime getFecha() {
		return fecha;
	}

	public void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ParametrosQuilatesJPA [id=" + id + ", quilatesDesde=" + quilatesDesde + ", quilatesHasta="
				+ quilatesHasta + ", quilatesBaseDesde=" + quilatesBaseDesde + ", quilatesBaseHasta="
				+ quilatesBaseHasta + ", porcentaje=" + porcentaje + ", fecha=" + fecha + "]";
	}
	
}
