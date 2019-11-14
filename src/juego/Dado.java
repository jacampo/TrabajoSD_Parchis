package juego;

import java.util.Random;

public class Dado {
	private int numCaras;
	
	public Dado(int caras) {
		this.numCaras = caras;
	}
	
	public int getCaras() {
		return this.numCaras;
	}
	
	public int lanzar() {
		Random r = new Random();
		return r.nextInt(this.numCaras);
	}
	
}
