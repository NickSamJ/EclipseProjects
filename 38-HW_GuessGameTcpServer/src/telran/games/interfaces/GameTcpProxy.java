package telran.games.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameTcpProxy {
	private static final String HOST = "localhost";
	private static final int PORT = 3008;
	private static int steps = 0;

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket(HOST, PORT);
		Scanner scanner = new Scanner(System.in);
		try(PrintStream writer = new PrintStream(socket.getOutputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			System.out.println("Enter guess number (4 unrepeated digits) from 1 to 9");
			while(true) {
				String request = scanner.nextLine();
				if(request.equalsIgnoreCase("exit"))break;
				
				writer.println(request);
				String response = reader.readLine();
				if(response == null)break;
				
				if(response.matches("win#\\d*{4}")) {
					String res = response.split("#")[1];
					System.out.println("you won in "+ steps +" steps, correct answer is " + res
							+ "\nEnter any value to escape");
					while(true) {
						if (scanner.nextLine() != null) return;
					}
				}
				steps++;
				System.out.println(response.toString());
				
			}
			
		}
	}
}
