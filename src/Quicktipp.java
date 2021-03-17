import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Quicktipp {

	private static Logger logger = QuicktippLogger.setupLogger();	

	public static void main(String[] args) {
		try {		
			if(checkParsedArgs(parseArgs(args))) {
				logger.info("Übergebene Argumente: " + java.util.Arrays.toString(args));
				filePresent();
				menu(parseArgs(args));			
			}
		} catch (NumberFormatException e) {
			logger.severe("Argumente ungültig: " + java.util.Arrays.toString(args)+"\n");
			System.out.println("Bitte geben Sie "+ "java Quicktipp" + " gefolgt von bis zu sechs Unglückszahlen ein");
			System.out.println("Die Ünglückszahlen müssen ganzzahlig sein und Werte zwischen 0 und 50 haben");
		}
	}

	public static int[] parseArgs (String [] args) {		
		int [] unglueckszahlen = new int [args.length];
		for(int i=0;i<args.length;i++) {
			unglueckszahlen[i]=Integer.parseInt(args[i]);
		}
		return unglueckszahlen;		
	}

	public static boolean checkParsedArgs (int [] unglueckszahlen) {

		if(unglueckszahlen.length>6) {
			logger.info("Der Benutzer hat mehr als 6 Zahlen übergeben.\n");
			System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es können bis zu 6 verschiedene Zahlen zwischen 0 und 50 verwendet werden");
			System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Ünglückszahlen auf");
			return false;
		}
		for(int i=0; i<unglueckszahlen.length;i++) {
			if(unglueckszahlen[i]<0) {
				logger.info("Ungültige Eigabe: " + unglueckszahlen[i] + " ist kleiner als 0.\n");
				System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es können bis zu 6 verschiedene Zahlen zwischen 0 und 50 verwendet werden");
				System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Ünglückszahlen auf");
				return false;
			}
			if(unglueckszahlen[i]>50) {
				logger.info("Ungültige Eigabe: " + unglueckszahlen[i] + " ist größer als 50.\n");
				System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es können bis zu 6 verschiedene Zahlen zwischen 0 und 50 verwendet werden");
				System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Ünglückszahlen auf");
				return false;
			}
			for(int j=0; j<unglueckszahlen.length;j++) {
				if(unglueckszahlen[i]==unglueckszahlen[j]&&i!=j) {
					logger.info("Ungültige Eingabe: " + unglueckszahlen[i] +  " ist mehrfach vorhanden.\n");
					System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es können bis zu 6 verschiedene Zahlen zwischen 0 und 50 verwendet werden");
					System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Ünglückszahlen auf");
					return false;
				}
			}
		}
		return true;
	}


	public static void unglueckszahlenSource(int [] unglueckszahlen) {

		if(unglueckszahlen.length==0) {
			if(unglueckszahlenSaved()) {					
				System.out.println("Sie haben keine neuen Ünglückszahlen übergeben");
				System.out.println("Es werden die gespeicherten Unglückszahlen verwendet");
				unglueckszahlen=loadUnglueckszahlen();
				logger.info("Keine neuen Unglückszahlen übergeben. Es werden die gespeicherten Zahlen verwendet.");
			}else {
				System.err.println("Es sind keine gespeicherten Unglückszahlen vorhanden");
				System.out.println("Bitte geben Sie "+ "java Quicktipp" + " gefolgt von bis zu sechs Unglückszahlen ein");
				System.out.println("Die Ünglückszahlen müssen ganzzahlig sein und Werte zwischen 0 und 50 haben");
				logger.info("Keine gespeicherten Unglückszahlen vorhanden. Das Programm wird beendet.\n");
				System.exit(0);
			}
		}
		System.out.println("------------------------------------------");		
		System.out.println("Folgende Unglückszahlen werden verwendet:\n ");
		System.out.println(java.util.Arrays.toString(unglueckszahlen));
		System.out.println("------------------------------------------");	
		logger.info("Die Unglückszahlen " + java.util.Arrays.toString(unglueckszahlen) + " werden dem Benutzer angezeigt.");
	}

	public static void lottoQuicktipp(int [] unglueckszahlen) {		
		Lotto lotto = new Lotto(6,49);
		lotto.generate(unglueckszahlen);
		logger.info("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
		logger.info("Superzahl: " + lotto.getSuperzahl());
		System.out.println("******************************************");
		System.out.println("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
		System.out.println("Superzahl: " +lotto.getSuperzahl());
		System.out.println("******************************************");
		System.out.println();

	}

	public static void eurojackpotQuicktipp(int[] unglueckszahlen) {
		Eurojackpot eurojackpot = new Eurojackpot (5,50);			
		eurojackpot.generate(unglueckszahlen);				
		logger.info("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		logger.info("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));				
		System.out.println("********************************************");
		System.out.println("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		System.out.println("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));
		System.out.println("********************************************");
		System.out.println();
	}

	public static boolean filePresent() {
		File f = new File("Unglueckszahlen.txt");
		if(f.exists() && !f.isDirectory()) { 
			logger.info("Unglueckszahlen.txt vorhanden.");
			return true;
		}
		try {
			f.createNewFile();
			logger.info("Datei zum Speichern der Unglückszahlen nicht vorhanden. Unglueckszahlen.txt wurde erstellt.");
		} catch (IOException e) {
			System.out.println("Erstellen der Datei zum Speichern der Unglückszahlen fehlgeschlagen");
			logger.severe("Erstellen der Datei zum Speichern der Unglückszahlen fehlgeschlagen.");
		}
		return false;
	}

	public static void deleteUnglueckszahlen(){
		try {			
			PrintWriter pw = new PrintWriter("Unglueckszahlen.txt");
			pw.close();
			logger.info("Inhalt von Unglueckszahlen.txt wurde gelöscht.");
		} catch (FileNotFoundException e) {
			System.out.println("Die Datei zum Speichern der Unglückszahlen ist nicht vorhanden");
			logger.severe("Unglueckszahlen.txt ist nicht vorhanden.");
		}
	}

	public static void saveUnglueckszahlen(int [] unglueckszahlen) {
		try {
			PrintStream printOut = new PrintStream("Unglueckszahlen.txt"); //Unglückszahlen als Textdatei speichern
			for(int i=0;i<unglueckszahlen.length;i++) {  
				printOut.println(unglueckszahlen[i]);
			}
			printOut.close();
			logger.info("Übergebene Unglückszahlen wurden in Unglueckszahlen.txt gespeichert.");
		} catch (FileNotFoundException e) {
			logger.severe("Unglueckszahlen.txt ist nicht vorhanden.");
			System.out.println("Die Datei zum Speichern der Unglückszahlen ist nicht vorhanden");
		}				
	}

	public static int [] loadUnglueckszahlen () {
		int [] unglueckszahlen= new int [howManySaved()];
		try {
			Scanner fileIn = new Scanner (new File("Unglueckszahlen.txt"));
			for(int i=0;i<unglueckszahlen.length;i++) {
				unglueckszahlen[i]=fileIn.nextInt();
			}
		} catch (FileNotFoundException e) {
			logger.severe("Unglueckszahlen.txt ist nicht vorhanden.\n");
			System.out.println("Die Datei zum Laden der Unglückszahlen ist nicht vorhanden");
		}
		return unglueckszahlen;
	}

	private static boolean unglueckszahlenSaved() {
		int [] unglueckszahlen = loadUnglueckszahlen();
		if(unglueckszahlen.length==0) {
			return false;
		}	
		logger.info("Es sind gespeicherte Unglückzahlen in Unglueckszahlen.txt vorhanden.");
		return true;
	}

	public static int howManySaved () {
		int count=0;
		try {
			Scanner fileIn = new Scanner (new File("Unglueckszahlen.txt"));
			while(fileIn.hasNext()) {
				count++;
				fileIn.next();
			}				
		} catch (FileNotFoundException e) {
			logger.severe("Unglueckszahlen.txt ist nicht vorhanden.\n");
			System.out.println("Die Datei zum Laden der Unglückszahlen ist nicht vorhanden");
		}
		return count;
	}

	public static void printMenuOptions() {
		System.out.println();
		System.out.println("Sie haben folgende Optionen:");
		System.out.println();
		System.out.println("1: Lotto 6aus49 Quicktipp erstellen");
		System.out.println("2: Eurojackpot Quicktipp erstellen");
		System.out.println("3: Gespeicherte Unglückszahlen löschen");
		System.out.println("4: Programm beenden");
		System.out.println();
		logger.info("Die Optionen wurden dem Benutzer angezeigt.");
	}

	public static  void createQuicktipp (int [] unglueckszahlen) {
		try {
			Scanner keyboard = new Scanner(System.in);
			int option = keyboard.nextInt();			
			while (option <0||option >4) {
				System.out.println("Falsche Eingabe \nDie Optionen lauten 1,2,3 oder 4");
				option = keyboard.nextInt();
			}
			switch (option) {
			case 1:
				logger.info("Der Benutzer hat Option 1 (Lotto) gewählt. Der Quicktipp wurde erstellt und angezeigt.");
				lottoQuicktipp(unglueckszahlen);
				if(unglueckszahlen.length>0) {
					saveUnglueckszahlen(unglueckszahlen);
				}
				menu(unglueckszahlen);
			case 2:
				logger.info("Der Benutzer hat Option 2 (Eurojackpot) gewählt. Der Quicktipp wurde erstellt und angezeigt.");
				eurojackpotQuicktipp(unglueckszahlen);
				if(unglueckszahlen.length>0) {
					saveUnglueckszahlen(unglueckszahlen);
				}
				menu(unglueckszahlen);
			case 3:
				logger.info("Der Benutzer hat Option 3 (Unglückszahlen löschen) gewählt.");
				if(!unglueckszahlenSaved()) {
					System.out.println("Es sind keine gespeicherten Unglückszahlen vorhanden");
					System.out.println("Bitte wählen Sie eine andere Option");
					logger.info("Es sind aber keine Zahlen zum Löschen vorhanden.");
					menu(unglueckszahlen);
				}else {						
					deleteUnglueckszahlen();
					System.out.println("Die gespeicherten Unglückszahlen wurden gelöscht");
					System.out.println("Programm wird beendet");
					System.out.println("Danke für die Nutzung des Quicktipp-Generators");
					keyboard.close();
					logger.info("Die in Unglueckszahlen.txt gespeicherten Unglückszahlen wurden gelöscht. Das Programm wird beendet.\n");
					System.exit(0);
				}			
			case 4:
				System.out.println("Programm wird beendet");
				System.out.println("Danke für die Nutzung des Quicktipp-Generators");
				keyboard.close();
				logger.info("Der Benutzer hat Option 4 (Programm beenden) gewählt.");
				logger.info("Das Programm wird beendet.\n");
				System.exit(0);				
			}
		} catch (InputMismatchException e) {
			logger.severe("Ungültige Eingabe durch den Benutzer. Das Menü wird erneut aufgerufen.");
			System.out.println("Sie können eine der Option mit den Tasten 1,2,3 oder 4 auswählen");
			menu(unglueckszahlen);
		}
	}


	private static void menu (int [] unglueckszahlen) {

		unglueckszahlenSource(unglueckszahlen);
		printMenuOptions();	
		createQuicktipp(unglueckszahlen);		
	}
}