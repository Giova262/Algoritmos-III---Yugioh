package fiuba.modelo;

import java.util.ArrayList;

import fiuba.modelo.excepciones.*;
import fiuba.modelo.cartas.*;

public class Jugador implements InterfaceJugador{
	
	private String nombre;
	private int vida;
	private Tablero tablero;
	private ArrayList<Carta> cartasEnMano;
	private ArrayList<CartaExodia> coleccionExodia;
	private Jugador oponente;
	
	public static final int VIDA = 8000 ;
		
	public Jugador( String nombre) {
		this.nombre = nombre;
		this.vida = VIDA;
		this.tablero = new Tablero(this);
		this.cartasEnMano = new ArrayList<Carta>();
		this.coleccionExodia = new ArrayList<CartaExodia>();
		this.oponente = null;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setOponente(Jugador unJugador) {
		oponente = unJugador;
	}
	
	public Jugador getOponente() {
		return oponente;
	}
	
	public ArrayList<Carta> getCartasEnMano() {
		return cartasEnMano;
	}
	
	public void verZonaDeMonstruos() {
		tablero.verZonaDeMonstruos();		
	}
	
	public void verCementerio() {
		tablero.verCementerio();		
	}
	
	public void verCartaMano() {
		for(Carta i: cartasEnMano) System.out.println(i.getNombre());	
	}
	
	public void tomarCartasDelMazo(int cantidadDeCartasATomar) {
		for (int i = 0; i < cantidadDeCartasATomar; i++) {
			Carta cartaNueva = tablero.sacarUnaCartaDelMazo();
			this.agregarCartaALaMano(cartaNueva);
		}
	}
	
	public void agregarCartaALaMano(Carta cartaNueva) {
		cartasEnMano.add(cartaNueva);
		if (cartaNueva.getClass() == CartaExodia.class) {
			CartaExodia exodia = (CartaExodia) cartaNueva;
			coleccionExodia.add(exodia);
			if (coleccionExodia.size() == 5) {
				exodia.activarEfecto(this);
			}
		}
	}
	
	public void recibirDanio(int danio) {
		vida -= danio;
		if (vida <= 0) {
			this.perderDueloPorVida();
		}
	}
	
	public void recibirAtaqueDirecto(CartaMonstruo monstruo) {
		if (this.cantidadDeMonstruos() > 0) throw new NoSePuedeAtacarDirectamenteError();
		monstruo.validarAtaque();
		if(this.verificarTrampas(null ,monstruo ,monstruo.getJugador())) {
			recibirDanio(monstruo.getPuntosDeBatalla());
		}
	}
	
	public void invocar(CartaMonstruo carta) {		
		if(carta.getClass()== CartaExodia.class) invocarExodia((CartaExodia) carta);
		tablero.invocar(carta);
		cartasEnMano.remove(carta);
	}
	
	public void invocar(CartaMagica carta) {		
		tablero.invocar(carta);
		cartasEnMano.remove(carta);
	}
	
	public void invocar(CartaTrampa carta) {		
		tablero.invocar(carta);
		cartasEnMano.remove(carta);
	}
	
	public void invocar(CartaDeCampo carta) {		
		tablero.invocar(carta);
		cartasEnMano.remove(carta);
	}
	
	public void invocarExodia(CartaExodia carta) {
		coleccionExodia.remove(carta);
	}
	
	public void activar(Carta carta, Jugador oponente) {
		carta.activarEfecto(oponente);
	}
	
	public void enviarAlCementerio(CartaMonstruo carta) {
		tablero.mandarAlCementerio(carta);
	}
	
	public void enviarAlCementerio(CartaMagica carta) {
		tablero.mandarAlCementerio(carta);
	}
	
	public void enviarAlCementerio(CartaTrampa carta) {
		tablero.mandarAlCementerio(carta);
	}
	
	public void enviarAlCementerio(CartaDeCampo carta) {
		tablero.mandarAlCementerio(carta);
	}
	
	public boolean estaEnLaZonaDeMonstruos(CartaMonstruo carta) {
		return tablero.estaEnLaZonaDeMonstruos(carta);
	}

	public boolean estaEnElCementerio(Carta carta) {
		return tablero.estaEnElCementerio(carta);
	}

	public void destruirMonstruos() {
		tablero.vaciarZonaDeMonstruos();
	}
	
	public CartaMonstruo getMonstruoConMayorNivel() {
		return tablero.getMonstruoConMayorNivel();
	}

	public void aumentarAtaqueMonstruos(int aumento) {
		tablero.aumentarAtaqueMonstruos(aumento);
	}

	public void aumentarDefensaMonstruos(int aumento) {
		tablero.aumentarDefensaMonstruos(aumento);
	}

	public void destruirMonstruoConMenorAtaque() {
		CartaMonstruo monstruo = tablero.getMonstruoConMenorAtaque();
		enviarAlCementerio(monstruo);
	}

	public void sacrificarMonstruo() {
		tablero.sacrificarMonstruo();
	}
	
	public int cantidadDeMonstruos(){
		return tablero.getCantidadDeMonstruos();
	}

	public CartaMonstruo obtenerMonstruoEnCampo(String nombre) {
		return tablero.obtenerMonstruoEnCampo(nombre);
	}

	public int cantidadCartasMonstruoEnCampo(String nombre) {
		return tablero.cantidadDeMonstruosEnCampo(nombre);
	}
	
	public boolean verificarTrampas(CartaMonstruo miMonstruo,CartaMonstruo monstruoEnemigo ,Jugador oponente) {
		return tablero.verificarTrampas(miMonstruo,monstruoEnemigo,oponente);
	}
	
	public void perderDueloPorVida() {
		throw new JugadorSeQuedoSinVidaError(this);
	}
	
	public void perderDueloPorCartas() {
		throw new JugadorSeQuedoSinCartasError(this);
	}

	public void ganarDuelo() {
		throw new JugadorSacoTodasLasPartesDeExodiaError(this);
	}

	public String devolverNombreDeLaImagenDeCartaMagicaOTrampa(int indice) {
		return tablero.devolverNombreDeLaImagenDeCartaMagicaOTrampa(indice);
	}

	public String devolverNombreDeLaImagenDeCartaMonstruo(int indice) {
		return tablero.devolverNombreDeLaImagenDeCartaMonstruo(indice);
	}

	public String devolverNombreDeLaImagenDeCartaCampo() {
		return tablero.devolverNombreDeLaImagenDeCartacampo();
	}

	public boolean estaEnModoDefensaElMonstruo(int indice) {
		return tablero.estaEnModoDefensaElMonstruo(indice);
	}
	
	public Boolean estaBocaAbajoLaCarta(int indice) {
		return tablero.estaBocaAbajoLaCarta(indice);
	}

	public CartaMonstruo getCartaMonstruo(int indiceDeMiCarta) {
		return tablero.getCartaMonstruo(indiceDeMiCarta);
	}

	public Carta getCartaDeMano(int i) {
		return cartasEnMano.get(i);
	}

	public int getTamanioZonaMonstruos() {
		return tablero.getCantidadDeMonstruos();
	}

	public ArrayList<CartaMonstruo> getZonaMonstruos() {
		return tablero.getZonaMonstruos();
	}

	public ArrayList<Carta> getZonaMagicasTrampas() {
		return tablero.getZonaMagicasTrampas();
	}

	public String devolverNombreDeLaUltimaCartaEnElCementerio() {
		return  tablero.devolverNombreDeLaUltimaCartaEncementerio();
	}

	public int cantidadDeCartasEnMazo() {
		return tablero.cantidadDeCartasEnMazo();
	}
	
	public Carta getCartaMagicaOTrampa(int indice) {	
		return tablero.getCartaMagicaOTrampa(indice);
	}
	
}