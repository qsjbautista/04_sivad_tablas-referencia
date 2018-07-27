package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain;

import javax.persistence.Column;
import java.math.BigDecimal;

public class CastigoCorteDiamanteJPA {

    @Column(name = "factor", precision = 10, scale = 3, nullable = false)
    protected BigDecimal factor;

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    @Override
    public String toString() {
        return "";
    }

}
