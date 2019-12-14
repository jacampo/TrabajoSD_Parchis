package juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class Partida {

	//variables juego
	private Tablero tablero;
	private Vector<Jugador> jugadores;
	private Dado dado;
	
	//variables red
	private Vector<Socket> sockets;
	private Vector<BufferedReader> leer;
	private Vector<BufferedWriter> escribir;
	
	public Partida(Vector<Socket> v){
		//iniciar variables juego
		this.tablero = new Tablero();
		this.jugadores = new Vector<>(4);
		this.jugadores.add(new Jugador(Color.Amarillo, tablero));
		this.jugadores.add(new Jugador(Color.Azul, tablero));
		this.jugadores.add(new Jugador(Color.Rojo, tablero));
		this.jugadores.add(new Jugador(Color.Verde, tablero));
		this.dado = new Dado(6);
		
		
		//iniciar variables red
		this.sockets = v;
		
		this.leer = new Vector<BufferedReader>();
		this.escribir = new Vector<BufferedWriter>();
		try {
			for(int i=0; i<4; i++) {
				this.leer.add(new BufferedReader(new InputStreamReader(this.sockets.get(i).getInputStream())));
				this.escribir.add(new BufferedWriter(new OutputStreamWriter(this.sockets.get(i).getOutputStream())));
			}

			for(int i=0; i<4; i++) {
				String s = this.leer.get(i).readLine();
				System.out.println(s);
			}
			for(int i=0; i<4; i++) {
					this.escribir.get(i).write("Hola\r\n");
					this.escribir.get(i).flush();	
			}
			
			
			
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fallo al coger los inputstream y outputstream");
		}
	}
	
	
	public void jugar() {
		int turno = 1;
		
		while(!this.terminado()) {
			
			
			
			
			
			
			
			
			
			
			turno++;
			if(turno>4) {
				turno=1;
			}

		}
	}
	

	//primerturno
	
	
	public boolean terminado() {
		for(Jugador jug : this.jugadores) {
			if(jug.haTerminado()) {
				return true;
			}
		}		
		return false;
	}
	
	//ganador
	
	//clasificacion

}
