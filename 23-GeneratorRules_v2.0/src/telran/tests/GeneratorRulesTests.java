package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;
import telran.numbers.DividerRule;
import telran.numbers.Generator;

class GeneratorRulesTests {
DividerRule divider10 = new DividerRule(10);
int min = 1, max = 10000, nNumbers = 1000000;
	@Test
	void testGenerate() {

		Generator generator = null;
		generator = new Generator(min, max, divider10);
		int ar[] = generator.generate(nNumbers);
		assertEquals(nNumbers, ar.length);
		for(int num: ar) {
			assertTrue(num % 10 == 0 && num >= min && num <= max);
		}
		try {
			generator = new Generator(max, min, divider10);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}
	@Test
	void testDivider()  {
		try {
			divider10.checkRule(10, min, max);
		} catch(Exception e) {
			fail("Unexpected Exception");
		}
		try {
			divider10.checkRule(12, min, max);
			fail("Expected RuleException");
		}catch (RuleException e) {
			assertEquals(-2, e.getDelta());
		}
		try {
			divider10.checkRule(12, 11, max);
			fail("Expected RuleException");
		}catch (RuleException e) {
			assertEquals(8, e.getDelta());
		}
		try {
			divider10.checkRule(2, 0, 11);
			fail("Expected RuleException");
		}catch (RuleException e) {
			assertEquals(8, e.getDelta());
		}
		try {
			divider10.checkRule(12, 11, 19);
			fail("Expected RangeException");
		}catch (RangeException e) {
			
		} catch (RuleException e) {
			fail("Unexpected Exception");
		}
	}
	
	@Test
	void testGeneratorRuleGreaterThanMax() {
		DividerRule divider100 = new DividerRule(100);
		int min = 1, max = 80, nNumbers = 10;
		Generator generator = new Generator(min, max, divider100);
		
		
		try {
			int[]arr = generator.generate(nNumbers);				
			fail("Expected RangeException");
		}catch (Exception e) {
		}
		
	}
	@Test
	void testDividerValueLessThanDivider() {
		DividerRule divider10 = new DividerRule(10);
		int min = 1, max = 8;
		try {
			divider10.checkRule(5, min, max);
			fail("Unexpected Exception");
		} catch(Exception e) {
		}	
	}
	
	@Test
	void testDivider2()
	{
		assertThrows(RangeException.class, () -> divider10.checkRule(12, 11, 19));
		assertThrows(RangeException.class, () -> divider10.checkRule(12, 19, 5));


		min = 10;

		assertDoesNotThrow(() -> divider10.checkRule(10, min, max));

		checkExceptionDelta(12, min, max, -2);
		checkExceptionDelta(9, min, max, 1);

		checkExceptionDelta(10, 11, max, 10);
		checkExceptionDelta(12, 11, max, 8);
		checkExceptionDelta(9, 11, max, 11);

		assertDoesNotThrow(() -> divider10.checkRule(100, min, 100));

		checkExceptionDelta(102, min, 100, -2);
		checkExceptionDelta(99, min, 100, 1);

		checkExceptionDelta(98, min, 99, -8);
		checkExceptionDelta(100, min, 99, -10);
	}

	private void checkExceptionDelta(int number, int min, int max, int delta)
	{
		try {
			divider10.checkRule(number, min, max);
			fail("Expected RuleException");
		} catch (RuleException e) {
			assertEquals(delta, e.getDelta());
		}
	}
}

