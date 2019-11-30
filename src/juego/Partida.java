package juego;

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
		
		//llamar a terminado de jugador, cuando lleguen sus fichas al final
		
		return false;
	}
	
	//ganador
	
	//clasificacion

}
