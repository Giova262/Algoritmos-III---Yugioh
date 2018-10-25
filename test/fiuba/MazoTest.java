package fiuba;

import fiuba.modelo.*;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MazoTest {

	@Test
	public void testMazoEmpiezaLleno() {
		//Arrange
		Jugador jugador = new Jugador("unJugador");
		Mazo mazo = new Mazo(jugador);
		
		//Assert
	   assertEquals(40,mazo.cartasDisponibles());
	}
	
	@Test
	public void testMazoSacarCarta() {
		//Arrange
		Jugador jugador = new Jugador("unJugador");
		Mazo mazo = new Mazo(jugador);
		
		//Act
		mazo.tomarCarta();
		
		//Assert
		assertEquals(39,mazo.cartasDisponibles());
	}
	
	@Test
	public void testMazoSacarVariasCartas() {
		//Arrange
		Jugador jugador = new Jugador("unJugador");
		Mazo mazo = new Mazo(jugador);
		
		//Act
		mazo.tomarCarta();
		mazo.tomarCarta();
		mazo.tomarCarta();
		mazo.tomarCarta();
		
		//Assert
		assertEquals(36, mazo.cartasDisponibles());
	}
	
	@Test
	public void testMazoSacarTodasLasCartas() {
		//Arrange
		Jugador jugador = new Jugador("unJugador");
		Mazo mazo = new Mazo(jugador);
		
		//Act
		for(int i=0;i<40;i++) {
			mazo.tomarCarta();
		}
		
		//Assert
		assertTrue(mazo.estaVacio());
	}
	
}