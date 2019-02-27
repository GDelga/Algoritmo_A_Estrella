package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controlador.Controlador;
import negocio.Casillas;
import negocio.Coordenadas;
import negocio.TAlgoritmo;
import negocio.TTamano;

public class GUITablero extends JFrame implements MouseListener, GUI{
	
	private GUIMatriz guiMatriz;
	private int anchura;
	private int altura;
	private boolean poniendoSavepoint;
		
	public GUITablero() {
		this.guiMatriz = null;
	}

	@Override
	public void actualizar(Contexto contexto) {
		this.poniendoSavepoint = false;
		this.setTitle("Algoritmo A*");
		if(this.guiMatriz != null) this.remove(guiMatriz);
		TTamano tTamano = (TTamano) contexto.getDato();
		this.altura = tTamano.getAltura();
		this.anchura = tTamano.getAnchura();
		this.guiMatriz = new GUIMatriz(this.altura, this.anchura);
		this.setLayout(new BorderLayout());
		this.add(guiMatriz, BorderLayout.CENTER);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1000, 500));
		this.guiMatriz.inicializarEscuchadores(this);
		JPanel panel = new JPanel(new GridLayout(1, 5));
		panel.setBackground(Color.ORANGE);
		panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 3));
		JButton buscar = new JButton("Buscar Camino");
		buscar.setBackground(Color.ORANGE);
		buscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
		buscar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(guiMatriz.getTieneInicio() && guiMatriz.getTieneMeta()){
					guiMatriz.getSavepoint().add(guiMatriz.getCorFinal());
					TAlgoritmo tAlgoritmo = new TAlgoritmo(guiMatriz.getCorInicio(), guiMatriz.getCorFinal(), guiMatriz.getMatriz(), guiMatriz.getSavepoint());
					Controlador.getInstance().accion(new Contexto(Events.BUSCAR_CAMINO, tAlgoritmo));
				}
			}
		});
		JButton limpiar = new JButton("Limpiar Tablero");
		limpiar.setBackground(Color.ORANGE);
		limpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
		limpiar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				poniendoSavepoint = false;
				guiMatriz.limpiarTablero();
			}
		});
		JButton tamano = new JButton("Cambiar Tamaño");
		tamano.setBackground(Color.ORANGE);
		tamano.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
		tamano.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstance().accion(new Contexto(Events.GUI_MAIN, null));
				
			}
		});
		JButton salir = new JButton("Salir");
		salir.setBackground(Color.ORANGE);
		salir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JButton savepoint = new JButton("Poner Savepoint");
		savepoint.setBackground(Color.ORANGE);
		savepoint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
		savepoint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!poniendoSavepoint) {
					if(!guiMatriz.getSavepoint().isEmpty()) guiMatriz.setSavepoint(new ArrayList<>());
					JLabel label = new JLabel("<html><body>Ahora puedes poner todos los savepoints que quieras<br>ten en cuenta que el orden en el que los pones"
							+ "<br>es en el orden en el que van a recorrerse.<br>No puedes quitarlos ni añadir más."
							+ "<br>Vuelve a pulsar este botón para dejar de poner Savepoints</body></html>");
					label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
					JOptionPane.showMessageDialog(null, label, "¡Aviso!", JOptionPane.INFORMATION_MESSAGE);
					poniendoSavepoint = true;
				}
				else poniendoSavepoint = false;
			}
		});
		buscar.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		limpiar.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		tamano.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		savepoint.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		salir.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		panel.add(buscar);
		panel.add(limpiar);
		panel.add(tamano);
		panel.add(savepoint);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		Celda celda = (Celda) e.getSource();
		if (poniendoSavepoint) {
			 if(celda.getTipo() == Casillas.LIBRE) {
				 this.guiMatriz.ponerSavePoint(celda.getFila(), celda.getColumna());
			 }
			 else {
				JLabel label = new JLabel("No puedes poner un savepoint en una celda ocupada");
				label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
				JOptionPane.showMessageDialog(null, label, "¡Aviso!", JOptionPane.INFORMATION_MESSAGE);
			 }
		}
		else if(celda.getTipo() == Casillas.LIBRE) {
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
			this.guiMatriz.pintarPenalizacion(celda.getFila(), celda.getColumna());
		}
		else if(celda.getTipo() == Casillas.PENALIZACION) {
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
