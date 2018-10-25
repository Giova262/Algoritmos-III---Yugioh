package fiuba.modelo;

import java.util.ArrayList;

import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaDeCampo;
import fiuba.modelo.cartas.CartaExodia;
import fiuba.modelo.cartas.CartaMagica;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.cartas.CartaTrampa;

public interface InterfaceJugador {
	public String getNombre();
	public int getVida();
	public Jugador getOponente();
	public ArrayList<Carta> getCartasEnMano();
	public void verZonaDeMonstruos();
	public void verCementerio();
	public void tomarCartasDelMazo(int cantidadDeCartasATomar);
	public void agregarCartaALaMano(Carta cartaNueva);
	public void recibirDanio(int danio);
	public void recibirAtaqueDirecto(CartaMonstruo monstruo);
	public void activar(Carta carta, Jugador oponente);
	public void enviarAlCementerio(CartaMonstruo carta);
	public boolean estaEnLaZonaDeMonstruos(CartaMonstruo carta);
	public boolean estaEnElCementerio(Carta carta);
	public void destruirMonstruos();
	public CartaMonstruo getMonstruoConMayorNivel();
	public void aumentarAtaqueMonstruos(int aumento);
	public void aumentarDefensaMonstruos(int aumento);
	public void destruirMonstruoConMenorAtaque();
	public void sacrificarMonstruo();
	public int cantidadDeMonstruos();
	public int cantidadCartasMonstruoEnCampo(String nombre);
	public boolean verificarTrampas(CartaMonstruo miMonstruo,CartaMonstruo monstruoEnemigo ,Jugador oponente);
	public void ganarDuelo();	
}
