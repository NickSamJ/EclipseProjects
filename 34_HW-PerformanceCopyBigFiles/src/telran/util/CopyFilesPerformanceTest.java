package telran.util;

import java.io.*;
import java.nio.file.*;

public class CopyFilesPerformanceTest {
	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		
		Path source = fs.getPath("test.avi");
		
		// Java 7 copy;
//		Files.copy(source, new FileOutputStream("testRes.avi"));
		
		InputStream input = Files.newInputStream(source);
		OutputStream output = new FileOutputStream("testRes.avi");
		try {

			byte[] buffer = new byte[(int) Runtime.getRuntime().freeMemory()];
			
			System.out.println(buffer.length);
			
			int length;
			
			while((length = input.read(buffer) ) > 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			output.close();
			
		}
		
	}

}
