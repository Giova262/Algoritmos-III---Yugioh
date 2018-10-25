package fiuba.modelo.excepciones;

public class FaltaSacrificioParaInvocacionError extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String mensajeError() {
		return "No hay cantidad de sacrificios necesarios en el campo.";
	}

}
