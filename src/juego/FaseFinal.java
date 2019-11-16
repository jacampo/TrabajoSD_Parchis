package juego;

import java.util.ArrayList;
import java.util.List;

public class FaseFinal {
	
	private  List<Casilla> casillas;
	
	public FaseFinal(Color color) {
		// TODO Auto-generated constructor stub
		this.casillas=new ArrayList<>(8);		
		for(int i=0;i<7;i++) {
			this.casillas.add(new Casilla(i+1,true,color));
		}
		this.casillas.add(new CasillaFinal(8,true,color));
	}

}
