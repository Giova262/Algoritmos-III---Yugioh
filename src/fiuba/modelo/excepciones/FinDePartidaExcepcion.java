package fiuba.modelo.excepciones;

import fiuba.modelo.Jugador;

public abstract class FinDePartidaExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Jugador jugador;
	
	public FinDePartidaExcepcion(Jugador unJugador) {
		jugador = unJugador;
	}
	
	public abstract String informarGanador();
	public abstract String informarCausa();
	
}