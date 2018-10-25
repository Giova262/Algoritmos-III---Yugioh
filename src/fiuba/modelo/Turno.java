package fiuba.modelo;

import java.util.ArrayList;
import java.util.Random;

import fiuba.modelo.fases.Fase;
import fiuba.modelo.fases.FaseInicial;

public class Turno {
	
	private int numeroTurno;
	Jugador jugadorActual;
	Fase faseActual;
	
	public Turno(Jugador jugador1, Jugador jugador2) {
		this.numeroTurno = 1;
		this.jugadorActual = elegirJugadorAleatorio(jugador1,jugador2);
	}
	
	public int getNumeroDeTurno(){
		return numeroTurno;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}
	
	private Jugador elegirJugadorAleatorio(Jugador jugador1, Jugador jugador2) {
		ArrayList <Jugador> jugadores = new ArrayList <Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		return jugadores.get(new Random().nextInt(jugadores.size()));
	}
	
	public void pasarFase() {
		faseActual = faseActual.pasarFase();
	}
	
	public void terminarTurno() {
		numeroTurno++;
		jugadorActual = jugadorActual.getOponente();
	}
	
}