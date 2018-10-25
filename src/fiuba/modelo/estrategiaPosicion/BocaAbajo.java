package fiuba.modelo.estrategiaPosicion;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaMonstruo;

public class BocaAbajo implements EstrategiaPosicion{

	private Carta carta;
 
	public void setCarta(Carta unaCarta) {
		carta = unaCarta;
	}

	public boolean voltear(Jugador jugador) {
		if (carta.getClass() == CartaMonstruo.class) {
			CartaMonstruo monstruo = (CartaMonstruo) carta;
			return monstruo.activarEfectoSiendoAtacado(jugador);
		}
		return false;
	}

}