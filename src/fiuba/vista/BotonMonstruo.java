package fiuba.vista;

import fiuba.modelo.AlGoOh;
import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.excepciones.JugadorSeQuedoSinVidaError;
import javafx.scene.control.Button;

public class BotonMonstruo extends Button {
	
	private CartaMonstruo carta;
	private int alto;
	private int ancho;
	
	
	public BotonMonstruo(ContenedorPrincipal contenedor,String nombre, int indice, int alto, int ancho,Carta c , Jugador jugador , AlGoOh yugioh, boolean estaRotado) {

		this.setOnAction(evento ->{     		
			
			int tamanioZonaRival = yugioh.getJugadorActual().getOponente().getTamanioZonaMonstruos();
			
			if( jugador == yugioh.getJugadorActual() )  contenedor.setIndiceDelAtacante(indice);
			if( jugador == yugioh.getJugadorActual().getOponente() ) {
				
				  if(yugioh.getNombreFase() == "Fase Ataque") {
							contenedor.setIndiceDelAtacado(indice);		
													
							Jugador jugador1 =  yugioh.getJugadorActual();
							Jugador jugador2 = jugador1.getOponente();
							
							CartaMonstruo cartaAtacante= jugador1.getCartaMonstruo(contenedor.getIndiceAtacante());
							CartaMonstruo cartaAtacada= jugador2.getCartaMonstruo(contenedor.getIndiceAtacado());
							
							try {
								if (cartaAtacada != null && tamanioZonaRival != 0 ) {
									cartaAtacada.esAtacadaPor(cartaAtacante);
									contenedor.informarAccion(cartaAtacante.getNombre() + " ataca a " + cartaAtacada.getNombre());
									yugioh.pasarFase();
								
								} else if( tamanioZonaRival == 0 ) {							
									jugador2.recibirAtaqueDirecto(cartaAtacante);
									contenedor.informarAccion(cartaAtacante.getNombre() + " ataca directamente a " + yugioh.getOponente().getNombre());
									yugioh.pasarFase();
				
								}
							}catch (JugadorSeQuedoSinVidaError e) {
								contenedor.finalizarPartida(e);
							}
							contenedor.setBotonera();
							contenedor.setCentro();
				  }							
			}		
		});
		this.alto = alto;
		this.ancho = ancho;
		this.setEstilo(estaRotado,nombre);
		this.carta = null;
	}
	
	public void setEstilo(boolean rotado, String nombre) {
		this.setStyle("-fx-background-color: transparent;"
        		+ "-fx-min-height: " + alto + "px;"
				+ "-fx-min-width: " + alto + "px;"
				+ "-fx-max-height: " + alto + "px;"
				+ "-fx-max-width: " + alto + "px;"
				+ "-fx-background-size: " + ancho + "px " + alto + "px;"
				+ "-fx-background-repeat: no-repeat;"
				+ "-fx-cursor: hand;");
		cambiarFondo(nombre);
		if (rotado) {
			rotar();
			cambiarFondo("cartaDorsoNormal");
		}
	}
	
	public void cambiarFondo(String nombre) {
		this.setStyle(this.getStyle() +  "-fx-background-image: url('file:src/fiuba/vista/img/" + nombre + ".jpg');");
	}
	
	public void rotar() {
		this.setRotate(90);
	}
}