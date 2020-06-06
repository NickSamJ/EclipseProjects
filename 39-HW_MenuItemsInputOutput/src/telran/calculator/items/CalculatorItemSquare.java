package telran.calculator.items;

import telran.menu.InputOutput;
import telran.menu.Item;

public class CalculatorItemSquare implements Item {
	InputOutput io;
	
	public CalculatorItemSquare(InputOutput io) {
		this.io = io;
	}

	@Override
	public String displayName() {
		return "Square";
	}

	@Override
	public void perform() {
		Integer res = io.inputInteger("Enter number to square");
		io.displayLine(Math.pow(res, 2));
	}

}
