package telran.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import telran.tests.performance.CopyFileInterface;

public class CopyFileFilesImpl implements CopyFileInterface {

	@Override
	public void copy(Path from, Path to) {
		
		
		try {
			// Java 7 implementation
			Files.copy(from, to);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
