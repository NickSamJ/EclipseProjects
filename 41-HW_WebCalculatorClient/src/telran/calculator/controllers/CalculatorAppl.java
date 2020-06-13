package telran.calculator.controllers;

import telran.calculator.ICalculator;
import telran.calculator.items.CalculatorItem;
import telran.calculator.items.ExitCalculatorItem;
import telran.calculator.net.CalculatorTcpProxy;
import telran.menu.ConsoleInputOutput;
import telran.menu.InputOutput;
import telran.menu.Item;
import telran.menu.Menu;

public class CalculatorAppl {
	private static final String HOST = "localhost";
	private static final int PORT = 4040;
	public static void main(String[] args) {
		ICalculator calc = null;
		try {
			calc = new CalculatorTcpProxy(HOST, PORT);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		InputOutput io = new ConsoleInputOutput();
		Item[] items = {
				new CalculatorItem(calc, io, "Add two numbers", calc::sum),
				new CalculatorItem(calc, io, "Sbustract two numbers", calc::substract),
				new CalculatorItem(calc, io, "Multiply two numbers", calc::multiply),
				new CalculatorItem(calc, io, "Divide two numbers", calc::divide),
				new ExitCalculatorItem(calc, io),
		};
		Menu menu = new Menu(items, io);
		menu.menuRun();
	}
}
