package juego;

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
				if(fichas.get(f) != null){
					 Casilla c = this.tablero.colocar(f, this.fichas.get(f).getNumero(), dado);
					 return c != null;
				}
				else {
					if(dado==5) {
						this.tablero.sacarFicha(f);
					
						if(this.color==Color.Amarillo) {
							this.fichas.put(f, new Casilla(5, true, this.color));
						}
						if(this.color==Color.Azul) {
							this.fichas.put(f, new Casilla(22, true, this.color));
						}
						if(this.color==Color.Rojo) {
							this.fichas.put(f, new Casilla(39, true, this.color));
						}
						if(this.color==Color.Verde) {
							this.fichas.put(f, new Casilla(56, true, this.color));
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	public boolean haTerminado() {
		for(Casilla c : this.fichas.values()) {
			if(c!= null && (!c.esEspecial() || !(c.getNumero() == 8))) {
				return false;
			}
		}
		return true;
	}
	public Color getColor() {
		return this.color;
	}
}
