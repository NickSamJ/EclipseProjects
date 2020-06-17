package telran.threads.controllers;

import telran.menu.*;
import telran.threads.items.ExitThreadsItem;
import telran.threads.items.ThreadRaceItem;

public class ThreadsRaceApplication {
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = {
				new ThreadRaceItem(io),
				new ExitThreadsItem(io),
		};
		Menu menu = new Menu(items, io);
		menu.menuRun();
	}
}
