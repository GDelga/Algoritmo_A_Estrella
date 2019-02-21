package negocio;

import java.util.ArrayList;

public class TAlgoritmo {
	private Coordenadas inicio;
	private Coordenadas meta;
	private Casillas[][] matriz;
	private ArrayList<Coordenadas> mejorCamino;
	
	public ArrayList<Coordenadas> getMejorCamino() {
		return mejorCamino;
	}

	public void setMejorCamino(ArrayList<Coordenadas> mejorCamino) {
		this.mejorCamino = mejorCamino;
	}

	public TAlgoritmo(Coordenadas ini, Coordenadas fin, Casillas[][] m) {
		this.inicio = ini;
		this.meta = fin;
		this.matriz = m;
	}

	public Coordenadas getCorInicio() {
		return this.inicio;
	}

	public Coordenadas getCorFinal() {
		return this.meta;
	}

	public Casillas[][] getMatriz() {
		return this.matriz;
	}
}
