package fiuba.vista;

import fiuba.modelo.AlGoOh;
import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaMagica;
import javafx.scene.control.Button;

public class BotonMagicoOTrampa extends Button {

	private Carta carta;
	private int alto;
	private int ancho;

	public BotonMagicoOTrampa(ContenedorPrincipal contenedor, String nombre,int indice, int alto, int ancho,AlGoOh yugioh, boolean estaBocaAbajo) {
		this.setOnAction( e -> {
			if ( yugioh.getNombreFase() ==  "Fase Final") {
				Jugador jugador = yugioh.getJugadorActual();
				Carta carta1= jugador.getCartaMagicaOTrampa(indice);
				if( carta1.getClass() == CartaMagica.class) {
					carta1.activarEfecto(jugador.getOponente());
					carta1.enviarmeAlCementerio();
				}
				contenedor.actualizarVista();
			}
		});
        this.alto = alto;
        this.ancho = ancho;
        this.setEstilo(estaBocaAbajo,nombre);
        this.carta = null;
	}
	
	public void setEstilo(boolean bocaAbajo,String nombre) {
		this.setStyle("-fx-background-color: transparent;"
        		+ "-fx-min-height: " + alto + "px;"
				+ "-fx-min-width: " + alto + "px;"
				+ "-fx-max-height: " + alto + "px;"
				+ "-fx-max-width: " + alto + "px;"
				+ "-fx-background-size: " + ancho + "px " + alto + "px;"
				+ "-fx-background-repeat: no-repeat;"
				+ "-fx-cursor: hand;");
		cambiarFondo(nombre);
		if (bocaAbajo) cambiarFondo("cartaDorsoNormal");
	}
	
	public void cambiarFondo(String nombre) {
		this.setStyle(this.getStyle() +  "-fx-background-image: url('file:src/fiuba/vista/img/" + nombre + ".jpg');");
	}
}