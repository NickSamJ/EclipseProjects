package telran.numbers;

public class Calculator {
	
	static public int sum(int a, int b) {
		int result = a + b;
		return result;
	}

	static public double divide(double a, double b) {
		double result=0;
		if(b!=0) {
			result=a / b;
		}
		
		return result;
	}

	static public int multiply(int a, int b) {
		int result = a * b;
		return result;
	}
	
	static public int minus(int a, int b) {
		int result = a - b;
		return result;
	}

	public static void main(String[] args) {
		System.out.println("**************\n");
		System.out.println(divide(10, 0));
		System.out.println("****************\n");
	}

}
