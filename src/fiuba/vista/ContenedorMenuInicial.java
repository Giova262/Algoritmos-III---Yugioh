package fiuba.vista;

import fiuba.vista.eventos.BotonEntrarEventHandler;
import fiuba.vista.eventos.BotonSalirEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContenedorMenuInicial extends VBox {

    Stage stage;

    public ContenedorMenuInicial(Stage stage, Scene proximaEscena) {
        super();

        this.stage = stage;

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        Image imagen = new Image("file:src/fiuba/vista/img/escena1imagen.jpg");
        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(imagenDeFondo));

        Button botonEntrar = new Button("Jugar");
        botonEntrar.setOnAction(new BotonEntrarEventHandler(stage,proximaEscena));
        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(new BotonSalirEventHandler());

        this.getChildren().addAll(botonEntrar,botonSalir);
    }

}