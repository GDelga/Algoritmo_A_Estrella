package negocio;

/**
 * @author Guillermo Delgado Yepes
 */
public class TTamano {
	private int altura;
	private int anchura;
	
	public TTamano (int altura, int anchura) {
		this.altura = altura;
		this.anchura = anchura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAnchura() {
		return anchura;
	}

	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}
}
