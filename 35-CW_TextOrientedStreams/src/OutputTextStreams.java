import java.io.*;

public class OutputTextStreams {
public static void main(String[] args) throws FileNotFoundException {
	try(PrintStream printStream = new PrintStream("ps_file");		
		PrintWriter printWriter = new PrintWriter("pw_file")){;
		
		printStream.println("Hello World");
		printWriter.println("Hello World");
//		printWriter.flush();
	}
	
	
}
}
