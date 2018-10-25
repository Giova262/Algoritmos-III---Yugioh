package fiuba;

import fiuba.modelo.*;
import fiuba.modelo.cartas.*;
import fiuba.modelo.efectos.*;
import fiuba.modelo.efectos.efectoCampo.EfectoCampo;
import fiuba.modelo.efectos.efectoCampo.EfectoSogen;
import fiuba.modelo.efectos.efectoCampo.EfectoWasteland;
import fiuba.modelo.efectos.efectoMagica.EfectoCilindroMagico;
import fiuba.modelo.efectos.efectoMagica.EfectoFisura;
import fiuba.modelo.efectos.efectoMagica.EfectoMagica;
import fiuba.modelo.efectos.efectoMonstruo.EfectoExodia;
import fiuba.modelo.efectos.efectoMonstruo.EfectoInsectoComeHombres;
import fiuba.modelo.efectos.efectoMonstruo.EfectoJinzo7;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruo;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruoNulo;
import fiuba.modelo.efectos.efectoTrampa.EfectoOllaDeLaCodicia;
import fiuba.modelo.efectos.efectoTrampa.EfectoReinforcements;
import fiuba.modelo.efectos.efectoTrampa.EfectoTrampa;
import fiuba.modelo.invocaciones.*;
import fiuba.modelo.estrategiaMonstruo.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.modelo.excepciones.JugadorSacoTodasLasPartesDeExodiaError;
import fiuba.modelo.excepciones.JugadorSeQuedoSinCartasError;
import fiuba.modelo.excepciones.JugadorSeQuedoSinVidaError;
import fiuba.modelo.excepciones.NoSePuedeAtacarDirectamenteError;

public class JugadorTest {

	@Test
	public void testSacrificarUnMonstruoDestruyeElMonstruoDeMenorNivel() {
		//Arrange
		int puntosDeAtaque = 900;
		int puntosDeDefensa = 300;
		int nivel1 = 1;
		int nivel2 = 2;
		int nivel5 = 5;
		int posicion0 = 0;
		int posicion1 = 1;
		int posicion2 = 2;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacionSinSacrificio = new InvocacionNivel1A4();
		Invocacion invocacionDeUnSacrificio = new InvocacionNivel5A6();		
		Jugador jugador = new Jugador("Wiss");
		CartaMonstruo monstruoDeNivel1 = new CartaMonstruo("monstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel1,jugador,invocacionSinSacrificio);
		CartaMonstruo monstruoDeNivel2 = new CartaMonstruo("monstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel2,jugador,invocacionSinSacrificio);
		CartaMonstruo monstruoDeNivel5 = new CartaMonstruo("monstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel5,jugador,invocacionDeUnSacrificio);		
		jugador.invocar(monstruoDeNivel1);
		jugador.invocar(monstruoDeNivel2);
		
		//Act
		jugador.invocar(monstruoDeNivel5);
		
		//Assert
		assertTrue(jugador.estaEnElCementerio(monstruoDeNivel1));
		assertTrue(jugador.estaEnLaZonaDeMonstruos(monstruoDeNivel2));
	}
	
	@Test
	public void testAtacarDirectamenteDevuelveErrorSiElJugadorTieneMonstruosEnElCampo() {
		//Arrange
		boolean seLanzoError= false;
		
		int puntosDeAtaque = 8000;
		int puntosDeDefensa = 0;
		int nivel1 = 1;
		int posicion =0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacionSinSacrificio = new InvocacionNivel1A4();
		Jugador jugador1 = new Jugador("Wiss");
		Jugador jugador2 = new Jugador("JugadorPerdedor");
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel1,jugador1,invocacionSinSacrificio);
		monstruo.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(monstruo);
		jugador2.invocar(monstruo);
		
		//Act
		try {
			jugador2.recibirAtaqueDirecto(monstruo);
		} catch (NoSePuedeAtacarDirectamenteError e) {
			seLanzoError = true;
		}
		
		//Assert
        assertTrue(seLanzoError);
	}
	
	@Test
	public void testElJugadorPierdeSiSuVidaLlegaACero() {
		//Arrange
		boolean seLanzoError = false;
		
		int puntosDeAtaque = 8000;
		int puntosDeDefensa = 0;
		int nivel1 = 1;
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacionSinSacrificio = new InvocacionNivel1A4();
		Jugador jugador1 = new Jugador("Wiss");
		Jugador jugador2 = new Jugador("JugadorPerdedor");
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel1,jugador1,invocacionSinSacrificio);
		monstruo.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(monstruo);
		
		//Act
        try {
    		jugador2.recibirAtaqueDirecto(monstruo);
		} catch (JugadorSeQuedoSinVidaError e) {
			seLanzoError = true;
		}
		
		//Assert
        assertTrue(seLanzoError);
	}
	
	@Test
	public void testActivarWastelandAumenta200ElAtaqueDelMonstruoDelActivadorY300LaDefensaDelOponente() {
		//Arrange
		Jugador jugador1 = new Jugador("unJugador");
		Jugador jugador2 = new Jugador("unJugador");
		int puntosDeAtaque = 200;
		int puntosDeDefensa = 500;
		int nivel = 1;
		int aumentoAtaque = 200;
		int aumentoDefensa = 300;
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador2,invocacion);
		EfectoCampo efectoWasteland = new EfectoWasteland();
		CartaDeCampo cartaDeCampo = new CartaDeCampo("Wasteland",efectoWasteland,jugador1);
		jugador1.invocar(monstruo1);
		monstruo1.setEstrategiaInicial(new ModoAtaque());
		jugador2.invocar(monstruo2);
		monstruo2.setEstrategiaInicial(new ModoDefensa());
		
		//Act
		jugador1.activar(cartaDeCampo, jugador2);
		
		//Assert
		assertEquals(aumentoAtaque + puntosDeAtaque,monstruo1.getPuntosDeBatalla());
		assertEquals(aumentoDefensa + puntosDeDefensa,monstruo2.getPuntosDeBatalla());
	}
	
	@Test
	public void testActivarSogenAumenta500LaDefensaDelMonstruoDelActivadorY200ElAtaqueDelOponente() {
		//Arrange
		Jugador jugador1 = new Jugador("unJugador");
		Jugador jugador2 = new Jugador("unJugador");
		int puntosDeAtaque = 200;
		int puntosDeDefensa = 500;
		int nivel = 1;
		int aumentoAtaque = 200;
		int aumentoDefensa = 500;
		int posicion = 0;
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador2,invocacion);
		EfectoCampo efectoSogen = new EfectoSogen();
		CartaDeCampo cartaDeCampo = new CartaDeCampo("Sogen",efectoSogen,jugador1);
		jugador1.invocar(monstruo1);
		monstruo1.setEstrategiaInicial(new ModoDefensa());
		jugador2.invocar(monstruo2);
		monstruo2.setEstrategiaInicial(new ModoAtaque());
		
		//Act
		jugador1.activar(cartaDeCampo, jugador2);
		
		//Assert
		assertEquals(aumentoDefensa + puntosDeDefensa,monstruo1.getPuntosDeBatalla());
		assertEquals(aumentoAtaque + puntosDeAtaque,monstruo2.getPuntosDeBatalla());
	}

	@Test
	public void testActivarOllaDeLaCodiciaAgregaDosCartasAEnMano() {
		//Arrange
		int cantidadDeCartasEnMano = 0;
		int cantidadDeCartasATomar = 2;
		Jugador jugador1 = new Jugador("unJugador");
		Jugador jugador2 = new Jugador("unJugador");
		EfectoMagica efecto = new EfectoOllaDeLaCodicia();
		CartaMagica magica = new CartaMagica("Olla de la Codicia",efecto,jugador1);
		
		//Act
		jugador1.activar(magica, jugador2);
		
		//Assert
		assertEquals(cantidadDeCartasEnMano + cantidadDeCartasATomar,(jugador1.getCartasEnMano()).size());
	}
	
	@Test
	public void testActivarFisuraDestruyeElMonstruoDeMenorAtaque() {
		//Arrange
		int puntosDeAtaque1 = 200;
		int puntosDeDefensa1 = 500;
		int puntosDeAtaque2 = 100;
		int puntosDeDefensa2 = 500;
		int nivel1 = 1;
		int nivel2 = 1;
		int posicion = 0;
		int posicion1 = 1;
		EfectoMonstruo efecto = new EfectoMonstruoNulo();
		Jugador jugador1 = new Jugador("unJugador");
		Jugador jugador2 = new Jugador("unJugador");
		EfectoMagica efectoFisura = new EfectoFisura();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMagica magica = new CartaMagica("Fisura",efectoFisura,jugador1);
		CartaMonstruo monstruo1 = new CartaMonstruo("unMonstruo",efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador2,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("unMonstruo",efecto,puntosDeAtaque2,puntosDeDefensa2,nivel2,jugador2,invocacion);
		jugador2.invocar(monstruo1);
		jugador2.invocar(monstruo2);
		
		//Act
		jugador1.activar(magica, jugador2);
		
		//Assert
		assertTrue(jugador2.estaEnElCementerio(monstruo2));
		assertFalse(jugador2.estaEnElCementerio(monstruo1));
	}
	
	@Test
	public void testActivarJinzo7ConMonstruoEnElTableroOponenteAtacaLosPuntosDeVida() {
		//Arrange
		int puntosDeAtaque = 200;
		int puntosDeDefensa = 500;
		int nivel = 1;
		EfectoMonstruo efecto = new EfectoMonstruoNulo();
		Jugador jugador1 = new Jugador("unJugador");
		Jugador jugador2 = new Jugador("unJugador");
		int posicion = 0;
		EfectoMonstruo efectoJinzo = new EfectoJinzo7();
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("unMonstruo",efecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador2,invocacion);
		CartaMonstruo jinzo7 = new CartaMonstruo("unMonstruo",efectoJinzo,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		jugador2.invocar(monstruo1);
		jugador1.invocar(jinzo7);
		
		//Act
		jugador1.activar(jinzo7,jugador2);
		
		//Assert
		assertFalse(jugador2.estaEnElCementerio(monstruo1));
		assertEquals(8000-500,jugador2.getVida());
	}
	
	@Test
	public void testInvocarDragonDefinitivoDeOjosAzulesRequiereSacrificarTresDragonesBlancosDeOjosAzules() {
		//Arrange
		int puntosDeAtaque = 3000;
		int puntosDeDefensa = 2500;
		int nivel1 = 1;
		int nivel8 = 8;
		int nivel12 = 12;
		int posicion0 = 0;
		int posicion1 = 1;
		int posicion2 = 2;
		int posicion3 = 3;
		int posicion4 = 4;
		
		EfectoMonstruo sinEfecto = new EfectoMonstruoNulo();
		
		Invocacion invocacionNivel1 = new InvocacionNivel1A4();
		Invocacion invocacionNivel8 = new InvocacionNivel7oMas();
		Invocacion invocacionDragonDefinitivoDeOjosAzules = new InvocacionDragonDefinitivoDeOjosAzules();
		
		Jugador jugador = new Jugador("unJugador");
		
		CartaMonstruo monstruoDeSacrificio = new CartaMonstruo("unMonstruo",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel1,jugador,invocacionNivel1);
		CartaMonstruo dragonComun = new CartaMonstruo("Dragon blanco de ojos azules",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel8,jugador,invocacionNivel8);		
		CartaMonstruo dragonDefinitivo = new CartaMonstruo("Dragon definitivo de ojos azules",sinEfecto,puntosDeAtaque,puntosDeDefensa,nivel12,jugador,invocacionDragonDefinitivoDeOjosAzules);
		
		//Act
		jugador.invocar(monstruoDeSacrificio);
		jugador.invocar(monstruoDeSacrificio);
		jugador.invocar(dragonComun);
		jugador.invocar(monstruoDeSacrificio);
		jugador.invocar(monstruoDeSacrificio);
		jugador.invocar(dragonComun);
		jugador.invocar(monstruoDeSacrificio);
		jugador.invocar(monstruoDeSacrificio);
		jugador.invocar(dragonComun);
		jugador.invocar(dragonDefinitivo);
		
		//Assert
		assertTrue(jugador.estaEnElCementerio(dragonComun));
		assertTrue(jugador.estaEnElCementerio(dragonComun));
		assertTrue(jugador.estaEnElCementerio(dragonComun));
		assertTrue(jugador.estaEnLaZonaDeMonstruos(dragonDefinitivo));
	}
	

	@Test
	public void testAtacarAInsectoComeHombresBocaAbajoActivaEfectoYMuereElAtacante() {
		//Arrange
		int vidaInicial = 8000;
		int puntosDeAtaque = 3000;
		int puntosDeDefensa = 2500;
		int nivel = 2;
		int posicion = 0;
		EfectoMonstruo efecto = new EfectoMonstruoNulo();
		EfectoMonstruo efectoInsecto = new EfectoInsectoComeHombres();
		Jugador jugador1 = new Jugador("Defensor");
		Jugador jugador2 = new Jugador("Atacante");
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo insecto = new CartaMonstruo("insecto come hombres",efectoInsecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",efecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador2,invocacion);
		insecto.setEstrategiaInicial(new ModoDefensa());
		monstruo.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(insecto);
		jugador2.invocar(monstruo);
		
		//Act
		insecto.esAtacadaPor(monstruo);
		
		//Assert
		assertTrue(jugador2.estaEnElCementerio(monstruo));
		assertFalse(jugador1.estaEnElCementerio(insecto));
		assertEquals(vidaInicial,jugador1.getVida());
		assertEquals(vidaInicial,jugador2.getVida());
	}
	
	@Test
	public void testActivarCilindroMagicoAnulaElAtaqueDirectoYElEnemigoRecibeElDanio() {
		//Arrange
		int vidaInicial = 8000;
		int puntosDeAtaque = 3000;
		int puntosDeDefensa = 2500;
		int nivel = 2;
		int posicion = 0;
		EfectoMonstruo efecto = new EfectoMonstruoNulo();
		EfectoTrampa efectoCilindro = new EfectoCilindroMagico();
		Jugador jugador1 = new Jugador("jugador");
		Jugador jugador2 = new Jugador("oponente");
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo = new CartaMonstruo("unMonstruo",efecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador2,invocacion);
		CartaTrampa cilindro = new CartaTrampa("cilindroMagico",efectoCilindro,jugador1);
		monstruo.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(cilindro);
		jugador2.invocar(monstruo);
		
		//Act
		jugador1.recibirAtaqueDirecto(monstruo);
		
		//Assert
		assertEquals(vidaInicial,jugador1.getVida());
		assertEquals(vidaInicial-puntosDeAtaque,jugador2.getVida());
		assertTrue(jugador1.estaEnElCementerio(cilindro));
	}
	
	@Test
	public void testActivarCilindroMagicoAnulaElAtaqueAUnMonstruoYElEnemigoRecibeElDanio() {
		//Arrange
		int vidaInicial = 8000;
		int puntosDeAtaque = 3000;
		int puntosDeDefensa = 2500;
		int nivel = 2;
		int posicion = 0;
		EfectoMonstruo efecto = new EfectoMonstruoNulo();
		EfectoTrampa efectoCilindro = new EfectoCilindroMagico();
		Jugador jugador1 = new Jugador("jugador");
		Jugador jugador2 = new Jugador("oponente");
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("unMonstruo",efecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("otroMonstruo",efecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador2,invocacion);
		CartaTrampa cilindro = new CartaTrampa("cilindroMagico",efectoCilindro,jugador1);
		monstruo1.setEstrategiaInicial(new ModoDefensa());
		monstruo2.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(cilindro);
		jugador1.invocar(monstruo1);
		jugador2.invocar(monstruo2);
		
		//Act
		monstruo1.esAtacadaPor(monstruo2);
		
		//Assert
		assertEquals(vidaInicial,jugador1.getVida());
		assertEquals(vidaInicial-puntosDeAtaque,jugador2.getVida());
		assertTrue(jugador1.estaEnLaZonaDeMonstruos(monstruo1));
		assertTrue(jugador1.estaEnElCementerio(cilindro));
	}
	
	@Test
	public void testActivarReinforcementsAumentaElAtaqueDelMonstruoAntesDelCalculoDeDanio() {
		//Arrange
		int vidaInicial = 8000;
		int puntosDeAtaque = 800;
		int puntosDeDefensa = 500;
		int nivel = 2;
		int posicion = 0;
		EfectoMonstruo efecto = new EfectoMonstruoNulo();
		EfectoTrampa efectoReinforcements = new EfectoReinforcements();
		Jugador jugador1 = new Jugador("jugador");
		Jugador jugador2 = new Jugador("oponente");
		Invocacion invocacion = new InvocacionNivel1A4();
		CartaMonstruo monstruo1 = new CartaMonstruo("unMonstruo",efecto,puntosDeAtaque,puntosDeDefensa,nivel,jugador1,invocacion);
		CartaMonstruo monstruo2 = new CartaMonstruo("otroMonstruo",efecto,puntosDeAtaque + 400,puntosDeDefensa,nivel,jugador2,invocacion);
		CartaTrampa reinforcements = new CartaTrampa("cilindroMagico",efectoReinforcements,jugador1);
		monstruo1.setEstrategiaInicial(new ModoAtaque());
		monstruo2.setEstrategiaInicial(new ModoAtaque());
		jugador1.invocar(reinforcements);
		jugador1.invocar(monstruo1);
		jugador2.invocar(monstruo2);
		
		//Act
		monstruo1.esAtacadaPor(monstruo2);
		
		//Assert
		assertEquals(vidaInicial,jugador1.getVida());
		assertEquals(vidaInicial-100,jugador2.getVida());
		assertTrue(jugador1.estaEnLaZonaDeMonstruos(monstruo1));
		assertTrue(jugador2.estaEnElCementerio(monstruo2));
		assertTrue(jugador1.estaEnElCementerio(reinforcements));
	}
	
	@Test
	public void testElJugadorPierdeSiSeQuedaSinCartasEnElMazo() {
		//Arrange
		boolean seLanzoError= false;
		
		Jugador jugador = new Jugador("Wiss");
		try {
			jugador.tomarCartasDelMazo(40);
		} catch (JugadorSacoTodasLasPartesDeExodiaError e) {
			//Ignora el hecho de que rob� las 5 partes de exodia
		}
		
		//Act
        try {
        	jugador.tomarCartasDelMazo(40);
		} catch (JugadorSeQuedoSinCartasError e) {
			seLanzoError = true;
		}
		
		//Assert
        assertTrue(seLanzoError);
	}
	
	@Test
	public void testTenerLasCincoPartesDeExodiaEnLaManoGanaElDueloInmediatamente() {
		//Arrange
		boolean seLanzoError= false;
		Jugador jugador1 = new Jugador("unJugador");
		int puntosDeAtaque1 = 200;
		int puntosDeDefensa1 = 300;
		int nivel1 = 1;
		int puntosDeAtaque2 = 1000;
		int puntosDeDefensa2 = 1000;
		int nivel2 = 3;
		Invocacion invocacion = new InvocacionNivel1A4();
		EfectoMonstruo efecto = new EfectoExodia();
		CartaExodia exodia1 = new CartaExodia("Brazo Derecho del Prohibido", efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador1,invocacion);
		CartaExodia exodia2 = new CartaExodia("Brazo Izquierdo del Prohibido", efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador1,invocacion);
		CartaExodia exodia3 = new CartaExodia("Pierna Derecha del Prohibido", efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador1,invocacion);
		CartaExodia exodia4 = new CartaExodia("Pierna Izquierda del Prohibido", efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador1,invocacion);
		CartaExodia exodia5 = new CartaExodia("Exodia, el Prohibido", efecto,puntosDeAtaque2,puntosDeDefensa2,nivel2,jugador1,invocacion);
		
		try {
			jugador1.agregarCartaALaMano(exodia1);
			jugador1.agregarCartaALaMano(exodia2);
			jugador1.agregarCartaALaMano(exodia3);
			jugador1.agregarCartaALaMano(exodia4);
			jugador1.agregarCartaALaMano(exodia5);
		 } catch (JugadorSacoTodasLasPartesDeExodiaError e) {
			seLanzoError = true;
		 }
		
		//Assert
		assertTrue(seLanzoError);
	}
	
	@Test
	public void testTomarCuatroCartasExodiaEInvocarUnaYLuegoTomarUnaQuintaCartaExodiaNoHaceGanarAlJugador() {
		//Arrange
		boolean seLanzoError= false;
		Jugador jugador1 = new Jugador("unJugador");
		int puntosDeAtaque1 = 200;
		int puntosDeDefensa1 = 300;
		int nivel1 = 1;
		int puntosDeAtaque2 = 1000;
		int puntosDeDefensa2 = 1000;
		int nivel2 = 3;
		int posicion = 0;
		Invocacion invocacion = new InvocacionNivel1A4();
		EfectoMonstruo efecto = new EfectoExodia();
		CartaExodia exodia1 = new CartaExodia("Brazo Derecho del Prohibido", efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador1,invocacion);
		CartaExodia exodia2 = new CartaExodia("Brazo Izquierdo del Prohibido", efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador1,invocacion);
		CartaExodia exodia3 = new CartaExodia("Pierna Derecha del Prohibido", efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador1,invocacion);
		CartaExodia exodia4 = new CartaExodia("Pierna Izquierda del Prohibido", efecto,puntosDeAtaque1,puntosDeDefensa1,nivel1,jugador1,invocacion);
		CartaExodia exodia5 = new CartaExodia("Exodia, el Prohibido", efecto,puntosDeAtaque2,puntosDeDefensa2,nivel2,jugador1,invocacion);
		
		 try {
			jugador1.agregarCartaALaMano(exodia1);
			jugador1.agregarCartaALaMano(exodia2);
			jugador1.agregarCartaALaMano(exodia3);
			jugador1.agregarCartaALaMano(exodia4);
			jugador1.invocar(exodia1);
			jugador1.agregarCartaALaMano(exodia5);
		 } catch (JugadorSacoTodasLasPartesDeExodiaError e) {
			seLanzoError = true;
		 }
				
		//Assert
		 assertFalse(seLanzoError);
	}

}