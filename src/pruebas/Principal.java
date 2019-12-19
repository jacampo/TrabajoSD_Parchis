package pruebas;

import juego.Color;
import juego.Jugador;
import juego.Tablero;

public class Principal {

	public static void main(String[] args) {
		
		/*Vector<String> v = new Vector<String>();
		v.size();
		
		Tablero t = new Tablero();
		System.out.println(t.toString());
		
		Ficha f = new Ficha(1,Color.Azul);
		t.colocar(f, 1, 0);
		System.out.println(t.toString());		
		t.colocar(f, 1,2);
		System.out.println(t.toString());*/
		
		Tablero t = new Tablero();
		Jugador j = new Jugador(Color.Azul, t);
		System.out.println(j.haTerminado());
		
	}

}
