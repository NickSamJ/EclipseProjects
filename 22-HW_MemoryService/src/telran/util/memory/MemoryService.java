package telran.util.memory;

public class MemoryService {
	public static int getAvailableMemoryLogSize() {
		int size = Integer.MAX_VALUE;
		
		while(true) {
			try {
				
				byte[] ar = new byte[size];
			} catch (OutOfMemoryError e) {
				size=size/2;
			}
		}
		
//		return size;
	}
}
