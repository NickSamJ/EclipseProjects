import telran.menu.InputOutput;
import telran.menu.Item;

public class CalculatorItem implements Item {
	InputOutput inputOutput;

	public CalculatorItem(InputOutput inputOutput) {
		super();
		this.inputOutput = inputOutput;
	}

	@Override
	public String displayName() {
		return "Divide two numbers ";
	}

	@Override
	public void perform() {
		Integer number1 = inputOutput.inputInteger("Enter number", 1, 100);
		Integer number2 = inputOutput.inputInteger("Enter number", 1, 100);

		inputOutput.displayLine((double)number1 / (double)number2);
	}

}
