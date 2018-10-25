package fiuba.modelo.efectos.efectoMonstruo;

import fiuba.modelo.Jugador;

public class EfectoExodia implements EfectoMonstruo {
	
	@Override
	public void activar(Jugador jugador) {
		jugador.ganarDuelo();
	}

	@Override
	public boolean activarSiendoAtacado(Jugador jugador) {
		/*No hace nada al ser atacado*/
		return false;
	}

}