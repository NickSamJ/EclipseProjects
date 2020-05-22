import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileSystemTestAppl {
	static int NUMBER_SPACES = 4;
	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystems.getDefault();
//		fs.getRootDirectories().forEach(System.out::println);
//		Path current = fs.getPath("FileSystemTestAppl").normalize(); 
//		System.out.printf("Is absolute: %s;\n Filename: %s;\n Asolute normalized path: %s",
//					current.isAbsolute(),
//					current.getFileName(),
//					current.toAbsolutePath()
//					);
//		
//		File test =  new File("test.txt");
//		
//		if (test.createNewFile()) {
//			System.out.println("\nFile was created");
//		}
//		
//		if(test.delete()) {
//			System.out.println("File was removed");
//		}

//		displayDirectoryContent(fs.getPath("/Users/user/eclipse-workspace/"), 0);
		
		// Start is here
//		displayDirectoryContent(fs.getPath("/System"), 1);
		
		Path filePathSource = fs.getPath("src/FileSystemTestAppl.java");
//		Path filePathDest = fs.getPath("CopyFileSystemTestAppl");
		InputStream input = Files.newInputStream(filePathSource);
		OutputStream output = new FileOutputStream("CopyFileSystemTestAppl");
		
		byte[] buffer = new byte[input.available()];
		input.read(buffer);
		
		//take care of it
//		output.write(b, off, len);
		output.write(buffer);
		output.close();
		
	}

	private static void displayDirectoryContent(Path path, int depth) throws IOException {
		// Print directory structure of some depth
		// If depth is 0 - it prints all directory structure from the given start
		
		
		Files.list(path).forEach(e -> {
			System.out.println(e.getFileName());
			if (Files.isDirectory(e)) {
				try {
					displayRecursive(e, 0, 0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
	}

	private static void displayRecursive(Path path, int level, int depth) throws IOException {
		int lvl = level + 1;
		if(level == depth) {
			return;
		}
		Files.list(path).forEach(e -> {
			System.out.println(" ".repeat(NUMBER_SPACES));
			System.out.println(e.getFileName());
			try {
				displayRecursive(e, lvl, depth);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
	}

}


