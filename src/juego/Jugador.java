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
		for(Ficha f:fichas.keySet()) {
			if(f.getNumero()==numficha) {				
				if(fichas.get(f)!=null){
					return this.tablero.colocar(f, this.fichas.get(f).getNumero(), dado);
				}
				else {
					this.tablero.sacarFicha(f);
					return true;
				}
			}
		}
		return false;
	}

}
