package telran.numbers.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.numbers.Calculator;

class CalculatorTest {

	@Test
	void testSum() {
//		fail("Not yet implemented");
		assertEquals(20, Calculator.sum(10, 10));
	}

	@Test
	void testDivide() {
		assertEquals(0, Calculator.divide(10, 0));
		assertEquals(0.5, Calculator.divide(1, 2), 0.1);

//		fail("Not yet implemented");
	}
	
	@Test
	void testMultiply() {
		assertEquals(10, Calculator.multiply(2, 5));
		assertEquals(0, Calculator.multiply(2, 0), "2x0 has to be 0");
		assertEquals(10, Calculator.multiply(2, 5));

//		fail("Not yet implemented");
	}
	
	@Test
	void testMinus() {
		assertEquals(8, Calculator.minus(10, 2));
		assertEquals(12, Calculator.minus(10, -2));

//		fail("Not yet implemented");
	}

}

