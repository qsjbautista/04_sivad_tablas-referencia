package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;

public class RangoPesoDiamanteJPA {

    @Column(name = "quilatesDesde", precision = 6, scale = 2, nullable = false)
    protected BigDecimal quilatesDesde;

    @Column(name = "quilatesHasta", precision = 6, scale = 2, nullable = false)
    protected BigDecimal quilatesHasta;

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

    @Override
    public String toString() {
        return "";
    }

}
