package fiuba.modelo;

import java.util.ArrayList;
import java.util.Random;

import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaDeCampo;
import fiuba.modelo.cartas.CartaExodia;
import fiuba.modelo.cartas.CartaMagica;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.cartas.CartaTrampa;
import fiuba.modelo.fases.Fase;
import fiuba.modelo.fases.FaseInicial;
import fiuba.vista.BotonMagicoOTrampa;

public class AlGoOh implements InterfaceJugador{
	private Jugador jugadorActual;
	private Fase faseActual;
	private int numeroTurno;
	
	public AlGoOh(String nombreJugador1, String nombreJugador2) {
		Jugador jugador1 = new Jugador(nombreJugador1);
		Jugador jugador2 = new Jugador(nombreJugador2);
		jugador1.setOponente(jugador2);
		jugador2.setOponente(jugador1);

		this.jugadorActual = this.elegirJugadorAleatorio(jugador1,jugador2);
		this.faseActual = new FaseInicial(jugadorActual ,this);
		this.numeroTurno = 1;
	}

	private Jugador elegirJugadorAleatorio(Jugador jugador1, Jugador jugador2) {
		ArrayList <Jugador> jugadores = new ArrayList <Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		return jugadores.get(new Random().nextInt(jugadores.size()));
	}
	
	public Jugador getJugadorActual() {
		return jugadorActual;
	}
	
	public int getNumeroDeTurno(){
		return numeroTurno;
	}
	
	public void pasarFase() {
		faseActual = faseActual.pasarFase();
	}
	
	public void terminarTurno() {
		numeroTurno++;
		jugadorActual = jugadorActual.getOponente();
	}

	public String getNombreFase() {
		return faseActual.getNombreFase();
	}
	
	@Override
	public String getNombre() {
		return faseActual.getNombre();
	}

	@Override
	public int getVida() {
		return faseActual.getVida();
		
	}

	@Override
	public Jugador getOponente() {
		return faseActual.getOponente();
	}

	@Override
	public ArrayList<Carta> getCartasEnMano() {
		return faseActual.getCartasEnMano();
	}

	@Override
	public void verZonaDeMonstruos() {
		faseActual.verZonaDeMonstruos();
	}

	@Override
	public void verCementerio() {
		faseActual.verCementerio();
	}

	@Override
	public void tomarCartasDelMazo(int cantidadDeCartasATomar) {
		faseActual.tomarCartasDelMazo(cantidadDeCartasATomar);
		
	}

	@Override
	public void agregarCartaALaMano(Carta cartaNueva) {
		faseActual.agregarCartaALaMano(cartaNueva);
		
	}

	@Override
	public void recibirDanio(int danio) {
		faseActual.recibirDanio(danio);
		
	}

	@Override
	public void recibirAtaqueDirecto(CartaMonstruo monstruo) {
		faseActual.recibirAtaqueDirecto(monstruo);
	}

	@Override
	public void activar(Carta carta, Jugador oponente) {
		faseActual.activar(carta, oponente);
	}

	@Override
	public void enviarAlCementerio(CartaMonstruo carta) {
		faseActual.enviarAlCementerio(carta);
	}

	@Override
	public boolean estaEnLaZonaDeMonstruos(CartaMonstruo carta) {
		return faseActual.estaEnLaZonaDeMonstruos(carta);
	}

	@Override
	public boolean estaEnElCementerio(Carta carta) {
		return faseActual.estaEnElCementerio(carta);
	}

	@Override
	public void destruirMonstruos() {
		faseActual.destruirMonstruos();
	}

	@Override
	public CartaMonstruo getMonstruoConMayorNivel() {
		return faseActual.getMonstruoConMayorNivel();
	}

	@Override
	public void aumentarAtaqueMonstruos(int aumento) {
		faseActual.aumentarAtaqueMonstruos(aumento);
	}

	@Override
	public void aumentarDefensaMonstruos(int aumento) {
		faseActual.aumentarDefensaMonstruos(aumento);
	}

	@Override
	public void destruirMonstruoConMenorAtaque() {
		faseActual.destruirMonstruoConMenorAtaque();
	}

	@Override
	public void sacrificarMonstruo() {
		faseActual.sacrificarMonstruo();
	}

	@Override
	public int cantidadDeMonstruos() {
		return faseActual.cantidadDeMonstruos();
	}

	@Override
	public int cantidadCartasMonstruoEnCampo(String nombre) {
		return faseActual.cantidadCartasMonstruoEnCampo(nombre);
	}

	@Override
	public boolean verificarTrampas(CartaMonstruo miMonstruo, CartaMonstruo monstruoEnemigo, Jugador oponente) {
		return faseActual.verificarTrampas(miMonstruo, monstruoEnemigo, oponente);
	}

	@Override
	public void ganarDuelo() {
		faseActual.ganarDuelo();
	}

	public String getMensajeFase() {
		return faseActual.getMensajeFase();
	}
}
