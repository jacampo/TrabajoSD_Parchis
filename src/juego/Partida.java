package juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class Partida {

	int a = 0;
	int[] vector = 
		{5,1,1,1,
		 5,1,1,1,
		 64,2,2,2,
		 64,2,2,2,
		 7,8,8,8,
		 7,8,8,8,
		 
		 5,1,1,1,
		 5,1,1,1,
		 64,2,2,2,
		 64,2,2,2,
		 7,8,8,8,
		 7,8,8,8,};
	
	
	
	
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
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fallo al coger los inputstream y outputstream");
		}
	}
	
	
	public void jugar() {
		String lineaLeida;
		int dado, numero=0;
		try {
			while(this.terminado() == null) {
				try {
					
					System.out.println(this.tablero.toString());
					
					
					//envia el dibujo y el dado
					//this.escribir.get(this.turno).write("Turno\r\n");
					this.escribir.get(this.turno).write("--------------------------------------------------------------------\r\n");
					this.escribir.get(this.turno).write("Turno jugador "+this.jugadores.get(this.turno).getColor().toString()+"\r\n");
					this.escribir.get(this.turno).write("Tablero\r\n");
					this.enviarDibujo(this.escribir.get(this.turno));
					this.escribir.get(this.turno).write(this.jugadores.get(this.turno).toStringCasillas() + "\r\n");
					dado = this.dado.lanzar();
					dado = vector[a++];
					this.escribir.get(this.turno).write("DADO: " + dado + "\r\n");
					this.escribir.get(this.turno).flush();
					
					
					//lee la ficha
					lineaLeida = this.leer.get(this.turno).readLine();
					if(lineaLeida.startsWith("FICHA")) {
						numero = Integer.parseInt(lineaLeida.substring(lineaLeida.length()-1)); //NumberFormatException
					}
					
					//intento mover la ficha si no se puede le doy otra oportunidad al jugador para mover otra o pasará turno
					if(this.jugadores.get(this.turno).moverFicha(numero, dado)) {
						Ficha f=this.jugadores.get(this.turno).getFicha(numero);	
						if(f!=null) {
							System.out.println(f.getNumero()+f.getColor().toString());
							int casilla=this.jugadores.get(this.turno).getCasilla(f);
							Ficha comida=this.tablero.comer(f, casilla);
							if(comida!=null) {
								for(Jugador j: this.jugadores) {
									if(j.getColor().equals(comida.getColor())) {
										j.volverFichaInicio(comida);
										System.out.println("intento poner null");
									}
								}
							}
						}
						this.escribir.get(this.turno).write("OK\r\n");
						this.escribir.get(this.turno).flush();
					}
					else {
						this.escribir.get(this.turno).write("error\r\n");
						this.escribir.get(this.turno).flush();
						lineaLeida = this.leer.get(this.turno).readLine();
						if(lineaLeida.startsWith("FICHA")) {
							numero = Integer.parseInt(lineaLeida.substring(lineaLeida.length()-1)); //NumberFormatException
							
						}
						this.jugadores.get(this.turno).moverFicha(numero, dado);
						Ficha f=this.jugadores.get(this.turno).getFicha(numero);				
						if(f!=null) {
							System.out.println(f.getNumero()+f.getColor().toString());
							int casilla=this.jugadores.get(this.turno).getCasilla(f);
							Ficha comida=this.tablero.comer(f, casilla);
							if(comida!=null) {
								for(Jugador j: this.jugadores) {
									if(j.getColor().equals(comida.getColor())) {
										j.volverFichaInicio(comida);
										System.out.println("intenti poner null");
									}
								}
							}
						}
						this.escribir.get(this.turno).write("OK\r\n");
						this.escribir.get(this.turno).flush();
						
					}
					
					
				
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				
				
				
				this.turno++;
				if(this.turno>3) {
					this.turno=0;
				}
			}
			
			for(int i=0;i<4;i++) {
				try {
					this.escribir.get(i).write("FIN\r\n");
					this.escribir.get(i).flush();
					this.clasificacion();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					try {
						this.escribir.get(i).write("El ganador es" + this.terminado().getColor() + "\r\n" + "FIN\r\n");
						this.escribir.get(i).flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		finally{
			
		}
	}
	
	private void enviarDibujo(BufferedWriter bw) throws IOException{
		bw.write(this.tablero.toString());
	}	
	
	public Jugador terminado() {
		for(Jugador jug : this.jugadores) {
			if(jug.haTerminado()) {
				return jug;
			}
		}		
		return null;
	}
	
	public void clasificacion() {
		
		
	}
}
