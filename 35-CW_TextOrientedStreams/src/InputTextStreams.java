import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
public class InputTextStreams {
public static void main(String[] args) throws IOException {
//	try(BufferedReader reader = Files.newBufferedReader
//			(Paths.get("text.txt"), Charset.forName("utf-8"));){
//		while(true) {
//			String line = reader.readLine();
//			if (line == null) break;
//			System.out.println(line);
//		}777777
//	}
	
	// entering password
//	System.out.println("Enter password");
//	String password = getPassword();
//	System.out.println("Entered password is: " + password);
	Scanner scanner = new Scanner(System.in);
	
	while(true) {
		System.out.println("enter two numbers separated by space ot Exit");
		String line = scanner.nextLine(); 
		if (line.equalsIgnoreCase("exit")) {
			break;
		}
		String[] strNumbers = line.split(" ");
		String n1 = strNumbers[0];
		String n2 = strNumbers[1];
		
		System.out.printf("Sum of numbers %s and %s is %d\n", n1, n2, Integer.parseInt(n1) + Integer.parseInt(n2));
	}
}

private static String getPassword() {
	Scanner scanner = new Scanner(System.in);
	Console console = System.console();
//	dedew
	return console == null ? scanner.nextLine() : new String(console.readPassword());
}
}
