/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controlador.Controlador;
import negocio.TTamano;

/**
 *
 * @author Bolil
 */
public class GUITamanoImp extends GUITamano {
	
	private GUITablero GUITablero;

    /**
     * Creates new form interfaz
     */
    public GUITamanoImp() {
    	this.GUITablero = new GUITablero();
        initComponents();
        this.setLocationRelativeTo(null);
        this.jLabelAvisoFilas.setVisible(false);
        this.jLabelAvisoColumnas.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	this.setTitle("Algoritmo A*");
        jButtonCrear = new javax.swing.JButton();
        jButtonCerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFilas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabelAvisoFilas = new javax.swing.JLabel();
        jLabelAvisoColumnas = new javax.swing.JLabel();
        jTextFieldColumnas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabelFont = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonCrear.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jButtonCrear.setForeground(new java.awt.Color(204, 102, 0));
        jButtonCrear.setText("Crear Matriz");
        jButtonCrear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2));
        jButtonCrear.setContentAreaFilled(false);
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 450, -1));

        jButtonCerrar.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jButtonCerrar.setForeground(new java.awt.Color(204, 102, 0));
        jButtonCerrar.setText("Salir");
        jButtonCerrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 0), 2, true));
        jButtonCerrar.setContentAreaFilled(false);
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 450, -1));

        jLabel2.setBackground(new java.awt.Color(204, 102, 0));
        jLabel2.setFont(new java.awt.Font("Bernard MT Condensed", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 102, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/filas.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 400, 50));

        jTextFieldFilas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldFilas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 51, 0)));
        jTextFieldFilas.setSelectionColor(new java.awt.Color(153, 51, 0));
        getContentPane().add(jTextFieldFilas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 210, 50));

        jLabel3.setFont(new java.awt.Font("Old English Text MT", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/columnas.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, 30));

        jLabelAvisoFilas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelAvisoFilas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAvisoFilas.setText("Debe ser un nº mayor que 0");
        getContentPane().add(jLabelAvisoFilas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 210, 20));

        jLabelAvisoColumnas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelAvisoColumnas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAvisoColumnas.setText("Debe ser un nº mayor que 0");
        getContentPane().add(jLabelAvisoColumnas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 210, 20));

        jTextFieldColumnas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldColumnas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 51, 0)));
        getContentPane().add(jTextFieldColumnas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 210, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/titulo.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 710, 70));

        jLabelFont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoSuperMario.jpg"))); // NOI18N
        getContentPane().add(jLabelFont, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {
    	JLabel label = new JLabel("<html><body>Vuelve pronto<center>😄</center></body></html>");
		label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
		JOptionPane.showMessageDialog(null, label, "¡Hasta pronto!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
    }

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {
    	int altura = 0, anchura = 0, nErrores = 0;
    	if(!this.jTextFieldFilas.getText().matches("^([1-9]{1}[0-9]*)$")) {
    		nErrores++;
    		this.jLabelAvisoFilas.setVisible(true);
    	}
    	else {
    		altura = Integer.parseInt(this.jTextFieldFilas.getText());
    		this.jLabelAvisoFilas.setVisible(false);
    	}
    	if(!this.jTextFieldColumnas.getText().matches("^([1-9]{1}[0-9]*)$")) {
    		nErrores++;
    		this.jLabelAvisoColumnas.setVisible(true);
    	}
    	else {
    		anchura = Integer.parseInt(this.jTextFieldColumnas.getText());
    		this.jLabelAvisoColumnas.setVisible(false);
    	}
    	if(nErrores == 0) {
    		Controlador.getInstance().accion(new Contexto(Events.GUI_TABLERO, new TTamano(altura, anchura)));
    	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAvisoColumnas;
    private javax.swing.JLabel jLabelAvisoFilas;
    private javax.swing.JLabel jLabelFont;
    private javax.swing.JTextField jTextFieldColumnas;
    private javax.swing.JTextField jTextFieldFilas;
    // End of variables declaration//GEN-END:variables
    
	@Override
	public void actualizar(Contexto contexto) {
		switch(contexto.getEvento()) {
		case(Events.GUI_TABLERO):
			this.limpiar();
			this.GUITablero.actualizar(contexto);
		break;
		case(Events.GUI_MAIN):
			this.setVisible(true);
		break;
		case(Events.BUSCAR_KO):
			JLabel label = new JLabel("No existe ningún camino");
			label.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
			JOptionPane.showMessageDialog(null, label, "¡GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
		break;
		case(Events.BUSCAR_OK):
			this.GUITablero.pintarCamino(contexto);
		break;
		}
	}

	private void limpiar() {
		this.jTextFieldColumnas.setText("");
		this.jTextFieldFilas.setText("");
		this.setVisible(false);
	}
}
