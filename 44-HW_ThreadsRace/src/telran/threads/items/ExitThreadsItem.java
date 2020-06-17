package telran.threads.items;

import telran.menu.InputOutput;
import telran.menu.Item;

public class ExitThreadsItem implements Item {

	private InputOutput io;
	
	public ExitThreadsItem(InputOutput io) {
		super();
		this.io = io;
	}

	@Override
	public String displayName() {
		return "Exit";
	}

	@Override
	public void perform() {
		io.displayLine("Good Bye");
	}
	
	@Override
	public boolean isFinished() {
		return true;
	}
}
