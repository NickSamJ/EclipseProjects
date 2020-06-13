package telran.calculator.controllers;

import java.io.*;

import telran.calculator.*;
import telran.net.*;
import telran.net.server.ServerJava;

public class TcpCalculatorAppl {
	private static final int PORT = 4040;
	private static ICalculator calc = new Calculator();

	public static void main(String[] args) {
		
		ServerJava server = new ServerJava(TcpCalculatorAppl::getResponse, PORT);
		server.run();
	}


	private static ResponseJava getResponse(RequestJava request) {
		TcpResponseCode code = TcpResponseCode.OK;
		Serializable responseData = null;
		
		try {
			Integer[] operands = (Integer[]) request.requestData;
			int op1 = operands[0];
			int op2 = operands[1];
			Integer res = null;
			
			switch (request.requestType) {
				case "+": {res = calc.sum(op1, op2);break;}
				case "-": {res = calc.subtract(op1, op2);break;}
				case "multiply": {res = calc.multiply(op1, op2);break;}
				case "/": {res = calc.divide(op1, op2); break;}
				default: throw new RuntimeException("Unknown operation");
			}
			if(responseData == null) {
				responseData = res;
			}
			
		} catch (Exception e) {
			code = TcpResponseCode.WRONG_REQUEST;
			responseData = e.getMessage();
		}

		return new ResponseJava(code, responseData);
	}
}
