
public class Printer extends Thread {
char symbol;
int nSymbols;
public Printer(char symbol, int nSymbols) {
	this.symbol = symbol;
	this.nSymbols = nSymbols;
}
@Override
public void run() {
	for(int i=0; i<nSymbols; i++) {
		System.out.println(symbol);
		try {
			sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
}
