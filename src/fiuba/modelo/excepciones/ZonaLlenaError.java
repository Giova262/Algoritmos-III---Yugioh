package fiuba.modelo.excepciones;

public class ZonaLlenaError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String mensajeError() {
		return "La zona de invocaci�n est� llena.";
	}

}