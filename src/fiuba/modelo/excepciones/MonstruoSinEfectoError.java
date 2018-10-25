package fiuba.modelo.excepciones;

public class MonstruoSinEfectoError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String mensajeError() {
		return "Este monstruo no tiene efecto.";
	}
	
}
