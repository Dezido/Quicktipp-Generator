import java.util.Random;

public class Lotto extends Lotterie{

	/*
	 * Superzahl aus 0-9
	 */
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

	/*
	 * Superzahl wird aus 0-9 gezogen
	 * Unglückszahlen werden berücksichtigt
	 */
	public int superzahlZiehen(int[] unglueckszahlen) { 
		int superzahl = new Random().nextInt(10) ;
		while(contains(unglueckszahlen,superzahl)) {
			superzahl = new Random().nextInt(10) ;
		}
		return superzahl;	
	}
	
	/*
	 * Generiert einen Quicktipp für das Produkt Lotto (6aus49)
	 */
	@Override
	public void generate(int[] unglueckszahlen) {
		this.tippreihe = zahlenZiehen(this.zuZiehendeKugeln,this.gesamtzahlKugeln,unglueckszahlen);
		this.superzahl = superzahlZiehen(unglueckszahlen);	
	}
	
	/*
	 * Prüft ob alle übergebenen UZ im Spiel Lotto vorkommen
	 */
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