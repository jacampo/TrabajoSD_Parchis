package juego;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	private  List<Casilla> casillas;
	private  List<FaseFinal> faseFinal;
	
	public Tablero(){
		this.casillas = new ArrayList<>(68);
		this.cargarCasillas();
		this.faseFinal = new ArrayList<>(4);
		this.cargarFasesFinales();
	}
	
	private void cargarCasillas() {
		int c = 0;
		for(int i=1; i<=68;i++) {
			if(i==5 || i==22 || i==39 || i==56) {
				this.casillas.add(new Casilla(i,true,Color.values()[c]));
				c++;
			}
			else {
				if(i==12 || i==17 || i==29 || i==34 || i==46 || i==51 || i==63 || i==68)
					this.casillas.add(new Casilla(i,true,null));
				
				else
					this.casillas.add(new Casilla(i));
			}
		}
	}
	
	private void cargarFasesFinales() {
		for(int i=0; i<=3;i++) {
			this.faseFinal.add(new FaseFinal(Color.values()[i]));
		}
	}
}
