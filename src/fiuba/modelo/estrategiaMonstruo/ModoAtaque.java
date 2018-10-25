package fiuba.modelo.estrategiaMonstruo;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.estrategiaPosicion.BocaArriba;

public class ModoAtaque extends EstrategiaMonstruo{
	
	@Override
	public int getPuntosDeBatalla(int puntosDeAtaque, int puntosDeDefensa) {
		return puntosDeAtaque;
	}
	
	@Override
	public EstrategiaMonstruo cambiarModo() {
		ModoDefensa estrategia = new ModoDefensa();
		estrategia.setPosicion(new BocaArriba());
		return estrategia;
	}

	@Override
	public int recibirDanio(int misPuntosDeAtaque, int puntosDeDanioQueRecibo) {
		if (misPuntosDeAtaque < puntosDeDanioQueRecibo) return puntosDeDanioQueRecibo - misPuntosDeAtaque;
		return 0;
	}
	
	@Override
	public void envioDeCartasAlCementerio(CartaMonstruo cartaMia, CartaMonstruo cartaAtacante, int puntosMios, int puntosAtacante) {
		if (puntosMios == puntosAtacante) {			
			cartaMia.enviarmeAlCementerio();
			cartaAtacante.enviarmeAlCementerio();
		}
		if (puntosMios < puntosAtacante) {				
			cartaMia.enviarmeAlCementerio();
		}
		if (puntosMios > puntosAtacante) {           	
			cartaAtacante.enviarmeAlCementerio();	
		}
	}

	@Override
	public void validarAtaque() {
		// No hace nada
	}
	
	public boolean voltearCarta(Jugador jugador) {
		return false;
	}

	@Override
	public void setCarta(CartaMonstruo cartaMonstruo) {
		// No hace nada
	}

}