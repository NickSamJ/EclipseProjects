import java.io.IOException;
import java.net.*;
import java.io.*;
public class ReverseLengthTcpAppl {

	private static final int PORT = 4000;

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is listening " + PORT);
		while(true) {
			Socket socket = serverSocket.accept();
			runClient(socket);
		}
	}

	private static void runClient(Socket socket) {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream writer = new PrintStream(socket.getOutputStream())) {
			while(true) {
				String request = reader.readLine();
				if(request == null)break;
				String response = getResponce(request);
				writer.println(response);
			}
		} catch (Exception e) {
			// client close connection by any illegal way
			System.out.println("Illegal way of closing connection");
			return;
		}
		System.out.println("Client closed connection");
		return;
	}

	private static String getResponce(String request) {
		String headersPayload[] = request.split("#");
		if(headersPayload.length!=2) return "Unknown request";
		String headers = headersPayload[0];
		String payload = headersPayload[1];
		switch(headers) {
		case "reverse" : return getReverse(payload);
		case "Length" : return getLength(payload);
		default : return "unknown request";
		}
	}

	private static String getLength(String payload) {
		
		return Integer.toString(payload.length());
	}

	private static String getReverse(String payload) {
		StringBuilder builder = new StringBuilder(payload);
		return builder.reverse().toString();
	}

}
