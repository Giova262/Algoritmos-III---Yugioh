package fiuba.vista.eventos;

import fiuba.modelo.AlGoOh;
import fiuba.modelo.excepciones.FinDePartidaExcepcion;
import fiuba.vista.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class BotonSacarCartaEventHandler implements EventHandler<ActionEvent> {

	private AlGoOh jugador;
	private Button botonSacarCarta;
	private Button botonPasarFase;
	private ContenedorPrincipal contenedor;
	
	public BotonSacarCartaEventHandler(AlGoOh unJugador, ContenedorPrincipal contenedor, Button esteBoton, Button otroBoton) {
		this.jugador = unJugador;
		this.botonSacarCarta = esteBoton;
		this.botonPasarFase = otroBoton;
		this.contenedor = contenedor;
	}
	
    @Override
    public void handle(ActionEvent actionEvent) {
    	try {
 			jugador.tomarCartasDelMazo(1);
    	} catch (FinDePartidaExcepcion e) {
    		contenedor.finalizarPartida(e);
 	   	}
		botonSacarCarta.setDisable(true);
		botonPasarFase.setDisable(false);
		jugador.pasarFase();
		contenedor.actualizarVista();
    }
    
}