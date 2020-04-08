package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.JoinStringsImplBuilder;
import telran.util.JoinStringsImplString;

class JoinStringsTests {
	String[] strings = {"la", "la", "la", "bla", "bla", "bla"};
	String sep = "-";
	String expected = "la-la-la-bla-bla-bla";
	@Test
	void testJoinStringsImplString() {
		JoinStringsImplString joinString = new JoinStringsImplString();
		assertEquals(expected, joinString.join(strings, sep));
	}
	@Test
	void testJoinStringsImplBuilder() {
		JoinStringsImplBuilder joinString = new JoinStringsImplBuilder();
		assertEquals(expected, joinString.join(strings, sep));
	}
}
