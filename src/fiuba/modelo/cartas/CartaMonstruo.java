package fiuba.modelo.cartas;

import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruo;
import fiuba.modelo.estrategiaMonstruo.*;
import fiuba.modelo.invocaciones.*;

public class CartaMonstruo implements Carta {

	private String nombre;	
	private EstrategiaMonstruo modoDeBatalla;
	private int puntosDeAtaque;
	private int puntosDeDefensa;
	private int nivel;
	private Jugador jugador;
	private EfectoMonstruo efecto;
	private Invocacion invocacion;
	
	public CartaMonstruo(String nombre, EfectoMonstruo efecto, int ataque, int defensa, int nivel, Jugador jugador, Invocacion invocacion) {
		this.nombre = nombre;
		this.puntosDeAtaque = ataque;
		this.puntosDeDefensa = defensa;
		this.nivel = nivel;
		this.jugador =  jugador;
		this.efecto = efecto;
		this.invocacion = invocacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void setEstrategiaInicial(EstrategiaMonstruo estrategia) {
		modoDeBatalla = estrategia;
		modoDeBatalla.setCarta(this);
	}
	
	public void cambiarModo() {
		modoDeBatalla = modoDeBatalla.cambiarModo();
	}
	
	public int getPuntosDeBatalla() {
		return modoDeBatalla.getPuntosDeBatalla(puntosDeAtaque,puntosDeDefensa);
	}

	public int recibirDanio(int puntosDeDanio) {
		return modoDeBatalla.recibirDanio(this.getPuntosDeBatalla(),puntosDeDanio);
	}

	public void esAtacadaPor(CartaMonstruo monstruoEnemigo) {
		monstruoEnemigo.validarAtaque();
		
		if (jugador.verificarTrampas(this,monstruoEnemigo,monstruoEnemigo.getJugador()) && !modoDeBatalla.voltearCarta(monstruoEnemigo.getJugador())) {
			this.resolverBatalla(monstruoEnemigo);
		}
	}

	public void resolverBatalla(CartaMonstruo monstruoEnemigo) {
		int puntosMios = this.getPuntosDeBatalla();
		int puntosRival= monstruoEnemigo.getPuntosDeBatalla() ;
			
		/*Modifico la vida del jugador que fue atacado*/
		this.jugador.recibirDanio(modoDeBatalla.recibirDanio(puntosMios,puntosRival));
		
		/*Modifico la vida del jugador que ataca*/
		monstruoEnemigo.enviarDanioAlDuenio(modoDeBatalla.devolverDanioAlAtacante(puntosMios,puntosRival));
		
		/*Evalua que cartas van al cementerio como resultado de la batalla*/ 
		this.modoDeBatalla.envioDeCartasAlCementerio(this,monstruoEnemigo,puntosMios,puntosRival);
	}
	
	public void enviarmeAlCementerio() {
		this.jugador.enviarAlCementerio(this);		
	}

	private void enviarDanioAlDuenio(int danio) {
		jugador.recibirDanio(danio);	
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public void activarEfecto(Jugador oponente) {
		efecto.activar(oponente);
	}

	public void aumentarPuntosDeAtaque(int aumento) {
		puntosDeAtaque += aumento;
	}

	public void aumentarPuntosDeDefensa(int aumento) {
		puntosDeDefensa += aumento;
	}

	public boolean tieneMenosPuntosDeAtaqueQue(CartaMonstruo otroMonstruo) {
		return otroMonstruo.tienePuntosDeAtaqueMayorA(this.puntosDeAtaque);
	}

	private boolean tienePuntosDeAtaqueMayorA(int puntos) {
		return puntosDeAtaque > puntos;
	}
	
	public boolean tieneMenosNivelQue(CartaMonstruo otroMonstruo) {
		return otroMonstruo.tieneNivelMayorA(this.nivel);
	}

	private boolean tieneNivelMayorA(int unNivel) {
		return nivel > unNivel;
	}

	public boolean tieneMasNivelQue(CartaMonstruo otroMonstruo) {
		return otroMonstruo.tieneNivelMenorA(this.nivel);
	}

	private boolean tieneNivelMenorA(int unNivel) {
		return nivel < unNivel;
	}
	
	public int sacrificiosNecesarios() {
		return invocacion.sacrificiosNecesarios(jugador);
	}

	public void sacrificarNecesarios() {
		invocacion.sacrificarNecesarios(jugador);
	}
	
	public void validarAtaque() {
		modoDeBatalla.validarAtaque();
	}

	public boolean activarEfectoSiendoAtacado(Jugador jugador) {
		return efecto.activarSiendoAtacado(jugador);
	}
	
	public boolean estaEnModoDefensa() {
		return modoDeBatalla.getClass() == ModoDefensa.class;
	}

	public boolean estaBocaAbajo() {
		return modoDeBatalla.getClass() == ModoDefensa.class;
	}

	
}