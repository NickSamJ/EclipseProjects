package telran.calculator.items;

import java.util.function.BinaryOperator;

import telran.calculator.ICalculator;
import telran.menu.InputOutput;
import telran.menu.Item;

public abstract class AbstractCalculatorItem implements Item {
	protected ICalculator calc;
	protected InputOutput io;
	
	public AbstractCalculatorItem(ICalculator calc, InputOutput io) {
		super();
		this.calc = calc;
		this.io = io;
	}
	
	protected void performCalculator(BinaryOperator<Integer> operator) {
		int firstNumber = io.inputInteger("Enter first number");
		int secondNumber = io.inputInteger("Enter second number");
		io.displayLine(operator.apply(firstNumber, secondNumber));
		
	}

}
