package fiuba.modelo.fases;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.excepciones.AccionNoPermitidaEnFaseFinalError;

import java.util.ArrayList;

import fiuba.modelo.AlGoOh;

public class FaseFinal extends Fase {
	private static String nombreFase = "Fase Final";
	private static String mensajeFase = "Activa tus cartas magicas.";
	
	public FaseFinal(Jugador unJugador, AlGoOh unTurno) {
		super(unJugador, unTurno);
	}

	@Override
	public Fase pasarFase() {
		turno.terminarTurno();
		return new FaseInicial(jugador.getOponente(),turno);
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
		throw new AccionNoPermitidaEnFaseFinalError();
	}

	@Override
	public void agregarCartaALaMano(Carta cartaNueva) {
		throw new AccionNoPermitidaEnFaseFinalError();
	}

	@Override
	public void recibirDanio(int danio) {
	//capaz no va
		jugador.recibirDanio(danio);
	}

	@Override
	public void recibirAtaqueDirecto(CartaMonstruo monstruo) {
	//Capaz no va
		jugador.recibirAtaqueDirecto(monstruo);
	}
	
	@Override
	public void activar(Carta carta, Jugador oponente) {
		jugador.activar(carta, oponente);
	}

	@Override
	public void enviarAlCementerio(CartaMonstruo carta) {
		jugador.enviarAlCementerio(carta);
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
		// creo que no hace falta
		jugador.destruirMonstruos();
	}

	@Override
	public CartaMonstruo getMonstruoConMayorNivel() {
		// creo que no hace falta
		return jugador.getMonstruoConMayorNivel();
	}

	@Override
	public void aumentarAtaqueMonstruos(int aumento) {
		// creo que no hace falta
		jugador.aumentarAtaqueMonstruos(aumento);
	}

	@Override
	public void aumentarDefensaMonstruos(int aumento) {
		// creo que no hace falta
		jugador.aumentarDefensaMonstruos(aumento);
	}

	@Override
	public void destruirMonstruoConMenorAtaque() {
		// creo que no hace falta
		jugador.destruirMonstruoConMenorAtaque();
	}

	@Override
	public void sacrificarMonstruo() {
		// creo que no hace falta
		jugador.sacrificarMonstruo();
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
		throw new AccionNoPermitidaEnFaseFinalError();
	}

	@Override
	public void ganarDuelo() {
		jugador.ganarDuelo();
	}
}