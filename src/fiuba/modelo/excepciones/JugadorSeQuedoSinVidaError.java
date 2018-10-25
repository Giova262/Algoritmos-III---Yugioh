package fiuba.modelo.excepciones;

import fiuba.modelo.Jugador;

public class JugadorSeQuedoSinVidaError extends FinDePartidaExcepcion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JugadorSeQuedoSinVidaError(Jugador unJugador) {
		super(unJugador);
	}

	@Override
	public String informarGanador() {
		return jugador.getOponente().getNombre() + " gano el duelo!!";
	}

	@Override
	public String informarCausa() {
		return jugador.getNombre() + " se quedo sin puntos de vida.";
	}
	
}