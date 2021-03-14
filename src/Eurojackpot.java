public class Eurojackpot  extends Lotterie {
	
	//Eurozahlen 2 aus 10
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

	@Override
	public void generate(int[] unglueckszahlen) {
		this.tippreihe = zahlenZiehen(5, 50, unglueckszahlen);
		this.eurozahlen = zahlenZiehen(2, 10, unglueckszahlen);
	}

	@Override
	public boolean areValid(int[] unglueckszahlen) {
		//TODO 
		return true;
	}

}