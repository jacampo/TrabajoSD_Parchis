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
	public Casilla colocar(Ficha f, int casilla, int NumDado){
		//caso fase final
		if(casilla > 68) {
			int pos = casilla % 10;
			int fin = pos + NumDado;	
			if(fin > 8) {
				return null;
			}
			int numColor=0;
			for(int i = 0; i<4; i++) {
				if(f.getColor().equals(Color.values()[i])) {
					numColor = i;
				}
			}	
			List<Casilla> cas = this.faseFinal.get(numColor).getCasillas();
			for(int i=pos;i <= fin; i++) {
				if( fin != 8 && !cas.get(i-1).sePuedeColocar()) {
					return null;
				}
			}
			cas.get(pos-1).getFichas().remove(f);
			if(fin == 8) {
				cas.get(7).getFichas().add(f);
			}
			else {
				cas.get(fin-1).getFichas().add(f);
			}
			//cas.get(fin-1).getFichas().add(f);
			return cas.get(fin-1);
		}
		else{
			//calculamos a que casilla tiene que ir
			int casillaFinal = casilla + NumDado;
			int salida = 5;
			int comienzoFaseFinalColor = 68;
			boolean esFaseFinal=false;
			for(int i=0; i<4; i++) {
				//si la casilla a la que tiene que ir esta entre el comienzo de la fasefinal de su color y la casilla en la que estaba                         
				if(f.getColor().equals(Color.values()[i]) && casillaFinal > comienzoFaseFinalColor && casilla>0 && casilla<=comienzoFaseFinalColor) {
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
				salida += 17;
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
				for(int i = casilla + 1; i <= this.numeroCasillaEntrada(f.getColor()); i++) {
					if(!this.casillas.get(i-1).sePuedeColocar()) {
						System.out.println("No puede pasar por este camino ya que hay 2 fichas bloqueando");
						return null;
					}
				}
				
				int casFinal = casilla + NumDado - this.numeroCasillaEntrada(f.getColor());
				cas =this.faseFinal.get(numColor).getCasillas();
				for(int i = 1; i <= casFinal; i++) {
					if(!cas.get(i-1).sePuedeColocar()) {
						System.out.println("No puede pasar por este camino ya que hay 2 fichas bloqueando");
						return null;
					}
				}
				this.casillas.get(casilla-1).getFichas().remove(f);
				cas.get(casFinal-1).getFichas().add(f);
				return cas.get(casFinal-1);
			}
			else {
				//caso casillas 1-68 normal
				int fin=casilla + NumDado;
				int casillaFin=fin;
				if(fin>68) {
					casillaFin = fin - 68;
					for(int i = casilla + 1; i <= 68; i++) {
						if(!this.casillas.get(i-1).sePuedeColocar()) {
							System.out.println("No puede pasar por este camino ya que hay 2 fichas bloqueando");
							return null;
						}
					}
					for(int i = 1; i <= casillaFin; i++) {
						if(!this.casillas.get(i-1).sePuedeColocar()) {
							System.out.println("No puede pasar por este camino ya que hay 2 fichas bloqueando");
							return null;
						}
					}
				}
				else {
					for(int i = casilla + 1; i <= fin; i++) {
						if(!this.casillas.get(i-1).sePuedeColocar()) {
							System.out.println("No puede pasar por este camino ya que hay 2 fichas bloqueando");
							return null;
						}
					}
				}
				
				
				
				this.casillas.get(casilla-1).getFichas().remove(f);
				this.casillas.get(casillaFin-1).getFichas().add(f);
				return this.casillas.get(casillaFin-1);
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
				if(!l.get(i).getColor().equals(f.getColor())) {//si el color no es igual, comemos
					Ficha aux = l.get(i);//cogemos la otra ficha
					l.remove(i);//comida
					return aux;
				}
			}
		}
		return null;
	}
	
	private int numeroCasillaEntrada(Color c) {
		if(c==Color.Amarillo) {
			
			return 68;
		}
		if(c==Color.Azul) {
			
			return 17;
		}
		if(c==Color.Rojo) {
			
			return 34;
		}
		if(c==Color.Verde) {
			
			return 51;
		}
		return 0;
	}
	
	
	public boolean sacarFicha(Ficha f) {
		if(f.getColor()==Color.Amarillo) {
			if(this.casillas.get(4).sePuedeColocar()) { 
				colocar(f,5,0);
				return true;
			}
			return false;
		}
		if(f.getColor()==Color.Azul) {
			if(this.casillas.get(21).sePuedeColocar()) { 
				colocar(f,22,0);
				return true;
			}
			return false;
		}
		if(f.getColor()==Color.Rojo) {
			if(this.casillas.get(38).sePuedeColocar()) {
				colocar(f,39,0);
				return true;
			}
			return false;
		}
		if(f.getColor()==Color.Verde) {
			if(this.casillas.get(55).sePuedeColocar()) { 
				colocar(f,56,0);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean eliminar(Ficha f, int casilla) {
		return this.casillas.get(casilla-1).eliminarFicha(f);
	}
	
	public List<Ficha> getFichas(int casilla){
		return this.casillas.get(casilla-1).fichas;
	}
	
	public String toString() {
		String s = "Casillas\r\n";
		int i = 0;
		for(Casilla c : this.casillas) {
			s+= c.getNumero();
			if(c.getFichas().size()>0) {
				s += "[";
				for(Ficha f : c.getFichas()) {
					s+= f;
				}
				s += "]";
			}
			s += "  ";	
			i++;
			if(i % 17 == 0)
				s+="\r\n";
		}
		
		s += "Fases Finales\r\n";
		for(FaseFinal ff : this.faseFinal) {
			s += " " + ff.getColor() + ":  ";
			for(Casilla c : ff.getCasillas()) {
				s+= c.getNumero();
				if(c.getFichas().size()>0) {
					s += "[";
					for(Ficha f : c.getFichas()) {
						s+= f;
					}
					s += "]";
				}
				s += "  ";	
			}
			s += "\r\n";	
		}
		
		return s;
	}
}
