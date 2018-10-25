package fiuba.modelo.excepciones;

public class NoSePuedeAtacarDirectamenteError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String mensajeError() {
		return "No se puede atacar directamente mientras haya monstruos en el campo enemigo.";
	}

}