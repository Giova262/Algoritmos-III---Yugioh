package fiuba.modelo;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Stack;
import fiuba.modelo.cartas.*;

public class Mazo {

	private Stack<Carta> cartas; 
	private Jugador jugador;
	
	public static final int NUMERO_CARTAS = 40 ;

	public Mazo(Jugador jugador) {
		this.cartas = new Stack<Carta>();
		this.jugador = jugador;
		this.llenarMazo();
	}
	
	private void llenarMazo() {
		CartasFactory fabrica = new CartasFactory();		
		ArrayList<Carta> cartasOrdenadas = barajar(fabrica.getCartas(jugador));
		for(Carta carta: cartasOrdenadas) {
			cartas.push(carta);
		}
	}
	
	private ArrayList<Carta> barajar(ArrayList<Carta> cartas){
		Collections.shuffle(cartas);
		return cartas;
	}
	
	public Carta tomarCarta() {		
		if (this.estaVacio()) {
			jugador.perderDueloPorCartas();
		}
		Carta carta = cartas.pop();
		return carta;
	}
	
	public boolean estaVacio() {
		return cartas.empty();
	}
	
	public int cartasDisponibles() {
		return cartas.size();
	}

}