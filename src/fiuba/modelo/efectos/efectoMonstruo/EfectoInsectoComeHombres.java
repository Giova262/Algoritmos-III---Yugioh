package fiuba.modelo.efectos.efectoMonstruo;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.CartaMonstruo;

public class EfectoInsectoComeHombres implements EfectoMonstruo {

	@Override
	public void activar(Jugador oponente) {
		CartaMonstruo monstruo = oponente.getMonstruoConMayorNivel();
		monstruo.enviarmeAlCementerio();
	}
	
	public boolean activarSiendoAtacado(Jugador jugador) {
		this.activar(jugador);
		return true;
	}

}