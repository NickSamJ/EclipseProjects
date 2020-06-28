import telran.menu.InputOutput;

public class Printer extends Thread {
	private String name;
	private int nSymbols;
	private int portion;
	InputOutput io;
	private int printed = 0;
	private Thread nextThread;
	
	int diff = nSymbols;
	
	Printer(String name, int nSymbols, int portion, InputOutput io, Thread nextThread){
		super(name);
		this.name = name;
		this.nSymbols = nSymbols;
		this.portion = portion;
		this.io = io;
		this.nextThread = nextThread;
	}
	
	public void run() {
		
		while(true) {
			try {
				sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				printPortion();
				nextThread.interrupt();
				if(nSymbols == printed) {
					return;
				}
				
			}
		}
		
	}
	
	public void setNextThread(Thread nextThread) {
		this.nextThread = nextThread;
	}
	
	private void printPortion() {
		int nSymbolsToPrint = getMaxSymbolsToPrint();
		io.displayLine("" + name.repeat(nSymbolsToPrint));
		printed =  printed + nSymbolsToPrint;
		
	}

	private int getMaxSymbolsToPrint() {
		int diff = nSymbols - printed;
		int res = diff > portion ? portion :  diff;
		return res;
	}
}
