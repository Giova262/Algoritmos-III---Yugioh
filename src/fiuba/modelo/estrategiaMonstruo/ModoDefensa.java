package fiuba.modelo.estrategiaMonstruo;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.estrategiaPosicion.BocaAbajo;
import fiuba.modelo.estrategiaPosicion.BocaArriba;
import fiuba.modelo.estrategiaPosicion.EstrategiaPosicion;
import fiuba.modelo.excepciones.NoPuedeAtacarEnModoDeDefensaError;

public class ModoDefensa extends EstrategiaMonstruo {
	
	private EstrategiaPosicion posicion;
	
	public ModoDefensa() {
		posicion = new BocaAbajo();
	}
	
	public int getPuntosDeBatalla(int puntosDeAtaque, int puntosDeDefensa) {
		return puntosDeDefensa;
	}
	
	@Override
	public EstrategiaMonstruo cambiarModo() {
		return new ModoAtaque();
	}
	
	public void setPosicion(EstrategiaPosicion posicionNueva) {
		posicion = posicionNueva;
	}

	public int recibirDanio(int misPuntos, int puntosDeDanioQueRecibo) {	
		return 0;
	}

	@Override
	public void envioDeCartasAlCementerio(CartaMonstruo cartaMia, CartaMonstruo cartaAtacante, int puntosMios, int puntosAtacante) {
		if (puntosMios < puntosAtacante) {				
			cartaMia.enviarmeAlCementerio();
		}
	}

	@Override
	public void validarAtaque() {
		throw new NoPuedeAtacarEnModoDeDefensaError();
	}
	
	@Override
	public boolean voltearCarta(Jugador jugador) {
		boolean seActivoEfecto = posicion.voltear(jugador);
		posicion = new BocaArriba();
		return seActivoEfecto;
	}

	@Override
	public void setCarta(CartaMonstruo cartaMonstruo) {
		posicion.setCarta(cartaMonstruo);
	}
	
}