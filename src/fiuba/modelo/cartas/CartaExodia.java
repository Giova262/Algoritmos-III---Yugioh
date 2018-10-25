package fiuba.modelo.cartas;

import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruo;
import fiuba.modelo.invocaciones.Invocacion;

public class CartaExodia extends CartaMonstruo {
	
	public CartaExodia(String nombre, EfectoMonstruo efecto, int ataque, int defensa, int nivel, Jugador jugador,
			Invocacion invocacion) {
		super(nombre, efecto, ataque, defensa, nivel, jugador, invocacion);
	}

}
