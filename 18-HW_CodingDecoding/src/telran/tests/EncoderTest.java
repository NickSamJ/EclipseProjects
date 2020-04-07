package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.Encoder;

class EncoderTest {

	@Test
	void testCode() {
		Encoder encoder = new Encoder("9876543210");
		assertEquals("876", encoder.code(123));
		encoder = new Encoder("+-");
		assertEquals("-+-", encoder.code(5));
	}
	@Test
	void testDecode() {
		Encoder encoder = new Encoder("9876543210");
		assertEquals(123, encoder.decode("876"));
		 encoder = new Encoder("+.");
		 assertEquals(5,encoder.decode(".+."));
	}
}
