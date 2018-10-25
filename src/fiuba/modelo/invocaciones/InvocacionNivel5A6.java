package fiuba.modelo.invocaciones;

import fiuba.modelo.Jugador;
import fiuba.modelo.excepciones.FaltaSacrificioParaInvocacionError;

public class InvocacionNivel5A6 implements Invocacion {
	
	private int sacrificiosNecesarios;
	
	public InvocacionNivel5A6() {
		this.sacrificiosNecesarios = 1;
	}
	
	public int sacrificiosNecesarios(Jugador jugador) {
		return sacrificiosNecesarios;
	}

	public void sacrificarNecesarios(Jugador jugador) {
		if (jugador.cantidadDeMonstruos() < sacrificiosNecesarios) throw new FaltaSacrificioParaInvocacionError();
		jugador.sacrificarMonstruo();	
	}

}