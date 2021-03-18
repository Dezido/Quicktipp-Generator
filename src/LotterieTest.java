import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;


class LotterieTest {

	Lotterie l1 = new Lotterie(5,10) { // fünf Kugeln ziehen
		public void generate(int[] unglueckszahlen) {}
		public boolean areValid(int[] unglueckszahlen) {return false;}
	};
	Lotterie l2 = new Lotterie(100,100){ //alle Kugeln ziehen
		public void generate(int[] unglueckszahlen) {}
		public boolean areValid(int[] unglueckszahlen) {return false;}
	};
	Lotterie l3 = new Lotterie(0, 10){ //keine Kugeln ziehen
		public void generate(int[] unglueckszahlen) {}
		public boolean areValid(int[] unglueckszahlen) {return false;}
	};
	Lotterie l4 = new Lotterie(1, 100){ //nur eine Kugel ziehen
		public void generate(int[] unglueckszahlen) {}
		public boolean areValid(int[] unglueckszahlen) {return false;}
	};
	Lotterie l5 = new Lotterie(1,5) { //eine Kugel aus der Mitte ziehen
		public void generate(int[] unglueckszahlen) {}
		public boolean areValid(int[] unglueckszahlen) {return false;}
	};		
	private int[] e1 = {1,4,6,9,10}; //übrig gebliebene Zahlen erwartet
	private int[] e2 = IntStream.range(1, 101).toArray(); //alle Kugeln von 1-100 erwartet
	private int[] e3 = {};
	private int[] e4 = {1}; //1 erwartet, da 2-100 UZ
	private int[] e5 = {3}; //3 erwartet, da 1,2,4 und 5 UZ
	private int[] u1 = {2,3,5,7,8}; //Unglückszahlen unzusammenhängend
	private int[] u2 = {};
	private int[] u3 = {};
	private int[] u4 = IntStream.range(2, 101).toArray(); //UZ 2-100
	private int[] u5 = {1,2,4,5};

	@Test
	public void testZahlenTippen() {	
		assertArrayEquals(l1.zahlenZiehen(l1.getZuZiehendeKugeln(), l1.getGesamtzahlKugeln(), u1), e1);
		assertArrayEquals(l2.zahlenZiehen(l2.getZuZiehendeKugeln(), l2.getGesamtzahlKugeln(), u2), e2);
		assertArrayEquals(l3.zahlenZiehen(l3.getZuZiehendeKugeln(), l3.getGesamtzahlKugeln(), u3), e3);
		assertArrayEquals(l4.zahlenZiehen(l4.getZuZiehendeKugeln(), l4.getGesamtzahlKugeln(), u4), e4);
		assertArrayEquals(l5.zahlenZiehen(l5.getZuZiehendeKugeln(), l5.getGesamtzahlKugeln(), u5), e5);
	}
}