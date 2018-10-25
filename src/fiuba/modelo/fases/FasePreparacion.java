package fiuba.modelo.fases;

import java.util.ArrayList;

import fiuba.modelo.AlGoOh;
import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.excepciones.AccionNoPermitidaEnFaseInicialError;
import fiuba.modelo.excepciones.AccionNoPermitidaEnFasePreparacionError;

public class FasePreparacion extends Fase {
	private static String nombreFase = "Fase Preparacion";
	private static String mensajeFase = "Coloca tus cartas en el campo.";
	
	public FasePreparacion(Jugador unJugador, AlGoOh turno) {
		super(unJugador,turno);
	}

	@Override
	public Fase pasarFase() {
		if (turno.getNumeroDeTurno() == 1) {
			return new FaseFinal(jugador,turno);
		}
		return new FaseAtaque(jugador,turno);
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
		throw new AccionNoPermitidaEnFasePreparacionError();
	}

	@Override
	public void agregarCartaALaMano(Carta cartaNueva) {
		throw new AccionNoPermitidaEnFasePreparacionError();
	}

	@Override
	public void recibirDanio(int danio) {
		throw new AccionNoPermitidaEnFasePreparacionError();
	}

	@Override
	public void recibirAtaqueDirecto(CartaMonstruo monstruo) {
		throw new AccionNoPermitidaEnFasePreparacionError();
	}

	@Override
	public void activar(Carta carta, Jugador oponente) {
		throw new AccionNoPermitidaEnFasePreparacionError();
	}

	@Override
	public void enviarAlCementerio(CartaMonstruo carta) {
		throw new AccionNoPermitidaEnFasePreparacionError();
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
		// creo que no hace falta
		return jugador.getMonstruoConMayorNivel();
	}

	@Override
	public void aumentarAtaqueMonstruos(int aumento) {
		throw new AccionNoPermitidaEnFasePreparacionError();
	}

	@Override
	public void aumentarDefensaMonstruos(int aumento) {
		throw new AccionNoPermitidaEnFasePreparacionError();
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
		throw new AccionNoPermitidaEnFasePreparacionError();
	}

	@Override
	public void ganarDuelo() {
		throw new AccionNoPermitidaEnFasePreparacionError();
	}

}