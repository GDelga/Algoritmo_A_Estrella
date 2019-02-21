package main;

import controlador.Controlador;
import presentacion.Contexto;
import presentacion.Events;

public class Main {

	public static void main(String[] args) {
		Controlador.getInstance().accion(new Contexto(Events.GUI_MAIN, null) );
	}

}