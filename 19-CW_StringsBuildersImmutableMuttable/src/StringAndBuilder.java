import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringAndBuilder {

	@Test
	void testStringReplace() {
		String str = "Hello";
		String res = str.replace("l", "*");
		assertEquals("He**o", res);
	}

	@Test
	void testStringbuilder() {
		StringBuilder str  = new StringBuilder("Hello");
		str.replace(2, 4, "**");
		assertEquals("He**o", str.toString());
	}
}
