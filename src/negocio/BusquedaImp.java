package negocio;

import java.util.ArrayList;

/**
 * @author Guillermo Delgado Yepes
 */
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
			if(!tAlgoritmo.getModoAltura() || (tAlgoritmo.getModoAltura() && tAlgoritmo.getAltura() >= tAlgoritmo.getMatriz()[x - 1][y].getAltura())){
				if(tAlgoritmo.getMatriz()[x - 1][y].getTipo() == Casillas.PENALIZACION) {
					adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x -1, y), 1 + tAlgoritmo.getMatriz()[x - 1][y].getPenalizacion()));
				}
				else if( tAlgoritmo.getMatriz()[x - 1][y].getTipo() != Casillas.BLOQUEADO) {
					adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x -1, y), 1));
				}
			}
		}
		else {
			diagonalArribaDerecha = false;
			diagonalArribaIzquierda = false;
		}
		//Abajo
		if( x < ( tAlgoritmo.getMatriz().length -1)) {
			if(!tAlgoritmo.getModoAltura() || (tAlgoritmo.getModoAltura() && tAlgoritmo.getAltura() >= tAlgoritmo.getMatriz()[x + 1][y].getAltura())){
				if( tAlgoritmo.getMatriz()[x + 1][y].getTipo() == Casillas.PENALIZACION) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y), 1 + tAlgoritmo.getMatriz()[x + 1][y].getPenalizacion()));
				else if( tAlgoritmo.getMatriz()[x + 1][y].getTipo() != Casillas.BLOQUEADO)adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y), 1));
			}
		}
		else {
			diagonalAbajoIzquierda = false;
			diagonalAbajoDerecha = false;
		}
		//Izquierda
		if(y > 0) {
			if(!tAlgoritmo.getModoAltura() || (tAlgoritmo.getModoAltura() && tAlgoritmo.getAltura() >= tAlgoritmo.getMatriz()[x][y -1].getAltura())){
				if(tAlgoritmo.getMatriz()[x][y-1].getTipo() == Casillas.PENALIZACION) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x, y - 1), 1 + tAlgoritmo.getMatriz()[x][y-1].getPenalizacion()));
				else if(tAlgoritmo.getMatriz()[x][y-1].getTipo() != Casillas.BLOQUEADO) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x, y - 1), 1));
			}
		}
		else {
			diagonalAbajoIzquierda = false;
			diagonalArribaIzquierda = false;
		}
		//Derecha
		if(y < ( tAlgoritmo.getMatriz()[x].length - 1)) {
			if(!tAlgoritmo.getModoAltura() || (tAlgoritmo.getModoAltura() && tAlgoritmo.getAltura() >= tAlgoritmo.getMatriz()[x][y + 1].getAltura())){
				if(tAlgoritmo.getMatriz()[x][y + 1].getTipo() == Casillas.PENALIZACION) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x, y + 1), 1 + tAlgoritmo.getMatriz()[x][y + 1].getPenalizacion()));
				else if(tAlgoritmo.getMatriz()[x][y + 1].getTipo() != Casillas.BLOQUEADO) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x, y + 1), 1));
			}
		}
		else {
			diagonalAbajoDerecha = false;
			diagonalArribaDerecha = false;
		}
		//Diagonal Arriba Izquierda
		if(diagonalArribaIzquierda &&  tAlgoritmo.getMatriz()[x - 1][y - 1].getTipo() != Casillas.BLOQUEADO) {
			if(!tAlgoritmo.getModoAltura() || (tAlgoritmo.getModoAltura() && tAlgoritmo.getAltura() >= tAlgoritmo.getMatriz()[x - 1][y - 1].getAltura())){
				if(tAlgoritmo.getMatriz()[x - 1][y - 1].getTipo() == Casillas.PENALIZACION) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x - 1, y - 1), Math.sqrt(2) + tAlgoritmo.getMatriz()[x - 1][y - 1].getPenalizacion()));
				else adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x - 1, y - 1), Math.sqrt(2)));
			}
		}
		//Diagonal Arriba Derecha
		if(diagonalArribaDerecha &&  tAlgoritmo.getMatriz()[x - 1][y + 1].getTipo() != Casillas.BLOQUEADO) {
			if(!tAlgoritmo.getModoAltura() || (tAlgoritmo.getModoAltura() && tAlgoritmo.getAltura() >= tAlgoritmo.getMatriz()[x - 1][y + 1].getAltura())){
				if (tAlgoritmo.getMatriz()[x - 1][y + 1].getTipo() == Casillas.PENALIZACION) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x - 1, y + 1), Math.sqrt(2) + tAlgoritmo.getMatriz()[x - 1][y + 1].getPenalizacion()));
				else adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x - 1, y + 1), Math.sqrt(2)));
			}
		}
		//Diagonal Abajo Izquierda
		if(diagonalAbajoIzquierda &&  tAlgoritmo.getMatriz()[x + 1][y - 1].getTipo() != Casillas.BLOQUEADO) {
			if(!tAlgoritmo.getModoAltura() || (tAlgoritmo.getModoAltura() && tAlgoritmo.getAltura() >= tAlgoritmo.getMatriz()[x + 1][y - 1].getAltura())){
				if(tAlgoritmo.getMatriz()[x + 1][y - 1].getTipo() == Casillas.PENALIZACION)  adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y - 1), Math.sqrt(2) + tAlgoritmo.getMatriz()[x + 1][y - 1].getPenalizacion()));
				else adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y - 1), Math.sqrt(2)));
			}
		}
		//Diagonal Abajo Derecha
		if(diagonalAbajoDerecha &&  tAlgoritmo.getMatriz()[x + 1][y + 1].getTipo() != Casillas.BLOQUEADO) {
			if(!tAlgoritmo.getModoAltura() || (tAlgoritmo.getModoAltura() && tAlgoritmo.getAltura() >= tAlgoritmo.getMatriz()[x + 1][y + 1].getAltura())){
				if(tAlgoritmo.getMatriz()[x + 1][y + 1].getTipo() == Casillas.PENALIZACION) adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y + 1), Math.sqrt(2) + tAlgoritmo.getMatriz()[x + 1][y + 1].getPenalizacion()));
				else adyacentes.add(new Nodo(actual, nodoFinal, new Coordenadas(x + 1, y + 1), Math.sqrt(2)));
			}
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
		Nodo nodoInicial, nodoFinal, actual, savePoint;
		if(tAlgoritmo.getModoAltura()) {
			if(!puedePasar(tAlgoritmo))
				return null;
		}
		nodoFinal = new Nodo(null, null,tAlgoritmo.getCorFinal(),0); //Creo el nodo final
		savePoint = new Nodo(null, null, tAlgoritmo.getMetas().get(0), 0); //Creo mi primer savepoint
		//Creo el nodo inicial que tiene como destino el savepoint
		nodoInicial = new Nodo(null, savePoint, tAlgoritmo.getCorInicio(), 0); 
		int cont = 0; //Contador del savepoint actual
		ArrayList<Nodo> listaAbierta = new ArrayList<>(); //lista de nodos abiertos
		ArrayList<Nodo> listaCerrada = new ArrayList<>(); //lista de nodos cerrados
		ArrayList<Coordenadas> mejorCamino = new ArrayList<>(); //lista del mejor camino
		ArrayList<Coordenadas> caminoActual = new ArrayList<>(); //lista del camino actual
		listaAbierta.add(nodoInicial);// añado el nodo inicial a la lista de abiertos
		ArrayList<Nodo> adyacentes;
		
		while(listaAbierta.size() > 0){
			actual = listaAbierta.get(0);
			if(!actual.equals(nodoFinal) && actual.equals(savePoint)) { 
				//Si mi actual no es el nodo final y hemos llegado a un savepoint
				cont++;
				savePoint = new Nodo(null, null, tAlgoritmo.getMetas().get(cont), 0); //Paso al siguiente savepoint
				nodoInicial = new Nodo(null, savePoint, actual.getCoordenadas(), 0); //Mi inicial es el nodo actual
				while(actual != null) { //Creo el camino actual
					caminoActual.add(actual.getCoordenadas());
					actual = actual.getNodoPadre();
				}
				this.crearCamino(mejorCamino, caminoActual); //Añado el actual al camino total
				caminoActual = new ArrayList<>(); //Reinicializo
				listaAbierta = new ArrayList<>(); //Reinicializo
				listaAbierta.add(nodoInicial); //Añado el nodo inicial a la lista de abiertas
				listaCerrada = new ArrayList<>(); //Reinicializo
			}
			else if(actual.equals(nodoFinal) && actual.equals(savePoint)){ 
				//Si estamos en el nodo final y es nuestro savePoint
				while(actual != null) { //creamos el camino actual
					caminoActual.add(actual.getCoordenadas());
					actual = actual.getNodoPadre();
				}
				this.crearCamino(mejorCamino, caminoActual);//Añado el actual al camino total
				return mejorCamino;
			}
			else {
				listaAbierta.remove(actual);
				adyacentes = encontrarAdyacentes(tAlgoritmo, actual, savePoint);
				for(Nodo i: adyacentes) {
					if(!buscarListaCerrada(listaCerrada, i)) { //Si no está en la cerrada
						Nodo n = this.buscarListaAbierta(listaAbierta,i);
						if(n == null) { //Si no está en la lista de abiertas lo metemos
							añadirNodoAListaAbierta(listaAbierta,i);
						}
						else { //Si ya está en la lista abierta
							if(i.getCosteTotal() < n.getCosteTotal()) {//Reevaluo el coste
								listaAbierta.remove(n); //Borro el nodo de la lista
								añadirNodoAListaAbierta(listaAbierta, i); //Añado el nodo que tiene mejor coste a la lista
							}
						}
					}
				}
				listaCerrada.add(actual);
			}
		}
		return null;
	}

	private boolean puedePasar(TAlgoritmo tAlgoritmo) {
		if(tAlgoritmo.getMatriz()[tAlgoritmo.getCorInicio().getX()][tAlgoritmo.getCorInicio().getY()].getAltura() > tAlgoritmo.getAltura()) {
			return false;
		}
		else {
			for(int i = 0; i < tAlgoritmo.getMetas().size(); ++i) {
				if(tAlgoritmo.getMatriz()[tAlgoritmo.getMetas().get(i).getX()][tAlgoritmo.getMetas().get(i).getY()].getAltura() > tAlgoritmo.getAltura())
					return false;
			}
		}
		return true;
	}

	private void crearCamino(ArrayList<Coordenadas> mejorCamino, ArrayList<Coordenadas> caminoActual) {
		for(int i = caminoActual.size() -1; i >= 0 ;--i) {
			mejorCamino.add(caminoActual.get(i));
		}
	}
	
	
}
