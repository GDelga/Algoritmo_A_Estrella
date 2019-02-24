package negocio;

import java.util.ArrayList;

import presentacion.Celda;

public class TAlgoritmo {
	private Coordenadas inicio;
	private Coordenadas meta;
	private Celda[][] matriz;
	private ArrayList<Coordenadas> mejorCamino;
	
	public ArrayList<Coordenadas> getMejorCamino() {
		return mejorCamino;
	}

	public void setMejorCamino(ArrayList<Coordenadas> mejorCamino) {
		this.mejorCamino = mejorCamino;
	}

	public TAlgoritmo(Coordenadas ini, Coordenadas fin, Celda[][] m) {
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

	public Celda[][] getMatriz() {
		return this.matriz;
	}
}
