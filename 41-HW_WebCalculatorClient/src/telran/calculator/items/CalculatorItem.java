package telran.calculator.items;

import java.util.function.BinaryOperator;

import telran.calculator.ICalculator;
import telran.menu.InputOutput;

public class CalculatorItem extends AbstractCalculatorItem {

	String displayName;
	BinaryOperator<Integer> operator;
	
	public CalculatorItem(ICalculator calc, InputOutput io, String displayName, BinaryOperator<Integer> operator) {
		super(calc, io);
		this.displayName = displayName;
		this.operator = operator;
	}

	@Override
	public String displayName() {
		return displayName;
	}

	@Override
	public void perform() {
		performCalculator(operator);

	}

}
