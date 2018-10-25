package fiuba;

import fiuba.modelo.*;
import fiuba.modelo.cartas.*;
import fiuba.modelo.efectos.*;
import fiuba.modelo.efectos.efectoMagica.EfectoAgujeroOscuro;
import fiuba.modelo.efectos.efectoMagica.EfectoMagica;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruo;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruoNulo;
import fiuba.modelo.invocaciones.*;
import fiuba.modelo.estrategiaMonstruo.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AlGoOhTest {

	@Test
	public void testMonstruoAtacaConAtaqueMasAltoDestruyeMonstruoEnemigoYLeRestaVidaAmbosEnModoAtaque() {
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		Jugador jugador2 = new Jugador("Mari");	
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("a",sinEfecto,800,400,1,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("b",sinEfecto,700,400,1,jugador2,invocacion);
		monstruo1.setEstrategiaInicial(new ModoAtaque());
		monstruo2.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(monstruo1);
		jugador2.invocar(monstruo2);
		
		//Act
		monstruo2.esAtacadaPor(monstruo1);
		
		//Assert
		assertEquals(8000, jugador1.getVida());
		assertEquals(7900, jugador2.getVida());
		assertTrue(jugador2.estaEnElCementerio(monstruo2));
	}
	
	@Test
	public void testMonstruoAtacaConAtaqueMasBajoEsDestruidoYPierdeVidaAmbosEnModoAtaque() {		
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		Jugador jugador2 = new Jugador("Mari");	
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("a",sinEfecto,800,400,1,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("b",sinEfecto,900,400,1,jugador2,invocacion);		
		monstruo1.setEstrategiaInicial(new ModoAtaque());
		monstruo2.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(monstruo1);
		jugador2.invocar(monstruo2);
		
		//Act
		monstruo2.esAtacadaPor(monstruo1);
	
		//Assert
		assertEquals(7900, jugador1.getVida());
		assertEquals(8000, jugador2.getVida());
		assertTrue(jugador1.estaEnElCementerio(monstruo1));
	}
	
	@Test
	public void testMonstruosLuchanConIgualAtaqueSeDestruyenYNoSePierdeVidaAmbosEnModoAtaque() {
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		Jugador jugador2 = new Jugador("Mari");	
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("a",sinEfecto,800,400,1,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("b",sinEfecto,800,400,1,jugador2,invocacion);
		monstruo1.setEstrategiaInicial(new ModoAtaque());		
		monstruo2.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(monstruo1);
		jugador2.invocar(monstruo2);
				
		//Act
		monstruo2.esAtacadaPor(monstruo1);
			
		//Assert
		assertEquals(8000, jugador1.getVida());
		assertEquals(8000, jugador2.getVida());		
		assertTrue(jugador1.estaEnElCementerio(monstruo1));
		assertTrue(jugador2.estaEnElCementerio(monstruo2));
	}
	
	@Test
	public void testMonstruoAtacaAMonstruoEnModoDefensaConMenosPuntosDeDefensaLoDestruyeYNoSePierdeVida() {
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		Jugador jugador2 = new Jugador("Mari");	
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("a",sinEfecto,800,400,1,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("b",sinEfecto,700,700,1,jugador2,invocacion);
		monstruo1.setEstrategiaInicial(new ModoAtaque());
		monstruo2.setEstrategiaInicial(new ModoDefensa());
		jugador1.invocar(monstruo1);
		jugador2.invocar(monstruo2);
		
		//Act
		monstruo2.esAtacadaPor(monstruo1);
		
		//Assert
		assertEquals(8000, jugador1.getVida());
		assertEquals(8000, jugador2.getVida());
		assertTrue(jugador2.estaEnElCementerio(monstruo2));
		
	}
	
	@Test
	public void testMonstruoAtacaAMonstruoEnModoDefensaConMasPuntosNoLoDestruyeYPierdeVida() {
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		Jugador jugador2 = new Jugador("Mari");	
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("a",sinEfecto,800,400,1,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("b",sinEfecto,700,900,1,jugador2,invocacion);		
		monstruo1.setEstrategiaInicial(new ModoAtaque());
		monstruo2.setEstrategiaInicial(new ModoDefensa());
		jugador1.invocar(monstruo1);
		jugador2.invocar(monstruo2);
				
		//Act
		monstruo2.esAtacadaPor(monstruo1);
				
		//Assert
		assertEquals(7900, jugador1.getVida());
		assertEquals(8000, jugador2.getVida());
		assertFalse(jugador1.estaEnElCementerio(monstruo1));
	}
	
	@Test
	public void testMonstruoAtacaAMonstruoEnModoDefensaConIgualPuntosNingunoSeDestruyeYNoPierdenVida() {
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		Jugador jugador2 = new Jugador("Mari");
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("a",sinEfecto,800,400,1,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("b",sinEfecto,700,800,1,jugador2,invocacion);		
		monstruo1.setEstrategiaInicial(new ModoAtaque());
		monstruo2.setEstrategiaInicial(new ModoDefensa());
		jugador1.invocar(monstruo1);
		jugador2.invocar(monstruo2);
				
		//Act
		monstruo2.esAtacadaPor(monstruo1);
				
		//Assert
		assertEquals(8000, jugador1.getVida());
		assertEquals(8000, jugador2.getVida());		
		assertFalse(jugador1.estaEnElCementerio(monstruo1));
		assertFalse(jugador2.estaEnElCementerio(monstruo2));
	}
	
	@Test
	public void testActivarEfectoDeAgujeroOscuroMandaAlCementerioATodasLasCartasMonstruo() {
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		Jugador jugador2 = new Jugador("Mari");	
		EfectoMagica vaciamiento = new EfectoAgujeroOscuro();
		EfectoMonstruo sinEfecto = null;
		
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("A1",sinEfecto, 700, 600, 1, jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("B1",sinEfecto, 700, 600, 1, jugador1,invocacion);
		CartaMonstruo monstruo3 = new CartaMonstruo("C2",sinEfecto, 800, 900, 1, jugador2,invocacion);
		CartaMonstruo monstruo4 = new CartaMonstruo("D2",sinEfecto, 800, 900, 1, jugador2,invocacion);
		
		CartaMagica agujeroOscuro = new CartaMagica("Agujero Oscuro",vaciamiento,jugador1);
		
		monstruo1.setEstrategiaInicial(new ModoAtaque());
		monstruo2.setEstrategiaInicial(new ModoAtaque());
		monstruo3.setEstrategiaInicial(new ModoAtaque());
		monstruo4.setEstrategiaInicial(new ModoAtaque());
		
		jugador1.invocar(monstruo1);
		jugador1.invocar(monstruo2);
		jugador2.invocar(monstruo3);
		jugador2.invocar(monstruo4);
						
		//Act
		agujeroOscuro.activarEfecto( jugador2);
						
		//Assert
		assertEquals(8000, jugador1.getVida());
		assertEquals(8000, jugador2.getVida());		
				
		assertTrue(jugador1.estaEnElCementerio(monstruo1));
		assertTrue(jugador1.estaEnElCementerio(monstruo2));
		assertTrue(jugador2.estaEnElCementerio(monstruo3));
		assertTrue(jugador2.estaEnElCementerio(monstruo4));
	}
	
	@Test
	public void testSacrificarUnaCartaParaInvocarMonstruoNivel5o6(){
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		int posicion = 0;
		int posicion2 = 1;

		Invocacion invocacionNivel1 = new InvocacionNivel1A4();
		CartaMonstruo carta1 = new CartaMonstruo("A1",sinEfecto, 700, 600, 1, jugador1,invocacionNivel1);
		Invocacion invocacionNivel5 = new InvocacionNivel5A6();
		CartaMonstruo monstruoNivel5 = new CartaMonstruo("B1",sinEfecto, 700, 600, 5, jugador1,invocacionNivel5);

		carta1.setEstrategiaInicial(new ModoAtaque());
		monstruoNivel5.setEstrategiaInicial(new ModoAtaque());
			
		jugador1.invocar(carta1);
								
		//Act
		jugador1.invocar(monstruoNivel5);
						
		//Assert				
		assertTrue(jugador1.estaEnElCementerio(carta1));
		assertTrue(jugador1.estaEnLaZonaDeMonstruos(monstruoNivel5));
	}
	
	@Test
	public void testSacrificarDosCartasParaInvocarMonstruoNivelMayorA6(){
		//Arrange
		Jugador jugador1 = new Jugador("Gio");
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacionNivel2 = new InvocacionNivel1A4();
		Invocacion invocacionNivel7 = new InvocacionNivel7oMas();
		int posicion0 = 0;
		int posicion1 = 1;
		int posicion2 = 2;
		
		CartaMonstruo monstruo1 = new CartaMonstruo("A1",sinEfecto, 700, 600, 2, jugador1,invocacionNivel2);
		CartaMonstruo monstruo2 = new CartaMonstruo("B1",sinEfecto, 700, 600, 2, jugador1,invocacionNivel2);
		CartaMonstruo monstruoNivel7 = new CartaMonstruo("C1",sinEfecto, 700, 600, 7, jugador1,invocacionNivel7);

		monstruo1.setEstrategiaInicial(new ModoAtaque());
		monstruo2.setEstrategiaInicial(new ModoAtaque());
		monstruoNivel7.setEstrategiaInicial(new ModoAtaque());
				
		jugador1.invocar(monstruo1);
		jugador1.invocar(monstruo2);
										
		//Act
		jugador1.invocar(monstruoNivel7);
								
		//Assert							
		assertTrue(jugador1.estaEnElCementerio(monstruo1));
		assertTrue(jugador1.estaEnElCementerio(monstruo2));
		assertTrue(jugador1.estaEnLaZonaDeMonstruos(monstruoNivel7));
	}
	
}