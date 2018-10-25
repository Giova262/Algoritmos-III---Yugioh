package fiuba.vista;

import fiuba.vista.eventos.BotonAcercaDeEventHandler;
import fiuba.vista.eventos.BotonPantallaCompletaEventHandler;
import fiuba.vista.eventos.BotonSalirEventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;

public class BarraDeMenu extends MenuBar {

    MenuItem opcionPantallaCompleta = new MenuItem("Pantalla completa");

    public BarraDeMenu(Stage stage) {

        Menu menuArchivo = new Menu("Archivo");
        Menu menuVer = new Menu("Ver");
        Menu menuAyuda = new Menu("Ayuda");

        MenuItem opcionSalir = new MenuItem("Salir");
        opcionSalir.setOnAction(new BotonSalirEventHandler());
        
        MenuItem opcionAbrir = new MenuItem("Abrir");
        
        MenuItem opcionAcercaDe = new MenuItem("Acerca de...");
        opcionAcercaDe.setOnAction(new BotonAcercaDeEventHandler());

        opcionPantallaCompleta.setOnAction(new BotonPantallaCompletaEventHandler(stage,opcionPantallaCompleta));
        opcionPantallaCompleta.setDisable(true);

        menuArchivo.getItems().addAll(opcionAbrir, new SeparatorMenuItem(), opcionSalir);
        menuAyuda.getItems().addAll(opcionAcercaDe);
        menuVer.getItems().addAll(opcionPantallaCompleta);

        this.getMenus().addAll(menuArchivo, menuVer, menuAyuda);
    }

    public void aplicacionMaximizada() {
        opcionPantallaCompleta.setDisable(false);
    }
    
}