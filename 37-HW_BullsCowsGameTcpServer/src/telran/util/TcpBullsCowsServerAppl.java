package telran.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class TcpBullsCowsServerAppl {
	private static final int PORT = 3008;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is running on port " + PORT);
		
		while(true) {
			BullsCowsGameImpl game = new BullsCowsGameImpl();
			Socket socket = serverSocket.accept();
			runGame(socket, game);
		}
		
	}

	private static void runGame(Socket socket, BullsCowsGameImpl game) {
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream writer = new PrintStream(socket.getOutputStream())) {
			
			// create new game
			
			String res = game.res();
			while(!game.isFinished()) {
				String request = reader.readLine();
				if(request == null)break;
				if(request.toLowerCase().equals("res")) {					
					writer.println(game.res());
					continue;
				};
				String response = game.move(request);
				if(game.isFinished()) {
					writer.println("win#"+res);
					break;
				}
				writer.println(response + " " + game.prompt());
			}
//			writer.println("win#"+res);
		} catch (Exception e) {
			// client close connection by any illegal way
			System.out.println("Illegal way of closing connection");
			return;
		}
		System.out.println("Client closed connection");
		return;
	}
}
