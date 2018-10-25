package fiuba.modelo.efectos.efectoCampo;

import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.Efecto;

public interface EfectoCampo extends Efecto{

	public void activar(Jugador jugador1, Jugador jugador2);

}