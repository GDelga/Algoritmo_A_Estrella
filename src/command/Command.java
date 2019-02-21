package command;

import presentacion.Contexto;

public interface Command {

	public Contexto execute(Object dato);
}