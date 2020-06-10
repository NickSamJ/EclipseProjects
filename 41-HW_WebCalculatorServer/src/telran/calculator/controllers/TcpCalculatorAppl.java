package telran.calculator.controllers;

import java.io.*;
import java.net.*;

import telran.calculator.Calculator;
import telran.calculator.net.RequestCalculator;
import telran.calculator.net.ResponseCalculator;

public class TcpCalculatorAppl {
	private static final int PORT = 4000;
	private static ServerSocket serverSocket;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		serverSocket = new ServerSocket(PORT);
		System.out.println("Socket was launched on the port " + PORT);
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
		Calculator calc = new Calculator();
		String type = request.type;
		Integer a = request.numbers[0];
		Integer b = request.numbers[1];
		String code = "success";
		ResponseCalculator res;

		String answer = "";

		switch (type) {
		case "-": {
			answer = Integer.toString(calc.subtract(a, b));
			break;
		}
		case "+": {
			answer = Integer.toString(calc.sum(a, b));
			break;
		}
		case "*": {
			answer = Integer.toString(calc.multiply(a, b));
			break;
		}
		case "/": {
			if (b.equals(0)) {
				answer = "Can't divide by zero";
				code = "error";
			} else {
				answer = Integer.toString(calc.divide(a, b));
			}
			break;
		}
		}

		res = new ResponseCalculator(code, answer);
		return res;
	}
}
