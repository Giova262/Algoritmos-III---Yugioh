package fiuba.vista;

import fiuba.modelo.cartas.Carta;
import fiuba.vista.eventos.ManejadorDePosicionCementerioEnTablero;
import javafx.scene.control.Button;

public class BotonCementerio extends Button {
	private Carta carta;
	private int alto;
	private int ancho;
	
	public BotonCementerio(ContenedorPrincipal contenedor, int alto, int ancho) {
		String nombreDeImagenDeFondo = "cartaDorsoGris";
        this.setOnAction(new ManejadorDePosicionCementerioEnTablero(contenedor));
        this.setEstilo(alto, ancho, nombreDeImagenDeFondo);
        this.carta = null;
        this.alto = alto;
        this.ancho = ancho;
	}
	
	public void setEstilo(int alto, int ancho, String nombre) {
		this.setStyle("-fx-background-color: transparent;"
        		+ "-fx-min-height: " + alto + "px;"
				+ "-fx-min-width: " + ancho + "px;"
				+ "-fx-max-height: " + alto + "px;"
				+ "-fx-max-width: " + ancho + "px;"
				+ "-fx-background-image: url('file:src/fiuba/vista/img/" + nombre + ".jpg');"
				+ "-fx-background-size: " + ancho + "px " + alto + "px;"
				+ "-fx-background-repeat: no-repeat;"
				+ "-fx-cursor: hand;");
	}

	public void insertar(Carta cartaSeleccionada) {
		this.carta = cartaSeleccionada;
		this.setEstilo(alto, ancho, carta.getNombre());
	}
}
