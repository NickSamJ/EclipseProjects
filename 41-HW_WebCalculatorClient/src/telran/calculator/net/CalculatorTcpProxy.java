package telran.calculator.net;

import telran.calculator.ICalculator;
import telran.net.TcpClientJava;

public class CalculatorTcpProxy extends TcpClientJava implements ICalculator {

	public CalculatorTcpProxy(String hostname, int port) {
		super(hostname, port);
	}

	@Override
	public int sum(int a, int b) {
		
		return sendRequest("+", new Integer[] {a,b});
	}

	@Override
	public int substract(int a, int b) {
		return sendRequest("-", new Integer[] {a,b});
	}

	@Override
	public int multiply(int a, int b) {
		return sendRequest("*", new Integer[] {a,b});
	}

	@Override
	public int divide(int a, int b) {
		return sendRequest("/", new Integer[] {a,b});
	}

}
