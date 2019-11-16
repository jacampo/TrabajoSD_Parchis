package juego;

public class Ficha {
	private int nFicha;
	private Color color;
	
	public Ficha(int nFicha, Color color) {
		this.nFicha = nFicha;
		this.color = color;
	}	
	
	public int getNumero() {
		return this.nFicha;
	}
	public Color getColor() {
		return this.color;
	}
	public boolean equals(Object o) {
		Ficha f = (Ficha)o;
		return this.nFicha==f.getNumero() && this.color.equals(f.getColor());
	}
}
