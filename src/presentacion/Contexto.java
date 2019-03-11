/**
 * 
 */
package presentacion;

/**
 * @author Guillermo Delgado Yepes
 */
public class Contexto {

	private Object dato;

	private int evento;

	public Contexto(int evento, Object dato) {
		this.evento = evento;
		this.dato = dato;
	}

	public Object getDato() {
		return dato;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}

	public int getEvento() {
		return evento;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}


}