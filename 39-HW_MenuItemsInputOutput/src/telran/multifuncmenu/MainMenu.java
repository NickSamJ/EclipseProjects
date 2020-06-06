package telran.multifuncmenu;

import telran.menu.*;

public class MainMenu {
	static InputOutput io = new ConsoleInputOutput();
			
	public static void main(String[] args) {
		Item[] items = {
				new PhoneNumber(io),
				new IPv4(io),				
				new Email(io),
				new DateItem(io),
				new ExitItem()
		};
		
		Menu menu = new Menu(items, io);
		menu.menuRun();
	}

}
