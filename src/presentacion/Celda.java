package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import negocio.Casillas;

public class Celda extends JLabel{
	
	private int fila;
	private int columna;
	private Casillas tipo;
	
	public Celda(){
		
	}

	public void setFoto(ImageIcon i){
		this.removeAll();
		this.setIcon(i);
		this.updateUI();
	}
	
	public void addFoto(ImageIcon fondo, ImageIcon i){
		this.removeAll();
		this.setIcon(fondo);
		this.setLayout(new BorderLayout());
		JLabel l = new JLabel(i);
		this.add(l,BorderLayout.CENTER);
		this.updateUI();
	}
	
	public int getColumna() {
		return columna;
	}
	
	public void setColumna(int columna) {
		this.columna = columna;
	}
	
	public int getFila() {
		return fila;
	}
	
	public void setFila(int fila) {
		this.fila = fila;
	}
	
	public void setTipo(Casillas c) {
		this.tipo = c;
	}
	
	public Casillas getTipo() {
		return this.tipo;
	}
}