package mx.com.nmp.ms.sivad.referencia.dominio.exception;

/**
 * 
 * @author Quarksoft
 *
 */
public class FactoresRangoColorNoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1111827286143982676L;
	
	/**
	 * Variable que contiene la clase del Factor Rango por Color que genero la excepción.
	 */
	private final Class<?> factoresRangoColor;

	/**
	 * Constructor
	 * 
	 * @param factoresRangoColor clase que genero la excepción.
	 * @param mensaje que describe la excepción.
	 */
	public FactoresRangoColorNoEncontradoException(Class<?> factoresRangoColor, String mensaje) {
		super(mensaje);
		this.factoresRangoColor = factoresRangoColor;
	}

	/**
	 * Método que regresa la clase del Factor por Rango de Color que género la excepción
	 * @return Clase del Factor por Rango de Color que género la excepción.
	 */
	public Class<?> getFactoresRangoColor() {
		return factoresRangoColor;
	}

}
