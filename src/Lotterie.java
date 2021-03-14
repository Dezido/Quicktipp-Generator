import java.util.Random;

public abstract class Lotterie implements Playable {

	protected int [] tippreihe;
	protected int zuZiehendeKugeln;
	protected int gesamtzahlKugeln;

	public Lotterie(int zuZiehendeKugeln, int gesamtzahlKugeln) {	
		this.zuZiehendeKugeln = zuZiehendeKugeln;
		this.gesamtzahlKugeln = gesamtzahlKugeln;
	}
	
	public int[] getTippreihe() {
		return tippreihe;
	}
	public void setTippreihe(int[] tippreihe) {
		this.tippreihe = tippreihe;
	}

	public int []  zahlenZiehen(int zuZiehendeKugeln, int gesamtzahlKugeln, int[] unglueckszahlen) {
		int [] gezogeneZahlen = new int [zuZiehendeKugeln];
		for(int i=0; i<gezogeneZahlen.length; i++) {
			int kugel = new Random().nextInt(gesamtzahlKugeln) + 1;
			while(kugel==0||contains(gezogeneZahlen, kugel)||contains(unglueckszahlen, kugel)) { 
				kugel = new Random().nextInt(gesamtzahlKugeln) + 1;
			}	
			gezogeneZahlen[i] = kugel;
		}
		return gezogeneZahlen;
	}

	public boolean contains (int [] arr, int value) {
		for (int i=0; i<arr.length; i++) {
			if(arr[i]==value) {
				return true;
			}
		}
		return false;
	}


	public abstract void generate(int[] unglueckszahlen);
	public abstract boolean areValid(int[] unglueckszahlen);

}