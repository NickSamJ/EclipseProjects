package telran.threads.items;

import telran.menu.InputOutput;
import telran.threads.realisation.AbstractRaceThread;
import telran.threads.realisation.RaceThread;

public class ThreadRaceItem extends ThreadItem {

	public ThreadRaceItem(InputOutput io) {
		super(io);
	}

	@Override
	public String displayName() {
		
		return "Start threads race";
	}

	@Override
	public void perform() {
		int nThreads = io.inputInteger("Enter number of threads");
		int distance = io.inputInteger("Enter distance");
		int winner;
		
		RaceThread[] threads = new RaceThread[nThreads];
		for(int i = 0; i<nThreads; i++) {
			threads[i] = new RaceThread(""+(i+1), distance, io);
			threads[i].start();	
		}
		for(RaceThread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		winner = AbstractRaceThread.getWinner();
		io.displayLine(String.format("Thread #%d Is winner", winner));
	}
}
