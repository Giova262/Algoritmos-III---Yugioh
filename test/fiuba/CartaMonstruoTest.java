package fiuba;

import fiuba.modelo.*;
import fiuba.modelo.cartas.*;
import fiuba.modelo.efectos.*;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruo;
import fiuba.modelo.invocaciones.*;
import fiuba.modelo.estrategiaMonstruo.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.modelo.excepciones.NoPuedeAtacarEnModoDeDefensaError;

public class CartaMonstruoTest {

	@Test
	public void testCambiarAModoDefensaYObtenerPuntosDeDefensaComoLosPuntosDeBatalla() {
		//Arrange
		Jugador jugador1 = new Jugador("Dtoke");
		int puntosDeAtaque = 200;
		int puntosDeDefensa = 500;
		int nivel = 1;
		EfectoMonstruo sinEfecto = null;
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		
		//Act
		monstruo.setEstrategiaInicial(new ModoDefensa());
		
		//Assert
		assertEquals(puntosDeDefensa,monstruo.getPuntosDeBatalla());
	}
	
	@Test
	public void testCambiarAModoAtaqueYObtenerPuntosDeAtaqueComoLosPuntosDeBatalla() {
		//Arrange
		Jugador jugador1 = new Jugador("Dtoke");
		int puntosDeAtaque = 200;
		int puntosDeDefensa = 500;
		int nivel = 1;
		EfectoMonstruo sinEfecto = null;
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		
		//Act
		monstruo.setEstrategiaInicial(new ModoAtaque());
		
		//Assert
		assertEquals(puntosDeAtaque,monstruo.getPuntosDeBatalla());
	}

	@Test
	public void testCambiarAModoDefensaAntesEstandoEnAtaqueYObtenerPuntosDeDefensaComoLosPuntosDeBatalla() {
		//Arrange
		Jugador jugador1 = new Jugador("Dtoke");
		int puntosDeAtaque = 200;
		int puntosDeDefensa = 500;
		int nivel = 1;
		EfectoMonstruo sinEfecto = null;
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		
		//Act
		monstruo.setEstrategiaInicial(new ModoAtaque());
		monstruo.cambiarModo();
		
		//Assert
		assertEquals(puntosDeDefensa,monstruo.getPuntosDeBatalla());
	}
	
	@Test
	public void testAtacarEstandoEnModoDeDefensaDevuelveError() {
		//Arrange
		boolean seLanzoError = false;
		Jugador jugador1 = new Jugador("Dtoke");
		int puntosDeAtaque = 200;
		int puntosDeDefensa = 500;
		int nivel = 1;
		EfectoMonstruo sinEfecto = null;
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("otroMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		monstruo2.setEstrategiaInicial(new ModoDefensa());
		
		//Act
		try {
			monstruo1.esAtacadaPor(monstruo2);
		} catch (NoPuedeAtacarEnModoDeDefensaError e) {
			seLanzoError = true;
		}
		
		//Assert
		assertTrue(seLanzoError);
	}
	
}