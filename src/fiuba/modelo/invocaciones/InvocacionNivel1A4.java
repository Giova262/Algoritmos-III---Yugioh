package fiuba.modelo.invocaciones;

import fiuba.modelo.Jugador;

public class InvocacionNivel1A4 implements Invocacion{
	
	private int sacrificiosNecesarios;
	
	public InvocacionNivel1A4() {
		this.sacrificiosNecesarios = 0;
	}

	public int sacrificiosNecesarios(Jugador jugador) {
		return sacrificiosNecesarios;
	}

	public void sacrificarNecesarios(Jugador jugador) {
		/*No debe sacrificar*/
	}
	
}