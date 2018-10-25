package fiuba.modelo.efectos.efectoMagica;

import fiuba.modelo.Jugador;

public class EfectoFisura implements EfectoMagica {

	public void activar(Jugador jugador1, Jugador jugador2) {
		jugador2.destruirMonstruoConMenorAtaque();
	}

}