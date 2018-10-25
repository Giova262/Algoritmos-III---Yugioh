package fiuba.modelo.fases;

import java.util.ArrayList;

import fiuba.modelo.AlGoOh;
import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.excepciones.AccionNoPermitidaEnFaseInicialError;

public class FaseInicial extends Fase {
	private static String nombreFase = "Fase Inicial";
	private static String mensajeFase = "Toma una carta del mazo.";
	
	public FaseInicial(Jugador unJugador, AlGoOh unTurno)	{
		super(unJugador,unTurno);
	}
	
	@Override
	public Fase pasarFase() {
		return new FasePreparacion(jugador,turno);
	}
	
	public String getNombreFase() {
		return nombreFase;
	}

	public String getMensajeFase() {
		return mensajeFase;
	}
	
	@Override
	public String getNombre() {
		return jugador.getNombre();
	}

	@Override
	public int getVida() {
		return jugador.getVida();
	}

	public Jugador getOponente() {
		return jugador.getOponente();
	}

	@Override
	public ArrayList<Carta> getCartasEnMano() {
		return jugador.getCartasEnMano();
	}

	@Override
	public void verZonaDeMonstruos() {
		jugador.verZonaDeMonstruos();
	}

	@Override
	public void verCementerio() {
		jugador.verCementerio();
		
	}

	@Override
	public void tomarCartasDelMazo(int cantidadDeCartasATomar) {
		jugador.tomarCartasDelMazo(cantidadDeCartasATomar);
	}

	@Override
	public void agregarCartaALaMano(Carta cartaNueva) {
		jugador.agregarCartaALaMano(cartaNueva);
	}

	@Override
	public void recibirDanio(int danio) {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public void recibirAtaqueDirecto(CartaMonstruo monstruo) {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public void activar(Carta carta, Jugador oponente) {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public void enviarAlCementerio(CartaMonstruo carta) {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public boolean estaEnLaZonaDeMonstruos(CartaMonstruo carta) {
		return jugador.estaEnLaZonaDeMonstruos(carta);
	}

	@Override
	public boolean estaEnElCementerio(Carta carta) {
		return jugador.estaEnElCementerio(carta);
	}

	@Override
	public void destruirMonstruos() {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public CartaMonstruo getMonstruoConMayorNivel() {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public void aumentarAtaqueMonstruos(int aumento) {
		throw new AccionNoPermitidaEnFaseInicialError();
		
	}

	@Override
	public void aumentarDefensaMonstruos(int aumento) {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public void destruirMonstruoConMenorAtaque() {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public void sacrificarMonstruo() {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public int cantidadDeMonstruos() {
		return jugador.cantidadDeMonstruos();
	}

	@Override
	public int cantidadCartasMonstruoEnCampo(String nombre) {
		return jugador.cantidadCartasMonstruoEnCampo(nombre);
	}

	@Override
	public boolean verificarTrampas(CartaMonstruo miMonstruo, CartaMonstruo monstruoEnemigo, Jugador oponente) {
		throw new AccionNoPermitidaEnFaseInicialError();
	}

	@Override
	public void ganarDuelo() {
		jugador.ganarDuelo();
	}

}