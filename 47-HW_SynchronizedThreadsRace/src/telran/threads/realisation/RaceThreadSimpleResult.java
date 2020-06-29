package telran.threads.realisation;

import telran.menu.InputOutput;

public class RaceThreadSimpleResult extends Thread{
	int distance;
	InputOutput io;
	private static int winner = 0;
	private static Object MUTEX = new Object();
	
	public RaceThreadSimpleResult(String name, int distance, InputOutput io) {
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
		
		synchronized (MUTEX) {	
			if(winner==0) {
				winner = Integer.parseInt(getName());
			}
		}
		
	}

	private long getRandInt(long min, long max) {
		return (long) (Math.random() * (max - min + 1) + min);
	}
	
	public static void resetWinner() {
		winner = 0;
	}
	public static int getWinner() {
		return winner;
	}
}
