package fiuba.modelo.estrategiaMonstruo;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.CartaMonstruo;

public abstract class EstrategiaMonstruo {
	
	public abstract int getPuntosDeBatalla(int puntosDeAtaque, int puntosDeDefensa);
	public abstract EstrategiaMonstruo cambiarModo();
	public abstract int recibirDanio(int puntosDeBatalla, int puntosDeDanio);
	public abstract void envioDeCartasAlCementerio(CartaMonstruo cartaMia, CartaMonstruo cartaRival, int puntosMios, int puntosRival);
	public abstract void validarAtaque();
	public abstract boolean voltearCarta(Jugador jugador);
	public abstract void setCarta(CartaMonstruo cartaMonstruo);
	
	public int devolverDanioAlAtacante(int puntosMios, int puntosAtacante) {
		if(puntosMios > puntosAtacante) return puntosMios - puntosAtacante;
		return 0;
	}
	
}
