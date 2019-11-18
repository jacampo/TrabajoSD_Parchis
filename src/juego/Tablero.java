package juego;

import java.util.List;
import java.util.Vector;

public class Tablero {
	private  List<Casilla> casillas;
	private  List<FaseFinal> faseFinal;
	
	public Tablero(){
		this.casillas = new Vector<>(68);
		this.cargarCasillas();
		this.faseFinal = new Vector<>(4);
		this.cargarFasesFinales();
	}
	
	private void cargarCasillas() {
		int c = 0;
		for(int i=1; i<=68;i++) {
			if(i==5 || i==22 || i==39 || i==56) {
				this.casillas.add(new Casilla(i, true, Color.values()[c]));
				c++;
			}
			else {
				if(i==12 || i==17 || i==29 || i==34 || i==46 || i==51 || i==63 || i==68)
					this.casillas.add(new Casilla(i, true, null));
				
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
	
	/*Pre:casilla es el numero de la sacilla en el que se encuentra la ficha. NumDado es el numero que ha salido en el dado
	 *Post: Coloca la ficha f en la casilla correspondinte segun el numero del dado en caso de que sea posible y 
	 *		devuelve true, false en caso contrario.
	 * */
	public boolean colocar(Ficha f, int casilla, int NumDado){
		//caso fase final
		if(casilla > 68) {
			
		}
		else{
			//calculamos a que casilla tiene que ir
			boolean esFaseFinal=false;

			
			//caso casillas 1-68 a faseFinal
			if(esFaseFinal) {
				
			}
			else {
				//caso casillas 1-68 normal
				
				
			}	
		}
		return false;
	}
		
	public boolean eliminar(Ficha f, int casilla) {
		return this.casillas.get(casilla-1).eliminarFicha(f);
	}
	
	public List<Ficha> getFichas(int casilla){
		return this.casillas.get(casilla-1).fichas;
	}
}
