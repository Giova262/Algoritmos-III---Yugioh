package fiuba.vista.eventos;

import java.util.ArrayList;

import fiuba.vista.BotonCampo;
import fiuba.vista.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ManejadorDePosicionCampoEnTablero implements EventHandler<ActionEvent> {

	private ContenedorPrincipal contenedor;
	private ArrayList<BotonCampo> campo;

	public ManejadorDePosicionCampoEnTablero(ContenedorPrincipal contenedor) {
		this.contenedor = contenedor;
	}
		
	@Override
	public void handle(ActionEvent arg0) {
	//	contenedor.invocarEn(this);
	}
}
