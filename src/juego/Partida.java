package juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class Partida {

	private Tablero tablero;
	private Vector<Jugador> jugadores;
	private Dado dado;
	
	public Partida(){
		this.tablero = new Tablero();
		this.jugadores = new Vector<>(4);
		this.jugadores.add(new Jugador(Color.Amarillo, tablero));
		this.jugadores.add(new Jugador(Color.Azul, tablero));
		this.jugadores.add(new Jugador(Color.Rojo, tablero));
		this.jugadores.add(new Jugador(Color.Verde, tablero));
		this.dado = new Dado(6);
		
		
		
	}
	
	
	public void jugar() {
		while(!this.terminado()) {
			
			
			
			
			
			
			
			
		}
	}
	

	//primerturno
	
	
	public boolean terminado() {
		
	}
	
	//ganador
	
	//clasificacion

}
