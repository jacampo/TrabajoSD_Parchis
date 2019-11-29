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
			int pos = casilla % 10;
			int fin = casilla + NumDado;	
			if(pos + fin > 8) {
				return false;
			}
			int numColor=0;
			for(int i = 0; i<4; i++) {
				if(f.getColor().equals(Color.values()[i])) {
					numColor = i;
					break;
				}
			}	
			List<Casilla> cas = this.faseFinal.get(numColor).getCasillas();
			if(pos + NumDado > 8) {
				return false;
			}
			int i=pos;
			for(;i <= pos + NumDado; i++) {
				if( pos + NumDado != 8 && !cas.get(i-1).sePuedeColocar()) {
					return false;
				}
			}
			i--;
			cas.get(pos-1).fichas.remove(f);//?????
			if(pos + NumDado == 8) {
				cas.get(7).fichas.add(f);
			}
			else {
				cas.get(i-1).fichas.add(f);
			}
			return true;
		}
		else{
			//calculamos a que casilla tiene que ir
			int casillaFinal = casilla + NumDado;
			int comienzoFaseFinalColor = 68;
			boolean esFaseFinal=false;
			for(int i=0; i<4; i++) {
				//si la casilla a la que tiene que ir esta entre el comienzo de la fasefinal de su color y la casilla en la que estaba
				if(f.getColor().equals(Color.values()[i]) && casilla < casillaFinal && casillaFinal > comienzoFaseFinalColor) {
					esFaseFinal=true;
					casillaFinal = casillaFinal - comienzoFaseFinalColor;//numero de casillas a recorrer dentro de la fase final
					break;
				}
				if(comienzoFaseFinalColor == 68) {
					comienzoFaseFinalColor = 17;
				}
				else {
					comienzoFaseFinalColor += 17;
				}
			}
			//caso casillas 1-68 a faseFinal
			if(esFaseFinal) {
				int numColor=0;
				for(int i = 0; i<4; i++) {
					if(f.getColor().equals(Color.values()[i])) {
						numColor = i;
						break;
					}
				}	
				List<Casilla> cas = this.faseFinal.get(numColor).getCasillas();
				
				int pos=0;
				
				/////////////////////////////////////////////////////////////////////////
				
				this.casillas.get(casilla-1).fichas.remove(f);
				return true;
			}
			else {
				//caso casillas 1-68 normal
				int i=casilla;
				for(;i <= casilla + NumDado; i++) {
					if(!this.casillas.get(i-1).sePuedeColocar()) {
						return false;
					}
				}
				i--;
				this.casillas.get(casilla-1).fichas.remove(f);
				this.casillas.get(i-1).fichas.add(f);
				return true;
			}	
		}
	}
	
	//Pre: se ha usado el metodo colocar, y ha sido true su resultado, f es la ficha que acabamos de colocar 
		//y casilla es el numero de la casilla en el que ha sido colocada
		//Post: si hay otra ficha de otro color a f en la casilla, y es una casilla en la que se puede comer devuelde la ficha comida, null en otro caso
	public Ficha comer(Ficha f, int casilla) {
		if(casilla > 68) {
			return null;
		}
		if(!this.casillas.get(casilla-1).esEspecial()) {//si no es especial, ya que si es especial no podemos comer
			List<Ficha> l = this.casillas.get(casilla-1).getFichas();
			if(l.size()==2) {
				int i=0;//para saber cual no cual no es f
				if(l.get(i).equals(f)) {
					i=1;
				}
				if(!l.get(i).getColor().equals(l.get(i).getColor())) {//si el color no es igual, comemos
					Ficha aux = l.get(i);//cogemos la otra ficha
					l.remove(i);//comida
					return aux;
				}
			}
		}
		return null;
	}
	
	public void sacarFicha(Ficha f) {
		if(f.getColor()==Color.Amarillo) {
			colocar(f,5,0);
		}
		if(f.getColor()==Color.Azul) {
			colocar(f,22,0);
		}
		if(f.getColor()==Color.Rojo) {
			colocar(f,39,0);
		}
		if(f.getColor()==Color.Verde) {
			colocar(f,56,0);
		}
	}
	
	public boolean eliminar(Ficha f, int casilla) {
		return this.casillas.get(casilla-1).eliminarFicha(f);
	}
	
	public List<Ficha> getFichas(int casilla){
		return this.casillas.get(casilla-1).fichas;
	}
	
	public String toString() {
		String s = "";
		for(Casilla c : this.casillas) {
			s+="Casilla " + c.getNumero() + " tiene fichas:" + c.getFichas().size() + "\n";
		}
		return s;
	}
}
