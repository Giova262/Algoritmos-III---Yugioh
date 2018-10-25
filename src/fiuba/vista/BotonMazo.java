package fiuba.vista;

import fiuba.modelo.cartas.Carta;
import fiuba.vista.eventos.ManejadorDePosicionMazoEnTablero;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class BotonMazo extends Button {
	private Carta carta;
	private int alto;
	private int ancho;
	
	public BotonMazo(ContenedorPrincipal contenedor, int alto, int ancho, int cantidadDeCartas) {		 
        String nombreDeImagenDeFondo = "mazo2";
        this.setOnAction(new ManejadorDePosicionMazoEnTablero(contenedor));
        this.setEstilo(alto, ancho, nombreDeImagenDeFondo);
        this.setText(String.valueOf(cantidadDeCartas));
        this.setAlignment(Pos.TOP_LEFT);
        this.carta = null;
        this.alto = alto;
        this.ancho = ancho;
	}
	
	public void setEstilo(int alto, int ancho, String nombre) {
		this.setStyle("-fx-text-fill: white;"
				+ "-fx-font-size: 20;"
				+ "-fx-background-color: transparent;"
        		+ "-fx-min-height: " + alto + "px;"
				+ "-fx-min-width: " + ancho + "px;"
				+ "-fx-max-height: " + alto + "px;"
				+ "-fx-max-width: " + ancho + "px;"
				+ "-fx-background-image: url('file:src/fiuba/vista/img/" + nombre + ".png');"
				+ "-fx-background-size: " + ancho + "px " + alto + "px;"
				+ "-fx-background-repeat: no-repeat;"
				+ "-fx-cursor: hand;");
	}

	public void insertar(Carta cartaSeleccionada) {
		this.carta = cartaSeleccionada;
		this.setEstilo(alto, ancho, carta.getNombre());
	}
}
