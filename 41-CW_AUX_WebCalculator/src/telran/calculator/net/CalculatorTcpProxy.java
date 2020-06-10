package telran.calculator.net;

import telran.calculator.ICalculator;
import java.net.*;
import java.io.*;
public class CalculatorTcpProxy implements ICalculator {
Socket socket;
ObjectOutputStream writer;
ObjectInputStream reader;

	@Override
	public Integer sum(int a, int b) {
		String type = "+";
		return sendCalculatorRequest(a, b, type);
		
	}

	private int sendCalculatorRequest(int a, int b, String type) {
		try {
			writer.writeObject(new RequestCalculator(type, new Integer[] {a, b}));
			ResponseCalculator response = (ResponseCalculator) reader.readObject();
			if(response.code.equals("success")) {
				return Integer.parseInt(response.res);
			} else {
				throw new RuntimeException(response.res);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public Integer subtract(int a, int b) {
		String type = "-";
		return sendCalculatorRequest(a, b, type);
	}

	@Override
	public Integer divide(int a, int b) {
		String type ="/";
		return sendCalculatorRequest(a, b, type);
	}

	@Override
	public Integer multiply(int a, int b) {
		String type = "*";
		return sendCalculatorRequest(a, b, type);
	}

	public CalculatorTcpProxy(String host, int port) {
		try {
			socket = new Socket(host, port);
			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream());
			
			
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
