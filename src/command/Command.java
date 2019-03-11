package command;

import presentacion.Contexto;

/**
 * @author Guillermo Delgado Yepes
 */
public interface Command {

	public Contexto execute(Object dato);
}