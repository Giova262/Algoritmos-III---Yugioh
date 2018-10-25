package fiuba.modelo.efectos.efectoTrampa;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.efectos.Efecto;

public interface EfectoTrampa extends Efecto {

	public boolean activar(CartaMonstruo carta1, CartaMonstruo carta2, Jugador oponente);

}
