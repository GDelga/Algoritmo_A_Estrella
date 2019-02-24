package presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.Controlador;
import negocio.Casillas;
import negocio.Coordenadas;
import negocio.TAlgoritmo;
import negocio.TTamano;

public class GUITablero extends JFrame implements MouseListener, GUI{
	
	private GUIMatriz guiMatriz;
	private int anchura;
	private int altura;
	private boolean buscado;
		
	public GUITablero() {
		this.guiMatriz = null;
	}

	@Override
	public void actualizar(Contexto contexto) {
		if(this.guiMatriz != null) this.remove(guiMatriz);
		TTamano tTamano = (TTamano) contexto.getDato();
		this.altura = tTamano.getAltura();
		this.anchura = tTamano.getAnchura();
		this.guiMatriz = new GUIMatriz(this.altura, this.anchura);
		this.setLayout(new BorderLayout());
		this.add(guiMatriz, BorderLayout.CENTER);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.guiMatriz.inicializarEscuchadores(this);
		JPanel panel = new JPanel(new GridLayout(1, 4));
		JButton buscar = new JButton("Buscar Camino");
		buscar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				TAlgoritmo tAlgoritmo = crearMatriz();
				Controlador.getInstance().accion(new Contexto(Events.BUSCAR_CAMINO, tAlgoritmo));
			}
		});
		JButton limpiar = new JButton("Limpiar Tablero");
		limpiar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				guiMatriz.limpiarTablero();
			}
		});
		JButton tamano = new JButton("Cambiar Tamaño");
		tamano.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstance().accion(new Contexto(Events.GUI_MAIN, null));
			}
		});
		JButton salir = new JButton("Salir");
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(buscar);
		panel.add(limpiar);
		panel.add(tamano);
		panel.add(salir);
		this.add(panel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public void pintarCamino(Contexto contexto) {
		ArrayList<Coordenadas> caminoMinimo = (ArrayList<Coordenadas>) contexto.getDato();
		for(int i = 0; i < caminoMinimo.size(); ++i) {
			this.guiMatriz.pintarCeldaCamino(caminoMinimo.get(i).getX(), caminoMinimo.get(i).getY());
		}
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
