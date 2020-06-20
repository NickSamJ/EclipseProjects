
public class Printer extends Thread{
	char symbol;
	String str;
	int currInd = 0;
	
	boolean running = true;
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Printer(char symbol) {
		this.symbol = symbol;
	}
	
	public Printer(String str) {
		this.str = str;
		this.symbol = str.charAt(0);
	}
	
	@Override
	public void run() {
		while(running) {
			System.out.println("Id " + getId()  + symbol);
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				symbol = str.charAt(currInd++);
				if(currInd == str.length()) {
					currInd = 0;
				}
			}
		}
	}
}
