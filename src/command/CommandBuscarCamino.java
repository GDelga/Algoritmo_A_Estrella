package command;

import java.util.ArrayList;

import negocio.Coordenadas;
import negocio.FactoriaNegocio;
import negocio.TAlgoritmo;
import presentacion.Contexto;
import presentacion.Events;

/**
 * @author Guillermo Delgado Yepes
 */
public class CommandBuscarCamino implements Command {

	@Override
	public Contexto execute(Object dato) {
		TAlgoritmo tAlgoritmo = (TAlgoritmo) dato;
		ArrayList<Coordenadas> res = FactoriaNegocio.getInstance().crearBusqueda().encontrarCamino(tAlgoritmo);
		if(res == null) return new Contexto(Events.BUSCAR_KO, null);
		else return new Contexto(Events.BUSCAR_OK, res);
	}



}
