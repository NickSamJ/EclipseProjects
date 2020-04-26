package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.memory.MemoryService;

class MemoryServiceTests {
	byte[] array = null;

	/*
	 *  Uncomment this test with extra long execution time search
	 */
	
//	@Test
//	void testMemoryService() {
//		int size = MemoryService.getAvailableMemoryLogSize();
//		array = new byte[size];
//		array = null;
//		try {
//			array = new byte[size+1];
//			fail("Expected out of memory exception");
//			
//		} catch (OutOfMemoryError e) {
//			System.out.println(size);
//		}
//		
//	}
	@Test
	void testMemoryServiceBinary() {
		int size = MemoryService.binaryGetAvailableMemoryLogSize();
		System.out.println(size);
		array = new byte[size];
		for (int i = 0; i < 10; i++) {
			array = null;
			try {
				array = new byte[size+1];
				fail("Expected out of memory exception");
				
			} catch (OutOfMemoryError e) {

			}
		}
		
	}

}
