package controlador;

import command.Command;
import command.FactoriaCommand;
import dispatcher.Dispatcher;
import presentacion.Contexto;

public class ControladorImp extends Controlador {

	@Override
	public void accion(Contexto contexto) {
		Command command = FactoriaCommand.getInstance().generarComando(contexto.getEvento());
		Contexto contextoResult = null;
		if (command != null) {
			contextoResult = command.execute(contexto.getDato());
			Dispatcher.getInstance().generarVista(contextoResult);
		}
		else Dispatcher.getInstance().generarVista(contexto);
	}

}
