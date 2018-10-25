package fiuba.modelo.efectos.efectoCampo;

import fiuba.modelo.Jugador;

public class EfectoSogen implements EfectoCampo {

	public void activar(Jugador jugador1, Jugador jugador2) {
		int aumentoDeAtaque = 200;
		int aumentoDeDefensa = 500;
		jugador1.aumentarDefensaMonstruos(aumentoDeDefensa);
		jugador2.aumentarAtaqueMonstruos(aumentoDeAtaque);
	}
	
}
