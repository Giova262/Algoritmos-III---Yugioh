package fiuba.modelo.efectos.efectoMonstruo;

import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.Efecto;

public interface EfectoMonstruo extends Efecto{

	public void activar(Jugador unJugador);

	public boolean activarSiendoAtacado(Jugador jugador);
}
