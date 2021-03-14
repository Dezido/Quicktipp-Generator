
public class Quicktipp {

	public static void main(String[] args) {
		int [] unglueckszahlen = new int [args.length];
		for(int i=0;i<args.length;i++) {
			unglueckszahlen[i]=Integer.parseInt(args[i]);
		}
		System.out.println();
		System.out.println("Unglückszahlen: " + java.util.Arrays.toString(unglueckszahlen));
		System.out.println();
		Lotto lotto = new Lotto(6,49);
		Eurojackpot eurojackpot = new Eurojackpot (5,50);
		lotto.generate(unglueckszahlen);
		eurojackpot.generate(unglueckszahlen);
		System.out.println();
		System.out.println("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
		System.out.println("Superzahl: " +lotto.getSuperzahl());		
		System.out.println();
		System.out.println("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		System.out.println("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));
		System.exit(0);
	}

}
