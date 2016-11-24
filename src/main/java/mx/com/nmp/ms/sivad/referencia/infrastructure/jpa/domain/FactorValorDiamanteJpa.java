/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import mx.com.nmp.ms.sivad.referencia.dominio.factory.FactorValorDiamanteFactory;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.ModificadorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

/**
 * Entidad JPA que permite mapear los factores de diamante a una tabla de unidades persistentes.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
@Entity
@Cache(usage = NONSTRICT_READ_WRITE)
@Table(name = "cfg_diamante_factor",
    indexes = {@Index(name = "idx_cfg_diamante_factor_id", columnList = "id", unique = true)})
public class FactorValorDiamanteJpa implements ModificadorValorDiamante.Builder,
        FactorValorDiamante.Builder, Serializable {
    private static final long serialVersionUID = -4561884219797438224L;

    /**
     * Identificador de la entidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Factor valor Mínimo
     */
    @Column(name = "factor_minimo", precision = 10, scale = 3, nullable = false)
    private BigDecimal minimo;

    /**
     * Factor valor Medio
     */
    @Column(name = "factor_medio", precision = 10, scale = 3, nullable = false)
    private BigDecimal medio;

    /**
     * Factor valor Máximo.
     */
    @Column(name = "factor_maximo", precision = 10, scale = 3, nullable = false)
    private BigDecimal maximo;

    /**
     * Fecha de vigencia de la lista de factores.
     */
    @Column(name = "fecha_carga", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime fechaCarga;

    /**
     * Fecha de origen de la información.
     */
    @Column(name = "fecha_listado", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate fechaListado;

    /**
     * Referencia a la fabrica de Value Object
     */
    @Transient
    private transient FactorValorDiamanteFactory fabrica;

    /**
     * Constructor.
     */
    public FactorValorDiamanteJpa() {
        super();
    }

    /**
     * Regresa el identificador.
     *
     * @return Identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador.
     *
     * @param id Identificador.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa el factor valor Mínimo.
     *
     * @return Factor valor Mínimo.
     */
    @Override
    public BigDecimal getMinimo() {
        return minimo;
    }

    /**
     * Establece el factor valor Mínimo.
     *
     * @param minimo Factor valor Mínimo.
     */
    public void setMinimo(BigDecimal minimo) {
        this.minimo = minimo;
    }

    /**
     * Regresa el factor valor Medio.
     *
     * @return Factor valor Medio.
     */
    @Override
    public BigDecimal getMedio() {
        return medio;
    }

    /**
     * Establece el factor valor Medio.
     *
     * @param medio Factor valor Medio.
     */
    public void setMedio(BigDecimal medio) {
        this.medio = medio;
    }

    /**
     * Regresa el factor valor Máximo.
     *
     * @return Factor valor Máximo.
     */
    @Override
    public BigDecimal getMaximo() {
        return maximo;
    }

    /**
     * Establece el factor valor Máximo.
     *
     * @param maximo Factor valor Máximo.
     */
    public void setMaximo(BigDecimal maximo) {
        this.maximo = maximo;
    }

    /**
     * Regresa fecha de vigencia de la lista de factores.
     *
     * @return Fecha de vigencia de la lista de factores.
     */
    @Override
    public DateTime getFechaCarga() {
        return fechaCarga;
    }

    /**
     * Establece la fecha de vigencia de la lista de factores.
     *
     * @param fechaCarga Fecha de vigencia de la lista de factores.
     */
    public void setFechaCarga(DateTime fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    /**
     * Regresa la fecha de origen de la información.
     *
     * @return Fecha de origen de la información.
     */
    @Override
    public LocalDate getFechaListado() {
        return fechaListado;
    }

    /**
     * Establece la fecha de origen de la información.
     *
     * @param fechaListado Fecha de origen de la información.
     */
    public void setFechaListado(LocalDate fechaListado) {
        this.fechaListado = fechaListado;
    }

    /**
     * Establece la fabrica de Value Object.
     *
     * @param fabrica Fabrica de Value Object.
     */
    public void setFabrica(FactorValorDiamanteFactory fabrica) {
        this.fabrica = fabrica;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FactorValorDiamante getFactor() {
        if (ObjectUtils.isEmpty(fabrica)) {
            return null;
        } else {
            return fabrica.crearDesde(this);
        }
    }
}
