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
		Thread[] threads = new Thread[nThreads];
		for(int i = 0; i < nThreads; i++) threads[i] = new Printer(Integer.toString(i+1), nSymbols, portion, io);
		
		for(Thread t : threads) {
			t.start();
		}
		
		while(Printer.threadsFinished != nThreads) {
			for(Thread d : threads) {
				d.interrupt();
				Thread.currentThread().sleep(500);
			}
		}
		for(Thread b : threads) {
			b.join();
		}
	}
	
}
