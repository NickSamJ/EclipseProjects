package telran.calculator;

public class Calculator implements ICalculator {

	@Override
	public Integer sum(int a, int b) {
		return a+b;
	}

	@Override
	public Integer subtract(int a, int b) {
		return a-b;
	}

	@Override
	public Integer multiply(int a, int b) {
		return a*b;
	}

	@Override
	public Integer divide(int a, int b) {
		return a/b;
	}

}
