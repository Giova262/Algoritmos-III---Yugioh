package fiuba.modelo.efectos.efectoCampo;

import fiuba.modelo.Jugador;

public class EfectoWasteland implements EfectoCampo {

	public void activar(Jugador jugador1, Jugador jugador2) {
		int aumentoDeAtaque = 200;
		int aumentoDeDefensa = 300;
		jugador1.aumentarAtaqueMonstruos(aumentoDeAtaque);
		jugador2.aumentarDefensaMonstruos(aumentoDeDefensa);
	}
	
}
