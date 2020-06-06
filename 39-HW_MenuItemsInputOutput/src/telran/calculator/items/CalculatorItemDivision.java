package telran.calculator.items;
import telran.menu.ConsoleInputOutput;
import telran.menu.InputOutput;
import telran.menu.Item;

public class CalculatorItemDivision implements Item {
	InputOutput io = new ConsoleInputOutput();

	public CalculatorItemDivision(InputOutput io) {
		super();
		this.io = io;
	}

	@Override
	public String displayName() {
		return "Divide two numbers";
	}

	@Override
	public void perform() {
		double number1 = io.inputInteger("Enter number", 1, 100);
		double number2 = io.inputInteger("Enter number", 0, 100);
		
		double res = number1 /number2;

		io.displayLine(Math.floor(res * 100) / 100);
	}

}
