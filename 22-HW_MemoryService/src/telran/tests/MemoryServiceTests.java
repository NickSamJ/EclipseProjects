package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.memory.MemoryService;

class MemoryServiceTests {
	byte[] array = null;
	@Test
	void testMemoryService() {
		int size = MemoryService.getAvailableMemoryLogSize();
		array = new byte[size];
		array = null;
		try {
			array = new byte[size+1];
			fail("Expected out of memory error");
			
		} catch (OutOfMemoryError e) {
			
		}
		
	}

}
