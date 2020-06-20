import telran.menu.InputOutput;
import telran.menu.Item;

public class PrintersItem implements Item {
	private InputOutput io;
	public PrintersItem(InputOutput io) {
		this.io = io;
	}

	@Override
	public String displayName() {
		return "Setup and run printers";
	}

	@Override
	public void perform() {
		int nPrinters = io.inputInteger("Enter number of printers", 1, 100);
		int nNumbers = io.inputInteger("Enter number of symbols that will print each printer", 1, 100);
		int portion = io.inputInteger("Enter numbers of symbols to print in 1 iteration", 1, 100);
		
		ThreadsRunner runner = new ThreadsRunner(nPrinters, nNumbers, portion, io);
		try {
			runner.runThreads();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
