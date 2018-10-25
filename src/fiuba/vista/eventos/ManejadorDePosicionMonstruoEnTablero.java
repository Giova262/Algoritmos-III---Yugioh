package fiuba.vista.eventos;

import java.util.ArrayList;

import fiuba.modelo.AlGoOh;
import fiuba.vista.BotonMonstruo;
import fiuba.vista.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ManejadorDePosicionMonstruoEnTablero implements EventHandler<ActionEvent>{
	
	private ContenedorPrincipal contenedor;
	private int indice;


	public ManejadorDePosicionMonstruoEnTablero(ContenedorPrincipal contenedor, int indice) {
		this.contenedor = contenedor;
		this.indice = indice;
		
	}
	
	@Override
	public void handle(ActionEvent arg0) {

		/*BotonAtacarEventHandler atacarHandler = new BotonAtacarEventHandler(jugadorActual,this,i);
		monstruo.setOnAction(atacarHandler);*/
	//	contenedor.invocarEn(this,indice);
	}
	
}