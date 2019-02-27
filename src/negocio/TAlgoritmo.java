package negocio;

import java.util.ArrayList;

import presentacion.Celda;

public class TAlgoritmo {
	private Coordenadas inicio;
	private Coordenadas meta;
	private Celda[][] matriz;
	private ArrayList<Coordenadas> metas;

	public ArrayList<Coordenadas> getMetas() {
		return metas;
	}

	public void setMetas(ArrayList<Coordenadas> metas) {
		this.metas = metas;
	}

	public TAlgoritmo(Coordenadas ini, Coordenadas fin, Celda[][] m, ArrayList<Coordenadas> arrayList) {
		this.inicio = ini;
		this.meta = fin;
		this.matriz = m;
		this.metas = arrayList;
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
