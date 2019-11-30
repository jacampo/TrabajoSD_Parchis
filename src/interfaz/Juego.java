package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Juego{

	private InterfazPrincipal interfaz;
	private int ancho, alto;
	private String titulo;
	private BufferedImage imagen;
	private Graphics g;
	private boolean recargando = true;
	private BufferStrategy buffSt;
	
	public Juego(String titulo, int ancho, int alto){
		
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto = alto;
		
		this.interfaz = new InterfazPrincipal(this.titulo, this.ancho, this.alto);
		//Guardamos la imagen para dibujarla despues
		try {
			this.imagen = ImageIO.read(new File("Tablero_parchis.png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		//Aumentamos el numero de buffers a utilizar para poder dibujar más rapido
		this.interfaz.getCanvas().createBufferStrategy(3);
		this.actualizar();
	}
	
	int p1=0,p2=0;
	//Se llamara al metodo para realizar los cambios y volver a dibujar
	private void actualizar() {		
		//Cambiamos los valores
		
	
		while(recargando) {
			p1++;
			p2++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//Dibujamos
			this.buffSt = this.interfaz.getCanvas().getBufferStrategy();
			this.g = this.buffSt.getDrawGraphics();
			this.g.clearRect(0, 0, ancho, alto); 
			
			this.g.setColor(Color.RED);
			
			
			this.g.drawImage(this.imagen,0,0,null);
			this.g.drawString("dfgdfgdfgdf", 0, 0);
			this.g.fillOval(p1, p2,15, 15);
			
			this.buffSt.show();
			this.g.dispose();
		
		}
	}
	

}
