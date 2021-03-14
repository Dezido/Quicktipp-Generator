import java.util.logging.Logger;

public class Quicktipp {

	public static void main(String[] args) {
		run(parseArgs(args));
		
	}
	
	public static int[] parseArgs (String [] args) {
		
		int [] unglueckszahlen = new int [args.length];
		
		for(int i=0;i<args.length;i++) {
			unglueckszahlen[i]=Integer.parseInt(args[i]);
		}
		return unglueckszahlen;		
	}
	
	public static void run(int[] unglueckszahlen) {
		
		Logger logger = QuicktippLogger.setupLogger();		
				
		System.out.println("Unglückszahlen: " + java.util.Arrays.toString(unglueckszahlen));
		System.out.println();
		
		logger.info("Übergebene Unglückszahlen: " + java.util.Arrays.toString(unglueckszahlen));
		
		Lotto lotto = new Lotto(6,49);
		Eurojackpot eurojackpot = new Eurojackpot (5,50);
		
		lotto.generate(unglueckszahlen);
		eurojackpot.generate(unglueckszahlen);
		
		logger.info("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
		logger.info("Superzahl: " +lotto.getSuperzahl());
		logger.info("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		logger.info("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));
		
		System.out.println();
		System.out.println("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
		System.out.println("Superzahl: " +lotto.getSuperzahl());
		System.out.println();
		System.out.println("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		System.out.println("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));
		System.exit(0);
	}

}
