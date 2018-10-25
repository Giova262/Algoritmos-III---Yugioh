package fiuba.vista;

import fiuba.vista.eventos.BotonSiguienteEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContenedorIngresoJugadores extends VBox {

    Stage stage;

    public ContenedorIngresoJugadores(Stage stage) {
        super();

        this.stage = stage;

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        Image imagen = new Image("file:src/fiuba/vista/img/escena2imagen.jpg");
        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(imagenDeFondo));
        
        Label titulo = new Label("Ingrese el nombre de cada jugador");
        titulo.setFont(Font.font("System Bold", FontWeight.BOLD,20));
        titulo.setTextFill(Color.web("#e0f8f8"));
        
        Label etiqueta1 = new Label("Jugador 1:");
        etiqueta1.setFont(Font.font("System Bold", FontWeight.BOLD,22));
        etiqueta1.setTextFill(Color.web("#e0f8f8"));
        
        TextField nombreJugador1 = new TextField();
        nombreJugador1.setMaxWidth(150);
        nombreJugador1.setPromptText("Ingrese un nombre.");
        
        HBox datosJugador1 = new HBox(20,etiqueta1,nombreJugador1);
        datosJugador1.setAlignment(Pos.CENTER);
        
        Label etiqueta2 = new Label("Jugador 2:");
        etiqueta2.setFont(Font.font("System Bold", FontWeight.BOLD,22));
        etiqueta2.setTextFill(Color.web("#e0f8f8"));
        
        TextField nombreJugador2 = new TextField();
        nombreJugador2.setMaxWidth(150);
        nombreJugador2.setPromptText("Ingrese un nombre.");
        
        HBox datosJugador2 = new HBox(20,etiqueta2,nombreJugador2);
        datosJugador2.setAlignment(Pos.CENTER);
        
        Button botonSiguiente = new Button("Siguiente");
        botonSiguiente.setOnAction(new BotonSiguienteEventHandler(stage,nombreJugador1,nombreJugador2));
        botonSiguiente.setDefaultButton(true);

        this.getChildren().addAll(titulo,datosJugador1,datosJugador2,botonSiguiente);
    }

}