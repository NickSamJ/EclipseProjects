import java.util.Arrays;

public class RecursionIntroductionTestAppl {
	public static void main(String[] args) {
//		System.out.println(factorial(5));
//		System.out.println(powWithMultiply(5, 2));
//		int[] arr = { 1, 2, 3, 4, 5, 6 };
//		System.out.println(sum(arr));

		System.out.println("Finding square of 5 -> " + square(5));
		System.out.println("POW function without multiply 5^4 -> " + pow(5, 4));

	}

	/*
	 * 
	 * Homework
	 * 
	 */
	private static long pow(int i, int j) {
		return pow(i, j, i);
	}

	private static long pow(int i, int j, long sum) {
		if (j == 1) return sum;
		return pow(i, j - 1, multiply(i, sum));
	}
	
	private static long multiply(int x, long i) {
		if (i == 0) return 0;
		return x + multiply(x, i - 1);
	}
	
	private static long square(int x) {
		if (x == 0) return 0;
		return square(x - 1) + x + x - 1;
	}
	
	/*END OF HOMEWORK*/
	
	private static long powWithMultiply(int i, int j) {
		if (j == 0) {
			return 1;
		}
		return i * powWithMultiply(i, --j);
	}

	private static long factorial(int a) {

		if (a == 0) {
			return 1;
		}
		return a * factorial(a - 1);
	}

	static int sum(int[] ar) {
		return sum(0, ar);
	}

	private static int sum(int i, int[] ar) {
		if (i == ar.length) {
			return 0;
		}
		return ar[i] + sum(++i, ar);
	}
}
