package fiuba.modelo.efectos.efectoMonstruo;

import fiuba.modelo.Jugador;

public class EfectoMonstruoNulo implements EfectoMonstruo{

	@Override
	public void activar(Jugador unJugador) {
		/*No tiene efecto*/
	}

	@Override
	public boolean activarSiendoAtacado(Jugador jugador) {
		return false;
	}
	
	
}
