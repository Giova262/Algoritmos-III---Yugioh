package fiuba.modelo.invocaciones;

import fiuba.modelo.Jugador;

public interface Invocacion {
	
	public int sacrificiosNecesarios(Jugador jugador);
	public void sacrificarNecesarios(Jugador jugador);
	
}