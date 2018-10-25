package fiuba.vista;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Aplicacion extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
						
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Al-Go-Oh!");
		stage.getIcons().add(new Image("file:src/fiuba/vista/img/icon.png"));
		
        ContenedorIngresoJugadores contenedorJugadores = new ContenedorIngresoJugadores(stage);
        Scene escenaJugadores = new Scene(contenedorJugadores,661,372);

        ContenedorMenuInicial contenedorInicial = new ContenedorMenuInicial(stage,escenaJugadores);
        Scene escenaBienvenidos = new Scene(contenedorInicial,400,600);
        
        setTeclaParaConfirmarEvento(contenedorInicial,escenaBienvenidos);
        
		stage.setScene(escenaBienvenidos);
		
		stage.show();
	}
	
	private void setTeclaParaConfirmarEvento(ContenedorMenuInicial contenedor, Scene escena) {
	    contenedor.addEventHandler(KeyEvent.KEY_PRESSED, evento -> {
	        if (evento.getCode() == KeyCode.ENTER) {
	        	Button botonFocus = (Button) escena.focusOwnerProperty().get();
	        	botonFocus.fire();
	        	evento.consume(); 
	        }
	    });
	}
	
}