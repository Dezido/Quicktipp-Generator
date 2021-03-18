import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EurojackpotTest {

	Eurojackpot e1 = new Eurojackpot(5,50);
	Eurojackpot e2 = new Eurojackpot(2,10);

	/*
	 * verschiedene Kombinationen von Unglückszahlen
	 */
	private int [] u1 = {1,2,3,4};
	private int [] u2 = {46,47,48,49,50};
	private int [] u3 = {5,10,15,20,25,};
	private int [] u4 = {10,20,30,40};
	private int [] u5 = {17,24,13,11,42};
	private int [] u6 = {4,5,6,7,8,9};
	
	/*
	 * Testfälle Eurojackpot 5 aus 50 Ziehung
	 */
	private int [] t1 = e1.zahlenZiehen(e1.getZuZiehendeKugeln(), e1.getGesamtzahlKugeln(), u1);
	private int [] t2 = e1.zahlenZiehen(e1.getZuZiehendeKugeln(), e1.getGesamtzahlKugeln(), u2);
	private int [] t3 = e1.zahlenZiehen(e1.getZuZiehendeKugeln(), e1.getGesamtzahlKugeln(), u3);
	private int [] t4 = e1.zahlenZiehen(e1.getZuZiehendeKugeln(), e1.getGesamtzahlKugeln(), u4);
	private int [] t5 = e1.zahlenZiehen(e1.getZuZiehendeKugeln(), e1.getGesamtzahlKugeln(), u5);
	
	/*
	 * Testfälle Eurozahlen
	 */
	private int [] ez1 = e2.zahlenZiehen(e2.getZuZiehendeKugeln(), e2.getGesamtzahlKugeln(), u1);
	private int [] ez2 = e2.zahlenZiehen(e2.getZuZiehendeKugeln(), e2.getGesamtzahlKugeln(), u6);
	
	
	@Test
	void testZahlenTippen1() {
		for(int i=0;i<t1.length;i++) {
			for(int j=0;j<u1.length;j++) {
			assertFalse(t1[i]==u1[j]);
			}
		}
	}
	
	@Test
	void testZahlenTippen2() {
		for(int i=0;i<t2.length;i++) {
			for(int j=0;j<u2.length;j++) {
			assertFalse(t2[i]==u2[j]);
			}
		}
	}
	
	@Test
	void testZahlenTippen3() {
		for(int i=0;i<t3.length;i++) {
			for(int j=0;j<u3.length;j++) {
			assertFalse(t3[i]==u3[j]);
			}
		}
	}
	
	@Test
	void testZahlenTippen4() {
		for(int i=0;i<t4.length;i++) {
			for(int j=0;j<u4.length;j++) {
			assertFalse(t4[i]==u4[j]);
			}
		}
	}
	
	@Test
	void testZahlenTippen5() {
		for(int i=0;i<t5.length;i++) {
			for(int j=0;j<u5.length;j++) {
			assertFalse(t5[i]==u5[j]);
			}
		}
	}
	
	@Test
	void testEurozahlen1() {
		for(int i=0;i<ez1.length;i++) {
			for(int j=0;j<u1.length;j++) {
			assertFalse(ez1[i]==u1[j]);
			}
		}
	}
	
	@Test
	void testEurozahlen2() {
		for(int i=0;i<ez2.length;i++) {
			for(int j=0;j<u6.length;j++) {
			assertFalse(ez2[i]==u6[j]);
			}
		}
	}
	
}
