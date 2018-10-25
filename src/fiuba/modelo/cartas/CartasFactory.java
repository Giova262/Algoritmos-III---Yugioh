package fiuba.modelo.cartas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fiuba.modelo.excepciones.FalloLaInicializacionDelArchivoError;
import fiuba.modelo.invocaciones.*;
import fiuba.modelo.Jugador;
import fiuba.modelo.efectos.*;
import fiuba.modelo.efectos.efectoCampo.EfectoCampo;
import fiuba.modelo.efectos.efectoCampo.EfectoSogen;
import fiuba.modelo.efectos.efectoCampo.EfectoWasteland;
import fiuba.modelo.efectos.efectoMagica.EfectoAgujeroOscuro;
import fiuba.modelo.efectos.efectoMagica.EfectoCilindroMagico;
import fiuba.modelo.efectos.efectoMagica.EfectoFisura;
import fiuba.modelo.efectos.efectoMagica.EfectoMagica;
import fiuba.modelo.efectos.efectoMonstruo.EfectoExodia;
import fiuba.modelo.efectos.efectoMonstruo.EfectoInsectoComeHombres;
import fiuba.modelo.efectos.efectoMonstruo.EfectoJinzo7;
import fiuba.modelo.efectos.efectoMonstruo.EfectoMonstruo;
import fiuba.modelo.efectos.efectoTrampa.EfectoOllaDeLaCodicia;
import fiuba.modelo.efectos.efectoTrampa.EfectoReinforcements;
import fiuba.modelo.efectos.efectoTrampa.EfectoTrampa;

public class CartasFactory {
	
	private	ArrayList<Carta> cartas = new ArrayList<Carta>();
	
	public ArrayList<Carta> getCartas(Jugador jugador) {
		FileReader archivo = null;
		BufferedReader lector = null ;
		
		//Efectos de Monstruo
		EfectoMonstruo nulo =  new EfectoMonstruoNulo();
		EfectoMonstruo exodia = new EfectoExodia();
		EfectoMonstruo insectoComeHombres = new EfectoInsectoComeHombres();
		EfectoMonstruo jinzo7 = new EfectoJinzo7();
		//Efectos de Campo
		EfectoCampo wasteland = new EfectoWasteland();
		EfectoCampo sogen = new EfectoSogen();
		//Efectos de Magica
		EfectoMagica agujero = new EfectoAgujeroOscuro();
		EfectoMagica fisura = new EfectoFisura();
		EfectoMagica olla = new EfectoOllaDeLaCodicia();
		//Efectos de Trampa
		EfectoTrampa cilindro = new EfectoCilindroMagico();
		EfectoTrampa reinforcements = new EfectoReinforcements();
	
		CartaTrampa cartaCilindro = new CartaTrampa("CilindroMagico",cilindro,jugador);
		CartaTrampa cartaReinforcements = new CartaTrampa("Reinforcements",reinforcements,jugador);
		
		CartaDeCampo cartaWasteland = new CartaDeCampo("WasteLand",wasteland,jugador);
		CartaDeCampo cartaSogen = new CartaDeCampo("Sogen",sogen,jugador);
		
		CartaMagica cartaAgujero = new CartaMagica("AgujeroOscuro",agujero,jugador);
		CartaMagica cartaFisura = new CartaMagica("Fisura",fisura,jugador);
		CartaMagica cartaOlla = new CartaMagica("OllaDeLaCodicia",olla,jugador);
		
		cartas.add(cartaCilindro);
		cartas.add(cartaReinforcements);
		cartas.add(cartaWasteland);
		cartas.add(cartaSogen);
		cartas.add(cartaAgujero);
		cartas.add(cartaFisura);
		cartas.add(cartaOlla);
			
		try {
			archivo = new FileReader("monstruos.txt");	
			
			if(archivo.ready()) {
				lector = new BufferedReader(archivo);
				String lineaEntera ;		
				String[] separadoPorComas;
				
				while ((lineaEntera = lector.readLine()) != null) {
					separadoPorComas = lineaEntera.split(",");
					
					String nombre = separadoPorComas[0];
					String efecto= separadoPorComas[1];
					int ataque=Integer.parseInt( separadoPorComas[2]);
					int defensa= Integer.parseInt( separadoPorComas[3]);
					int nivel= Integer.parseInt( separadoPorComas[4]);
					
					Invocacion invocacion;
					
					//Factory Begin
					if (nombre.equals("DragonDefinitivoDeOjosAzules")) {
						invocacion = new InvocacionDragonDefinitivoDeOjosAzules();
					}
					else if (nivel > 0 && nivel < 5) {
						invocacion = new InvocacionNivel1A4();
					}
					else if (nivel > 4 && nivel < 7) {
						invocacion = new InvocacionNivel5A6();
					}
					else {
						invocacion = new InvocacionNivel7oMas();	
					}
					
					if (efecto.equals("null")) {						
						CartaMonstruo c = new CartaMonstruo(nombre,nulo,ataque,defensa,nivel,jugador,invocacion);
						cartas.add(c);
					}
					if (efecto.equals("exodia")) {
						CartaExodia c = new CartaExodia(nombre,exodia,ataque,defensa,nivel,jugador,invocacion);
						cartas.add(c);
					}
					if (efecto.equals("insecto come hombres")) {
						CartaMonstruo c = new CartaMonstruo(nombre,insectoComeHombres,ataque,defensa,nivel,jugador,invocacion);
						cartas.add(c);
					}
					if (efecto.equals("jinzo7")) {
						CartaMonstruo c = new CartaMonstruo(nombre,jinzo7,ataque,defensa,nivel,jugador,invocacion);
						cartas.add(c);
					}
				}
				archivo.close();
			}else System.out.println("No existe");
		} catch (IOException e) {
			throw new FalloLaInicializacionDelArchivoError();
		}
		return cartas;	
	}	

}