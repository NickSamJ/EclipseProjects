package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;
import telran.numbers.DividerRule;
import telran.numbers.Generator;

class GeneratorRulesTests {

	DividerRule divider10 = new DividerRule(10);
	int min = 0, max = 10000, nNumbers = 1000000;
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
				divider10.checkRule(12, 11, 19);
				fail("Expected RangeException");
			}catch (RangeException e) {
				
			} catch (RuleException e) {
				fail("Unexpected Exception");
			}
		}
}


