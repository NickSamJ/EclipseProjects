package telran.multifuncmenu;

import telran.menu.InputOutput;
import telran.menu.Item;

public class IPv4 implements Item {
	InputOutput io;
	
	IPv4(InputOutput io) {
		this.io = io;
	}

	@Override
	public String displayName() {
		return "IPv4 check";
	}

	@Override
	public void perform() {
		String res = io.inputIpV4("Enter IP");
		io.displayLine(res + " - this is your IPv4");
	}
}
