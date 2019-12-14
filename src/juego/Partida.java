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
	private int turno;
	
	//variables red
	private Vector<Socket> sockets;
	private Vector<BufferedReader> leer;
	private Vector<BufferedWriter> escribir;
	
	public Partida(Vector<Socket> v){
		//iniciar variables juego
		this.turno = 0;
		
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
		String lineaLeida;
		int dado, numero=0;
		while(this.terminado() != null) {
			try {
				
				//envia el dibujo y el dado
				//this.escribir.get(this.turno).write("Turno\n");
				this.enviarDibujo(this.escribir.get(this.turno));
				dado = this.dado.lanzar();
				this.escribir.get(this.turno).write("DADO" + dado + "\n");
				this.escribir.get(this.turno).flush();
				
				//lee la ficha
				lineaLeida = this.leer.get(this.turno).readLine();
				if(lineaLeida.startsWith("FICHA")) {
					numero = Integer.parseInt(lineaLeida.substring(lineaLeida.length()-1)); //NumberFormatException
				}
				
				//intento mover la ficha si no se puede le doy otra oportunidad al jugador para mover otra o pasará turno
				if(this.jugadores.get(this.turno).moverFicha(numero, dado)) {
					this.escribir.get(this.turno).write("OK\n");
					this.escribir.get(this.turno).flush();
				}
				else {
					lineaLeida = this.leer.get(this.turno).readLine();
					if(lineaLeida.startsWith("FICHA")) {
						numero = Integer.parseInt(lineaLeida.substring(lineaLeida.length()-1)); //NumberFormatException
					}
					if(this.jugadores.get(this.turno).moverFicha(numero, dado)) {
						this.escribir.get(this.turno).write("OK\n");
						this.escribir.get(this.turno).flush();
					}
				}
				
				
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			this.turno++;
			if(this.turno>3) {
				this.turno=0;
			}

		}
	}
	
	private void enviarDibujo(BufferedWriter bw) throws IOException{
		bw.write(this.tablero.toString());
	}
	

	//primerturno
	
	
	public Jugador terminado() {
		for(Jugador jug : this.jugadores) {
			if(jug.haTerminado()) {
				return jug;
			}
		}		
		return null;
	}
	
	//ganador
	
	//clasificacion

}
