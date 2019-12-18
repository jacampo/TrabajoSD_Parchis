package juego;

import java.util.Vector;

public class Casilla {
	private int numero;
	private boolean especial;
	private Color especialColor;
	private boolean fasefinal;
	protected Vector<Ficha> fichas; // casilla normal 2 fichas como maximo
	
	public Casilla(int numero, boolean especial, Color color) {
		this.numero = numero;
		this.especial = especial;
		this.especialColor = color;
		this.fasefinal=false;
		this.fichas= new Vector<Ficha>();
	}
	
	public Casilla(int numero, boolean especial, Color color, boolean esfaseFinal) {
		this.numero = numero;
		this.especial = especial;
		this.especialColor = color;
		this.fasefinal=esfaseFinal;
		this.fichas= new Vector<Ficha>();
	}
	
	public Casilla(int numero) {
		this(numero, false, null);
	}

	public int getNumero() {
		return numero;
	}

	public Vector<Ficha> getFichas(){
		return this.fichas;
	}
	
	public boolean esEspecial() {
		return especial;
	}

	public Color esEspecialColor() {
		return especialColor;
	}
	public boolean sePuedeColocar() {
		return this.fichas.size() < 2;
	}
	
	public boolean esfaseFinal() {
		return this.fasefinal;
	}
	
	/*Pre: Se puede colocar la ficha en la casilla excepto para la ultima casilla del tablero
	 * Post: coloca la ficha en la casilla
	 * */
	public void colocarFicha(Ficha f) {
		this.fichas.add(f);
	}
	
	public boolean eliminarFicha(Ficha f) {
		return this.fichas.remove(f);
	}
}
