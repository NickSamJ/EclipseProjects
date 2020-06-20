import telran.menu.ConsoleInputOutput;
import telran.menu.ExitItem;
import telran.menu.InputOutput;
import telran.menu.Item;
import telran.menu.Menu;

public class PrintersController {
public static void main(String[] args) {
	InputOutput io = new ConsoleInputOutput();
	Item[] items = {
			new PrintersItem(io),
			new ExitItem()
	};
	Menu menu = new Menu(items, io);
	menu.menuRun();
}
}
