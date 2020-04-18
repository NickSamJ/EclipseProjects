package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import static telran.util.RegularExpression.*;

import org.junit.jupiter.api.Test;

class RegularExpressionTest {

	private void testTrue(String[] cases, String pattern) {
		for(String s : cases) {
			assertTrue(s.matches(pattern));
		}
	}

	private void testFalse(String[] cases, String pattern) {
		for(String s : cases) {
			assertFalse(s.matches(pattern));
		}
	}
	@Test
	void testVariableName() {
		// assertTrue tests
			
//		assertTrue("ab3c$_D".matches("^([a-z, A-Z, $, ])[a-z, A-Z, 0-9, $, _]*"));
		
		assertTrue("$".matches(variableName()));
		assertTrue("A".matches(variableName()));
		assertTrue("a$4".matches(variableName()));
		assertTrue("aAc12".matches(variableName()));
		assertTrue("__12345_t".matches(variableName()));
//		
//		// assertFalse tests
		assertFalse("_".matches(variableName()));
		assertFalse("12_".matches(variableName()));
		assertFalse("as _".matches(variableName()));
		assertFalse("as_*".matches(variableName()));
		
	}
	@Test
	void testIpV4() {
		// assertTrue tests
		assertTrue("0.0.0.0".matches(ipV4()));
		assertTrue("12.230.188.0".matches(ipV4()));
		assertTrue("0.255.0.255".matches(ipV4()));
		assertTrue("255.255.255.255".matches(ipV4()));

		// assertFalse tests
		assertFalse("280.1.2.3".matches(ipV4()));
		assertFalse("240.1.2".matches(ipV4()));
		assertFalse("*.1.2.3".matches(ipV4()));
		assertFalse("255.1.2.*".matches(ipV4()));
	}
	@Test
	void testLessThan256() {
		// assertTrue tests
		assertTrue("0".matches(numberLess256()));
		assertTrue("12".matches(numberLess256()));
		assertTrue("129".matches(numberLess256()));
		assertTrue("205".matches(numberLess256()));
		assertTrue("249".matches(numberLess256()));
		assertTrue("255".matches(numberLess256()));
//		
//		// assertFalse tests
		assertFalse("-1".matches(numberLess256()));
		assertFalse("1111".matches(numberLess256()));
		assertFalse("12#".matches(numberLess256()));
		assertFalse("1 ".matches(numberLess256()));
		assertFalse("256".matches(numberLess256()));
		assertFalse("2 7".matches(numberLess256()));
	}

	@Test 
	void testEmail() {
		// assertTrue tests
		String[] trueCases = {
				"a@a.a",
				"kokoo@aasdas.aasd",
				"ko-koo@aasdas.aasd",
				"ko-koo@aasdas.aasd.ss",
				"k.s@s.aasd.ss",
				"k.s@s.aasd.ss.ff",
				"k.s@s.aasd.ss.o-o",
				"k.s@s2.aas-d.ss",
				"k@2.aasd"
				
				};
		testTrue(trueCases, emailPattern());

		// assertFalse tests
		String[] falseCases = {
				"-name@domain.com",
				"name@domain",
				"name_@domain.com",
				"name@do_main.com",
				"n ame@domain.com",
				"name@@domain.com",
				"name@-domain.com",
				"name@domain-.com",
				"name@doma#in.com",
				"name@doma in.com",
				"name@domain22++.com",
				"name@?domain.com",
				"name@domain.com.poi.low-",
				"name@domain.",
				"name@domain.com----",
				"name,not@d.cm.22",				
		};
		testFalse(falseCases, emailPattern());
	}
	
	@Test
	void testIsraelNumber() {
		String[] trueCases = {
				"+972537658899",
				"+972-537658899",
				"+972-53-765-8899",
				"+97253-6658899",
				"+972537658-899",
		};
		testTrue(trueCases, israelNumberPattern());
		
		String[] falseCases = {
				"057+1223344",
				"050-1-22-33-445",
				"50-1-22-33-44",
				"972-50-1-22-33-445",
				"++972-050-1-22-33-44",
				"050-1-22-33-4t5",
				"057-122—-3344",
				"057-122—3344",
				"051-122-33-44\n",
		};
		testFalse(falseCases, israelNumberPattern());
		 		
	}
	
	@Test 
	void testArithmetic() {
		String[] trueTestCases = {
				"2+2",
				"2*2",
				"2/2",
				"2-2",
				"2+3 /7",
				"2 / 2 -5",
				"2-2",
				"  2 + 2 ",
				" 3 + 4/2 -15 + 42",
		};
		testTrue(trueTestCases, simpleArithmeticExpressionPattern());

		String[] falseTestCases = {
				"2++2",
				"+2",
				"-2",
				"/2++2",
				"*2++2",
				"  2 / 2 4 2",
				"2+2/",
				"2+2*",
				"2+2+",
				"2+2_",
				"a + b",
				"2-n",
		};
		testFalse(falseTestCases, simpleArithmeticExpressionPattern());
	}
}







