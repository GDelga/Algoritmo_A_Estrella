/**
 * 
 */
package dispatcher;

import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUITamano;

public class DispatcherImp extends Dispatcher {

	@Override
	public void generarVista(Contexto contexto) {
		int evento = contexto.getEvento();
		switch(evento) {
		case(Events.GUI_MAIN):
			GUITamano.getInstance().actualizar(contexto);
		break;
		case(Events.BUSCAR_CAMINO):
			GUITamano.getInstance().actualizar(contexto);
		break;
		case(Events.BUSCAR_KO):
			GUITamano.getInstance().actualizar(contexto);
		break;
		case(Events.BUSCAR_OK):
			GUITamano.getInstance().actualizar(contexto);
		break;
		case(Events.GUI_TABLERO):
			GUITamano.getInstance().actualizar(contexto);
		break;
		}
	}
}