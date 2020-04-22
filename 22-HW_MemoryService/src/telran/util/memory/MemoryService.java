package telran.util.memory;

public class MemoryService {
	public static int getAvailableMemoryLogSize() {
		int size = Integer.MAX_VALUE;
		
		while(true) {
			try {
				
				byte[] ar = new byte[size];
				return size;
			} catch (OutOfMemoryError e) {
				size=size-1;
			}
		}
	}
	
	public static int binaryGetAvailableMemoryLogSize() {
		int left = 0;
		int size = Integer.MAX_VALUE;
		int center = 0;
		
		while(left<size) {
			try {				
				center = (left + size)/2;
				if (left == center) return left;
				byte[] ar = new byte[center];
				ar = null;
				left = center;
			} catch (OutOfMemoryError e) {
				size = center;
			}
		}
		return left;
	}
}
