package telran.numbers;

public class TempTests {
	public static void main(String[] args) {
		ArrayListNumbersBox bx= new ArrayListNumbersBox();
		bx.addNumber(22);
		bx.addNumber(22);
		bx.addNumber(42);
		
		for(int a : bx) {
			System.out.println(a);
		}
	}
}
