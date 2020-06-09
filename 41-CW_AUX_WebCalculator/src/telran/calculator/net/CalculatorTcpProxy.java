package telran.calculator.net;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Stream;

import telran.calculator.ICalculator;

public class CalculatorTcpProxy implements ICalculator {
	Socket socket;
	ObjectOutputStream writer;
	ObjectInputStream reader;
	
	

	private Integer sendCalculatorRequest(String type, Integer[] params) {
		try {
			writer.writeObject(new RequestCalculator(type, params));
			ResponseCalculator response = (ResponseCalculator)reader.readObject();
			if(response.code.equals("success") ) {
				return Integer.parseInt(response.res);
			}else {
				throw new RuntimeException(response.res);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} 
	}
	
	@Override
	public Integer sum(int a, int b) {
		String type = "+";
		Integer[] params = new Integer[]{a,b};
		return sendCalculatorRequest(type, params);
		
	}
	@Override
	public Integer subtract(int a, int b) {
		String type = "-";
		Integer[] params = new Integer[]{a,b};
		return sendCalculatorRequest(type, params);
	}

	@Override
	public Integer multiply(int a, int b) {
		String type = "*";
		Integer[] params = new Integer[]{a,b};
		return sendCalculatorRequest(type, params);
	}

	@Override
	public Integer divide(int a, int b) {
		String type = "/";
		Integer[] params = new Integer[]{a,b};
		return sendCalculatorRequest(type, params);
	}
	
	public CalculatorTcpProxy(String host, Integer port)  {
		try {
			socket = new Socket(host, port);
			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
