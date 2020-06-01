import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ReverseLengthConsoleAppl {

	private static final String HOST = "localhost";
	private static final int PORT = 4000;

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket(HOST, PORT);
		Scanner scanner = new Scanner(System.in);
		try(PrintStream writer = new PrintStream(socket.getOutputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			while(true) {
				System.out.println("Enter <type># <String>: ");
				String request = scanner.nextLine();
				if(request.equalsIgnoreCase("exit"))break;
				
				writer.println(request);
				String response = reader.readLine();
				if(response == null)break;
				
				System.out.println(response.toString());
				
			}
			
		}
	}

}
