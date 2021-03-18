import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LottoTest {

	Lotto l1= new Lotto (6,49);

	/*
	 * verschiedene Kombinationen von Unglückszahlen
	 */
	private int[] u1 = {1,2,3,4,5,6};
	private int[] u2 = {44,45,46,47,48,49};
	private int[] u3 = {5,10,15,20,25,30};
	private int[] u4 = {10,20,30,40};
	private int[] u5 = {17,24,13,11,42};
	
	/*
	 * Testfälle Lotto 6 aus 49 Ziehung
	 */
	private int [] t1 = l1.zahlenZiehen(l1.getZuZiehendeKugeln(), l1.getGesamtzahlKugeln(), u1);
	private int [] t2 = l1.zahlenZiehen(l1.getZuZiehendeKugeln(), l1.getGesamtzahlKugeln(), u2);
	private int [] t3 = l1.zahlenZiehen(l1.getZuZiehendeKugeln(), l1.getGesamtzahlKugeln(), u3);
	private int [] t4 = l1.zahlenZiehen(l1.getZuZiehendeKugeln(), l1.getGesamtzahlKugeln(), u4);
	private int [] t5 = l1.zahlenZiehen(l1.getZuZiehendeKugeln(), l1.getGesamtzahlKugeln(), u5);
	

	@Test
	void testZahlenTippen1() {
		for(int i=0;i<t1.length;i++) {
			for(int j=0;j<u1.length;j++) {
			assertFalse(t1[i]==u1[j]);
			assertFalse(l1.getSuperzahl()==u1[j]);
			}
		}
	}
	
	@Test
	void testZahlenTippen2() {
		for(int i=0;i<t2.length;i++) {
			for(int j=0;j<u2.length;j++) {
			assertFalse(t2[i]==u2[j]);
			assertFalse(l1.getSuperzahl()==u2[j]);
			}
		}
	}
	
	@Test
	void testZahlenTippen3() {
		for(int i=0;i<t3.length;i++) {
			for(int j=0;j<u3.length;j++) {
			assertFalse(t3[i]==u3[j]);
			assertFalse(l1.getSuperzahl()==u3[j]);
			}
		}
	}
	
	@Test
	void testZahlenTippen4() {
		for(int i=0;i<t4.length;i++) {
			for(int j=0;j<u4.length;j++) {
			assertFalse(t4[i]==u4[j]);
			assertFalse(l1.getSuperzahl()==u4[j]);
			}
		}
	}
	
	@Test
	void testZahlenTippen5() {
		for(int i=0;i<t5.length;i++) {
			for(int j=0;j<u5.length;j++) {
			assertFalse(t5[i]==u5[j]);
			assertFalse(l1.getSuperzahl()==u5[j]);
			}
		}
	}



}
