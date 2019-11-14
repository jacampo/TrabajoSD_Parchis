package juego;

public class Casilla {
	private int numero;
	private boolean especial;
	private Color especialColor;
	
	public Casilla(int numero, boolean especial, Color color) {
		this.numero = numero;
		this.especial = especial;
		this.especialColor = color;
	}
	
	public Casilla(int numero) {
		this.numero = numero;
		this.especial = false;
		this.especialColor = null;
	}

	public int getNumero() {
		return numero;
	}

	public boolean esEspecial() {
		return especial;
	}

	public Color esEspecialColor() {
		return especialColor;
	}
	
	
	
}
