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
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.placeholder.PlaceHolder;

import controlador.Controlador;
import negocio.Casillas;
import negocio.Coordenadas;
import negocio.TAlgoritmo;
import negocio.TTamano;

/**
 * @author Guillermo Delgado Yepes
 */
public class GUITablero extends JFrame implements MouseListener, GUI{
	
	private GUIMatriz guiMatriz;
	private int anchura;
	private int altura;
	private boolean poniendoSavepoint;
	private boolean modoAltura;
		
	public GUITablero() {
		this.guiMatriz = null;
	}

	@Override
	public void actualizar(Contexto contexto) {
		if(this.guiMatriz == null) {
			this.setTitle("Algoritmo A*");
			this.setExtendedState(MAXIMIZED_BOTH);
			this.setLayout(new BorderLayout());
			this.setMinimumSize(new Dimension(1000, 500));
			this.setBackground(Color.ORANGE);
			JPanel panel = new JPanel(new GridLayout(2, 3));
			panel.setBackground(Color.ORANGE);
			panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 3));
			JButton buscar = new JButton("Buscar Camino");
			buscar.setBackground(Color.ORANGE);
			buscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
			buscar.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					if(guiMatriz.getTieneInicio() && guiMatriz.getTieneMeta()){
						modoAltura = false;
						guiMatriz.getSavepoint().add(guiMatriz.getCorFinal());
						TAlgoritmo tAlgoritmo = new TAlgoritmo(guiMatriz.getCorInicio(), guiMatriz.getCorFinal(), guiMatriz.getMatriz(), guiMatriz.getSavepoint(), modoAltura);
						Controlador.getInstance().accion(new Contexto(Events.BUSCAR_CAMINO, tAlgoritmo));
					}
					else {
						JLabel label = new JLabel("<html><body>Tiene que existir un inicio y una meta</body></html>");
						label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
						JOptionPane.showMessageDialog(null, label, "¡Aviso!", JOptionPane.INFORMATION_MESSAGE);
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
			JPanel p2 = new JPanel(new GridLayout(1, 2));
			JButton instrucciones = new JButton("Instrucciones");
			instrucciones.setBackground(Color.ORANGE);
			instrucciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
			instrucciones.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JLabel label = new JLabel("<html><body>1. Buscar Camino: Busca el camino desde el inicio hasta la meta"
							+ "en el modo sin alturas<p>"
							+ "2. Limpiar tablero: Quita todas las casillas ocupadas y caminos pintados<p>"
							+ "3. Cambiar tamaño: Abre la ventana para cambiar la altura y anchura de la matriz<p>"
							+ "4. Poner Savepoint: Al activar esta acción puedes poner todas las metas que quieras en el tablero<p>"
							+ "5. Poner alturas: Introduce la altura máxima por la que puedes pasar e inicia el modo con alturas<p>"
							+ "6. Instrucciones: Muestra las instrucciones<p>"
							+ "7. Salir: Salir de la aplicación</body></html>");
					label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
					JOptionPane.showMessageDialog(null, label, "Instrucciones", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			p2.add(instrucciones);
			JButton salir = new JButton("Salir");
			salir.setBackground(Color.ORANGE);
			salir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
			salir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JLabel label = new JLabel("<html><body>Vuelve pronto<center>😄</center></body></html>");
					label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
					JOptionPane.showMessageDialog(null, label, "¡Hasta pronto!", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			});
			p2.add(salir);
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
						savepoint.setBackground(Color.RED);
					}
					else {
						poniendoSavepoint = false;
						savepoint.setBackground(Color.ORANGE);
						JLabel label = new JLabel("<html><body>Se ha desactivado la opción de poner Savepoint</body></html>");
						label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
						JOptionPane.showMessageDialog(null, label, "¡Aviso!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			JPanel pa = new JPanel(new GridLayout(1, 2));
			pa.setBackground(Color.ORANGE);
			JTextField tex = new JTextField();
			PlaceHolder placeholder = new PlaceHolder(tex, "Introduce altura máxima");
			tex.setHorizontalAlignment(SwingConstants.CENTER);
			JButton altura = new JButton("Buscar con altura");
			altura.setBackground(Color.ORANGE);
			altura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
			altura.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(guiMatriz.getTieneInicio() && guiMatriz.getTieneMeta()){
						int nErrores = 0;
						double alt = 0;
						if(!tex.getText().matches("^([1-9]{1}[0-9]*)$")) {
				    		nErrores++;
				    	}
				    	else {
				    		alt = Double.parseDouble(tex.getText());
				    	}
						if(nErrores ==  0) {
							modoAltura = true;
							guiMatriz.ponerAlturas(alt);
							guiMatriz.getSavepoint().add(guiMatriz.getCorFinal());
							TAlgoritmo tAlgoritmo = new TAlgoritmo(guiMatriz.getCorInicio(), guiMatriz.getCorFinal(), guiMatriz.getMatriz(), guiMatriz.getSavepoint(), modoAltura, alt);
							Controlador.getInstance().accion(new Contexto(Events.BUSCAR_CAMINO, tAlgoritmo));
						}
						else {
							JLabel label = new JLabel("<html><body>Introduce un número positivo mayor que 0</body></html>");
							label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
							JOptionPane.showMessageDialog(null, label, "¡Aviso!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else {
						JLabel label = new JLabel("<html><body>Tiene que existir un inicio y una meta</body></html>");
						label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
						JOptionPane.showMessageDialog(null, label, "¡Aviso!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			buscar.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
			limpiar.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
			tamano.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
			savepoint.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
			salir.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
			altura.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
			tex.setFont(new Font("Harlow Solid Italic", Font.BOLD, 17));
			altura.setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
			instrucciones.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
			pa.add(tex);
			pa.add(altura);
			panel.add(buscar);
			panel.add(limpiar);
			panel.add(tamano);
			panel.add(savepoint);
			panel.add(pa);
			panel.add(p2);
			this.add(panel, BorderLayout.SOUTH);
		}
		this.modoAltura = false;
		this.poniendoSavepoint = false;		
		if(this.guiMatriz != null) this.remove(guiMatriz);
		TTamano tTamano = (TTamano) contexto.getDato();
		this.altura = tTamano.getAltura();
		this.anchura = tTamano.getAnchura();
		this.guiMatriz = new GUIMatriz(this.altura, this.anchura);
		this.add(guiMatriz, BorderLayout.CENTER);
		this.guiMatriz.inicializarLabels(this);
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

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
