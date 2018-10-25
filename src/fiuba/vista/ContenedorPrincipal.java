package fiuba.vista;

import java.util.ArrayList;

import fiuba.modelo.AlGoOh;
import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.Carta;
import fiuba.modelo.cartas.CartaExodia;
import fiuba.modelo.cartas.CartaMagica;
import fiuba.modelo.cartas.CartaMonstruo;
import fiuba.modelo.cartas.CartaTrampa;
import fiuba.modelo.estrategiaMonstruo.ModoAtaque;
import fiuba.modelo.estrategiaMonstruo.ModoDefensa;
import fiuba.modelo.estrategiaPosicion.BocaAbajo;
import fiuba.modelo.estrategiaPosicion.BocaArriba;
import fiuba.modelo.excepciones.FinDePartidaExcepcion;
import fiuba.modelo.excepciones.JugadorSeQuedoSinVidaError;
import fiuba.vista.eventos.BotonPasarFaseEventHandler;
import fiuba.vista.eventos.BotonSacarCartaEventHandler;
import fiuba.vista.eventos.BotonVerManoEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContenedorPrincipal extends BorderPane {

	private BarraDeMenu menuBar;
    private Carta cartaSeleccionada;
	private Stage escenario;
	private AlGoOh yugioh;
	private int indiceMonstruoAtacante,indiceMonstruoAtacado;
	private Button botonPasarFase;
	
	private static int altoCarta = 100;
	private static int anchoCarta = 65;

	public ContenedorPrincipal(Stage stage, AlGoOh juego) {
		this.yugioh = juego;
        this.cartaSeleccionada = null;
        this.escenario = stage;
    	this.setMenu(stage);
        this.actualizarVista();
    }

    private void setMenu(Stage stage) {
        this.menuBar = new BarraDeMenu(stage);
        this.setTop(menuBar);
    }
    
    public BarraDeMenu getBarraDeMenu() {
        return menuBar;
    }

    public void setBotonera() {        
        VBox contenedorVertical = this.SetContenedorVerticalIzquierdo();
        this.setLeft(contenedorVertical);
    }
    
    private Label setNombreTurno() {
    	Label turno = new Label("Turno de " + yugioh.getNombre());
        turno.setFont(Font.font("System Bold", FontWeight.BOLD,20));
        turno.setTextFill(Color.WHITE);
        return turno;
    }
    
    private Label setNombreFase() {
    	Label fase = new Label(yugioh.getNombreFase());
        fase.setFont(Font.font("System Bold", FontWeight.BOLD,16));
        fase.setTextFill(Color.WHITE);
        return fase;
    }
    
    private Button setBotonPasarFase() {
    	Button botonPasarFase = new Button("Pasar de Fase");
        botonPasarFase.setOnAction(new BotonPasarFaseEventHandler(yugioh, this));
        return botonPasarFase;
    }
    
    private VBox SetContenedorVerticalIzquierdo() {
    	Label etiquetaNombreTurno = setNombreTurno();
    	Label etiquetaNombreFase = setNombreFase();
    	Button botonFase = setBotonPasarFase();
    	botonPasarFase = botonFase;
    
        VBox contenedorEstadoPartida = setEstadoPartida();
            
        if(this.yugioh.getNombreFase() == "Fase Inicial") {
        	botonPasarFase.setDisable(true);
        }
        
    	VBox contenedorVertical = new VBox(etiquetaNombreTurno, etiquetaNombreFase, botonPasarFase, contenedorEstadoPartida);
        contenedorVertical.setSpacing(20);
        contenedorVertical.setPadding(new Insets(15));
        
        Image imagen = new Image("file:src/fiuba/vista/img/fondo2.jpg");
    	BackgroundSize tamanio = new BackgroundSize (BackgroundSize.AUTO,BackgroundSize.AUTO, false, false, false, true);
    	Background fondo = new Background(new BackgroundImage(imagen,
    			BackgroundRepeat.NO_REPEAT,
    			BackgroundRepeat.NO_REPEAT,
    			BackgroundPosition.CENTER,
    			tamanio));
    	contenedorVertical.setBackground(fondo);
     
        return contenedorVertical;
    }
    
    private void setFondo(HBox tablero) {
    	Image imagen = new Image("file:src/fiuba/vista/img/fondo1.jpg");
    	BackgroundSize tamanio = new BackgroundSize (BackgroundSize.AUTO,BackgroundSize.AUTO, false, false, false, true);
    	Background fondo = new Background(new BackgroundImage(imagen,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,tamanio));
    	tablero.setBackground(fondo);
    }

    public void setCentro() {
    	HBox tablero = new HBox();
    	setFondo(tablero);
    	tablero.setAlignment(Pos.CENTER);
    	tablero.setPadding(new Insets(50));
    	VBox centroDelTablero = new VBox(80);
    	VBox bordeIzquieroDelTablero = new VBox();
    	VBox bordeDerechoDelTablero = new VBox();
    	VBox subtablero1 = new VBox(10);
    	VBox subtablero2 = new VBox(10);
    	HBox botones = new HBox();
    	
    	Button botonVerMano = new Button("Ver mano");
        botonVerMano.setOnAction(new BotonVerManoEventHandler(yugioh, this));
        botones.getChildren().add(botonVerMano);
        
        bordeDerechoDelTablero.getChildren().add(setParteDerechaDelTablero());
        bordeIzquieroDelTablero.getChildren().add(setParteIzquierdaDelTablero());
        
     
        HBox tableroMagico1 = setZonaMagiaTrampa(yugioh.getJugadorActual());
        HBox tableroMonstruo1 = setZonaMonstruos(yugioh.getJugadorActual());
        HBox tableroMagico2 = setZonaMagiaTrampa(yugioh.getOponente());
        HBox tableroMonstruo2 = setZonaMonstruos(yugioh.getOponente());
        
    
        subtablero1.getChildren().add(tableroMonstruo1);
        subtablero1.getChildren().add(tableroMagico1);
        subtablero2.getChildren().add(tableroMagico2);
        subtablero2.getChildren().add(tableroMonstruo2);
        
        centroDelTablero.getChildren().add(subtablero2);
        centroDelTablero.getChildren().add(subtablero1);
        centroDelTablero.getChildren().add(botones);

        tablero.getChildren().add(bordeIzquieroDelTablero);
        tablero.getChildren().add(centroDelTablero);
        tablero.getChildren().add(bordeDerechoDelTablero);
        
        this.setCenter(tablero);
    }
    
    private Label setEtiquetaNombreJugador(String nombreJugador) {
    	Label etiquetaNombre = new Label(nombreJugador);
    	etiquetaNombre.setFont(Font.font("Tahoma", FontWeight.BOLD,40));
    	etiquetaNombre.setTextFill(Color.WHITE);
    	return etiquetaNombre;
    }
    
    private Label setEtiquetaVidaJugador(int vidaJugador) {
    	Label etiquetaVida = new Label();
    	etiquetaVida.setText("Vida: " + vidaJugador);
    	etiquetaVida.setFont(Font.font("Tahoma", FontWeight.BOLD,20));
    	etiquetaVida.setTextFill(Color.WHITE);
    	return etiquetaVida;
    }

    public VBox setEstadoPartida() {
		VBox cajaEstado = new VBox(0);
    	
		String nombreJugador1 = yugioh.getJugadorActual().getNombre();
		String nombreJugador2 = yugioh.getOponente().getNombre();
		int vidaJugador1 = yugioh.getJugadorActual().getVida();
		int vidaJugador2 = yugioh.getOponente().getVida();
    	
    	Label etiquetaNombre1 = setEtiquetaNombreJugador(nombreJugador1);    	
    	Label etiquetaNombre2 = setEtiquetaNombreJugador(nombreJugador2);    
 	
    	Label etiquetaVida1 = setEtiquetaVidaJugador(vidaJugador1);
    	Label etiquetaVida2 = setEtiquetaVidaJugador(vidaJugador2);
 
        cajaEstado.getChildren().addAll(etiquetaNombre1, etiquetaVida1, etiquetaNombre2, etiquetaVida2);
        
		return cajaEstado;
	}

    private Label setEtiquetaConsola(String mensaje) {
    	Label etiqueta = new Label();
        etiqueta.setText(mensaje);
        etiqueta.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 14));
        etiqueta.setTextFill(Color.WHITE);
        return etiqueta;
    }
    
    private VBox setContenedorConsola(String mensaje) {
    	Label etiquetaConsola = setEtiquetaConsola(mensaje);
    	VBox contenedorConsola = new VBox(etiquetaConsola);
        contenedorConsola.setSpacing(10);
        contenedorConsola.setPadding(new Insets(15));
        contenedorConsola.setStyle("-fx-background-color: black;");
    	return contenedorConsola;
    }
    
	public void setConsola() {
		String mensaje = yugioh.getMensajeFase();
		VBox contenedorConsola = setContenedorConsola(mensaje);
        this.setBottom(contenedorConsola);
    }
	
	public void informarAccion(String mensaje) {
		VBox contenedorConsola = setContenedorConsola(mensaje);
        this.setBottom(contenedorConsola);
    }
	
    private VBox setParteIzquierdaDelTablero() {
    	VBox bordeIzquierdo = new VBox(50);

    	int cantidadDeCartas = yugioh.getOponente().cantidadDeCartasEnMazo();
    	BotonMazo mazoBoton = new BotonMazo(this, altoCarta, anchoCarta,cantidadDeCartas);

    	VBox campoYCementerio = new VBox();
        campoYCementerio.getChildren().addAll(setCementerio(yugioh.getJugadorActual()),setCampo(yugioh.getJugadorActual()));
           
        bordeIzquierdo.getChildren().addAll(mazoBoton,campoYCementerio);
        
        return bordeIzquierdo;
	}

	private VBox setParteDerechaDelTablero() {
    	VBox bordeDerecho = new VBox(50);
    	bordeDerecho.setPadding(new Insets(150, 0, 0, 0));
   

        int cantidadDeCartas = yugioh.getJugadorActual().cantidadDeCartasEnMazo();
    	BotonMazo mazoBoton = new BotonMazo(this, altoCarta, anchoCarta,cantidadDeCartas);

    	VBox campoYCementerio = new VBox();
        campoYCementerio.getChildren().addAll(setCampo(yugioh.getOponente()),setCementerio(yugioh.getOponente()));
    	
    	mazoBoton.setOnAction(new BotonSacarCartaEventHandler(this.yugioh, this, mazoBoton, botonPasarFase));
        if(this.yugioh.getNombreFase() != "Fase Inicial") {
        	mazoBoton.setDisable(true);		
    	}
    	
        bordeDerecho.getChildren().addAll(campoYCementerio,mazoBoton);
        
        return bordeDerecho;
	}
	
    private HBox setZonaMonstruos(Jugador jugador) {
    	ArrayList<String> nombresDeMonstruos = new ArrayList<String>();
		ArrayList<CartaMonstruo> cartasMonstruosActual = jugador.getZonaMonstruos();
		ArrayList<Boolean> posicion = new ArrayList<Boolean>();
		
		for(int i = 0;i < cartasMonstruosActual.size();i++) {
			nombresDeMonstruos.add(cartasMonstruosActual.get(i).getNombre());
			posicion.add(jugador.estaEnModoDefensaElMonstruo(i));
    	}
		for(int j = cartasMonstruosActual.size();j < 5;j++) {
			nombresDeMonstruos.add("cartaDorsoNormal");
			posicion.add(false);
		}
	
    	HBox zonaMonstruos = new HBox(0);
    	
    	for (int i = 0; i<5; i++) {
    		BotonMonstruo monstruo = new BotonMonstruo(this,nombresDeMonstruos.get(i),i,altoCarta,anchoCarta, cartaSeleccionada,jugador,yugioh,posicion.get(i));
            zonaMonstruos.getChildren().add(monstruo);
    	}
    	
    	return zonaMonstruos;
	}

	private HBox setZonaMagiaTrampa(Jugador jugador) {
		ArrayList<String> nombresDeMagiasYTrampas = new ArrayList<String>();
		ArrayList<Carta> cartasMagicasYTrampasActuales = jugador.getZonaMagicasTrampas();
		ArrayList<Boolean> posicion = new ArrayList<Boolean>();
		
    	for(int i = 0 ; i < cartasMagicasYTrampasActuales.size() ; i++) {
    		nombresDeMagiasYTrampas.add(cartasMagicasYTrampasActuales.get(i).getNombre());
			posicion.add(jugador.estaBocaAbajoLaCarta(i));
    	}
		for(int j = ( cartasMagicasYTrampasActuales.size()) ; j < 5 ; j++) {
			nombresDeMagiasYTrampas.add("cartaDorsoAzul");
			posicion.add(false);
		}
		
    	HBox zonaMagicasOTrampas = new HBox(0);
    	
    	for (int i = 0; i<5; i++) {
    		BotonMagicoOTrampa magiaOTrampa = new BotonMagicoOTrampa(this,nombresDeMagiasYTrampas.get(i),i,altoCarta,anchoCarta,yugioh,posicion.get(i));
    		zonaMagicasOTrampas.getChildren().add(magiaOTrampa);
    	}
    	
    	return zonaMagicasOTrampas;
	}
	
    private BotonCampo setCampo(Jugador unJugador) {
        String nombreCartaCampo = unJugador.devolverNombreDeLaImagenDeCartaCampo();	
        BotonCampo campoBoton = new BotonCampo(this,nombreCartaCampo,altoCarta,anchoCarta);
        campoBoton.setStyle("-fx-background-color: transparent;"
     		    + "-fx-min-height: " + altoCarta + "px;"
 				+ "-fx-min-width: " + altoCarta + "px;"
 				+ "-fx-max-height: " + altoCarta + "px;"
 				+ "-fx-max-width: " + altoCarta + "px;"
 				+ "-fx-background-size: " + anchoCarta + "px " + altoCarta + "px;"
 				+ "-fx-background-repeat: no-repeat;"
 				+ "-fx-cursor: hand;"
 				+  "-fx-background-image: url('file:src/fiuba/vista/img/" + nombreCartaCampo + ".jpg');");
 		return campoBoton;
 	}
     
     private BotonCementerio setCementerio(Jugador unJugador) {
     	String nombreCementerio = unJugador.getOponente().devolverNombreDeLaUltimaCartaEnElCementerio(); 
         BotonCementerio cementerioBoton = new BotonCementerio(this, altoCarta, anchoCarta);
         cementerioBoton.setStyle("-fx-background-color: transparent;"
         		+ "-fx-min-height: " + altoCarta + "px;"
 				+ "-fx-min-width: " + altoCarta + "px;"
 				+ "-fx-max-height: " + altoCarta + "px;"
 				+ "-fx-max-width: " + altoCarta + "px;"
 				+ "-fx-background-size: " + anchoCarta + "px " + altoCarta + "px;"
 				+ "-fx-background-repeat: no-repeat;"
 				+ "-fx-cursor: hand;"
 				+  "-fx-background-image: url('file:src/fiuba/vista/img/" + nombreCementerio + ".jpg');");
 		return cementerioBoton;
 	}

	public void setModoAtaque(boolean modo , Carta cartanueva) {
		if (modo && ( cartanueva.getClass() == CartaMonstruo.class || cartanueva.getClass() == CartaExodia.class       )    ) {
			CartaMonstruo monstruo = (CartaMonstruo) cartanueva;
			monstruo.setEstrategiaInicial(new ModoAtaque());
		}
	}

	public void setModoDefensa(boolean modo , Carta cartanueva) {
		if (modo &&  ( cartanueva.getClass() == CartaMonstruo.class || cartanueva.getClass() == CartaExodia.class       ) ) {
			CartaMonstruo monstruo = (CartaMonstruo) cartanueva;
			monstruo.setEstrategiaInicial(new ModoDefensa());
		}
	}

	public void setBocaArriba(boolean modo , Carta cartanueva) {
		if (modo && cartanueva.getClass() == CartaMagica.class) {
			CartaMagica magica = (CartaMagica) cartanueva;
			magica.setEstrategiaPosicion(new BocaArriba());
		}else if (modo && cartanueva.getClass() == CartaTrampa.class) {
			CartaTrampa trampa = (CartaTrampa) cartanueva;
			trampa.setEstrategiaPosicion(new BocaArriba());
		}
	}

	public void setBocaAbajo(boolean modo , Carta cartanueva) {
		if (modo && cartanueva.getClass() == CartaMagica.class) {
			CartaMagica magica = (CartaMagica) cartanueva;
			magica.setEstrategiaPosicion(new BocaAbajo());
		}else if (modo && cartanueva.getClass() == CartaTrampa.class) {
			CartaTrampa trampa = (CartaTrampa) cartanueva;
			trampa.setEstrategiaPosicion(new BocaAbajo());
		}
	}

	public void efectuarAtaque( ) {
		Jugador jugador1 =  yugioh.getJugadorActual();
		Jugador jugador2 = jugador1.getOponente();
		
		CartaMonstruo cartaAtacante= jugador1.getCartaMonstruo(indiceMonstruoAtacante);
		CartaMonstruo cartaAtacada= jugador2.getCartaMonstruo(indiceMonstruoAtacado);
		
		try {
			if (cartaAtacada == null) {
				jugador2.recibirAtaqueDirecto(cartaAtacante);
				informarAccion(cartaAtacante.getNombre() + " ataca directamente a " + yugioh.getOponente().getNombre());
			} else {
				cartaAtacada.esAtacadaPor(cartaAtacante);
				informarAccion(cartaAtacante.getNombre() + " ataca a " + cartaAtacada.getNombre());
			}
		}catch (JugadorSeQuedoSinVidaError e) {
			finalizarPartida(e);
		}
		this.setBotonera();
		this.setCentro();
	}

	public void setIndiceDelAtacante(int indice) {
		indiceMonstruoAtacante = indice;		
	}

	public void setIndiceDelAtacado(int indice) {
		indiceMonstruoAtacado = indice;
	}
	
	public int getIndiceAtacante() {		
		return indiceMonstruoAtacante;
	}
	
	public int getIndiceAtacado() {
		return indiceMonstruoAtacado;
	}
	
	public void actualizarVista() {
		this.setBotonera();
		this.setConsola();
		this.setCentro();
	}

	public void finalizarPartida(FinDePartidaExcepcion e) {
		ContenedorVictoria contenedorVictoria = new ContenedorVictoria(e);
   		Scene escenaFinal = new Scene(contenedorVictoria,458,550);
   		
   		escenario.setScene(escenaFinal);
        escenario.setFullScreenExitHint("");
		escenario.setFullScreen(true);
		            
        escenario.show();
	}

}