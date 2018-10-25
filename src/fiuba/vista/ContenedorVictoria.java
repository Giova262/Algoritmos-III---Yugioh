package fiuba.vista;

import fiuba.modelo.excepciones.FinDePartidaExcepcion;
import fiuba.vista.eventos.BotonSalirEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ContenedorVictoria extends VBox {

    public ContenedorVictoria(FinDePartidaExcepcion e) {
        super();

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        Image imagen = new Image("file:src/fiuba/vista/img/victoria_juego.jpg");
        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(imagenDeFondo));

        Label victoria = new Label(e.informarGanador());
        victoria.setFont(Font.font("System Bold", FontWeight.BOLD,40));
        victoria.setTextFill(Color.WHITE);
        
        Label causa = new Label(e.informarCausa());
        causa.setFont(Font.font("Tahoma", FontWeight.BOLD,16));
        causa.setTextFill(Color.WHITE);
        
        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(new BotonSalirEventHandler());

        this.getChildren().addAll(victoria,causa,botonSalir);
    }

}