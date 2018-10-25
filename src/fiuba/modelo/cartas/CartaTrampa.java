package fiuba.modelo.cartas;

import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.efectoTrampa.EfectoTrampa;
import fiuba.modelo.estrategiaPosicion.BocaAbajo;
import fiuba.modelo.estrategiaPosicion.EstrategiaPosicion;

public class CartaTrampa implements Carta {

	private String nombre;
	private EstrategiaPosicion modo;
	private EfectoTrampa efecto;
	private Jugador jugador;
	
	public CartaTrampa(String nombre , EfectoTrampa efecto , Jugador jugador) {
		this.nombre = nombre;
		this.modo = new BocaAbajo();
		this.efecto = efecto;
		this.jugador = jugador;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean activarEfecto(CartaMonstruo miMonstruo, CartaMonstruo monstruoEnemigo, Jugador oponente) {
		return efecto.activar(miMonstruo,monstruoEnemigo,oponente);
	}

	@Override
	public void activarEfecto(Jugador oponente) {
		
	}

	public boolean estaBocaAbajo() {
		return modo.getClass() == BocaAbajo.class;
	}

	public void setEstrategiaPosicion(EstrategiaPosicion estrategia) {
		modo = estrategia;
		
	}

	@Override
	public void enviarmeAlCementerio() {
		this.jugador.enviarAlCementerio(this);	
	}
	
}