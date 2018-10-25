package fiuba.vista.eventos;

import fiuba.modelo.AlGoOh;
import fiuba.vista.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BotonSiguienteEventHandler implements EventHandler<ActionEvent> {

    private Stage stage;
    private TextField nombreJugador1,nombreJugador2;
    private final int CANTIDAD_CARTAS_INICIALES = 5;
    
    public BotonSiguienteEventHandler(Stage stage, TextField nombre1, TextField nombre2) {
        this.stage = stage;
        this.nombreJugador1 = nombre1;
        this.nombreJugador2 = nombre2;
    }

    private boolean nombreJugadorEsValido(TextField nombreJugador) {
    	if (nombreJugador.getText().isEmpty()) {
    		return false;
    	}
    	return true;
    }
    
    private void cambiarColorTexto(TextField nombreJugador) {
		nombreJugador.setStyle("-fx-prompt-text-fill: red");
	}
    
    public void handle(ActionEvent actionEvent) {
    	
    	if(!nombreJugadorEsValido(nombreJugador1)) {
    		cambiarColorTexto(nombreJugador1);
    		return;
    	}
    	
    	if(!nombreJugadorEsValido(nombreJugador2)) {
    		cambiarColorTexto(nombreJugador2);
    		return;
    	}
    	
    	// Creo una instancia de AlGoOh y le paso los dos nombres.   	
    	AlGoOh juego = new AlGoOh(nombreJugador1.getText(), nombreJugador2.getText());
    	
    	juego.getJugadorActual().tomarCartasDelMazo(CANTIDAD_CARTAS_INICIALES);
    	juego.getOponente().tomarCartasDelMazo(CANTIDAD_CARTAS_INICIALES);
 	
        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(stage,juego);
        Scene escenaJuego = new Scene(contenedorPrincipal, 640, 480);

        escenaJuego.setOnKeyPressed(new AccionAlPresionarTeclaEventHandler(stage,contenedorPrincipal.getBarraDeMenu()));
        
        stage.setScene(escenaJuego);
        stage.setFullScreen(true);
        
        stage.show();
    }
    
}