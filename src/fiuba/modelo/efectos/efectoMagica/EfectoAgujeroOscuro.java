package fiuba.modelo.efectos.efectoMagica;

import fiuba.modelo.Jugador;

public class EfectoAgujeroOscuro implements EfectoMagica {

	public void activar(Jugador jugador1, Jugador jugador2) {
		jugador1.destruirMonstruos();
		jugador2.destruirMonstruos();
	}

}