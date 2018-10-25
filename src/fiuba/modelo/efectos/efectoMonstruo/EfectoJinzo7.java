package fiuba.modelo.efectos.efectoMonstruo;

import fiuba.modelo.Jugador;

public class EfectoJinzo7 implements EfectoMonstruo {

	@Override
	public void activar(Jugador oponente) {
		int danio = 500;
		oponente.recibirDanio(danio);
	}
	
	public boolean activarSiendoAtacado(Jugador jugador) {
		/*No hace nada al ser atacado*/
		return false;
	}

}