package fiuba.modelo.efectos.efectoMagica;

import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.Efecto;

public interface EfectoMagica extends Efecto{

	public void activar(Jugador jugador1, Jugador jugador2);

}