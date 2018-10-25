package fiuba.modelo.cartas;

import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.efectoMagica.EfectoMagica;
import fiuba.modelo.estrategiaPosicion.*;

public class CartaMagica implements Carta {

	private String nombre;
	private EstrategiaPosicion modo;
	private EfectoMagica efecto;
	private Jugador jugador;
	
	public CartaMagica(String nombre , EfectoMagica efecto2, Jugador jugador) {
		this.nombre = nombre;
		this.efecto = efecto2;
		this.jugador = jugador;
		this.modo = new BocaAbajo();
	}

	public String getNombre() {
		return nombre;
	}

	public void activarEfecto(Jugador oponente) {
		efecto.activar(jugador,oponente);
	}

	public boolean estaBocaAbajo() {
		return modo.getClass() == BocaAbajo.class;
	}

	public void setEstrategiaPosicion(EstrategiaPosicion estrategia) {
		modo = estrategia;
		
	}
	
	public void enviarmeAlCementerio() {
		this.jugador.enviarAlCementerio(this);		
	}
	
}