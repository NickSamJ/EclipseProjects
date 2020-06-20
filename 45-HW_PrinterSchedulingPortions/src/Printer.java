import telran.menu.InputOutput;

public class Printer extends Thread {
	private String name;
	private int nSymbols;
	private int portion;
	InputOutput io;
	public static int threadsFinished = 0;
	
	int diff = nSymbols;
	
	Printer(String name, int nSymbols, int portion, InputOutput io){
		super(name);
		this.name = name;
		this.nSymbols = nSymbols;
		this.portion = portion;
		this.io = io;
	}

	@Override
	public void run() {		
		threadsFinished = 0;
		int printed = 0;
		int diff = printed;
		while(printed < nSymbols) {
			try {
				sleep(Integer.MAX_VALUE);
				
			} catch (InterruptedException e) {
				diff = nSymbols-printed;
				if(diff <= portion) {
					System.out.println(name.repeat(diff));
					threadsFinished ++;
					return;
				}else {
					System.out.println(name.repeat(portion));
					printed = printed + portion;
				}
			}
			
		}
		
		
	}
}
