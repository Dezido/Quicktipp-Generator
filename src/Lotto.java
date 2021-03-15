import java.util.Random;

public class Lotto extends Lotterie{

	//Superzahl aus 0-9
	private int superzahl;

	public Lotto(int zuZiehendeKugeln, int gesamtzahlKugeln) {
		super(zuZiehendeKugeln,gesamtzahlKugeln);		
	}

	public int getSuperzahl() {
		return superzahl;
	}
	public void setSuperzahl(int superzahl) {
		this.superzahl = superzahl;
	}


	public int superzahlZiehen(int[] unglueckszahlen) { 
		int superzahl = new Random().nextInt(10) ;
		while(contains(unglueckszahlen,superzahl)) {
			superzahl = new Random().nextInt(10) ;
		}
		return superzahl;	
	}

	@Override
	public void generate(int[] unglueckszahlen) {
		this.tippreihe = zahlenZiehen(6,49,unglueckszahlen);
		this.superzahl = superzahlZiehen(unglueckszahlen);	
	}

	@Override
	public boolean areValid(int[] unglueckszahlen) {
		for(int i=0; i<unglueckszahlen.length;i++) {
			if(unglueckszahlen[i]>49) {
				return false;
			}
		}
		return true;
	}

}