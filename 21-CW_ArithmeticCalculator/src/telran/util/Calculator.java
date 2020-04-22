package telran.util;

import java.util.Arrays;

public class Calculator {
	static public double calculate(String expr) {
		String[] operands = getOperands(expr);
		String[] operations = getOperations(expr);
		
		if(operands.length == 0 || operands.length != operations.length) {			
			return  Double.NaN;
		}
		double res  =	Double.parseDouble(operands[0]);
		for (int i = 1; i < operands.length; i++) {
			res = calculateOne(res, operands[i], operations[i]);
		}
		return res;
		
	}
	public static double calculateOne(double op1, String op2, String operation) {
		double doubleOp2 = Double.parseDouble(op2);
		switch (operation) {
			case "+": return op1 + doubleOp2; 
			case "-": return op1 - doubleOp2;
			case "*": return op1 * doubleOp2;
			case "/":
				if(doubleOp2==0) {
					return Double.POSITIVE_INFINITY;
				}
				return op1 / doubleOp2;
		}
		return Double.NaN;
	}

	public static String[] getOperations(String expr) {
		String[] res = expr.trim().split("[\\d ]+");

		return res;
	}

	public static String[] getOperands(String expr) {
		return expr.trim().split("\\D+");
	}
	
	public static boolean expressionIsValid(String expr) {
		String checkPattern = "[ \t]*\\d{1,13}([ \\t]*[-\\+/\\*][ \\t]*\\d{1,13})*[ \\t]*" ; 
		return expr.matches(checkPattern);
	}
}
