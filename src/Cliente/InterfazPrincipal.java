package Cliente;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;


public class InterfazPrincipal {
	private String titulo;
	private int ancho, alto;
	private JFrame pantalla;
	private Canvas dibujo;
	
	
	public InterfazPrincipal(String titulo, int ancho, int alto) {
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto = alto;
		this.crearPantalla();
	}
	
	public void crearPantalla() {
		
		//Crear pantalla principal, JFrame, para añadir dentro los componentes
		this.pantalla = new JFrame(this.titulo);
		//this.pantalla.setLayout(new FlowLayout());
		this.pantalla.setSize(this.ancho, this.alto);
		this.pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pantalla.setResizable(false);
		this.pantalla.setLocationRelativeTo(null);
		this.pantalla.setVisible(true);
		
		
		//Creamos el canvas, donde dibujar la imagen del tablero y las fichas 
		this.dibujo = new Canvas();
		this.dibujo.setPreferredSize(new Dimension(this.ancho, this.alto));
		this.dibujo.setMaximumSize(new Dimension(this.ancho, this.alto));
		this.dibujo.setMinimumSize(new Dimension(this.ancho, this.alto));	
		this.dibujo.setVisible(true);	
		this.dibujo.setFocusable(false);
		this.dibujo.setBackground(Color.black);
		
		
		/*JPanel panel = new JPanel();
		panel.add(new JButton("Holaa"));
		this.pantalla.add(panel);*/
		
		
		
		this.pantalla.add(this.dibujo);
		this.pantalla.pack();
	}
	
	public Canvas getCanvas() {
		return this.dibujo;
	}
}
