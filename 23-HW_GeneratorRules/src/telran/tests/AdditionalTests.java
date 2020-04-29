package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;
import telran.numbers.DividerRule;
import telran.numbers.Generator;

class AdditionalTests {

	
		
		@Test
		void testTemp() {
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
		void testTemp2() {
			DividerRule divider10 = new DividerRule(10);
			int min = 1, max = 8;
			try {
				divider10.checkRule(5, min, max);
				fail("Unexpected Exception");
			} catch(Exception e) {
			}		
		}
}


