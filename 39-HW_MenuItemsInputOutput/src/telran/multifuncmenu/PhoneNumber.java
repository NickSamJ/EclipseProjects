package telran.multifuncmenu;

import telran.menu.InputOutput;
import telran.menu.Item;

public class PhoneNumber implements Item {
	InputOutput io;
	
	PhoneNumber(InputOutput io) {
		this.io = io;
	}

	@Override
	public String displayName() {
		return "Phone check";
	}

	@Override
	public void perform() {
		String res = io.inputPhoneNumber("Enter phone number");
		io.displayLine(res + " - this is your phone");
	}

}
