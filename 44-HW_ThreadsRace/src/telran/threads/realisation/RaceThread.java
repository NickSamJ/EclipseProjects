package telran.threads.realisation;

import java.util.Random;

import telran.menu.InputOutput;

public class RaceThread extends AbstractRaceThread {
	int distance;
	InputOutput io;
	public RaceThread(String name, int distance, InputOutput io) {
		super(name);
		this.io = io;
		this.distance = distance;
	}
	
	@Override 
	public void run() {		
		for(int i = 0; i<distance; i++) {
			io.displayLine(getName());
			try {
				sleep(getRandInt(2, 5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(winner==0) {
			winner = Integer.parseInt(getName());
		}
		
	}

	private long getRandInt(long min, long max) {
		return (long) (Math.random() * (max - min + 1) + min);
	}
}
