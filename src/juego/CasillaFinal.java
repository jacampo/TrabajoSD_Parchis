package juego;

public class CasillaFinal extends Casilla{
	
	public CasillaFinal(int numero, boolean especial, Color color) {
		super(numero, especial, color);
		// TODO Auto-generated constructor stub
		this.fichas=new Ficha[4];
	}
	public void colocarFicha(Ficha f) {
		for(int i=0;i<4;i++) {
			if(this.fichas[i]!=null) {
				this.fichas[i] = f;
			}
		}
	}
	
	public boolean haGanado() {
		int i=0;
		while(i<4) {
			if(this.fichas[i]==null)
				return false;
			i++;
		}
		return true;
	}
}
