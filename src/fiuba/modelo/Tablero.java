package fiuba.modelo;

import java.util.ArrayList;

import fiuba.modelo.excepciones.ZonaLlenaError;
import fiuba.modelo.cartas.*;

public class Tablero {

	private ArrayList<CartaMonstruo> zonaDeMonstruos = new ArrayList<CartaMonstruo>(); ;
	private ArrayList<Carta> zonaDeCartasMagicasOTrampa;
	private CartaDeCampo zonaDeCampo;
	private ArrayList<Carta> cementerio;
	private Mazo mazo;
	private static final String nombreDeImagenDefault = "cartaDorsoGris";
	private static final String nombreDeImagenMagicaVolteada = "cartaDorsoAzul";
	private static final String nombreDeImagenMonstruoVolteada = "cartaDorsoNormal";

	
	public Tablero(Jugador jugador) {
		this.zonaDeCartasMagicasOTrampa = new ArrayList<Carta>();
		this.cementerio = new ArrayList<Carta>();
		this.mazo = new Mazo(jugador);
	}
	
	public void verZonaDeMonstruos() {		
		if ( zonaDeMonstruos == null ) System.out.println("La zona de monstruos esta vacia");
		for(CartaMonstruo monstruo: zonaDeMonstruos) {
				System.out.println(monstruo.getNombre());
		}		
	}

	public void verCementerio() {
		for(Carta carta: cementerio) {
			  System.out.println(carta.getNombre());
		}
	}
	
	public Carta sacarUnaCartaDelMazo() {
		return mazo.tomarCarta();
	}
	
	public void invocar(CartaMonstruo cartaMonstruo) {	
		int cantidadSacrificios = cartaMonstruo.sacrificiosNecesarios();
		if (zonaDeMonstruos.size() - cantidadSacrificios == 5) throw new ZonaLlenaError();
		cartaMonstruo.sacrificarNecesarios();
		zonaDeMonstruos.add(cartaMonstruo);		
	}
	
	public void invocar(CartaExodia cartaMonstruo) {	
		zonaDeMonstruos.add(cartaMonstruo);
		this.verZonaDeMonstruos();
	}

	public void invocar(CartaMagica cartaMagica) {
		zonaDeCartasMagicasOTrampa.add(cartaMagica);
	}

	public void invocar(CartaTrampa cartaTrampa) {
		zonaDeCartasMagicasOTrampa.add(cartaTrampa);
	}
	
	public void invocar(CartaDeCampo cartaCampo) {
		if (zonaDeCampo != null) throw new ZonaLlenaError();
		zonaDeCampo = cartaCampo;
	}

	public void mandarAlCementerio(CartaMonstruo carta) {
		zonaDeMonstruos.remove(carta);
		cementerio.add(carta);
	}
	
	public void mandarAlCementerio(CartaMagica carta) {
		zonaDeCartasMagicasOTrampa.remove(carta);
		cementerio.add(carta);
	}
	
	public void mandarAlCementerio(CartaTrampa carta) {
		zonaDeCartasMagicasOTrampa.remove(carta);
		cementerio.add(carta);
	}
	
	public void mandarAlCementerio(CartaDeCampo carta) {
		zonaDeCampo = null;
		cementerio.add(carta);
	}
	
	public boolean estaEnLaZonaDeMonstruos(CartaMonstruo carta) {
		return zonaDeMonstruos.contains(carta);
	}
	
	public boolean estaEnLaZonaDeMagiasYTrampas(Carta carta) {
		return zonaDeCartasMagicasOTrampa.contains(carta);
	}
	
	public boolean estaEnElCementerio(Carta carta) {
		return cementerio.contains(carta);
	}

	public void sacrificarMonstruo() {
		CartaMonstruo monstruoASacrificar = this.getMonstruoConMenorNivel();
		mandarAlCementerio(monstruoASacrificar);
	}
	
	public boolean verificarTrampas(CartaMonstruo miMonstruo, CartaMonstruo monstruoEnemigo, Jugador oponente) {
		for(Carta carta : zonaDeCartasMagicasOTrampa) {
			if(carta != null && carta.getClass()== CartaTrampa.class) {
				CartaTrampa cartaTrampa = (CartaTrampa) carta;
				mandarAlCementerio((CartaTrampa) carta);
				return (cartaTrampa.activarEfecto(miMonstruo,monstruoEnemigo,oponente)) ;
			}		
		}
		return true;
	}
	
	public void vaciarZonaDeMonstruos() {		
		CartaMonstruo c ;
		for (int i = 0 ;i < zonaDeMonstruos.size() ; i++) {		
			c = zonaDeMonstruos.get(i);		
			cementerio.add(c);					
		}		
		zonaDeMonstruos.clear();			
		
	}

	public void aumentarAtaqueMonstruos(int aumento) {
		for(CartaMonstruo monstruo: zonaDeMonstruos) {
			if (monstruo != null) {
				monstruo.aumentarPuntosDeAtaque(aumento);
			}
		}
	}

	public void aumentarDefensaMonstruos(int aumento) {
		for(CartaMonstruo monstruo: zonaDeMonstruos) {
			if (monstruo != null) {
				monstruo.aumentarPuntosDeDefensa(aumento);
			}
		}
	}

	public CartaMonstruo getMonstruoConMenorAtaque() {
		CartaMonstruo monstruoConMenorAtaque = null;
		for(CartaMonstruo monstruo: zonaDeMonstruos) {
			if (monstruo != null && (monstruoConMenorAtaque == null || monstruo.tieneMenosPuntosDeAtaqueQue(monstruoConMenorAtaque))) {
				monstruoConMenorAtaque = monstruo;
			}
		}
		return monstruoConMenorAtaque;
	}
	
	public CartaMonstruo getMonstruoConMenorNivel() {
		CartaMonstruo monstruoConMenorNivel = null;
		for(CartaMonstruo monstruo: zonaDeMonstruos) {
			if (monstruo != null && (monstruoConMenorNivel == null || monstruo.tieneMenosNivelQue(monstruoConMenorNivel))) {
				monstruoConMenorNivel = monstruo;
			}
		}
		return monstruoConMenorNivel;
	}
	
	public CartaMonstruo getMonstruoConMayorNivel() {
		CartaMonstruo monstruoConMayorNivel = null;
		for(CartaMonstruo monstruo: zonaDeMonstruos) {
			if (monstruo != null && (monstruoConMayorNivel == null || monstruo.tieneMasNivelQue(monstruoConMayorNivel))) {
				monstruoConMayorNivel = monstruo;
			}
		}
		return monstruoConMayorNivel;
	}

	public int getCantidadDeMonstruos() {
		
		int contador = 0;
		for (Carta c: zonaDeMonstruos) {
			if (c != null) {
				contador++;
			}
		}
		return contador;
	}

	public int cantidadDeMonstruosEnCampo(String nombre) {
		int cantidadMonstruos = 0;
		for(CartaMonstruo monstruo : zonaDeMonstruos) {
			if(monstruo != null && monstruo.getNombre() == nombre) cantidadMonstruos++;
		}
		return cantidadMonstruos;
	}

	public CartaMonstruo obtenerMonstruoEnCampo(String nombre) {
		for(CartaMonstruo monstruo : zonaDeMonstruos) {
			if(monstruo != null && monstruo.getNombre() == nombre) return monstruo;
		}
		return null;
	}

	public String devolverNombreDeLaImagenDeCartaMagicaOTrampa(int indice) {
	
		if(zonaDeCartasMagicasOTrampa.isEmpty()) {
			return nombreDeImagenDefault;
		}
		Carta carta = zonaDeCartasMagicasOTrampa.get(indice);
		boolean esCartaMagicaBocaAbajo = (carta.getClass() == CartaMagica.class && ((CartaMagica)carta).estaBocaAbajo());
		boolean esCartaTrampaBocaAbajo = (carta.getClass() == CartaTrampa.class && ((CartaTrampa)carta).estaBocaAbajo());
		if (esCartaMagicaBocaAbajo || esCartaTrampaBocaAbajo) {
			return nombreDeImagenMagicaVolteada;
		}
		return zonaDeCartasMagicasOTrampa.get(indice).getNombre();
	}

	public String devolverNombreDeLaImagenDeCartaMonstruo(int indice) {

		if(zonaDeMonstruos.isEmpty()) {
			
			return nombreDeImagenDefault;
		}
		if (zonaDeMonstruos.get(indice).estaBocaAbajo()) {
			return nombreDeImagenMonstruoVolteada;
		}
		return zonaDeMonstruos.get(indice).getNombre();
	}

	public String devolverNombreDeLaImagenDeCartacampo() {
		if(zonaDeCampo == null) {
	
			return nombreDeImagenDefault;
		}
		return zonaDeCampo.getNombre();
	}

	public boolean estaEnModoDefensaElMonstruo(int indice) {
		return zonaDeMonstruos.get(indice).estaEnModoDefensa();
	}
	
	public Boolean estaBocaAbajoLaCarta(int indice) {
		return true;
	}

	public CartaMonstruo getCartaMonstruo(int indiceDeMiCarta) {
		if (  indiceDeMiCarta < zonaDeMonstruos.size() ) 	return zonaDeMonstruos.get(indiceDeMiCarta);
		return null;
	}

	public ArrayList<CartaMonstruo> getZonaMonstruos() {
		return zonaDeMonstruos;
	}

	public ArrayList<Carta> getZonaMagicasTrampas() {
		return zonaDeCartasMagicasOTrampa;
	}

	public String devolverNombreDeLaUltimaCartaEncementerio() {
		if(cementerio.isEmpty() ) 	return nombreDeImagenDefault;		
		int ultima = cementerio.size()-1;
		return cementerio.get( ultima ).getNombre();
	}

	public int cantidadDeCartasEnMazo() {
		return mazo.cartasDisponibles();
	}

	public Carta getCartaMagicaOTrampa(int indice) {
		return zonaDeCartasMagicasOTrampa.get(indice);
	}

}