package juego;

import java.util.ArrayList;
import java.util.List;

public class FaseFinal {
	private Color color;
	private  List<Casilla> casillas;
	
	public FaseFinal(Color color) {
		this.color=color;
		this.casillas=new ArrayList<>(8);		
		for(int i=0;i<8;i++) {
			this.casillas.add(new Casilla(i+1,true,this.color,true));
		}
	}

	public List<Casilla> getCasillas(){
		return this.casillas;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	
	
	
	
}
