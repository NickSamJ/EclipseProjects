package telran.threads.realisation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import telran.menu.InputOutput;

public class RaceThreadTableStats extends Thread{
	int distance;
	InputOutput io;
	public static ArrayList<Integer[]> order = new ArrayList<Integer[]>();
	private static int waitingTime;
	private static final Object MUTEX = new Object();
	
	public RaceThreadTableStats(String name, int distance, InputOutput io) {
		super(name);
		this.io = io;
		this.distance = distance;
	}
	
	@Override 
	public void run() {
		Instant start = Instant.now();
		for(int i = 0; i<distance; i++) {
			try {
				int awaitTime = (int) getRandInt(2, 5);
				waitingTime += awaitTime;
				sleep(awaitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			io.displayLine(getName());
		}
		int duration = (int)ChronoUnit.MILLIS.between(start, Instant.now());
		
		Integer[] toAdd = new Integer[3];
		toAdd[0] = Integer.parseInt(getName());
		toAdd[1] = duration;
		toAdd[2] = waitingTime;
		synchronized (order) {	
			order.add(toAdd);
		}
		
	}

	private long getRandInt(long min, long max) {
		return (long) (Math.random() * (max - min + 1) + min);
	}

	public static void resetStats() {
		order.clear();
		waitingTime = 0;
	}
}
