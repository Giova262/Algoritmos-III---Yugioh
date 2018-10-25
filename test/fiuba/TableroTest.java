package fiuba;

import fiuba.modelo.*;
import fiuba.modelo.cartas.*;
import fiuba.modelo.efectos.*;
import fiuba.modelo.efectos.efectoMagica.EfectoAgujeroOscuro;
import fiuba.modelo.efectos.efectoMagica.EfectoCilindroMagico;
import fiuba.modelo.efectos.efectoMagica.EfectoMagica;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruo;
import fiuba.modelo.efectos.efectoTrampa.EfectoTrampa;
import fiuba.modelo.invocaciones.*;
import fiuba.modelo.estrategiaMonstruo.*;

import static org.junit.Assert.*;

import org.junit.Test;

import fiuba.modelo.excepciones.FaltaSacrificioParaInvocacionError;
import fiuba.modelo.excepciones.ZonaLlenaError;

public class TableroTest {

	@Test
	public void testColocarMonstruoEnModoDeAtaqueEnElTableroLoDejaEnLaZonaDeMonstruos() {
		//Arrange
		int puntosDeAtaque = 700;
		int puntosDeDefensa = 500;
		int nivel = 2;
		int posicion = 0;
		EfectoMonstruo sinEfecto = null;
		Invocacion invocacion = new InvocacionNivel1A4();
		Jugador jugador1 = new Jugador("Wiss");
		Tablero tablero = new Tablero(jugador1);
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		
		//Act
		monstruo.setEstrategiaInicial(new ModoAtaque());
		tablero.invocar(monstruo);
		
		//Assert
		assertTrue(tablero.estaEnLaZonaDeMonstruos(monstruo));
	}

	
	@Test
	public void testInvocarMonstruoDeNivelAltoSinSacrificioDevuelveError() {
		//Arrange
		int puntosDeAtaque = 2500;
		int puntosDeDefensa = 2000;
		int nivel = 8;
		int posicion = 0;
		EfectoMonstruo sinEfecto = null;
		Invocacion invocacion = new InvocacionNivel7oMas();
		Jugador jugador1 = new Jugador("Wiss");
		boolean seLanzoError = false;		
		Tablero tablero = new Tablero(jugador1);
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		
		//Act
		monstruo.setEstrategiaInicial(new ModoDefensa());
		try {
			tablero.invocar(monstruo);
		} catch (FaltaSacrificioParaInvocacionError e) {
			seLanzoError = true;
		}
		
		//Assert
		assertTrue(seLanzoError);	
	}

	@Test
	public void testInvocarCartaEnCampoLlenoDevuelveError() {
		//Arrange
		int puntosDeAtaque = 2500;
		int puntosDeDefensa = 2000;
		int nivel = 3;
		int posicion = 0;
		EfectoMonstruo sinEfecto = null;
		Invocacion invocacion = new InvocacionNivel1A4();
		Jugador jugador1 = new Jugador("Wiss");
		boolean seLanzoError = false;		
		Tablero tablero = new Tablero(jugador1);
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		
		//Act
		try {
			for(int i=0;i < 6;i++) {
				tablero.invocar(monstruo);
			}
		} catch (ZonaLlenaError e) {
			seLanzoError = true;
		}
		
		//Assert
		assertTrue(seLanzoError);	
	}
	
	@Test
	public void testColocarCartaMagicaBocaAbajo() {
		//Arrange
		EfectoMagica efecto = new EfectoAgujeroOscuro();
		Jugador jugador1 = new Jugador("Wiss");
		Tablero tablero = new Tablero(jugador1);
		int posicion = 0;
		CartaMagica magica = new CartaMagica("cartaMagica",efecto,jugador1);
		
		//Act	
		tablero.invocar(magica);
		
		//Assert
		assertTrue(tablero.estaEnLaZonaDeMagiasYTrampas(magica));
	}
	
	@Test
	public void testColocarCartaTrampaBocaAbajo() {
		//Arrange
		EfectoTrampa efecto = new EfectoCilindroMagico();
		Jugador jugador1 = new Jugador("Wiss");
		Tablero tablero = new Tablero(jugador1);
		int posicion = 0;
		CartaTrampa trampa = new CartaTrampa("cartaTrampa",efecto,jugador1);
		
		//Act	
		tablero.invocar(trampa);
		
		//Assert
		assertTrue(tablero.estaEnLaZonaDeMagiasYTrampas(trampa));
	}
	
	@Test
	public void testDestruirUnaCartaMostruoLaDejaEnElCementerio() {
		//Arrange
		int puntosDeAtaque = 900;
		int puntosDeDefensa = 300;
		int nivel = 2;
		int posicion = 0;
		EfectoMonstruo sinEfecto = null;
		Invocacion invocacion = new InvocacionNivel1A4();
		Jugador jugador1 = new Jugador("Wiss");
		Tablero tablero = new Tablero(jugador1);
		CartaMonstruo monstruo = new CartaMonstruo("monstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		
		
		//Act	
		tablero.invocar(monstruo);
		tablero.mandarAlCementerio(monstruo);
		
		//Assert
		assertTrue(tablero.estaEnElCementerio(monstruo));
		assertFalse(tablero.estaEnLaZonaDeMonstruos(monstruo));
	}
	
}