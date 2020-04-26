package telran.util;
import static telran.util.MemoryService.*;

import java.util.ArrayList;
public class MemoryTestAppl {
	static final int Mg = 1024*1024;
	static Runtime runtime = Runtime.getRuntime();
	public static void main(String[] args) {
//		long freeMe = runtime.freeMemory();
//		long totalMem = runtime.totalMemory();
//		long maxMem = runtime.maxMemory();
//		
//		int handmadeSearch = getAvailableMemoryBlockSize();
//
//		System.out.printf("Free memory: %d \nFree total memory: %d\nMaximal memory: %d\nMax array size: %d\n",freeMe/Mg, totalMem/Mg, maxMem/Mg, handmadeSearch/Mg);
//		
		ArrayList<byte[]> arrays = new ArrayList<>();
		
		System.out.println("Maximal Memory: " + runtime.maxMemory()/Mg);
		printMemoryState();
		System.out.println("Before allocations\n-----------------");
		int numberArrays = 0;
		while (true) {
			long freeMemory = runtime.freeMemory();
			int size = freeMemory>= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)freeMemory;
			try {
				byte arr[] = new byte[size];
				arrays.add(arr);
				printMemoryState();
				arrays=null;
				arrays=new ArrayList<>();
				numberArrays++;
			} catch (Throwable e) {
				break;
			}
		}
		System.out.printf("number of arrays: %d", arrays.size());
	}
	private static void printMemoryState() {
		System.out.printf("Free memory %d Mb; Total memoty %d Mb;\n", runtime.freeMemory()/Mg, runtime.totalMemory()/Mg);
	}
		
}





