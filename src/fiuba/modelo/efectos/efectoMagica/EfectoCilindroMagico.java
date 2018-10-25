package fiuba.modelo.efectos.efectoMagica;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.efectos.efectoTrampa.EfectoTrampa;

public class EfectoCilindroMagico implements EfectoTrampa {

	@Override
	public boolean activar(CartaMonstruo miMonstruo, CartaMonstruo monstruoEnemigo, Jugador oponente) {
		int danio = monstruoEnemigo.getPuntosDeBatalla();
		oponente.recibirDanio(danio);
		return false;
	}
}