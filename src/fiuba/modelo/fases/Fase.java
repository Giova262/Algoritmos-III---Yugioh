package fiuba.modelo.fases;

import fiuba.modelo.Jugador;
import fiuba.modelo.AlGoOh;
import fiuba.modelo.InterfaceJugador;

public abstract class Fase implements InterfaceJugador{
	
	protected Jugador jugador;
	protected AlGoOh turno;
	
	public Fase(Jugador unJugador, AlGoOh unTurno){
		jugador = unJugador;
		turno = unTurno;
	}
	
	public abstract Fase pasarFase();
	public abstract String getNombreFase();
	public abstract String getMensajeFase();
	
}