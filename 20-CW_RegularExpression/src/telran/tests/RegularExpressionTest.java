package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import static telran.util.RegularExpression.*;

import org.junit.jupiter.api.Test;

class RegularExpressionTest {

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
		assertTrue("a@a.a".matches(emailPattern()));
		assertTrue("kokoo@aasdas.aasd".matches(emailPattern()));
		assertTrue("kokoo@aasdas.aasd".matches(emailPattern()));
		assertTrue("ko-koo@aasdas.aasd".matches(emailPattern()));
		assertTrue("ko-koo@aasdas.aasd.ss".matches(emailPattern()));
		assertTrue("k.s@s.aasd.ss".matches(emailPattern()));
		assertTrue("k.s@s2.aasd.ss".matches(emailPattern()));
		assertTrue("k@2.aasd".matches(emailPattern()));
		
		// assertTrue tests
		assertFalse("-name@domain.com".matches(emailPattern()));
		assertFalse("name@domain.com2".matches(emailPattern()));
		assertFalse("name_@domain.com".matches(emailPattern()));
		assertFalse("nam$e@domain.com".matches(emailPattern()));
		assertFalse("$name@domain.com".matches(emailPattern()));
		assertFalse("name@@domain.com".matches(emailPattern()));
		assertFalse("name@-domain.com".matches(emailPattern()));
		assertFalse("name@domain-.com".matches(emailPattern()));
		assertFalse("name@doma#in.com".matches(emailPattern()));
		assertFalse("name@doma in.com".matches(emailPattern()));
		assertFalse("name@domain22++.com".matches(emailPattern()));
		assertFalse("name@?domain.com".matches(emailPattern()));
		assertFalse("name@domain.com.poi.low".matches(emailPattern()));
		assertFalse("name@domain.".matches(emailPattern()));
		assertFalse("name@domain.com----".matches(emailPattern()));
		assertFalse("name@d.cm.22".matches(emailPattern()));
	}
	
	@Test
	void testIsraelNumber() {
		assertTrue("+972537658899".matches(israelNumberPattern()));
		assertTrue("+972537658899".matches(israelNumberPattern()));
		assertTrue("+972 53 76 58 899".matches(israelNumberPattern()));
		assertTrue("+972-53 76 58 899".matches(israelNumberPattern()));
		assertTrue("+972 53 - 76 58 899".matches(israelNumberPattern()));
		assertTrue("+972 53 -76 58 899".matches(israelNumberPattern()));
		assertTrue("+972 53 76 58 -899".matches(israelNumberPattern()));
	}
}







