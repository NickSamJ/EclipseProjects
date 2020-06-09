import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import telran.calculator.*;
import telran.calculator.net.*;

public class CalculatorImpl {
private static final String HOST = "localhost";
private static final Integer PORT = 5000;
	static ICalculator calc= new CalculatorTcpProxy(HOST, PORT);
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String[] ops = { "-", "+", "/", "*", "exit"};
		HashSet<String> listAlloved = new HashSet<>(Arrays.asList(ops));
		
		while (true) {
			System.out.println("Enter operation");
			String operation = scanner.nextLine();
			
			if (operation.toLowerCase().equals("exit")) {
				System.out.println("Good bye!");
				return;
			};
			if(!listAlloved.contains(operation)) {
				System.out.println("You`ve entered wrong symbol");
				continue;
			}

			System.out.println("Enter first number");
			int a = Integer.parseInt(scanner.nextLine());

			System.out.println("Enter second item");
			int b = Integer.parseInt(scanner.nextLine());

			showResult(operation, a, b);

		}
	}

	private static void showResult(String operation, int a, int b) {
		int res = 0;

		switch (operation) {
		case "-": {
			res = calc.subtract(a, b);
			break;
		}
		case "+": {
			res = calc.sum(a, b);
			break;
		}
		case "*": {
			res = calc.multiply(a, b);
			break;
		}
		case "/": {
			res = calc.divide(a, b);
			break;
		}
		}
		System.out.println(res);

	}
}
