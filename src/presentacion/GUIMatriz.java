package presentacion;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import negocio.Casillas;
import negocio.Coordenadas;


public class GUIMatriz extends JPanel{

	private Celda[][] panel;
	private int altura;
	private int anchura;
	private ImageIcon libre, inicio, meta, bloqueado, camino, caja, penalizacion;
	private boolean tieneInicio;
	private boolean tieneMeta;
	private double penalizacionMaxima;
	private double penalizacionMinima;
	private Coordenadas corInicio;
	private Coordenadas corFinal;
	
	public GUIMatriz(int altura, int anchura){
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
		this.camino = new ImageIcon(getClass().getResource("/imagenes/suelo.jpg"));
		this.caja = new ImageIcon(getClass().getResource("/imagenes/cajita.gif"));
		Image c = caja.getImage();
		this.caja = new ImageIcon(c.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.penalizacion = new ImageIcon(getClass().getResource("/imagenes/plantaPirana.gif"));
		Image p = penalizacion.getImage();
		this.penalizacion = new ImageIcon(p.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.penalizacionMaxima = Math.sqrt(Math.pow(altura, 2) + Math.pow(anchura, 2));
		this.penalizacionMinima = this.penalizacionMaxima / 2;
		this.corInicio = null;
		this.corFinal = null;
		inicializarPanel();
	}

	private void inicializarPanel() {
		this.setName("Tablero");
		panel= new Celda[altura][anchura];
		this.setLayout(new GridLayout(altura, anchura)); //ordenacion de celdas alto x ancho
		//INICIALIZACION DE LA TABLA.
		for( int x=0; x< altura;x++){
			for (int y=0; y< anchura;y++){
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
		for(int x=0;x<altura;x++){
			for(int y=0; y<anchura;y++){
				this.panel[x][y].addMouseListener(e);;
				this.panel[x][y].setName("Map");
				this.panel[x][y].setFila(x);
				this.panel[x][y].setColumna(y);
			}
		}
	}
	
	public void pintarCeldaCamino(int x, int y){		
		if(this.panel[x][y].getTipo() == Casillas.LIBRE) {
			this.panel[x][y].addFoto(camino, caja);
		}
		else if(this.panel[x][y].getTipo() == Casillas.INICIO) {
			this.panel[x][y].addFoto(camino, inicio);
		}
		else if(this.panel[x][y].getTipo() == Casillas.FINAL) {
			this.panel[x][y].addFoto(camino, meta);
		}
		else if(this.panel[x][y].getTipo() == Casillas.PENALIZACION) {
			this.panel[x][y].addFoto(camino, penalizacion);
		}
	}
	
	public void pintarCeldaNormal(int x, int y){		
		this.panel[x][y].setFoto(this.libre);
		this.panel[x][y].setTipo(Casillas.LIBRE);
	}
	
	public void pintarObstaculo(int x, int y){		
		this.panel[x][y].addFoto(this.libre, this.bloqueado);
		this.panel[x][y].setTipo(Casillas.BLOQUEADO);
	}
	
	public void pintarPenalizacion(int x, int y){		
		this.panel[x][y].addFoto(this.libre, this.penalizacion);
		this.panel[x][y].setTipo(Casillas.PENALIZACION);
		this.panel[x][y].crearPenalizacion(penalizacionMinima, penalizacionMaxima);
	}

	public void pintarFinal(int xFinal, int yFinal) {
		this.panel[xFinal][yFinal].addFoto(this.libre, this.meta);
		this.panel[xFinal][yFinal].setTipo(Casillas.FINAL);
		this.tieneMeta = true;
		this.corFinal = new Coordenadas(xFinal, yFinal);
	}
	
	public Coordenadas getCorInicio() {
		return corInicio;
	}

	public void setCorInicio(Coordenadas corInicio) {
		this.corInicio = corInicio;
	}

	public Coordenadas getCorFinal() {
		return corFinal;
	}

	public void setCorFinal(Coordenadas corFinal) {
		this.corFinal = corFinal;
	}

	public void pintarInicio(int x, int y) {
		this.panel[x][y].addFoto(this.libre, this.inicio);
		this.panel[x][y].setTipo(Casillas.INICIO);
		this.tieneInicio = true;
		this.corInicio = new Coordenadas(x, y);
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
	
	public void limpiarTablero() {
		for( int x=0; x< altura;x++){
			for (int y=0; y< anchura;y++){
				this.panel[x][y].setFoto(this.libre);
				this.panel[x][y].setTipo(Casillas.LIBRE);
				this.panel[x][y].setToolTipText(Integer.toString(x + 1) + " , " + Integer.toString(y + 1));
				this.panel[x][y].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
			}
		}
		tieneInicio = false;
		tieneMeta = false;
	}
	
	public Celda[][] getMatriz() {
		return this.panel;
	}
}
