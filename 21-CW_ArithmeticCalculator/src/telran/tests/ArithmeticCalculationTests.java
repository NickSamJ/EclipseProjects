package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import static telran.util.Calculator.*;

import org.junit.jupiter.api.Test;

class ArithmeticCalculationTests {
	/* **************************
	 * 	Methods
	 * ************************/
	private Object removeAllExceptLetters(String str) {
		String newChar = "";
		String oldChar ="[^\\p{Alpha}]*";
		String res = str.replaceAll(  oldChar, newChar);
		return res;
	}
	
	/* **************************
	 * 	Tests
	 * ************************/	
	@Test
	void testReplaceAll() {
		String str = " abcd%&7777777777*t12g";
		assertEquals("abcdtg", removeAllExceptLetters(str));
		
	}
	@Test
	void testSplit() {
		String str = "  abcd%&87777777777t12g";
		String[] tokensExp = {"abcd", "t", "g"};
		String[] tokensActual = str.trim().split("[^\\p{Alpha}]+");
		
		assertArrayEquals(tokensExp, tokensActual);
		
		
	}
	@Test
	void testCalculator() {
		assertEquals(10,calculate("2 * 10 - 5*2/ 3"));
		assertEquals(10,calculate(" 2 * 10 - 5*2/ 3 ") );
		assertEquals(Double.POSITIVE_INFINITY,calculate("2 + 5 /0"));
		assertEquals(Double.NaN,calculate(" * 10 - 5*2/ 3"));
		assertEquals(Double.NaN,calculate("2 * 10 - 5*2 # 3"));
	}
	@Test
	void testGetOperationsOperands() {
		String str = "  2 * 10 - 5*2/ 3";
		String[] operands = {"2", "10", "5", "2", "3"};
		String[] operators = {"", "*", "-", "*", "/"};
		assertArrayEquals(operands, getOperands(str));
		assertArrayEquals(operators, getOperations(str));
	}
	@Test
	void testCalculateNumber() {
		assertEquals(10, calculateOne(20, "2", "/"));
		assertEquals(10, calculateOne(5, "2", "*"));
		assertEquals(Double.NaN, calculateOne(5, "2", "#"));
		assertEquals(Double.POSITIVE_INFINITY, calculateOne(5, "0", "/"));
//		System.out.println(Double.parseDouble("") + 5);
	}

}
