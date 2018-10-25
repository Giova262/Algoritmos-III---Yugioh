package fiuba.modelo.excepciones;

import fiuba.modelo.Jugador;

public class JugadorSacoTodasLasPartesDeExodiaError extends FinDePartidaExcepcion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JugadorSacoTodasLasPartesDeExodiaError(Jugador unJugador) {
		super(unJugador);
	}

	@Override
	public String informarGanador() {
		return jugador.getNombre() + " gano el duelo!!";
	}

	@Override
	public String informarCausa() {
		return jugador.getNombre() + " saco las 5 partes de Exodia.";
	}
	
}