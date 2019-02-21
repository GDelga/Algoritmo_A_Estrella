package presentacion;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import negocio.Casillas;


public class GUIMatriz extends JPanel{

	private Celda[][] panel;
	private int altura;
	private int anchura;
	private ImageIcon libre, inicio, meta, bloqueado;
	private boolean tieneInicio;
	private boolean tieneMeta;
	
	public GUIMatriz(int anchura, int altura){
		this.tieneInicio = false;
		this.tieneMeta = false;
		this.anchura = anchura;
		this.altura = altura;
		this.libre = new ImageIcon(getClass().getResource("/imagenes/hierba.jpg"));
		this.inicio = new ImageIcon(getClass().getResource("/imagenes/mario3.png"));
		Image m = this.inicio.getImage();
		this.inicio = new ImageIcon(m.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.meta = new ImageIcon(getClass().getResource("/imagenes/peach2.gif"));
		Image f = this.meta.getImage();
		this.meta = new ImageIcon(f.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.bloqueado = new ImageIcon(getClass().getResource("/imagenes/bowser.gif"));
		Image b = bloqueado.getImage();
		this.bloqueado = new ImageIcon(b.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		inicializarPanel();
	}

	private void inicializarPanel() {
		this.setName("Tablero");
		panel= new Celda[anchura][altura];
		this.setLayout(new GridLayout(anchura, altura)); //ordenacion de celdas alto x ancho
		//INICIALIZACION DE LA TABLA.
		for( int x=0; x< anchura;x++){
			for (int y=0; y< altura;y++){
				this.panel[x][y] = new Celda();
				this.add(panel[x][y]);
				this.panel[x][y].setFoto(this.libre);
				this.panel[x][y].setTipo(Casillas.LIBRE);
				this.panel[x][y].setToolTipText(Integer.toString(x + 1) + " , " + Integer.toString(y + 1));
				this.panel[x][y].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
			}
		}
	}
	
	//Inicializa los escuchadores de los botones de la matriz
	public void inicializarEscuchadores(MouseListener e){
		for(int x=0;x<anchura;x++){
			for(int y=0; y<altura;y++){
				this.panel[x][y].addMouseListener(e);;
				this.panel[x][y].setName("Map");
				this.panel[x][y].setFila(x);
				this.panel[x][y].setColumna(y);
			}
		}
	}
	
	public void pintarCeldaCamino(int x, int y){		
		this.panel[x][y].setFoto(this.bloqueado);
	}
	
	public void pintarCeldaNormal(int x, int y){		
		this.panel[x][y].setFoto(this.libre);
		this.panel[x][y].setTipo(Casillas.LIBRE);
	}
	
	public void pintarObstaculo(int x, int y){		
		this.panel[x][y].addFoto(this.libre, this.bloqueado);
		this.panel[x][y].setTipo(Casillas.BLOQUEADO);
	}

	public void pintarFinal(int xFinal, int yFinal) {
		this.panel[xFinal][yFinal].addFoto(this.libre, this.meta);
		this.panel[xFinal][yFinal].setTipo(Casillas.FINAL);
		this.tieneMeta = true;
	}
	
	public void pintarInicio(int x, int y) {
		this.panel[x][y].addFoto(this.libre, this.inicio);
		this.panel[x][y].setTipo(Casillas.INICIO);
		this.tieneInicio = true;
	}

	/**
	 * @return the altura
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * @return the anchura
	 */
	public int getAnchura() {
		return anchura;
	}

	/**
	 * @param anchura the anchura to set
	 */
	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}
	
	
	public Celda getCelda(int x, int y) {
		return panel[x][y];
	}
	
	public ImageIcon getLibre() {
		return this.libre;
	}

	public boolean getTieneInicio() {
		return this.tieneInicio;
	}

	public boolean getTieneMeta() {
		return this.tieneMeta;
	}

	public void setTieneInicio(boolean b) {
		this.tieneInicio = b;
	}

	public void setTieneMeta(boolean b) {
		this.tieneMeta = b;
	}

	public ImageIcon getInicio() {
		return this.inicio;
	}
	
	public ImageIcon getBloqueo() {
		return this.bloqueado;
	}
	
	public ImageIcon getFinal() {
		return this.meta;
	}
	
}
