package telran.calculator.test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import telran.calculator.net.RequestCalculator;

public class ClientTest {
	private static final String HOST = "localhost";
	private static final int PORT = 3008;

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket(HOST, PORT);
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			RequestCalculator toSend = new RequestCalculator("success", new Integer[] {1,2});
			out.writeObject(toSend);
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
