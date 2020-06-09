package telran.calculator.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import telran.calculator.net.RequestCalculator;
import telran.calculator.net.ResponseCalculator;

public class ServerCalculator {
	private static final int PORT = 5000;
	private static ServerSocket serverSocket;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		serverSocket = new ServerSocket(PORT);
		System.out.println("Socket was launced on the port " + PORT);
		while (true) {
			Socket socket = serverSocket.accept();
			runServer(socket);
		}

	}

	public static void runServer(Socket socket) throws IOException, ClassNotFoundException {

		try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {

			while (true) {
				RequestCalculator request = (RequestCalculator) in.readObject();

				ResponseCalculator res = makeCalculations(request);

				out.writeObject(res);
			}
		} catch (Exception e) {
			System.out.println("Client closed connection");
		}

	}

	private static ResponseCalculator makeCalculations(RequestCalculator request) {
		String type = request.type;
		Integer a = request.numbers[0];
		Integer b = request.numbers[1];
		String code = "success";
		ResponseCalculator res;

		String answer = "";

		switch (type) {
		case "-": {
			answer = Integer.toString(a - b);
			break;
		}
		case "+": {
			answer = Integer.toString(a + b);
			break;
		}
		case "*": {
			answer = Integer.toString(a * b);
			break;
		}
		case "/": {
			if (b.equals(0)) {
				answer = "Can't divide by zero";
				code = "error";
			} else {
				answer = Integer.toString(a / b);
			}
			break;
		}
		}

		res = new ResponseCalculator(code, answer);
		return res;
	}
}
