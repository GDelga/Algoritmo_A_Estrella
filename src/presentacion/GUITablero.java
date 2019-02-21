package presentacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import negocio.Casillas;
import negocio.Coordenadas;
import negocio.TAlgoritmo;
import negocio.TTamano;

public class GUITablero extends JFrame implements MouseListener, GUI{
	
	private GUIMatriz guiMatriz;
	private int anchura;
	private int altura;
		
	public GUITablero() {
	}

	@Override
	public void actualizar(Contexto contexto) {
		TTamano tTamano = (TTamano) contexto.getDato();
		this.altura = tTamano.getAltura();
		this.anchura = tTamano.getAnchura();
		this.guiMatriz = new GUIMatriz(this.anchura, this.altura);
		this.add(guiMatriz);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.guiMatriz.inicializarEscuchadores(this);
		this.setVisible(true);
	}

	public TAlgoritmo crearMatriz() {
		Casillas matriz[][] = new Casillas[altura][anchura];
		Coordenadas ini = null, fin = null;
		for(int i = 0; i < altura; ++i) {
			for(int j = 0; j < anchura; ++j) {
				if(this.guiMatriz.getCelda(i, j).getTipo() == Casillas.LIBRE) {
					matriz[i][j] = Casillas.LIBRE;
				}
				else if(this.guiMatriz.getCelda(i, j).getTipo() == Casillas.BLOQUEADO) {
					matriz[i][j] = Casillas.BLOQUEADO;
				}
				else if(this.guiMatriz.getCelda(i, j).getTipo() == Casillas.INICIO) {
					matriz[i][j] = Casillas.INICIO;
					ini = new Coordenadas(i, j);
				}
				else if(this.guiMatriz.getCelda(i, j).getTipo() == Casillas.FINAL) {
					matriz[i][j] = Casillas.FINAL;
					fin = new Coordenadas(i, j);
				}
			}
		}
		return new TAlgoritmo(ini, fin, matriz);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Celda celda = (Celda) e.getSource();
		if(celda.getTipo() == Casillas.LIBRE) {
			if(!this.guiMatriz.getTieneInicio()) {
				this.guiMatriz.pintarInicio(celda.getFila(), celda.getColumna());
			}
			else if(!this.guiMatriz.getTieneMeta()) {
				this.guiMatriz.pintarFinal(celda.getFila(), celda.getColumna());
			}
			else this.guiMatriz.pintarObstaculo(celda.getFila(), celda.getColumna());
		}
		else if(celda.getTipo() == Casillas.INICIO) {
			this.guiMatriz.setTieneInicio(false);
			if(!this.guiMatriz.getTieneMeta()) {
				this.guiMatriz.pintarFinal(celda.getFila(), celda.getColumna());
				this.guiMatriz.setTieneMeta(false);
			}
			else this.guiMatriz.pintarObstaculo(celda.getFila(), celda.getColumna());
		}
		else if(celda.getTipo() == Casillas.FINAL) {
			this.guiMatriz.setTieneMeta(false);
			this.guiMatriz.pintarObstaculo(celda.getFila(), celda.getColumna());
		}
		else if(celda.getTipo() == Casillas.BLOQUEADO) {
			this.guiMatriz.pintarCeldaNormal(celda.getFila(), celda.getColumna());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
