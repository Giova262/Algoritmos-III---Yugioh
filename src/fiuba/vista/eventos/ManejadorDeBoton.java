package fiuba.vista.eventos;

import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaDeCampo;
import fiuba.modelo.cartas.CartaExodia;
import fiuba.modelo.cartas.CartaMonstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

public class ManejadorDeBoton implements EventHandler<ActionEvent>{
	private Carta carta;
	private CheckBox botonModoAtaque;
	private CheckBox botonModoDefensa;
	private CheckBox botonBocaArriba;
	private CheckBox botonBocaAbajo;
	
	
	public ManejadorDeBoton(Carta carta,CheckBox botonModoAtaque,CheckBox botonModoDefensa, CheckBox botonBocaArriba, CheckBox botonBocaAbajo) {
		this.carta = carta;
		this.botonBocaAbajo = botonBocaAbajo;
		this.botonBocaArriba = botonBocaArriba;
		this.botonModoAtaque = botonModoAtaque;
		this.botonModoDefensa = botonModoDefensa;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		if ((carta.getClass() != CartaMonstruo.class) && (carta.getClass() != CartaExodia.class)) {
			botonModoAtaque.setDisable(true);
			botonModoDefensa.setDisable(true);
			botonBocaAbajo.setDisable(false);
		}else {
			botonModoAtaque.setDisable(false);
			botonModoDefensa.setDisable(false);	
			botonBocaAbajo.setDisable(false);		
		}
		
		if (carta.getClass() == CartaDeCampo.class) {
			botonBocaAbajo.setDisable(true);
		}
	}

}