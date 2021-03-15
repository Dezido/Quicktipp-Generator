import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
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

	public static boolean checkParsedArgs (int [] unglueckszahlen) {		
		for(int i=0; i<unglueckszahlen.length;i++) {
			if(unglueckszahlen[i]<0||unglueckszahlen[i]>50) {
				return false;
			}
		}
		return true;
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

	public static boolean filePresent() {
		File f = new File("Unglueckszahlen.txt");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void deleteUnglueckszahlen(){
		try {
			PrintWriter pw = new PrintWriter("Unglueckszahlen.txt");
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveUnglueckszahlen(int [] unglueckszahlen) {
		try {
			PrintStream printOut = new PrintStream("Unglueckszahlen.txt"); //Unglückszahlen als Textdatei speichern
			for(int i=0;i<unglueckszahlen.length;i++) {  
				printOut.println(unglueckszahlen[i]);
			}
			printOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("Die Datei zum speichern der Unglückszahlen ist nicht vorhanden");
			e.printStackTrace();
		}				
	}

	public static int [] loadUnglueckszahlen () {
		int [] unglueckszahlen= new int [howManySaved()];
		try {
			Scanner fileIn = new Scanner (new File("Unglueckszahlen.txt"));
			for(int i=0;i<howManySaved();i++) {
					unglueckszahlen[i]=fileIn.nextInt();
					}
			} catch (FileNotFoundException e) {
				System.out.println("Die Datei zum laden der Unglückszahlen ist nicht vorhanden");
				e.printStackTrace();
			}
		return unglueckszahlen;
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
				System.out.println("Die Datei zum laden der Unglückszahlen ist nicht vorhanden");
				e.printStackTrace();
			}
		return count;
	}


	private static void menu (int [] unglueckszahlen) {
		
		filePresent();
		
		if(!checkParsedArgs(unglueckszahlen)) {
			System.out.println("Die übergebenen Unglückszahlen sind ungültig. Es können maximal 6 Zahlen zwischen 0 und 50 verwendet werden");
			System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Ünglückszahlen auf");
			System.exit(0);
		}
		if(unglueckszahlen.length==0) {
			if(unglueckszahlenSaved()) {					
				System.out.println("Sie haben keine neuen Ünglückszahlen übergeben");
				System.out.println("Es werden die gespeicherten Unglückszahlen verwendet");
				unglueckszahlen=loadUnglueckszahlen();
			}else {
				System.err.println("Es sind keine gespeicherten Unglückszahlen vorhanden");
				System.out.println("Rufen Sie bitte die Anwendung erneut mit gültigen Ünglückszahlen auf");
				System.exit(0);
			}
		}		
		System.out.println("Folgende Unglückszahlen werden verwendet: ");
		for(int i=0; i<unglueckszahlen.length;i++) {
			if(unglueckszahlen[i]!=-1) {
				System.out.print(unglueckszahlen[i]+ " ");
			}
		}		
		System.out.println();
		System.out.println("Sie haben folgende Optionen:");
		System.out.println();
		System.out.println("1: Lotto 6aus49 Quicktipp erstellen");
		System.out.println("2: Eurojackpot Quicktipp erstellen");
		System.out.println("3: Gespeicherte Unglückszahlen löschen");
		System.out.println("4: Programm beenden");						
		Scanner keyboard = new Scanner(System.in);
		int option = keyboard.nextInt();			
		while (option <0||option >4) {
			System.out.println("Falsche Eingabe \nDie Optionen lauten 1,2,3 oder 4");
			option = keyboard.nextInt();			
		}
		switch (option) {
		case 1:
			lottoQuicktipp(unglueckszahlen);
			saveUnglueckszahlen(unglueckszahlen);
			System.exit(0);
		case 2:
			eurojackpotQuicktipp(unglueckszahlen);
			saveUnglueckszahlen(unglueckszahlen);
			System.exit(0);
		case 3:
			if(!unglueckszahlenSaved()) {
				System.out.println("Es sind keine gespeicherten Unglückszahlen vorhanden");
				System.out.println("Bitte wählen Sie eine andere Option");
			}else {
			deleteUnglueckszahlen();
			System.out.println("Die gespeicherten Unglückszahlen wurden gelöscht");
			System.out.println("Programm wird beendet");
			System.out.println("Danke für die Nutzung des Quicktipp-Generators");
			keyboard.close();
			System.exit(0);
			}			
		case 4:
			System.out.println("Programm wird beendet");
			System.out.println("Danke für die Nutzung des Quicktipp-Generators");
			keyboard.close();
			System.exit(0);
		}
	}

	private static boolean unglueckszahlenSaved() {
		int [] unglueckszahlen = loadUnglueckszahlen();
		if(unglueckszahlen.length==0) {
			return false;
		}	
		return true;
	}
}