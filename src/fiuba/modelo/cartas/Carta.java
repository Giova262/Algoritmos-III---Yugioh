package fiuba.modelo.cartas;

import fiuba.modelo.Jugador;

public interface Carta {
	
	public String getNombre() ;
	public void activarEfecto(Jugador oponente);
	public void enviarmeAlCementerio();
	
}