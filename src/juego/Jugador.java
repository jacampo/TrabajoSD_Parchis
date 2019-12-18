package juego;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Jugador {
	private Color color;
	private Tablero tablero;
	private HashMap<Ficha, Casilla> fichas;
	
	public Jugador(Color c,Tablero t) {
		this.color=c;
		this.fichas=new HashMap<>();
		for(int i=1;i<=4;i++) {
			Ficha f=new Ficha(i,this.color);
			fichas.put(f, null);
		}
		this.tablero=t;
	}
	public boolean moverFicha(int numficha,int dado) {
		/*if(dado==5) {
			//miramos si quedan por sacar
			Casilla cas = null;
			for(Casilla c : this.fichas.values()){
				if(c == null) {
					cas = c;
				}
			}
		}*/
		for(Ficha f : fichas.keySet()) {
			if(f.getNumero()==numficha) {	
				System.out.println("Ficha del jugador encontrada");
				if(fichas.get(f) != null){
					if(fichas.get(f).esfaseFinal() && fichas.get(f).getNumero() > 0 && fichas.get(f).getNumero() < 9 ) {
						 Casilla c = this.tablero.colocar(f, this.fichas.get(f).getNumero() + 70, dado);
						 //System.out.println("Nos devuelve la casilla: " + c.getNumero());
						 this.fichas.put(f, c);
						 return c != null;
					}
					else {
						System.out.println("Llamamos a colocar con: Ficha: " + f.getNumero()+" Numero: " + 
								this.fichas.get(f).getNumero()+ "Dado : "+ dado );
						 Casilla c = this.tablero.colocar(f, this.fichas.get(f).getNumero(), dado);
						 //System.out.println("Nos devuelve la casilla: " + c.getNumero());
						 this.fichas.put(f, c);
						 return c != null;
					 }
				}
				else {
					System.out.println("Dado : "+ dado );
					if(dado==5) {
						System.out.println("Numero del dado igual a 5");
						boolean b=this.tablero.sacarFicha(f);
							
						if(b) {
							if(this.color==Color.Amarillo) {
								this.fichas.put(f, new Casilla(5, true, this.color));
								System.out.println("Casilla de la ficha es: " + this.fichas.get(f).getNumero());
							}
							if(this.color==Color.Azul) {
								this.fichas.put(f, new Casilla(22, true, this.color));
								System.out.println("Casilla de la ficha es: " + this.fichas.get(f).getNumero());
							}
							if(this.color==Color.Rojo) {
								this.fichas.put(f, new Casilla(39, true, this.color));
								System.out.println("Casilla de la ficha es: " + this.fichas.get(f).getNumero());
							}
							if(this.color==Color.Verde) {
								this.fichas.put(f, new Casilla(56, true, this.color));
								System.out.println("Casilla de la ficha es: " + this.fichas.get(f).getNumero());
							}	
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public Ficha getFicha(int num) {
		for(Ficha f : fichas.keySet()) {
			if(f.getNumero()==num) {
				if(this.fichas.get(f)!=null) {
					return f;
				}			
			}
		}
		return null;
	}
	
	public Integer getCasilla(Ficha f) {
		return this.fichas.get(f).getNumero();
	}
	public void volverFichaInicio(Ficha f) {
		this.fichas.put(f, null);
	}

	public boolean haTerminado() {
		int terminadas=0; 
		for(Casilla c : this.fichas.values()) 
		{ 
			if(c!= null && c.esEspecial() && c.getNumero() == 8) 
			{ 
				terminadas++; 
			} 
		} 
		return terminadas != 4;
	}
	public Color getColor() {
		return this.color;
	}
	
	public String toStringCasillas() {
		String s = "";
		s += "Casillas jugador: ";
		int i=1;
		for(Casilla c : this.fichas.values()) {
			if(c!=null)
				s +=  i++ + "-" +c.getNumero() + " ";
			else
				s +=  i++ + "-SinSacar ";
		}
		return s;
	}
}
