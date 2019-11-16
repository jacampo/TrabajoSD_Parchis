package juego;

import java.util.ArrayList;
import java.util.LinkedList;
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
	
	/*Pre:casilla es el numero de la sacilla en el que se encuentra la ficha. NumDado es el numero que ha salido en el dado
	 *Post: Coloca la ficha f en la casilla correspondinte segun el numero del dado en caso de que sea posible y 
	 *		devuelve true, false en caso contrario.
	 * */
	public boolean colocar(Ficha f, int casilla, int NumDado){
		if(NumDado<1 || NumDado >6) {
			return false;
		}
		
		int casillaFinal=casilla+NumDado;
		int empiezafasefinal=17;
		boolean fasefinal=false;
		for(Color c : Color.values()) {
			if(c.equals(f.getColor()) && casillaFinal > empiezafasefinal) {
				//esta dentro ya de la fase final del tablero
				fasefinal=true;
				casillaFinal = casillaFinal - empiezafasefinal;//el numero de casillas que tiene que recorrer dentro
				break;
			}
			empiezafasefinal += 17;
		}

		if(fasefinal) {
			int i=0;
			for(Color c : Color.values()) {
				if(c.equals(f.getColor())) {
					//FALTA DONDE BORRAR DONDE ESTABA ANTES EN ESTE CASO, DEPENDE DE SI ESTABA AL PRINCIPIO EN LA FASEFINAL O NO
					this.faseFinal.get(i).getCasillas().get(i).colocarFicha(f);
					break;
				}
				i++;
			}
		}
		else {
			for(int i=casilla+1;i<=casillaFinal;i++) {
				if(!this.casillas.get(i-1).sePuedeColocar()) {//Si se puede colocar quiere decir que puede pasar tambien por esa casilla
					return false;
				}
			}
			//Sabemos que puede pasar por todas las casillas y colocarse en la correspondiente
			this.casillas.get(casilla-1).eliminarFicha(f);
			this.casillas.get(casillaFinal-1).colocarFicha(f);
		}
		return true;
	}
	
	public boolean eliminar(Ficha f, int casilla) {
		return this.casillas.get(casilla-1).eliminarFicha(f);
	}
	
	public List<Ficha> getFichas(int casilla){
		List<Ficha> f = new LinkedList<Ficha>();
		Ficha f2[] = this.casillas.get(casilla-1).getFichas();
		for(int i=0;i<f2.length;i++) {
			if(f2[i]!=null) {
				f.add(f2[i]);
			}
		}
		return f;
	}
}
