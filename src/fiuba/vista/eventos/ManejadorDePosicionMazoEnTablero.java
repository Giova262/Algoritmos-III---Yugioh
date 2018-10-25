package fiuba.vista.eventos;

import fiuba.vista.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ManejadorDePosicionMazoEnTablero implements EventHandler<ActionEvent>{

	private ContenedorPrincipal contenedor;

	public ManejadorDePosicionMazoEnTablero(ContenedorPrincipal contenedor) {
		this.contenedor = contenedor;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
	}

}
