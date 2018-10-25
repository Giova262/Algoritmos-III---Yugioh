package fiuba.modelo.estrategiaPosicion;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;

public interface EstrategiaPosicion {

	public boolean voltear(Jugador jugador);
	public void setCarta(Carta carta);

}
