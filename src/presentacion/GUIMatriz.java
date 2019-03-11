package presentacion;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import negocio.Casillas;
import negocio.Coordenadas;

/**
 * @author Guillermo Delgado Yepes
 */
public class GUIMatriz extends JPanel{

	private Celda[][] panel;
	private int altura;
	private int anchura;
	private ImageIcon libre, inicio, meta, bloqueado, camino, caja, penalizacion, bandera;
	private boolean tieneInicio;
	private boolean tieneMeta;
	private double penalizacionMaxima;
	private double penalizacionMinima;
	private Coordenadas corInicio;
	private Coordenadas corFinal;
	private ArrayList<Coordenadas> savepoint;
	private double alturaCasillas;
	
	public GUIMatriz(int altura, int anchura){
		this.setBackground(Color.ORANGE);
		this.tieneInicio = false;
		this.tieneMeta = false;
		this.anchura = anchura;
		this.altura = altura;
		this.libre = new ImageIcon(getClass().getResource("/imagenes/hierba.png"));
		this.inicio = new ImageIcon(getClass().getResource("/imagenes/mario3.png"));
		Image m = this.inicio.getImage();
		this.inicio = new ImageIcon(m.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.meta = new ImageIcon(getClass().getResource("/imagenes/peach2.gif"));
		Image f = this.meta.getImage();
		this.meta = new ImageIcon(f.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.bloqueado = new ImageIcon(getClass().getResource("/imagenes/bowser.gif"));
		Image b = bloqueado.getImage();
		this.bloqueado = new ImageIcon(b.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.camino = new ImageIcon(getClass().getResource("/imagenes/suelo.png"));
		this.caja = new ImageIcon(getClass().getResource("/imagenes/cajita.gif"));
		Image c = caja.getImage();
		this.caja = new ImageIcon(c.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.penalizacion = new ImageIcon(getClass().getResource("/imagenes/plantaPirana.gif"));
		Image p = penalizacion.getImage();
		this.penalizacion = new ImageIcon(p.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.penalizacionMaxima = (Math.sqrt(Math.pow(altura, 2) + Math.pow(anchura, 2)))*80/100;
		this.penalizacionMinima = this.penalizacionMaxima / 2;
		this.corInicio = null;
		this.corFinal = null;
		this.bandera = new ImageIcon(getClass().getResource("/imagenes/castle.gif"));
		Image band = bandera.getImage();
		this.bandera = new ImageIcon(band.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		this.savepoint = new ArrayList<>();
		inicializarPanel();
	}

	private void inicializarPanel() {
		this.setName("Tablero");
		panel= new Celda[altura][anchura];
		this.setLayout(new GridLayout(altura, anchura));
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
	
	//Inicializa los labels
	public void inicializarLabels(MouseListener e){
		for(int x=0;x<altura;x++){
			for(int y=0; y<anchura;y++){
				this.panel[x][y].addMouseListener(e);;
				this.panel[x][y].setName("Map");
				this.panel[x][y].setFila(x);
				this.panel[x][y].setColumna(y);
			}
		}
	}
	
	public void pintarInicio(int x, int y) {
		this.panel[x][y].addFoto(this.libre, this.inicio);
		this.panel[x][y].setTipo(Casillas.INICIO);
		this.tieneInicio = true;
		this.corInicio = new Coordenadas(x, y);
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
		else if(this.panel[x][y].getTipo() == Casillas.SAVEPOINT) {
			this.panel[x][y].addFoto(camino, bandera);
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
	
	
	public Celda getCelda(int x, int y) {
		return panel[x][y];
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
		this.savepoint = new ArrayList<>();
	}
	
	public Celda[][] getMatriz() {
		return this.panel;
	}

	public void ponerSavePoint(int fila, int columna) {
		this.panel[fila][columna].setTipo(Casillas.SAVEPOINT);
		savepoint.add(new Coordenadas(fila, columna));
		this.panel[fila][columna].addFoto(this.libre, this.bandera);
	}

	public double getAlturaCasillas() {
		return alturaCasillas;
	}

	public void setAlturaCasillas(double alturaCasillas) {
		this.alturaCasillas = alturaCasillas;
	}
	
	public ArrayList<Coordenadas> getSavepoint() {
		return savepoint;
	}

	public void setSavepoint(ArrayList<Coordenadas> savepoint) {
		this.savepoint = savepoint;
	}


	public void ponerAlturas(double alt) {
		this.alturaCasillas = alt;
		for( int x=0; x< altura;x++){
			for (int y=0; y< anchura;y++){
				this.panel[x][y].crearAltura(alturaCasillas);
			}
		}
	}
	
	
}
