package fiuba.vista;

import java.util.ArrayList;

import fiuba.modelo.AlGoOh;
import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaDeCampo;
import fiuba.modelo.cartas.CartaExodia;
import fiuba.modelo.cartas.CartaMagica;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.cartas.CartaTrampa;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContenedorCartasEnMano extends Stage {

    private AlGoOh yugioh;
    private ContenedorPrincipal contenedor;
    private Scene escena;
    private VBox newRoot;
    private int indiceCartaQueElegi;
    private final int CARTAS_POR_COLUMNA = 6;
    
	public ContenedorCartasEnMano(AlGoOh yugiohh, ContenedorPrincipal contenedor) {
		this.setMaximized(true);
		this.setTitle("Mano de " + yugiohh.getJugadorActual().getNombre());
		this.getIcons().add(new Image("file:src/fiuba/vista/img/icon2.png"));
        this.yugioh = yugiohh;
        this.contenedor = contenedor;
		this.newRoot = new VBox(5);
		this.newRoot.setAlignment(Pos.CENTER);
		this.escena = new Scene(newRoot,BackgroundSize.AUTO,BackgroundSize.AUTO);
		this.setScene(escena);
		this.show();
		this.iniciarVentana();
	}
	
	public void iniciarVentana() {
		ArrayList<Carta> cartas = yugioh.getCartasEnMano();
		
		Image imagen = new Image("file:src/fiuba/vista/img/fondo3.jpg");
    	BackgroundSize tamanio = new BackgroundSize (BackgroundSize.AUTO,BackgroundSize.AUTO, false, false, false, true);
    	Background fondo = new Background(new BackgroundImage(imagen,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,tamanio));
    	newRoot.setBackground(fondo);

		final ToggleGroup grupoEstrategiaMonstruo = new ToggleGroup();
		final ToggleGroup grupoEstrategiaPosicion = new ToggleGroup();
		
		RadioButton botonModoAtaque = new RadioButton("Modo Ataque");
		botonModoAtaque.setStyle("-fx-text-fill: white");
		RadioButton botonModoDefensa = new RadioButton("Modo Defensa");
		botonModoDefensa.setStyle("-fx-text-fill: white");
		RadioButton botonBocaArriba = new RadioButton("Boca Arriba");
		botonBocaArriba.setStyle("-fx-text-fill: white");
		RadioButton botonBocaAbajo = new RadioButton("Boca Abajo");
		botonBocaAbajo.setStyle("-fx-text-fill: white");
		
		
		crearOyenteRelacionado(botonModoAtaque, botonBocaArriba);
		crearOyenteRelacionado(botonBocaArriba, botonModoAtaque);
		crearOyenteRelacionado(botonModoDefensa, botonBocaAbajo);
		crearOyenteRelacionado(botonBocaAbajo, botonModoDefensa);
		
		botonModoAtaque.setToggleGroup(grupoEstrategiaMonstruo);
		botonModoDefensa.setToggleGroup(grupoEstrategiaMonstruo);
		botonBocaArriba.setToggleGroup(grupoEstrategiaPosicion);
		botonBocaAbajo.setToggleGroup(grupoEstrategiaPosicion);
		
		for (int j = 0;(j * CARTAS_POR_COLUMNA) < cartas.size() && (j < 2);j++) {
			HBox unaColumnaDeCartas = new HBox(10);
			unaColumnaDeCartas.setAlignment(Pos.CENTER);
			for (int i = 0; (i < CARTAS_POR_COLUMNA) && (j * CARTAS_POR_COLUMNA + i) < cartas.size(); i++) {
				unaColumnaDeCartas.getChildren().add(this.setBotonCarta(cartas.get(j * CARTAS_POR_COLUMNA + i),botonModoAtaque,botonModoDefensa,botonBocaArriba,botonBocaAbajo,(j * CARTAS_POR_COLUMNA + i)));
			}
			newRoot.getChildren().add(unaColumnaDeCartas);
		}
		
		Button invocar = new Button("Invocar");		
		if(yugioh.getNombreFase() != "Fase Preparacion") {
			invocar.setDisable(true);
		}
		//Accion de invocar
		invocar.setOnAction(e -> {
			if (!botonModoAtaque.isSelected() && !botonModoDefensa.isSelected() && !botonBocaArriba.isSelected() && !botonBocaAbajo.isSelected()) {
				return ;
			}
			
			Jugador jugadorAct = yugioh.getJugadorActual();		
			Carta cartanueva = jugadorAct.getCartaDeMano(indiceCartaQueElegi);
					
			if( cartanueva.getClass()== CartaMonstruo.class || cartanueva.getClass()== CartaExodia.class   ) {
				jugadorAct.invocar(  (CartaMonstruo) cartanueva);
				contenedor.setModoAtaque(botonModoAtaque.isSelected() , cartanueva );
				contenedor.setModoDefensa(botonModoDefensa.isSelected(), cartanueva);
				contenedor.setBocaArriba(botonBocaArriba.isSelected(), cartanueva);
				contenedor.setBocaAbajo(botonBocaAbajo.isSelected(), cartanueva);
			}
			if( cartanueva.getClass()== CartaMagica.class  ) {
				jugadorAct.invocar( ( CartaMagica)cartanueva);				
				contenedor.setBocaArriba(botonBocaArriba.isSelected(), cartanueva);
				contenedor.setBocaAbajo(botonBocaAbajo.isSelected(), cartanueva);
			}
			if( cartanueva.getClass()== CartaTrampa.class  ) {
				jugadorAct.invocar( ( CartaTrampa)cartanueva);				
				contenedor.setBocaArriba(botonBocaArriba.isSelected(), cartanueva);
				contenedor.setBocaAbajo(botonBocaAbajo.isSelected(), cartanueva);
			}
			
			if( cartanueva.getClass()== CartaDeCampo.class  ) {
				jugadorAct.invocar( ( CartaDeCampo)cartanueva);				
				contenedor.setBocaArriba(botonBocaArriba.isSelected(), cartanueva);
				contenedor.setBocaAbajo(botonBocaAbajo.isSelected(), cartanueva);
			}
			
			contenedor.setCentro();		
			this.close();
			
		});
		
		Button botonVolver = new Button("Volver");
		botonVolver.setOnAction( e -> this.close() );
		
		HBox botones = new HBox(20);
		botones.setAlignment(Pos.CENTER);
		botones.getChildren().addAll(botonBocaAbajo,botonBocaArriba,botonModoAtaque,botonModoDefensa,invocar,botonVolver);

		newRoot.getChildren().add(botones);
    }
/* esto es para no elegir todas las opciones a la vez*/
	private void crearOyenteRelacionado(RadioButton boton1, RadioButton boton2) {
		boton1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean estabaSeleccionado, Boolean estaSeleccionado) {
				if (estaSeleccionado) {
					boton2.setSelected(true);
				}
			}
		});
	}
/*aca creo un boton le pongo la imagen de la carta una accion y la devuelvo*/
	private Button setBotonCarta(Carta carta, RadioButton botonModoAtaque,RadioButton botonModoDefensa, RadioButton botonBocaArriba, RadioButton botonBocaAbajo,int indice) {
		Button botonCarta = new Button();
		
		botonCarta.setStyle("-fx-min-height: 340px;"
				+ "-fx-min-width: 220px;"
				+ "-fx-max-height: 340px;"
				+ "-fx-max-width: 220px;"
				+ "-fx-background-image: url('fiuba/vista/img/" + carta.getNombre() + ".jpg');"
				+ "-fx-background-size: 220px 340px;"
				+ "-fx-background-repeat: no-repeat;");

		botonCarta.setOnAction(e ->{
				this.indiceCartaQueElegi = indice;
				
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
			
		});	
		return botonCarta;
	}

}