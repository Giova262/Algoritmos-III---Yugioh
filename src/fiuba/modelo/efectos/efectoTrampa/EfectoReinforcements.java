package fiuba.modelo.efectos.efectoTrampa;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.CartaMonstruo;

public class EfectoReinforcements implements EfectoTrampa {

	@Override
	public boolean activar(CartaMonstruo miMonstruo, CartaMonstruo monstruoEnemigo, Jugador oponente) {
		int aumento = 500;
		miMonstruo.aumentarPuntosDeAtaque(aumento);
		return true;
	}

}