import java.util.Scanner;
import java.util.logging.Logger;

public class Quicktipp {

	private static Logger logger = QuicktippLogger.setupLogger();	

	public static void main(String[] args) {
		menu(parseArgs(args));

	}

	public static int[] parseArgs (String [] args) {

		int [] unglueckszahlen = new int [args.length];

		for(int i=0;i<args.length;i++) {
			unglueckszahlen[i]=Integer.parseInt(args[i]);
		}
		return unglueckszahlen;		
	}



	public static void lottoQuicktipp(int [] unglueckszahlen) {		
		Lotto lotto = new Lotto(6,49);
		lotto.generate(unglueckszahlen);
		logger.info("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
		logger.info("Superzahl: " +lotto.getSuperzahl());
		System.out.println();
		System.out.println("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
		System.out.println("Superzahl: " +lotto.getSuperzahl());
		System.out.println();
		System.out.println("Danke für die Nutzung des Quicktipp-Generators");
	}

	public static void eurojackpotQuicktipp(int[] unglueckszahlen) {
		Eurojackpot eurojackpot = new Eurojackpot (5,50);			
		eurojackpot.generate(unglueckszahlen);				
		logger.info("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		logger.info("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));				
		System.out.println();
		System.out.println("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		System.out.println("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));
		System.out.println();
		System.out.println("Danke für die Nutzung des Quicktipp-Generators");
	}

	public static void changeUnglueckszahlen() {
		//TODO
	}
	
	public static void deleteUnglueckszahlen(){
		//TODO
	}

	//Hauptmenü
	private static void menu (int [] unglueckszahlen) {
		System.out.println("Guten Tag, Sie haben folgende Optionen:");
		System.out.println("1: Lotto (6aus49) Quicktipp erstellen");
		System.out.println("2: Eurojackpot Quicktipp erstellen");
		System.out.println("3: Unglückszahlen bearbeiten");
		System.out.println("4: Programm beenden");
		Scanner keyboard = new Scanner(System.in);
		int option = keyboard.nextInt();			
		while (option <0||option >4) {
			System.out.println("Falsche Eingabe \nDie Optionen lauten 1,2,3 oder 4.");
			option = keyboard.nextInt();			
		}
		switch (option) {
		case 1:
			lottoQuicktipp(unglueckszahlen);
			System.exit(0);
		case 2:
			eurojackpotQuicktipp(unglueckszahlen);
			System.exit(0);
		case 3:
			submenu(unglueckszahlen);
		case 4:
			System.out.println("Programm wird beendet");
			System.out.println("Danke für die Nutzung des Quicktipp-Generators");
			keyboard.close();
			System.exit(0);
		}


	}

	//Untermenü zur Änderung der Unglückszahlen
	private static void submenu (int [] unglueckszahlen) {
		System.out.println("Welche Änderungen möchten Sie durchführen?");
		System.out.println("1: Gespeicherte Unglückszahlen bearbeiten");
		System.out.println("2: Gespeicherte Unglückszahlen löschen");
		System.out.println("3: Zurück zum Hauptmenü");
		Scanner keyboard = new Scanner(System.in);
		int option = keyboard.nextInt();	
		while (option <0||option >3) {
			System.out.println("Falsche Eingabe \nDie Optionen lauten 1,2 oder 3.");
			option = keyboard.nextInt();			
		}
		switch (option) {
		case 1:
			System.out.println("Folgende Zahlen wurden gespeichert: ");
			changeUnglueckszahlen();
			System.exit(0);
		case 2:
			System.out.println("Die gespeicherten Zahlen wurden gelöscht");
			deleteUnglueckszahlen();
			System.exit(0);
		case 3:
			menu(unglueckszahlen);		
		}
		keyboard.close();
		System.exit(0);
	}

}
