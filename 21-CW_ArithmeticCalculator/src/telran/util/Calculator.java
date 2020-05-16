package telran.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BinaryOperator;

public class Calculator {
	static HashMap<String, BinaryOperator<Double>> mapOperations;
	static {
		mapOperations = new HashMap<>();
		mapOperations.put("+", (op1, op2) -> op1 + op2);
		mapOperations.put("-", (op1, op2) -> op1 - op2);
		mapOperations.put("*", (op1, op2) -> op1 * op2);
		mapOperations.put("/", (op1, op2) -> op1 == 0 ? Double.POSITIVE_INFINITY : op1/op2);
	}
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
		try {
		double opNumber = Double.parseDouble(op2);
		return mapOperations.getOrDefault(operation, (a, b) -> Double.NaN).apply(op1, opNumber);
		}catch(Exception e) {
			return Double.NaN;
		}
		
//		double doubleOp2 = Double.parseDouble(op2);
//		switch (operation) {
//			case "+": return op1 + doubleOp2; 
//			case "-": return op1 - doubleOp2;
//			case "*": return op1 * doubleOp2;
//			case "/":
//				if(doubleOp2==0) {
//					return Double.POSITIVE_INFINITY;
//				}
//				return op1 / doubleOp2;
//		}
//		return Double.NaN;
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
