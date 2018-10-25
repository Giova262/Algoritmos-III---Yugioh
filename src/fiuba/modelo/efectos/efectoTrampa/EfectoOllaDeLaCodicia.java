package fiuba.modelo.efectos.efectoTrampa;

import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.efectoMagica.EfectoMagica;

public class EfectoOllaDeLaCodicia implements EfectoMagica {

	public void activar(Jugador jugador1, Jugador jugador2) {
		int cantidadDeCartasATomar = 2;
		jugador1.tomarCartasDelMazo(cantidadDeCartasATomar);
	}

}
