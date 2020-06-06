package telran.menu;

public class Menu {
	Item[] items ;
	InputOutput io;
	
	public Menu(Item[] items, InputOutput inputOutput) {
		this.items = items;
		this.io = inputOutput;
	}
	
	public void menuRun() {
		Item item = null;
		do {
			for (int i = 1; i <= items.length; i++) {
				io.displayLine(i + ". " + items[i - 1].displayName());
			}
			int nItem = io.inputInteger("Select item number", 1, items.length);
			item = items[nItem - 1];
			try {
				item.perform();
			} catch (Exception e) {
				io.displayLine(e.getMessage());
			}
		} while (!item.isFinished());
	}

}
