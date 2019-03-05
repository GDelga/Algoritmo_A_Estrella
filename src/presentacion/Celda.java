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
	private double penalizacion;
	private double altura;
	
	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	public void crearAltura(double altura) {
		this.altura = (Math.random() * ((altura+500) - (altura/2))) + (altura/2);
		System.out.println(this.altura);
	}

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
	
	public void crearPenalizacion(double penalizacionMinima, double penalizacionMaxima) {
		this.penalizacion = (Math.random() * ((penalizacionMaxima+1) - penalizacionMinima)) + penalizacionMinima;
	}

	public double getPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(double penalizacion) {
		this.penalizacion = penalizacion;
	}
}
