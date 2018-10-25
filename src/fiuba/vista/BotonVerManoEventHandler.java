package fiuba.vista;

import java.util.ArrayList;

import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.vista.eventos.ManejadorDeBoton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BotonVerManoEventHandler implements EventHandler<ActionEvent> {

    private Jugador jugador;

    public BotonVerManoEventHandler(Jugador unJugador) {
        this.jugador = unJugador;
    }
    
    public void handle (ActionEvent actionEvent) {
    	final Stage newStage = new Stage();
		Group newRoot = new Group();
		Scene scene = new Scene(newRoot, 1000, 1000);
		newStage.setScene(scene);
		newStage.show();
		ArrayList<Button> botonesCarta = new ArrayList<Button>();
		ArrayList<Carta> cartas = jugador.getCartasEnMano();
       
		HBox hBox = new HBox(10);

		CheckBox botonModoAtaque = new CheckBox();
		botonModoAtaque.setText("Modo Ataque");
		CheckBox botonModoDefensa = new CheckBox();
		botonModoDefensa.setText("Modo Defensa");
		CheckBox botonBocaArriba = new CheckBox();
		botonBocaArriba.setText("Boca Arriba");
		CheckBox botonBocaAbajo = new CheckBox();
		botonBocaAbajo.setText("Boca Abajo");
		
		VBox cartasVerticales = new VBox();
		for(int i = 0; i < cartas.size(); i++) {
			cartasVerticales.getChildren().add(this.setBotonCarta(cartas.get(i), botonModoAtaque,botonModoDefensa, botonBocaArriba, botonBocaAbajo));
			if (i%2 == 0) {
				hBox.getChildren().add(cartasVerticales);
				cartasVerticales = new VBox();
			}
			
		}
		
		
		
		VBox botones = new VBox(10);
		botones.getChildren().add(botonBocaAbajo);
		botones.getChildren().add(botonBocaArriba);
		botones.getChildren().add(botonModoAtaque);
		botones.getChildren().add(botonModoDefensa);

		hBox.getChildren().add(botones);
		
		System.out.println("---------------------");
           
		Button btnClose = new Button("Close");
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				newStage.close();
			}
		});
            
		hBox.getChildren().addAll( btnClose);
		newRoot.getChildren().add(hBox);
	
    }

	private Button setBotonCarta(Carta carta, CheckBox botonModoAtaque,CheckBox botonModoDefensa, CheckBox botonBocaArriba, CheckBox botonBocaAbajo) {
		Button botonCarta = new Button();
		botonCarta.setText("");
		System.out.println(carta.getNombre());
		
		botonCarta.setStyle("-fx-min-height: 340px;"
				+ "-fx-min-width: 220px;"
				+ "-fx-max-height: 340px;"
				+ "-fx-max-width: 220px;"
				+ "-fx-background-image: url('fiuba/vista/img/" + carta.getNombre() + ".jpg');"
				+ "-fx-background-size: 220px 340px;"
				+ "-fx-background-repeat: no-repeat;");
		
		botonCarta.setOnAction(new ManejadorDeBoton(carta, botonModoAtaque,botonModoDefensa, botonBocaArriba, botonBocaAbajo));
		
		return botonCarta;
	}
    
}