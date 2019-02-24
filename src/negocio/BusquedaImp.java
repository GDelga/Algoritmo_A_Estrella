package negocio;

import java.util.ArrayList;

public class BusquedaImp implements Busqueda {

	private ArrayList<Nodo> encontrarAdyacentes(TAlgoritmo tAlgoritmo, Nodo actual, Nodo nodoFinal){
		ArrayList<Nodo> adyacentes = new ArrayList<>();
		Boolean diagonalArribaIzquierda = true;
		Boolean diagonalAbajoIzquierda = true;
		Boolean diagonalArribaDerecha = true;
		Boolean diagonalAbajoDerecha = true;
		int x = actual.getCoordenadas().getX();
		int y = actual.getCoordenadas().getY();
		//Arriba
		if(x > 0) {
			if( tAlgoritmo.getMatriz()[x - 1][y] != Casillas.BLOQUEADO)adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x -1, y), 1));
		}
		else {
			diagonalArribaDerecha = false;
			diagonalArribaIzquierda = false;
		}
		//Abajo
		if( x < ( tAlgoritmo.getMatriz().length -1)) {
			if( tAlgoritmo.getMatriz()[x + 1][y] != Casillas.BLOQUEADO) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y), 1));
		}
		else {
			diagonalAbajoIzquierda = false;
			diagonalAbajoDerecha = false;
		}
		//Izquierda
		if(y > 0) {
			if(tAlgoritmo.getMatriz()[x][y-1] != Casillas.BLOQUEADO) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x, y - 1), 1));
		}
		else {
			diagonalAbajoIzquierda = false;
			diagonalArribaIzquierda = false;
		}
		//Derecha
		if(y < ( tAlgoritmo.getMatriz()[x].length - 1)) {
			if(tAlgoritmo.getMatriz()[x][y + 1] != Casillas.BLOQUEADO)adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x, y + 1), 1));
		}
		else {
			diagonalAbajoDerecha = false;
			diagonalArribaDerecha = false;
		}
		//Diagonal Arriba Izquierda
		if(diagonalArribaIzquierda &&  tAlgoritmo.getMatriz()[x - 1][y - 1] != Casillas.BLOQUEADO) {
			adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x - 1, y - 1), Math.sqrt(2)));
		}
		//Diagonal Arriba Derecha
		if(diagonalArribaDerecha &&  tAlgoritmo.getMatriz()[x - 1][y + 1] != Casillas.BLOQUEADO) {
			adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x - 1, y + 1), Math.sqrt(2)));
		}
		//Diagonal Abajo Izquierda
		if(diagonalAbajoIzquierda &&  tAlgoritmo.getMatriz()[x + 1][y - 1] != Casillas.BLOQUEADO) {
			adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y - 1), Math.sqrt(2)));
		}
		//Diagonal Abajo Derecha
		if(diagonalAbajoDerecha &&  tAlgoritmo.getMatriz()[x + 1][y + 1] != Casillas.BLOQUEADO) {
			adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y + 1), Math.sqrt(2)));
		}
		return adyacentes;
	}
	
	private void añadirNodoAListaAbierta(ArrayList<Nodo> listaAbierta, Nodo actual) {
		int i = 0;
		boolean encontrado = false;
		double coste = actual.getCosteTotal();
		while(i < listaAbierta.size() && !encontrado) {
			if(coste < listaAbierta.get(i).getCosteTotal()) encontrado = true;
			else ++i;
		}
		listaAbierta.add(i,actual);
	}
	
	private Nodo buscarListaAbierta(ArrayList<Nodo> listaAbierta, Nodo n) {
		for(int i = 0; i < listaAbierta.size(); ++i) {
			if(listaAbierta.get(i).equals(n)) return listaAbierta.get(i);
		}
		return null;
	}
	
	private boolean buscarListaCerrada(ArrayList<Nodo> listaCerrada, Nodo n) {
		for(int i = 0; i < listaCerrada.size(); ++i) {
			if(listaCerrada.get(i).equals(n)) return true;
		}
		return false;
	}
	
	public ArrayList<Coordenadas> encontrarCamino(TAlgoritmo tAlgoritmo) {
		Nodo nodoInicial, nodoFinal, actual;
		nodoFinal = new Nodo(null, null,tAlgoritmo.getCorFinal(),0);
		nodoInicial = new Nodo(null, nodoFinal, tAlgoritmo.getCorInicio(), 0);
		ArrayList<Nodo> listaAbierta = new ArrayList<>();
		ArrayList<Nodo> listaCerrada = new ArrayList<>();
		listaAbierta.add(nodoInicial);
		ArrayList<Nodo> adyacentes;
		while(listaAbierta.size() > 0 && !buscarListaCerrada(listaCerrada, nodoFinal)){
			actual = listaAbierta.get(0);
			//Si es el nodoFinal
			if(actual.equals(nodoFinal)){
				ArrayList<Coordenadas> mejorCamino = new ArrayList<>();
				while(actual != null) {
					mejorCamino.add(actual.getCoordenadas());
					actual = actual.getNodoPadre();
				}
				return mejorCamino; //LLamada al controlador
			}
			else {
				listaAbierta.remove(actual);
				adyacentes = encontrarAdyacentes(tAlgoritmo, actual, nodoFinal);
				for(Nodo i: adyacentes) {
					if(!buscarListaCerrada(listaCerrada, i)) { //Si no está en la cerrada
						Nodo n = this.buscarListaAbierta(listaAbierta,i);
						if(n == null) { //Si no está en la lista de abiertas lo metemos
							añadirNodoAListaAbierta(listaAbierta,i);
						}
						else { //Si ya está en la lista abierta
							if(i.getCosteTotal() < n.getCosteTotal()) {//Reevaluo el coste
								listaAbierta.remove(n);
								añadirNodoAListaAbierta(listaAbierta, i);
							}
						}
					}
				}
				listaCerrada.add(actual);
			}
		}
		return null;
	}
	
	
}
