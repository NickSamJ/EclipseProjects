package telran.calculator;
import telran.calculator.items.CalculatorItemDivision;
import telran.calculator.items.CalculatorItemSquare;
import telran.menu.ConsoleInputOutput;
import telran.menu.ExitItem;
import telran.menu.InputOutput;
import telran.menu.Item;
import telran.menu.Menu;

public class CalculatorAppl {
	static InputOutput inputOutput =new ConsoleInputOutput();
	public static void main(String[] args) {
		Item[] items = {
				new CalculatorItemDivision(inputOutput),
				new CalculatorItemSquare(inputOutput),
				new ExitItem(),
		};
		Menu menu = new Menu(items, inputOutput);
		menu.menuRun();
	}
}
