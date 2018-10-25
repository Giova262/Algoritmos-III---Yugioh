package fiuba.modelo.invocaciones;

import fiuba.modelo.excepciones.FaltaSacrificioParaInvocacionError;
import fiuba.modelo.Jugador;
import fiuba.modelo.cartas.CartaMonstruo;

public class InvocacionDragonDefinitivoDeOjosAzules implements Invocacion {
	
	private int sacrificiosNecesarios;
	
	public InvocacionDragonDefinitivoDeOjosAzules() {
		this.sacrificiosNecesarios = 3;
	}

	public int sacrificiosNecesarios(Jugador jugador) {
		return sacrificiosNecesarios;
	}

	@Override
	public void sacrificarNecesarios(Jugador jugador) {
		String nombreMonstruo = "Dragon blanco de ojos azules";
		if (jugador.cantidadCartasMonstruoEnCampo(nombreMonstruo) < sacrificiosNecesarios) throw new FaltaSacrificioParaInvocacionError();
		
		// Falta mejorar esto, busco cada carta y la mando al cementerio
		CartaMonstruo dragon = jugador.obtenerMonstruoEnCampo(nombreMonstruo);
		jugador.enviarAlCementerio(dragon);
		dragon = jugador.obtenerMonstruoEnCampo(nombreMonstruo);
		jugador.enviarAlCementerio(dragon);
		dragon = jugador.obtenerMonstruoEnCampo(nombreMonstruo);
		jugador.enviarAlCementerio(dragon);
	}

}