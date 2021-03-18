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
				logger.info("übergebene Argumente: " + java.util.Arrays.toString(args));
				isfilePresent();
				menu(parseArgs(args));			
			}
		} catch (NumberFormatException e) {
			logger.severe("Argumente ungültig: " + java.util.Arrays.toString(args)+"\n");
			System.out.println("Bitte geben Sie "+ "java Quicktipp" + " gefolgt von bis zu sechs Unglückszahlen ein");
			System.out.println("Die Unglückszahlen müssen ganzzahlig sein und Werte zwischen 0 und 50 haben");
		}
	}

	/*
	 * parsed die vom Benutzer übergebenen Argumente von String zu int
	 */
	public static int[] parseArgs (String [] args) {		
		int [] unglueckszahlen = new int [args.length];
		for(int i=0;i<args.length;i++) {
			unglueckszahlen[i]=Integer.parseInt(args[i]);
		}
		logger.info("Argumente werden geparsed.");
		return unglueckszahlen;		
	}

	/*
	 * Überprüft, ob die übergebenen Argumente gültig sind
	 */
	public static boolean checkParsedArgs (int [] unglueckszahlen) {

		if(unglueckszahlen.length>6) {
			logger.info("Der Benutzer hat mehr als 6 Zahlen übergeben.\n");
			System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es können bis zu 6 verschiedene Zahlen zwischen 0 und 50 verwendet werden");
			System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Unglückszahlen auf");
			return false;
		}
		for(int i=0; i<unglueckszahlen.length;i++) {
			if(unglueckszahlen[i]<0) {
				logger.info("Ungültige Eigabe: " + unglueckszahlen[i] + " ist kleiner als 0.\n");
				System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es können bis zu 6 verschiedene Zahlen zwischen 0 und 50 verwendet werden");
				System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Unglückszahlen auf");
				return false;
			}
			if(unglueckszahlen[i]>50) {
				logger.info("Ungültige Eigabe: " + unglueckszahlen[i] + " ist größer als 50.\n");
				System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es künnen bis zu 6 verschiedene Zahlen zwischen 0 und 50 verwendet werden");
				System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Unglückszahlen auf");
				return false;
			}
			for(int j=0; j<unglueckszahlen.length;j++) {
				if(unglueckszahlen[i]==unglueckszahlen[j]&&i!=j) {
					logger.info("Ungültige Eingabe: " + unglueckszahlen[i] +  " ist mehrfach vorhanden.\n");
					System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es können bis zu 6 verschiedene Zahlen zwischen 0 und 50 verwendet werden");
					System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Unglückszahlen auf");
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * prüft ob gespeicherte UZ vorhanden sind, falls der Benutzer keine übergibt
	 */
	public static void unglueckszahlenSource(int [] unglueckszahlen) {

		if(unglueckszahlen.length==0) {
			if(areUnglueckszahlenSaved()) {					
				System.out.println("Sie haben keine neuen Unglückszahlen übergeben");
				System.out.println("Es werden die gespeicherten Unglückszahlen verwendet");
				unglueckszahlen=loadUnglueckszahlen();
				logger.info("Keine neuen Unglückszahlen übergeben. Es werden die gespeicherten Zahlen verwendet.");
			}else {
				System.err.println("Es sind keine gespeicherten Unglückszahlen vorhanden");
				System.out.println("Bitte geben Sie "+ "java Quicktipp" + " gefolgt von bis zu sechs Unglückszahlen ein");
				System.out.println("Die Unglückszahlen müssen ganzzahlig sein und Werte zwischen 0 und 50 haben");
				logger.info("Keine gespeicherten Unglückszahlen vorhanden. Das Programm wird beendet.\n");
				System.exit(0);
			}
		}
		System.out.println();
		System.out.println("------------------------------------------");		
		String singOrPlur = (unglueckszahlen.length==1) ? "Unglückszahl wird" : "Unglückszahlen werden";
		System.out.println("Folgende " +singOrPlur + " verwendet:\n ");
		System.out.println(java.util.Arrays.toString(unglueckszahlen));
		System.out.println("------------------------------------------");	
		logger.info("Die " + singOrPlur + " dem Benutzer angezeigt." + java.util.Arrays.toString(unglueckszahlen));
	}

	/*
	 * erstellt den Quicktipp für Lotto und gibt in aus
	 */
	public static void lottoQuicktipp(int [] unglueckszahlen) {		
		Lotto lotto = new Lotto(6,49);
		lotto.generate(unglueckszahlen);
		logger.info("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
		logger.info("Superzahl: " + lotto.getSuperzahl());	
		System.err.println();
		System.out.println("Soll zusätzlich zur Tippreihe (6 aus 49) eine Superzahl generiert werden?");
		System.out.println("1:Ja");
		System.out.println("2:Nein");
		System.out.println();
		System.out.print("Eingabe: ");
		try {
			Scanner keyboard = new Scanner(System.in);
			int option = keyboard.nextInt();			
			while (option<1||option>2) {
				System.out.println("Ungültige Eingabe");
				System.out.println("1:Ja");
				System.out.println("2:Nein");
				System.out.println();
				System.out.print("Eingabe: ");
				option = keyboard.nextInt();
			}

			if(option==1) {
				logger.info("Benutzer wünscht Superzahl.");
				System.out.println();
				System.out.println();
				System.out.println("******************************************");
				System.out.println("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
				System.out.println("Superzahl: " +lotto.getSuperzahl());
				System.out.println("******************************************");
				System.out.println();
			}
			if(option==2) {
				logger.info("Benutzer wünscht keine Superzahl.");
				System.out.println();
				System.out.println();
				System.out.println("******************************************");
				System.out.println("Tippreihe Lotto: " + java.util.Arrays.toString(lotto.getTippreihe()));
				System.out.println("******************************************");
				System.out.println();
			}

		} catch ( InputMismatchException e) {
			logger.severe("Ungültige Eingabe durch den Benutzer.");	
			System.out.println("Ungültige Eingabe");
			lottoQuicktipp(unglueckszahlen);

		}


	}

	/*
	 * erstellt den Quicktipp für Eurojackpot und gibt in aus
	 */
	public static void eurojackpotQuicktipp(int[] unglueckszahlen) {
		Eurojackpot eurojackpot = new Eurojackpot (5,50);			
		eurojackpot.generate(unglueckszahlen);				
		logger.info("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		logger.info("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));		
		System.out.println();
		System.out.println();
		System.out.println("********************************************");
		System.out.println("Tippreihe Eurojackpot: " + java.util.Arrays.toString(eurojackpot.getTippreihe()));
		System.out.println("Eurozahlen: " + java.util.Arrays.toString(eurojackpot.getEurozahlen()));
		System.out.println("********************************************");
		System.out.println();
	}

	/*
	 * prüft ob die Datei zum Speichern der UZ vorhanden ist, erstellt sie sonst
	 */
	public static boolean isfilePresent() {
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

	/*
	 * löscht die gespeicherten UZ
	 */
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

	/*
	 * speichert die UZ in die dafür vorgesehene Textdatei
	 */
	public static void saveUnglueckszahlen(int [] unglueckszahlen) {
		try {
			PrintStream printOut = new PrintStream("Unglueckszahlen.txt"); //Ungl�ckszahlen als Textdatei speichern
			for(int i=0;i<unglueckszahlen.length;i++) {  
				printOut.println(unglueckszahlen[i]);
			}
			printOut.close();
			logger.info("übergebene Unglückszahlen wurden in Unglueckszahlen.txt gespeichert.");
		} catch (FileNotFoundException e) {
			logger.severe("Unglueckszahlen.txt ist nicht vorhanden.");
			System.out.println("Die Datei zum Speichern der Unglückszahlen ist nicht vorhanden");
		}				
	}

	/*
	 * lädt die UZ 
	 */
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

	/*
	 * prüft ob UZ gespeichert sind
	 */
	private static boolean areUnglueckszahlenSaved() {
		int [] unglueckszahlen = loadUnglueckszahlen();
		if(unglueckszahlen.length==0) {
			return false;
		}	
		logger.info("Es sind gespeicherte Unglückzahlen in Unglueckszahlen.txt vorhanden.");
		return true;
	}

	/*
	 * gibt zurück wie viele Unglückszahlenb gespeichert sind
	 * der wert wird benätigt um das Array aus den gespeicherten Zahlen zu erstellen 
	 */
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
	/*
	 * gibt dem Nutzer die Menüoptionen aus
	 */
	public static void printMenuOptions() {
		System.out.println();
		System.out.println("Sie haben folgende Optionen:");
		System.out.println();
		System.out.println("1: Lotto 6aus49 Quicktipp erstellen");
		System.out.println("2: Eurojackpot Quicktipp erstellen");
		if(areUnglueckszahlenSaved()) {
			System.out.println("3: Gespeicherte Unglückszahlen löschen (gespeicherte Unglückszahlen: " + java.util.Arrays.toString(loadUnglueckszahlen())+")");
			System.out.println("   Hinweis: Programm wird nach dem Löschen beendet");

		}else {
			System.out.println("3: Gespeicherte Unglückszahlen löschen (Keine gespeicherten Zahlen zum Löschen vorhanden)");
		}
		System.out.println("4: Programm beenden");
		System.out.println();
		System.out.println("Eingabe: ");
		logger.info("Die Optionen werrden dem Benutzer angezeigt.");
	}
	/*
	 * verwaltet Benutzeingaben und ruft die Methoden zur Erstellung und zum Anzeigen der Quicktipps auf
	 */
	public static  void createQuicktipp (int [] unglueckszahlen) {
		try {
			Scanner keyboard = new Scanner(System.in);
			int option = keyboard.nextInt();			
			while (option <0||option >4) {
				System.out.println("Ungültige Eingabe");
				printMenuOptions();
				option = keyboard.nextInt();
			}

			switch (option) {
			case 1:
				logger.info("Der Benutzer hat Option 1 (Lotto) gewählt. Der Quicktipp wird erstellt und angezeigt.");
				lottoQuicktipp(unglueckszahlen);
				if(unglueckszahlen.length>0) {
					saveUnglueckszahlen(unglueckszahlen);
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					logger.severe("Der Thread wurde unterbrochen.");
					menu(unglueckszahlen);
				}
				menu(unglueckszahlen);
			case 2:
				logger.info("Der Benutzer hat Option 2 (Eurojackpot) gewählt. Der Quicktipp wird erstellt und angezeigt.");
				eurojackpotQuicktipp(unglueckszahlen);
				if(unglueckszahlen.length>0) {
					saveUnglueckszahlen(unglueckszahlen);
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					logger.severe("Der Thread wurde unterbrochen.");
					menu(unglueckszahlen);
				}
				menu(unglueckszahlen);
			case 3:
				logger.info("Der Benutzer hat Option 3 (Unglückszahlen löschen) gewählt.");
				if(!areUnglueckszahlenSaved()) {
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

	/*
	 * informiert den Benutzer, dass 0 nur bei den Superzahlen im Spiel Lotto und 50 im Eurojackpot verwendet wird
	 */
	public static void printEdgeCaseInfo(int [] unglueckszahlen) {
		for(int i=0; i<unglueckszahlen.length;i++) {
			if(unglueckszahlen[i]==0){
				System.out.println("\nHinweis: Die Unglückszahl 0 wird nur bei der Ziehung der Superzahl im Spiel Lotto verwendet");
				logger.info("Benutzer darauf hingewiesen, dass 0 nur zur Superzahlziehung verwendet wird.");
			}
			if(unglueckszahlen[i]==50) {
				System.out.println("\nHinweis: Die Unglückszahl 50 wird nur im Spiel Eurojackpot verwendet");
				logger.info("Benutzer darauf hingewiesen, dass 50 nur zur Eurozahlziehung verwendet wird.");
			}
		}
	}

	/*
	 * steuert den Programmablauf
	 */
	private static void menu (int [] unglueckszahlen) {
		printEdgeCaseInfo(unglueckszahlen);
		unglueckszahlenSource(unglueckszahlen);
		printMenuOptions();	
		createQuicktipp(unglueckszahlen);		
	}
}