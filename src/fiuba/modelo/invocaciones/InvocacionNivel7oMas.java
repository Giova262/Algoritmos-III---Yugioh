package fiuba.modelo.invocaciones;

import fiuba.modelo.Jugador;
import fiuba.modelo.excepciones.FaltaSacrificioParaInvocacionError;

public class InvocacionNivel7oMas implements Invocacion {
	
	private int sacrificiosNecesarios;
	
	public InvocacionNivel7oMas() {
		this.sacrificiosNecesarios = 2;
	}
	
	public int sacrificiosNecesarios(Jugador jugador) {
		return sacrificiosNecesarios;
	}

	public void sacrificarNecesarios(Jugador jugador) {
		if (jugador.cantidadDeMonstruos() < sacrificiosNecesarios) throw new FaltaSacrificioParaInvocacionError();
		jugador.sacrificarMonstruo();
		jugador.sacrificarMonstruo();
	}
	
}