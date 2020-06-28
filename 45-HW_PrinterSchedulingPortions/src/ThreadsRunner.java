import telran.menu.InputOutput;

public class ThreadsRunner {
	int nThreads;
	int nSymbols;
	int portion;
	InputOutput io;
	
	public ThreadsRunner(int nThreads, int nSymbols, int portion, InputOutput io) {
		this.nThreads = nThreads;
		this.nSymbols = nSymbols;
		this.portion = portion;
		this.io = io;
	}


	public void runThreads() throws InterruptedException {
		Printer[] threads = new Printer[nThreads];
		generateThreads(threads);
		for(Thread t : threads)t.start();
		
		// Starting the chain
		threads[0].interrupt();
		
		for(Thread b : threads) b.join();
		
	}


	private void generateThreads(Printer[] threads) {
		threads[0] = new Printer(Integer.toString(1), nSymbols, portion, io, null);
		for(int i = 1; i < nThreads; i++) {
			threads[i] = new Printer(Integer.toString(i+1), nSymbols, portion, io, null);
			threads[i-1].setNextThread(threads[i]);
		} 
		threads[nThreads-1].setNextThread(threads[0]);
	}	
}
