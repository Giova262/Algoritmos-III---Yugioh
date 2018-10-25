package fiuba.vista;

import fiuba.modelo.AlGoOh;
import fiuba.modelo.cartas.CartaDeCampo;
import fiuba.vista.eventos.ManejadorDePosicionCampoEnTablero;
import javafx.scene.control.Button;

public class BotonCampo extends Button {
	private CartaDeCampo carta;
	private int alto;
	private int ancho;
	
	public BotonCampo(ContenedorPrincipal contenedor, String nombre, int alto, int ancho) {
		String nombreDeImagenDeFondo = "cartaDorsoVerde";
        this.setOnAction(new ManejadorDePosicionCampoEnTablero(contenedor));
        this.setEstilo(alto, ancho, nombreDeImagenDeFondo);
        this.carta = null;
        this.alto = alto;
        this.ancho = ancho;
        this.cambiarFondo(nombre);
	}
	
	public void setEstilo(int alto, int ancho, String nombre) {
		this.setStyle("-fx-background-color: transparent;"
        		+ "-fx-min-height: " + alto + "px;"
				+ "-fx-min-width: " + ancho + "px;"
				+ "-fx-max-height: " + alto + "px;"
				+ "-fx-max-width: " + ancho + "px;"
				+ "-fx-background-size: " + ancho + "px " + alto + "px;"
				+ "-fx-background-repeat: no-repeat;"
				+ "-fx-cursor: hand;");
		
	}
	
	public void cambiarFondo(String nombre) {
		this.setStyle(this.getStyle() +  "-fx-background-image: url('file:src/fiuba/vista/img/" + nombre + ".jpg');");
	}

	public void insertar(CartaDeCampo cartaSeleccionada, AlGoOh jugador) {
		this.carta = cartaSeleccionada;
		//jugador.invocar(carta);
		this.cambiarFondo(carta.getNombre());
	}
}