/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.dominio.modelo;

import mx.com.nmp.ms.sivad.referencia.dominio.exception.ValorComercialNoEncontradoException;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.FactorValorDiamante;
import mx.com.nmp.ms.sivad.referencia.dominio.modelo.vo.ValorComercialDiamanteVO;
import mx.com.nmp.ms.sivad.referencia.dominio.repository.ModificadorValorDiamanteRepository;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * Entidad que representa un diamante.
 *
 * @author ngonzalez
 */
public class Diamante {

    /**
     * El tipo de corte del diamante con base en el catálogo de cortes.
     */
    private String corte;

    /**
     * El tipo de color del diamante con base en la clasificación GIA.
     */
    private String color;

    /**
     * El tipo de claridad del diamante con base en la clasificación GIA.
     */
    private String claridad;

    /**
     * Tamaño inferior en quilates que abarca el valor comercial.
     */
    private BigDecimal tamanioInferior;

    /**
     * Tamaño superior en quilates que abarca el valor comercial.
     */
    private BigDecimal tamanioSuperior;

    /**
     * Precio en dólares del diamante.
     */
    private BigDecimal precio;

    /**
     * Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    private ModificadorValorDiamanteRepository modificadorValorDiamanteRepository;



    // METODOS

    /**
     * Constructor.
     *
     * @param corte El tipo de corte del diamante.
     * @param color El tipo de color del diamante.
     * @param claridad El tipo de claridad del diamante.
     * @param tamanioInferior Tamaño inferior en quilates que abarca el valor comercial.
     * @param tamanioSuperior Tamaño superior en quilates que abarca el valor comercial.
     * @param precio Precio en dólares del diamante.
     * @param modificadorValorDiamanteRepository Referencia al repositorio de ValorComercialDiamanteRepository.
     */
    Diamante(String corte, String color, String claridad, BigDecimal tamanioInferior,
             BigDecimal tamanioSuperior, BigDecimal precio,
             ModificadorValorDiamanteRepository modificadorValorDiamanteRepository) {
        this.corte = corte;
        this.color = color;
        this.claridad = claridad;
        this.tamanioInferior = tamanioInferior;
        this.tamanioSuperior = tamanioSuperior;
        this.precio = precio;
        this.modificadorValorDiamanteRepository = modificadorValorDiamanteRepository;
    }

    /**
     * Permite obtener el valor comercial del diamante.
     *
     * @return El valor comercial.
     */
    public ValorComercialDiamanteVO obtenerValorComercial() {
        ModificadorValorDiamante modificadorValorDiamante = modificadorValorDiamanteRepository.consultar();

        if (ObjectUtils.isEmpty(modificadorValorDiamante) ||
            ObjectUtils.isEmpty(modificadorValorDiamante.getFactor()) ||
            ObjectUtils.isEmpty(modificadorValorDiamante.getFactor().getMinimo()) ||
            ObjectUtils.isEmpty(modificadorValorDiamante.getFactor().getMedio()) ||
            ObjectUtils.isEmpty(modificadorValorDiamante.getFactor().getMaximo())) {

            String msg = "No hay factores de valor de diamante vigentes.";
            throw new ValorComercialNoEncontradoException(
                FactorValorDiamante.class, msg);
        }

        // TODO - Pregunta: ¿Regresar el resultado redondeado a 4 decimales?
        ValorComercialDiamanteVO valorComercialDiamanteVO = new ValorComercialDiamanteVO(
            modificadorValorDiamante.getFactor().getMinimo().multiply(this.getPrecio()),
            modificadorValorDiamante.getFactor().getMedio().multiply(this.getPrecio()),
            modificadorValorDiamante.getFactor().getMaximo().multiply(this.getPrecio()));

        return valorComercialDiamanteVO;
    }



    // GETTERS

    public String getCorte() {
        return corte;
    }

    public String getColor() {
        return color;
    }

    public String getClaridad() {
        return claridad;
    }

    public BigDecimal getTamanioInferior() {
        return tamanioInferior;
    }

    public BigDecimal getTamanioSuperior() {
        return tamanioSuperior;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Diamante{" +
            "corte='" + corte + '\'' +
            ", color='" + color + '\'' +
            ", claridad='" + claridad + '\'' +
            ", tamanioInferior=" + tamanioInferior +
            ", tamanioSuperior=" + tamanioSuperior +
            ", precio=" + precio +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diamante)) return false;

        Diamante diamante = (Diamante) o;

        if (!claridad.equals(diamante.claridad)) return false;
        if (!color.equals(diamante.color)) return false;
        if (!corte.equals(diamante.corte)) return false;
        if (!precio.equals(diamante.precio)) return false;
        if (!tamanioInferior.equals(diamante.tamanioInferior)) return false;
        if (!tamanioSuperior.equals(diamante.tamanioSuperior)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = corte.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + claridad.hashCode();
        result = 31 * result + tamanioInferior.hashCode();
        result = 31 * result + tamanioSuperior.hashCode();
        result = 31 * result + precio.hashCode();
        return result;
    }

}
