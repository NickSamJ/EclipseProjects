package telran.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import telran.tests.performance.CopyFileInterface;

public class CopyFileStreamsImpl implements CopyFileInterface {
	private int bufferSize = 100;

	public CopyFileStreamsImpl() {}
	public CopyFileStreamsImpl(int buffersize) {
		this.bufferSize = buffersize;
	}
	@Override
	public void copy(Path from, Path to) {
		
		try {
			InputStream input = Files.newInputStream(from);
			OutputStream output = new FileOutputStream(to.toFile());

			byte[] buffer = new byte[bufferSize];
						
			int length;
			
			while((length = input.read(buffer) ) > 0) {
				output.write(buffer, 0, length);
			}
			
			output.close();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
