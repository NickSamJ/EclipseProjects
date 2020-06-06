package telran.multifuncmenu;

import telran.menu.InputOutput;
import telran.menu.Item;

public class Email implements Item {
	InputOutput io;
	
	Email(InputOutput io) {
		this.io = io;
	}

	@Override
	public String displayName() {
		return "Email check";
	}

	@Override
	public void perform() {
		String res = io.inputEmail("Enter Email");
		io.displayLine(res + " - this is your Email");
	}

}
