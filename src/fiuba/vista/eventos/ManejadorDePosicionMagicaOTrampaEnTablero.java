package fiuba.vista.eventos;

import java.util.ArrayList;

import fiuba.vista.BotonMagicoOTrampa;
import fiuba.vista.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ManejadorDePosicionMagicaOTrampaEnTablero implements EventHandler<ActionEvent> {

	private ContenedorPrincipal contenedor;
	private int indice;

	public ManejadorDePosicionMagicaOTrampaEnTablero(ContenedorPrincipal contenedor, int indice) {
		this.contenedor = contenedor;
		this.indice = indice;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
	//	contenedor.invocarEn(this,indice);
	}

}
