package mx.com.nmp.ms.sivad.referencia.dominio.exception;

public class ParametrosQuilatesNoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4550542755375893129L;
	private Class<?> entidad;
	
	
	
	public ParametrosQuilatesNoEncontradoException(String msg, Class<?> entidad) {
		super(msg);
		this.entidad = entidad;
	}



	public Class<?> getEntidad() {
		return entidad;
	}



	public void setEntidad(Class<?> entidad) {
		this.entidad = entidad;
	}
	
	
	
	

}
