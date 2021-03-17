public class Eurojackpot  extends Lotterie {

	/*
	 * Eurozahlen 2 aus 10
	 */
	private int [] eurozahlen = new int [2]; 

	public Eurojackpot(int zuZiehendeKugeln, int gesamtzahlKugeln) {
		super(zuZiehendeKugeln,gesamtzahlKugeln);
	}

	public int[] getEurozahlen() {
		return eurozahlen;
	}
	public void setEurozahlen(int[] eurozahlen) {
		this.eurozahlen = eurozahlen;
	}
	
	/*
	 * Generiert einen Quicktipp für das Produkt Eurojackpot
	 */
	@Override
	public void generate(int[] unglueckszahlen) {
		this.tippreihe = zahlenZiehen(5, 50, unglueckszahlen);
		this.eurozahlen = zahlenZiehen(2, 10, unglueckszahlen);
	}
	
	/*
	 * Prüft ob alle übergebenen UZ im Spiel Eurojackpot vorkommen
	 */
	@Override
	public boolean areValid(int[] unglueckszahlen) {
		for(int i=0; i<unglueckszahlen.length;i++) {
			if(unglueckszahlen[i]>50) {
				return false;
			}
		}
		return true;
	}

}