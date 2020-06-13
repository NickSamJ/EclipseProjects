package telran.calculator.items;

import java.io.Closeable;

import telran.calculator.ICalculator;
import telran.menu.InputOutput;

public class ExitCalculatorItem extends AbstractCalculatorItem {
	
	public ExitCalculatorItem(ICalculator calc, InputOutput io) {
		super(calc, io);
	}

	@Override
	public String displayName() {
		
		return "Exit";
	}

	@Override
	public void perform() {
		if(calc instanceof Closeable) {
			try {
				((Closeable) calc).close();
			} catch (Exception e) {
				io.displayLine("Wrong closing " + e.getMessage());
			}
		}
	}
	
	@Override
	public boolean isFinished() {
		return true;
	}

}
