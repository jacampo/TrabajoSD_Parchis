package juego;

public class Casilla {
	private int numero;
	private boolean especial;
	private Color especialColor;
	protected Ficha[] fichas; // casilla normal 2 fichas como maximo
	
	public Casilla(int numero, boolean especial, Color color) {
		this.numero = numero;
		this.especial = especial;
		this.especialColor = color;
		this.fichas=new Ficha[2];
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
	public boolean sePuedeColocar() {
		if(this.fichas[0]==null || this.fichas[1]==null) {
			return true;
		}
		return false;
	}
	public void colocarFicha(Ficha f) {
		if(this.fichas[0]==null) {
			this.fichas[0]=f;
		}
		else 
			this.fichas[1]=f;
	}
	public boolean EliminarFicha(Ficha f) {
		for(int i=0;i<2;i++) {
			if(this.fichas[i]!=null && this.fichas[i].equals(f)) {
				this.fichas[i]=null;
				return true;
			}
		}
		return false;
	}
}
