package telran.multifuncmenu;

import java.time.LocalDate;
import java.time.Period;

import telran.menu.InputOutput;
import telran.menu.Item;

public class DateItem implements Item {
	InputOutput io;
	
	DateItem(InputOutput io){
		this.io = io;
	}
	
	@Override
	public String displayName() {
		return "How long ago";
	}

	@Override
	public void perform() {
		LocalDate date = io.inputDate("Input date in format 2000-02-26");
		io.displayLine(Math.abs(Period.between(LocalDate.now(), date).getYears()) + "years ago");

	}

}
