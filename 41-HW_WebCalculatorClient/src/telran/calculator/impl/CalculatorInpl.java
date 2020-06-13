package telran.calculator.impl;

import telran.calculator.ICalculator;

public class CalculatorInpl implements ICalculator {
	// Execution on the side of Client
	
	@Override
	public int sum(int a, int b) {
		return a+b;
	}

	@Override
	public int substract(int a, int b) {
		return a-b;
	}

	@Override
	public int multiply(int a, int b) {
		return a*b;
	}

	@Override
	public int divide(int a, int b) {
		return a/b;
	}

}
