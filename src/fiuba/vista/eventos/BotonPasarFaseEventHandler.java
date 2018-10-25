package fiuba.vista.eventos;

import fiuba.modelo.AlGoOh;
import fiuba.vista.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonPasarFaseEventHandler implements EventHandler<ActionEvent> {
	private AlGoOh jugador;
	private ContenedorPrincipal contenedorPrincipal;
	
	public BotonPasarFaseEventHandler(AlGoOh jugadorActual, ContenedorPrincipal contenedor) {
		this.jugador = jugadorActual;
		this.contenedorPrincipal = contenedor;
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		jugador.pasarFase();
		contenedorPrincipal.actualizarVista();
	}

}