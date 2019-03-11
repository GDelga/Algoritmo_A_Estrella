/**
 * 
 */
package command;

import presentacion.Events;

/**
 * @author Guillermo Delgado Yepes
 */
public class FactoriaCommandImp extends FactoriaCommand {

	@Override
	public Command generarComando(int evento) {
		Command command = null;
		switch(evento){
			case(Events.BUSCAR_CAMINO):
				command = new CommandBuscarCamino();
			break;
		}
		return command;
	
	}
}