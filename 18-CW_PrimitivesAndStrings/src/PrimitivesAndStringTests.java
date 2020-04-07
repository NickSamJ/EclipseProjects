import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrimitivesAndStringTests {

	@Test
	void testInteger() {
		Integer A = 500;
		Integer B = 100;
		Integer C = 500;
		Integer D = 100;
		assertEquals(A, C);
//		assertTrue(A == C); // A and C are different objects
		assertEquals(B, D); 
		assertTrue(B == D); //  B == C  -> True (Because values -128:127 are in Cache !!!) 
	}
	
	@Test
	void testDouble(){
		double a  = Double.NaN;
		double b  = Double.NaN;
		assertTrue(Double.isNaN(a) && Double.isNaN(b));
		double pi1 = 3.1472065;
		double pi2 = 3.1472065;
		assertTrue(pi1 == pi2);
		
		assertEquals(pi1, pi2, 0.01);
		
	}
	
	@Test
	void testStringsEquals(){
		String str1 = "Hello";
		String str2 = "Hello";
		String str3 = new String("Hello");
		assertTrue(str1 == str2); 	// Links to the same object 
		assertFalse(str1 == str3); // Links to different objects
		assertEquals(str1, str3);
	}

}
