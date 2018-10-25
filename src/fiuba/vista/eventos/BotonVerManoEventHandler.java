package fiuba.vista.eventos;

import fiuba.modelo.AlGoOh;
import fiuba.vista.ContenedorCartasEnMano;
import fiuba.vista.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonVerManoEventHandler implements EventHandler<ActionEvent> {

    private AlGoOh jugador;
    private ContenedorPrincipal contenedor;

    public BotonVerManoEventHandler(AlGoOh jugadorActual, ContenedorPrincipal contenedor) {
        this.jugador = jugadorActual;
        this.contenedor = contenedor;
    }
    
    public void handle (ActionEvent actionEvent) {
    	new ContenedorCartasEnMano(jugador, contenedor);
    }
    
}	