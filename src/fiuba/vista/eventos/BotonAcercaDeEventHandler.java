package fiuba.vista.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonAcercaDeEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Acerca de...");
        alert.setHeaderText("Como jugar");
        String mensaje = "Para sacar una carta durante la fase inicial, haga click encima del mazo.\n"
        		+ "Para atacar, haga click sobre el monstruo con el que desea atacar y luego sobre el monstruo al ue desea atacar. Si no hay monstruos se realizara un ataque directo.\n"
        		+ "Para activar una carta magica, haga click sobre la misma durante la fase final.";
        alert.setContentText(mensaje);
        alert.show();
    }
    
}