import java.util.Arrays;
import java.util.Random;

public abstract class Lotterie implements Playable {

	/*
	 *  Tippreihe 
	 */
	protected int [] tippreihe;
	/*
	 * Anzahl der Kugeln, die gezogen werden
	 */
	protected int zuZiehendeKugeln;
	/*
	 * Anzahl der Kugeln, die insgesamt verwendet werden
	 */
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

	public int getZuZiehendeKugeln() {
		return zuZiehendeKugeln;
	}
	public void setZuZiehendeKugeln(int zuZiehendeKugeln) {
		this.zuZiehendeKugeln = zuZiehendeKugeln;
	}

	public int getGesamtzahlKugeln() {
		return gesamtzahlKugeln;
	}
	public void setGesamtzahlKugeln(int gesamtzahlKugeln) {
		this.gesamtzahlKugeln = gesamtzahlKugeln;
	}

	/*
	 * zieht/tippt Zahlen unter Berücksichtigung der UZ
	 */
	public int []  zahlenZiehen(int zuZiehendeKugeln, int gesamtzahlKugeln, int[] unglueckszahlen) {
		int [] gezogeneZahlen = new int [zuZiehendeKugeln];
		for(int i=0; i<gezogeneZahlen.length; i++) {
			int kugel = new Random().nextInt(gesamtzahlKugeln) + 1;
			while(kugel==0||contains(gezogeneZahlen, kugel)||contains(unglueckszahlen, kugel)) { 
				kugel = new Random().nextInt(gesamtzahlKugeln) + 1;
			}	
			gezogeneZahlen[i] = kugel;
		}
		Arrays.sort(gezogeneZahlen);
		return gezogeneZahlen;
	}

	/*
	 * gibt an, ob sich ein Element mit dem Wert value im Array befindet
	 */
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